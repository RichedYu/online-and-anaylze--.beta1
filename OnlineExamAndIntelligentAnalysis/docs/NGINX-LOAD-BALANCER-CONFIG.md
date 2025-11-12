# Nginx 负载均衡配置说明

# Nginx Load Balancer Configuration Guide

## 📋 配置文件位置 / Configuration File Location

**主配置文件 / Main Configuration:**

- 路径 / Path: [`../src/main/resources/nginx/nginx-load-balancer.conf`](../src/main/resources/nginx/nginx-load-balancer.conf)
- 行数 / Lines: ~550 lines
- 状态 / Status: ✅ 生产就绪 / Production Ready

---

## 🎯 配置概览 / Configuration Overview

### 核心功能 / Core Features

1. **负载均衡 / Load Balancing**

   - 算法: `least_conn` (最少连接)
   - 4 个后端实例 (3 active + 1 backup)
   - 自动健康检查和故障转移

2. **多级缓存 / Multi-level Caching**

   - L1: AI 分析结果缓存 (1GB, 4h)
   - L2: 提示词模板缓存 (500MB, 24h)
   - L3: 热点数据缓存 (2GB, 7d)

3. **请求限流 / Rate Limiting**

   - API 通用限流: 100 req/s
   - AI 接口限流: 10 req/s
   - 登录接口限流: 5 req/min

4. **WebSocket 支持 / WebSocket Support**
   - 流式 AI 响应
   - 长连接保持
   - 禁用缓冲

---

## 🔧 配置详解 / Configuration Details

### 1. Upstream 配置 / Upstream Configuration

```nginx
upstream exam_backend {
    least_conn;  # 最少连接算法 / Least connections algorithm

    # 主服务器 / Primary servers
    server 127.0.0.1:8880 weight=3 max_fails=3 fail_timeout=30s;
    server 127.0.0.1:8881 weight=3 max_fails=3 fail_timeout=30s;
    server 127.0.0.1:8882 weight=2 max_fails=3 fail_timeout=30s;

    # 备用服务器 / Backup server
    server 127.0.0.1:8883 weight=1 backup;

    # 长连接配置 / Keep-alive configuration
    keepalive 64;
    keepalive_timeout 60s;
    keepalive_requests 100;
}
```

**参数说明 / Parameter Description:**

| 参数 / Parameter | 说明 / Description | 推荐值 / Recommended |
| ---------------- | ------------------ | -------------------- |
| `least_conn`     | 负载均衡算法       | 最少连接优先         |
| `weight`         | 服务器权重         | 根据性能调整         |
| `max_fails`      | 最大失败次数       | 3                    |
| `fail_timeout`   | 失败超时时间       | 30s                  |
| `backup`         | 备用服务器标记     | 仅故障时使用         |
| `keepalive`      | 保持连接数         | 64                   |

### 2. 缓存配置 / Cache Configuration

```nginx
# AI分析结果缓存 / AI analysis result cache
proxy_cache_path /var/cache/nginx/ai_analysis
                 levels=1:2
                 keys_zone=ai_analysis_cache:100m
                 max_size=1g
                 inactive=4h
                 use_temp_path=off;

# 提示词模板缓存 / Prompt template cache
proxy_cache_path /var/cache/nginx/ai_prompts
                 levels=1:2
                 keys_zone=ai_prompts_cache:50m
                 max_size=500m
                 inactive=24h
                 use_temp_path=off;

# 热点数据缓存 / Hot data cache
proxy_cache_path /var/cache/nginx/ai_hot
                 levels=1:2
                 keys_zone=ai_hot_cache:200m
                 max_size=2g
                 inactive=7d
                 use_temp_path=off;
```

**缓存策略 / Cache Strategy:**

| 缓存类型 / Cache Type | 内存区域 / Memory Zone | 磁盘空间 / Disk Space | TTL     | 用途 / Purpose |
| --------------------- | ---------------------- | --------------------- | ------- | -------------- |
| `ai_analysis_cache`   | 100MB                  | 1GB                   | 4 小时  | AI 分析结果    |
| `ai_prompts_cache`    | 50MB                   | 500MB                 | 24 小时 | 提示词模板     |
| `ai_hot_cache`        | 200MB                  | 2GB                   | 7 天    | 热点数据       |

### 3. 限流配置 / Rate Limiting Configuration

```nginx
# 定义限流区域 / Define rate limit zones
limit_req_zone $binary_remote_addr zone=api_limit:10m rate=100r/s;
limit_req_zone $binary_remote_addr zone=ai_limit:10m rate=10r/s;
limit_req_zone $binary_remote_addr zone=login_limit:10m rate=5r/m;

# 应用限流 / Apply rate limiting
location /login/ai-enhanced/ {
    limit_req zone=ai_limit burst=5 nodelay;
    limit_conn addr 3;
    # ... 其他配置
}
```

**限流说明 / Rate Limit Description:**

| 区域 / Zone   | 速率 / Rate | 突发 / Burst | 应用场景 / Use Case |
| ------------- | ----------- | ------------ | ------------------- |
| `api_limit`   | 100 req/s   | 20           | 通用 API 接口       |
| `ai_limit`    | 10 req/s    | 5            | AI 分析接口         |
| `login_limit` | 5 req/min   | -            | 登录接口            |

### 4. Location 配置 / Location Configuration

#### a. 前端静态资源 / Frontend Static Resources

```nginx
location / {
    root /usr/share/nginx/html;
    index index.html;
    try_files $uri $uri/ /index.html;  # SPA路由支持

    # 静态资源缓存 / Static resource caching
    expires 30d;
    add_header Cache-Control "public, immutable";
}
```

#### b. AI 增强接口 / AI Enhanced Endpoints

```nginx
location /login/ai-enhanced/ {
    limit_req zone=ai_limit burst=5 nodelay;

    proxy_pass http://exam_backend;
    proxy_http_version 1.1;

    # 启用缓存 / Enable caching
    proxy_cache ai_analysis_cache;
    proxy_cache_key "$request_method$request_uri$request_body";
    proxy_cache_valid 200 4h;
    proxy_cache_use_stale error timeout updating;

    # 缓存状态头 / Cache status header
    add_header X-Cache-Status $upstream_cache_status;
}
```

#### c. WebSocket 支持 / WebSocket Support

```nginx
location /login/stream {
    proxy_pass http://exam_backend;
    proxy_http_version 1.1;

    # WebSocket升级 / WebSocket upgrade
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection "upgrade";

    # 禁用缓冲 / Disable buffering
    proxy_buffering off;
    proxy_cache off;

    # 长超时 / Long timeout
    proxy_read_timeout 300s;
}
```

---

## 🚀 部署步骤 / Deployment Steps

### 1. 复制配置文件 / Copy Configuration

```bash
# 复制到nginx配置目录 / Copy to nginx config directory
sudo cp OnlineExamAndIntelligentAnalysis/src/main/resources/nginx/nginx-load-balancer.conf \
        /etc/nginx/conf.d/exam-system.conf
```

### 2. 创建缓存目录 / Create Cache Directories

```bash
# 创建缓存目录 / Create cache directories
sudo mkdir -p /var/cache/nginx/ai_analysis
sudo mkdir -p /var/cache/nginx/ai_prompts
sudo mkdir -p /var/cache/nginx/ai_hot

# 设置权限 / Set permissions
sudo chown -R nginx:nginx /var/cache/nginx
sudo chmod -R 755 /var/cache/nginx
```

### 3. 测试配置 / Test Configuration

```bash
# 测试nginx配置 / Test nginx configuration
sudo nginx -t

# 应该看到 / Should see:
# nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
# nginx: configuration file /etc/nginx/nginx.conf test is successful
```

### 4. 重载 Nginx / Reload Nginx

```bash
# 重载配置 / Reload configuration
sudo nginx -s reload

# 或重启nginx / Or restart nginx
sudo systemctl restart nginx
```

### 5. 验证部署 / Verify Deployment

```bash
# 检查nginx状态 / Check nginx status
sudo systemctl status nginx

# 测试负载均衡 / Test load balancing
curl http://localhost/health

# 查看缓存目录 / Check cache directories
ls -lh /var/cache/nginx/
```

---

## 📊 监控和维护 / Monitoring & Maintenance

### 监控命令 / Monitoring Commands

```bash
# 1. 查看nginx状态 / Check nginx status
curl http://localhost/nginx_status

# 2. 查看访问日志 / View access logs
tail -f /var/log/nginx/exam_access.log

# 3. 查看错误日志 / View error logs
tail -f /var/log/nginx/exam_error.log

# 4. 查看缓存命中率 / Check cache hit rate
grep "X-Cache-Status: HIT" /var/log/nginx/exam_access.log | wc -l
grep "X-Cache-Status: MISS" /var/log/nginx/exam_access.log | wc -l

# 5. 查看缓存使用情况 / Check cache usage
du -sh /var/cache/nginx/*
```

### 维护任务 / Maintenance Tasks

```bash
# 清理缓存 / Clear cache
sudo rm -rf /var/cache/nginx/ai_*/*

# 重建缓存目录 / Recreate cache directories
sudo mkdir -p /var/cache/nginx/{ai_analysis,ai_prompts,ai_hot}
sudo chown -R nginx:nginx /var/cache/nginx

# 日志轮转 / Log rotation
sudo logrotate -f /etc/logrotate.d/nginx
```

---

## ⚙️ 高级配置 / Advanced Configuration

### 1. HTTPS 配置 / HTTPS Configuration

```nginx
server {
    listen 443 ssl http2;
    server_name exam.example.com;

    # SSL证书 / SSL certificates
    ssl_certificate /etc/nginx/ssl/exam.crt;
    ssl_certificate_key /etc/nginx/ssl/exam.key;

    # SSL优化 / SSL optimization
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;
    ssl_prefer_server_ciphers on;
    ssl_session_cache shared:SSL:10m;
    ssl_session_timeout 10m;

    # ... 其他配置
}

# HTTP跳转HTTPS / HTTP to HTTPS redirect
server {
    listen 80;
    server_name exam.example.com;
    return 301 https://$server_name$request_uri;
}
```

### 2. 会话保持 / Session Persistence

```nginx
upstream exam_backend {
    # 基于IP的会话保持 / IP-based session persistence
    ip_hash;

    # 或基于cookie / Or cookie-based
    # hash $cookie_sessionid;

    server 127.0.0.1:8880;
    server 127.0.0.1:8881;
    server 127.0.0.1:8882;
}
```

### 3. 健康检查增强 / Enhanced Health Checks

```nginx
upstream exam_backend {
    server 127.0.0.1:8880 max_fails=3 fail_timeout=30s;

    # 定期健康检查 / Periodic health checks
    check interval=3000 rise=2 fall=3 timeout=1000;
    check_http_send "HEAD /health HTTP/1.0\r\n\r\n";
    check_http_expect_alive http_2xx http_3xx;
}
```

### 4. 缓存预热 / Cache Warming

```bash
#!/bin/bash
# cache-warm-up.sh

# 预热常用API / Warm up common APIs
curl http://localhost/login/models
curl http://localhost/login/prompt-templates/list

# 预热热门模板 / Warm up popular templates
for code in EXAM1 KNOWLEDGE1 STUDENT1; do
    curl "http://localhost/login/prompt-templates/$code"
done
```

---

## 🔍 故障排查 / Troubleshooting

### 常见问题 / Common Issues

#### 1. 502 Bad Gateway

**原因 / Cause:**

- 后端服务未启动
- 端口配置错误
- 防火墙阻止

**解决方案 / Solution:**

```bash
# 检查后端服务 / Check backend services
for port in 8880 8881 8882 8883; do
    curl http://localhost:$port/health
done

# 检查nginx错误日志 / Check nginx error log
tail -f /var/log/nginx/exam_error.log
```

#### 2. 缓存不生效 / Cache Not Working

**原因 / Cause:**

- 缓存目录权限问题
- 缓存配置错误
- 响应头禁止缓存

**解决方案 / Solution:**

```bash
# 检查缓存目录 / Check cache directories
ls -la /var/cache/nginx/

# 检查缓存配置 / Check cache config
nginx -T | grep proxy_cache

# 查看缓存状态 / View cache status
curl -I http://localhost/login/models | grep X-Cache-Status
```

#### 3. 负载不均衡 / Load Imbalance

**原因 / Cause:**

- 权重配置不合理
- 某些实例性能差
- 会话保持导致

**解决方案 / Solution:**

```bash
# 查看各实例负载 / Check instance load
for port in 8880 8881 8882 8883; do
    echo "Port $port:"
    curl -s http://localhost:$port/actuator/metrics | jq '.http.server.requests'
done

# 调整权重 / Adjust weights
# 编辑nginx配置文件修改weight参数
```

---

## 📈 性能优化 / Performance Optimization

### 1. Worker 进程优化 / Worker Process Optimization

```nginx
# 根据CPU核心数设置 / Set based on CPU cores
worker_processes auto;

# 绑定worker到CPU / Bind worker to CPU
worker_cpu_affinity auto;

# 增加连接数 / Increase connections
events {
    worker_connections 4096;
    use epoll;
    multi_accept on;
}
```

### 2. 缓冲区优化 / Buffer Optimization

```nginx
# 客户端缓冲区 / Client buffers
client_body_buffer_size 128k;
client_max_body_size 20m;
client_header_buffer_size 1k;
large_client_header_buffers 4 4k;

# 代理缓冲区 / Proxy buffers
proxy_buffer_size 4k;
proxy_buffers 8 4k;
proxy_busy_buffers_size 8k;
```

### 3. 文件缓存优化 / File Cache Optimization

```nginx
# 打开文件缓存 / Open
```
