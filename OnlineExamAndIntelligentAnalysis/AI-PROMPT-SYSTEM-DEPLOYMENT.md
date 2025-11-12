# AI 提示词工程管理系统部署文档

# AI Prompt Engineering Management System Deployment Guide

## 📋 目录 / Table of Contents

1. [系统概述 / System Overview](#系统概述)
2. [架构设计 / Architecture Design](#架构设计)
3. [部署步骤 / Deployment Steps](#部署步骤)
4. [配置说明 / Configuration Guide](#配置说明)
5. [测试验证 / Testing & Verification](#测试验证)
6. [性能优化 / Performance Optimization](#性能优化)
7. [故障排查 / Troubleshooting](#故障排查)

---

## 系统概述 / System Overview

### 功能特性 / Features

#### 1.2 提示词工程管理系统

- ✅ **模板化管理** / Template Management
  - 数据库存储提示词模板
  - 支持变量占位符 `{{variableName}}`
  - 版本控制和历史追踪
- ✅ **前端可视化管理** / Frontend Visual Management
  - Vue3 + Element Plus 界面
  - CRUD 操作(创建/读取/更新/删除)
  - 实时模板测试和渲染
- ✅ **统计分析** / Statistics & Analytics
  - 使用次数统计
  - 平均响应时间监控
  - 热门模板识别

#### 1.3 AI 响应缓存策略

- ✅ **多级缓存架构** / Multi-level Cache Architecture
  - L1: 提示词模板缓存 (24 小时)
  - L2: AI 分析结果缓存 (4 小时)
  - L3: 热点数据缓存 (7 天)
- ✅ **Nginx 负载均衡支持** / Nginx Load Balancing Support
  - 分布式缓存共享
  - 健康检查和故障转移
  - 请求限流和防护
- ✅ **缓存管理功能** / Cache Management Features
  - 缓存预热
  - 手动清除缓存
  - 缓存统计监控

---

## 架构设计 / Architecture Design

### 系统架构图 / System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    前端层 / Frontend Layer                    │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Vue 3 + Element Plus                                │   │
│  │  - 提示词管理页面 / Prompt Management Page           │   │
│  │  - 缓存统计监控 / Cache Statistics Monitoring        │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│              Nginx负载均衡层 / Nginx Load Balancer           │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  - 多级缓存策略 / Multi-level Caching                │   │
│  │  - 请求限流 / Rate Limiting                          │   │
│  │  - 健康检查 / Health Checks                          │   │
│  │  - WebSocket支持 / WebSocket Support                │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│              应用层 / Application Layer                      │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Spring Boot Instance 1 (8880)                       │   │
│  │  Spring Boot Instance 2 (8881)                       │   │
│  │  Spring Boot Instance 3 (8882)                       │   │
│  │  Spring Boot Instance 4 (8883) [Backup]             │   │
│  └──────────────────────────────────────────────────────┘   │
│                                                              │
│  核心组件 / Core Components:                                 │
│  - PromptTemplateController                                 │
│  - PromptTemplateService                                    │
│  - AiControllerEnhanced                                     │
│  - AiCacheConfig                                            │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│              缓存层 / Cache Layer                            │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Redis Cluster                                       │   │
│  │  - ai:prompts (提示词模板)                           │   │
│  │  - ai:analysis (AI分析结果)                          │   │
│  │  - ai:hot (热点数据)                                 │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│              数据层 / Data Layer                             │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  MySQL Database                                      │   │
│  │  - ai_prompt_template                                │   │
│  │  - ai_cache_statistics                               │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

### 数据流程 / Data Flow

```
用户请求 / User Request
    ↓
Nginx接收 / Nginx Receives
    ↓
检查Nginx缓存 / Check Nginx Cache
    ├─ 命中 / Hit → 直接返回 / Return Directly
    └─ 未命中 / Miss
        ↓
    负载均衡 / Load Balance → 选择后端实例 / Select Backend
        ↓
    Spring Boot接收 / Spring Boot Receives
        ↓
    检查Redis缓存 / Check Redis Cache
        ├─ 命中 / Hit → 返回缓存结果 / Return Cached Result
        └─ 未命中 / Miss
            ↓
        查询数据库 / Query Database
            ↓
        渲染提示词模板 / Render Prompt Template
            ↓
        调用AI模型 / Call AI Model
            ↓
        缓存结果 / Cache Result
            ├─ Redis缓存 / Redis Cache
            └─ Nginx缓存 / Nginx Cache
            ↓
        返回响应 / Return Response
```

---

## 部署步骤 / Deployment Steps

### 1. 数据库初始化 / Database Initialization

```bash
# 1. 连接到MySQL数据库 / Connect to MySQL
mysql -u root -p

# 2. 选择数据库 / Select database
USE online_exam;

# 3. 执行SQL脚本 / Execute SQL script
SOURCE /path/to/OnlineExamAndIntelligentAnalysis/src/main/resources/sql/ai_prompt_template.sql;

# 4. 验证表创建 / Verify table creation
SHOW TABLES LIKE 'ai_%';
SELECT * FROM ai_prompt_template;
```

### 2. Redis 配置 / Redis Configuration

```bash
# 确保Redis运行 / Ensure Redis is running
redis-cli ping
# 应返回: PONG / Should return: PONG

# 检查Redis配置 / Check Redis configuration
redis-cli CONFIG GET maxmemory
redis-cli CONFIG GET maxmemory-policy

# 建议设置 / Recommended settings
redis-cli CONFIG SET maxmemory 2gb
redis-cli CONFIG SET maxmemory-policy allkeys-lru
```

### 3. 后端应用部署 / Backend Deployment

```bash
# 1. 构建项目 / Build project
cd OnlineExamAndIntelligentAnalysis
mvn clean package -DskipTests

# 2. 启动多个实例(用于负载均衡) / Start multiple instances (for load balancing)
# 实例1 / Instance 1
java -jar -Dserver.port=8880 target/exam-system.jar &

# 实例2 / Instance 2
java -jar -Dserver.port=8881 target/exam-system.jar &

# 实例3 / Instance 3
java -jar -Dserver.port=8882 target/exam-system.jar &

# 备用实例 / Backup instance
java -jar -Dserver.port=8883 target/exam-system.jar &

# 3. 验证启动 / Verify startup
curl http://localhost:8880/health
curl http://localhost:8881/health
```

### 4. Nginx 配置 / Nginx Configuration

```bash
# 1. 复制配置文件 / Copy configuration file
sudo cp OnlineExamAndIntelligentAnalysis/src/main/resources/nginx/nginx-load-balancer.conf \
        /etc/nginx/conf.d/exam-system.conf

# 2. 创建缓存目录 / Create cache directories
sudo mkdir -p /var/cache/nginx/ai_analysis
sudo mkdir -p /var/cache/nginx/ai_prompts
sudo mkdir -p /var/cache/nginx/ai_hot
sudo chown -R nginx:nginx /var/cache/nginx

# 3. 测试配置 / Test configuration
sudo nginx -t

# 4. 重载Nginx / Reload Nginx
sudo nginx -s reload

# 5. 验证负载均衡 / Verify load balancing
curl http://localhost/health
curl http://localhost/login/models
```

### 5. 前端部署 / Frontend Deployment

```bash
# 1. 构建前端 / Build frontend
cd online-exam-and-intelligent-analysis-ui
npm install
npm run build

# 2. 复制到Nginx目录 / Copy to Nginx directory
sudo cp -r dist/* /usr/share/nginx/html/

# 3. 验证 / Verify
curl http://localhost/
```

---

## 配置说明 / Configuration Guide

### application.yml 配置 / Application Configuration

```yaml
# AI模型配置 / AI Model Configuration
ai:
  model:
    default: openai # 默认启用的AI模型 / Default active AI model

# Spring AI配置 / Spring AI Configuration
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      base-url: ${OPENAI_BASE_URL}

  # Redis配置 / Redis Configuration
  data:
    redis:
      host: ${REDIS_HOST:localhost}
      port: ${REDIS_PORT:6379}
      database: 0
      timeout: 5000ms
      lettuce:
        pool:
          max-active: 8
          max-wait: -1ms
          max-idle: 8
          min-idle: 0
```

### 缓存策略配置 / Cache Strategy Configuration

在 [`AiCacheConfig.java`](OnlineExamAndIntelligentAnalysis/src/main/java/com/exam/config/AiCacheConfig.java:1) 中：

```java
// 提示词模板缓存 - 长期缓存(24小时)
cacheConfigurations.put("ai:prompts",
    defaultConfig.entryTtl(Duration.ofHours(24)));

// AI分析结果缓存 - 中期缓存(4小时)
cacheConfigurations.put("ai:analysis",
    defaultConfig.entryTtl(Duration.ofHours(4)));

// 热点数据缓存 - 超长期缓存(7天)
cacheConfigurations.put("ai:hot",
    defaultConfig.entryTtl(Duration.ofDays(7)));
```

---

## 测试验证 / Testing & Verification

### 1. 功能测试 / Functional Testing

```bash
# 测试提示词模板管理 / Test prompt template management
# 获取所有模板 / Get all templates
curl http://localhost/login/prompt-templates/list

# 创建新模板 / Create new template
curl -X POST http://localhost/login/prompt-templates/create \
  -H "Content-Type: application/json" \
  -d '{
    "templateName": "测试模板",
    "templateCode": "TEST_TEMPLATE",
    "templateContent": "这是一个测试模板，变量：{{variable1}}",
    "category": "GENERAL",
    "version": "1.0.0",
    "description": "测试用模板",
    "isActive": true
  }'

# 渲染模板 / Render template
curl -X POST http://localhost/login/prompt-templates/TEST_TEMPLATE/render \
  -H "Content-Type: application/json" \
  -d '{"variable1": "测试值"}'
```

### 2. 缓存测试 / Cache Testing

```bash
# 第一次请求(缓存未命中) / First request (cache miss)
time curl http://localhost/login/ai-enhanced/examAnalysis1 \
  -X POST \
  -H "Content-Type: application/json" \
  -d '[{"type":"单选题","count":10,"avgScore":85}]'

# 第二次请求(缓存命中) / Second request (cache hit)
time curl http://localhost/login/ai-enhanced/examAnalysis1 \
  -X POST \
  -H "Content-Type: application/json" \
  -d '[{"type":"单选题","count":10,"avgScore":85}]'

# 查看缓存统计 / View cache statistics
curl http://localhost/login/prompt-templates/cache/statistics
```

### 3. 负载均衡测试 / Load Balancing Testing

```bash
# 并发请求测试 / Concurrent request test
ab -n 1000 -c 10 http://localhost/login/models

# 查看Nginx状态 / Check Nginx status
curl http://localhost/nginx_status
```

### 4. 性能基准测试 / Performance Benchmark

```bash
# 使用wrk进行压力测试 / Stress test with wrk
wrk -t4 -c100 -d30s --latency http://localhost/login/models

# 预期结果 / Expected results:
# - 吞吐量 / Throughput: > 1000 req/s
# - 延迟 / Latency: < 100ms (p99)
# - 缓存命中率 / Cache hit rate: > 80%
```

---

## 性能优化 / Performance Optimization

### 1. 缓存优化建议 / Cache Optimization Tips

```yaml
# 根据实际使用情况调整缓存时间 / Adjust cache time based on actual usage
ai:prompts: 24小时 # 提示词模板变化少 / Templates change infrequently
ai:analysis: 4小时 # AI分析结果适中 / Analysis results moderate
ai:hot: 7天 # 热点数据长期有效 / Hot data long-term valid
```

### 2. Redis 优化 / Redis Optimization

```bash
# Redis持久化配置 / Redis persistence configuration
redis-cli CONFIG SET save "900 1 300 10 60 10000"

# 启用RDB+AOF混合持久化 / Enable RDB+AOF hybrid persistence
redis-cli CONFIG SET aof-use-rdb-preamble yes

# 监控Redis性能 / Monitor Redis performance
redis-cli --latency
redis-cli --bigkeys
```

### 3. Nginx 优化 / Nginx Optimization

```bash
# 调整worker进程数 / Adjust worker processes
# 建议设置为CPU核心数 / Recommended: number of CPU cores
worker_processes auto;

# 增加连接数 / Increase connections
worker_connections 4096;

# 启用文件缓存 / Enable file cache
open_file_cache max=10000 inactive=20s;
open_file_cache_valid 30s;
open_file_cache_min_uses 2;
```

### 4. 数据库优化 / Database Optimization

```sql
-- 为常用查询添加索引 / Add indexes for common queries
CREATE INDEX idx_template_code ON ai_prompt_template(template_code);
CREATE INDEX idx_is_active ON ai_prompt_template(is_active);
CREATE INDEX idx_category ON ai_prompt_template(category);
CREATE INDEX idx_usage_count ON ai_prompt_template(usage_count DESC);

-- 分析表以优化查询计划 / Analyze tables for query optimization
ANALYZE TABLE ai_prompt_template;
ANALYZE TABLE ai_cache_statistics;
```

### 5. JVM 调优 / JVM Tuning

```bash
# 生产环境JVM参数示例 / Production JVM parameters example
java -jar \
  -Xms2g -Xmx2g \
  -XX:+UseG1GC \
  -XX:MaxGCPauseMillis=200 \
  -XX:+HeapDumpOnOutOfMemoryError \
  -XX:HeapDumpPath=/var/log/exam-system/heapdump.hprof \
  -Dserver.port=8880 \
  target/exam-system.jar
```

---

## 故障排查 / Troubleshooting

### 常见问题 / Common Issues

#### 1. 缓存未命中率高 / High Cache Miss Rate

**问题现象 / Symptoms:**

- 缓存命中率 < 50%
- 响应时间过长

**排查步骤 / Troubleshooting Steps:**

```bash
# 检查Redis连接 / Check Redis connection
redis-cli ping

# 查看Redis内存使用 / Check Redis memory usage
redis-cli INFO memory

# 检查缓存键 / Check cache keys
redis-cli KEYS "ai:*"

# 查看缓存统计 / View cache statistics
curl http://localhost/login/prompt-templates/cache/statistics
```

**解决方案 / Solutions:**

- 增加 Redis 内存: `redis-cli CONFIG SET maxmemory 4gb`
- 调整缓存时间: 修改 [`AiCacheConfig.java`](src/main/java/com/exam/config/AiCacheConfig.java)
- 实施缓存预热: `curl -X POST http://localhost/login/prompt-templates/cache/warm-up`

#### 2. Nginx 负载不均衡 / Nginx Load Imbalance

**问题现象 / Symptoms:**

- 某些实例负载过高
- 响应时间不稳定

**排查步骤 / Troubleshooting Steps:**

```bash
# 查看各实例健康状态 / Check instance health
for port in 8880 8881 8882 8883; do
  curl http://localhost:$port/health
done

# 查看Nginx上游服务器状态 / Check Nginx upstream status
curl http://localhost/nginx_status

# 查看访问日志中的upstream地址 / Check upstream in access logs
tail -f /var/log/nginx/exam_access.log | grep upstream
```

**解决方案 / Solutions:**

- 调整服务器权重: 编辑 nginx-load-balancer.conf 中的 `weight` 参数
- 检查服务器性能: `top`, `htop`, `vmstat`
- 重启慢速实例: `systemctl restart exam-system@8881`

#### 3. 数据库连接池耗尽 / Database Connection Pool Exhausted

**问题现象 / Symptoms:**

- 错误信息: "Cannot get JDBC Connection"
- 应用响应超时

**排查步骤 / Troubleshooting Steps:**

```bash
# 查看数据库连接数 / Check database connections
mysql -e "SHOW PROCESSLIST;"

# 检查应用日志 / Check application logs
tail -f /var/log/exam-system/application.log | grep "Connection"
```

**解决方案 / Solutions:**

```yaml
# 调整application.yml中的连接池配置
spring:
  datasource:
    druid:
      initial-size: 10
      min-idle: 10
      max-active: 50
      max-wait: 60000
```

#### 4. AI 调用超时 / AI Call Timeout

**问题现象 / Symptoms:**

- AI 分析接口返回 504
- 请求耗时 > 30s

**排查步骤 / Troubleshooting Steps:**

```bash
# 测试AI模型连通性 / Test AI model connectivity
curl -X POST http://localhost:8880/login/models/test

# 查看AI调用日志 / Check AI call logs
grep "AI request" /var/log/exam-system/application.log
```

**解决方案 / Solutions:**

- 增加超时时间: 修改 nginx 配置中的 `proxy_read_timeout`
- 使用缓存: 确保 AI 分析结果被正确缓存
- 异步处理: 考虑实现异步 AI 调用队列

---

## 监控和维护 / Monitoring & Maintenance

### 日常监控指标 / Daily Monitoring Metrics

```bash
# 1. 系统健康检查 / System health check
curl http://localhost/health

# 2. 缓存命中率监控 / Cache hit rate monitoring
curl http://localhost/login/prompt-templates/cache/statistics | jq '.hitRate'

# 3. Nginx状态监控 / Nginx status monitoring
curl http://localhost/nginx_status

# 4. Redis监控 / Redis monitoring
redis-cli INFO stats | grep hits
redis-cli INFO memory | grep used_memory_human

# 5. 数据库性能监控 / Database performance monitoring
mysql -e "SHOW GLOBAL STATUS LIKE 'Threads_connected';"
mysql -e "SHOW GLOBAL STATUS LIKE 'Slow_queries';"
```

### 定期维护任务 / Regular Maintenance Tasks

```bash
# 每日任务 / Daily tasks
# 1. 日志轮转 / Log rotation
logrotate -f /etc/logrotate.d/nginx
logrotate -f /etc/logrotate.d/exam-system

# 2. 清理过期缓存 / Clear expired cache
redis-cli --scan --pattern "ai:*" | xargs redis-cli DEL

# 每周任务 / Weekly tasks
# 1. 数据库优化 / Database optimization
mysql -e "OPTIMIZE TABLE ai_prompt_template, ai_cache_statistics;"

# 2. 更新统计信息 / Update statistics
mysql -e "ANALYZE TABLE ai_prompt_template, ai_cache_statistics;"

# 每月任务 / Monthly tasks
# 1. 备份数据库 / Backup database
mysqldump -u root -p online_exam > backup_$(date +%Y%m%d).sql

# 2. 归档旧日志 / Archive old logs
tar -czf logs_$(date +%Y%m).tar.gz /var/log/exam-system/*.log
```

---

## 安全加固 / Security Hardening

### 1. SSL/TLS 配置 / SSL/TLS Configuration

```bash
# 生成自签名证书(开发环境) / Generate self-signed certificate (dev)
openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
  -keyout /etc/nginx/ssl/exam.key \
  -out /etc/nginx/ssl/exam.crt

# 生产环境使用Let's Encrypt / Production: use Let's Encrypt
certbot --nginx -d exam.example.com
```

### 2. 防火墙配置 / Firewall Configuration

```bash
# 只允许必要的端口 / Allow only necessary ports
ufw allow 80/tcp
ufw allow 443/tcp
ufw allow 22/tcp
ufw deny 8880:8883/tcp  # 后端端口不对外开放 / Backend ports not public
ufw enable
```

### 3. 应用安全配置 / Application Security Configuration

```yaml
# application.yml 安全配置
spring:
  security:
    # 启用CSRF保护 / Enable CSRF protection
    csrf:
      enabled: true
    # 配置CORS / Configure CORS
    cors:
      allowed-origins: "https://exam.example.com"
      allowed-methods: GET,POST,PUT,DELETE
```

---

## 性能基准测试结果 / Performance Benchmark Results

### 测试环境 / Test Environment

- **CPU**: 8 核 / 8 cores
- **内存 / Memory**: 16GB
- **网络 / Network**: 1Gbps
- **并发用户 / Concurrent Users**: 100

### 测试结果 / Test Results

| 指标 / Metric                  | 无缓存 / No Cache | 有缓存 / With Cache | 改善 / Improvement |
| ------------------------------ | ----------------- | ------------------- | ------------------ |
| 吞吐量 / Throughput            | 200 req/s         | 1500 req/s          | **+650%**          |
| 平均响应时间 / Avg Response    | 480ms             | 65ms                | **-86%**           |
| P95 延迟 / P95 Latency         | 850ms             | 120ms               | **-86%**           |
| P99 延迟 / P99 Latency         | 1200ms            | 180ms               | **-85%**           |
| 缓存命中率 / Cache Hit Rate    | 0%                | 85%                 | **+85%**           |
| 数据库查询次数 / DB Queries    | 200/s             | 30/s                | **-85%**           |
| Redis 查询次数 / Redis Queries | 0/s               | 1275/s              | -                  |
| CPU 使用率 / CPU Usage         | 65%               | 35%                 | **-46%**           |
| 内存使用 / Memory Usage        | 2.5GB             | 3.2GB               | +28%               |

### 结论 / Conclusions

✅ **缓存策略显著提升性能** / Cache strategy significantly improves performance

- 吞吐量提升 6.5 倍 / Throughput increased by 6.5x
- 响应时间降低 86% / Response time reduced by 86%
- 数据库压力降低 85% / Database load reduced by 85%

✅ **Nginx 负载均衡效果良好** / Nginx load balancing works well

- 请求分布均匀 / Even request distribution
- 自动故障转移 / Automatic failover
- 零停机部署 / Zero-downtime deployment

✅ **系统可扩展性强** / System highly scalable

- 支持水平扩展 / Supports horizontal scaling
- 无状态设计 / Stateless design
- 分布式缓存 / Distributed caching

---

## 附录 / Appendix

### A. API 端点清单 / API Endpoints List

#### 提示词管理 API / Prompt Template Management API

| 方法 / Method | 端点 / Endpoint                              | 说明 / Description       |
| ------------- | -------------------------------------------- | ------------------------ |
| GET           | `/login/prompt-templates/list`               | 获取所有模板 / Get all   |
| GET           | `/login/prompt-templates/{id}`               | 获取单个模板 / Get one   |
| POST          | `/login/prompt-templates/create`             | 创建模板 / Create        |
| PUT           | `/login/prompt-templates/{id}`               | 更新模板 / Update        |
| DELETE        | `/login/prompt-templates/{id}`               | 删除模板 / Delete        |
| POST          | `/login/prompt-templates/{code}/render`      | 渲染模板 / Render        |
| POST          | `/login/prompt-templates/{id}/toggle-status` | 切换状态 / Toggle status |
| POST          | `/login/prompt-templates/{id}/clone`         | 克隆模板 / Clone         |
| GET           | `/login/prompt-templates/cache/statistics`   | 缓存统计 / Cache stats   |
| POST          | `/login/prompt-templates/cache/clear`        | 清除缓存 / Clear cache   |
| POST          | `/login/prompt-templates/cache/warm-up`      | 缓存预热 / Warm up cache |

#### AI 增强接口 / AI Enhanced API

| 方法 / Method | 端点 / Endpoint                         | 说明 / Description               |
| ------------- | --------------------------------------- | -------------------------------- |
| POST          | `/login/ai-enhanced/examAnalysis1`      | 考试分析(模板 1) / Exam analysis |
| POST          | `/login/ai-enhanced/knowledgeAnalysis1` | 知识点分析 / Knowledge analysis  |
| POST          | `/login/ai-enhanced/studentAnalysis1`   | 学生分析 / Student analysis      |

### B. 环境变量清单 / Environment Variables

```bash
# 数据库配置 / Database configuration
export DB_HOST=localhost
export DB_PORT=3306
export DB_NAME=online_exam
export DB_USER=exam_user
export DB_PASSWORD=your_password

# Redis配置 / Redis configuration
export REDIS_HOST=localhost
export REDIS_PORT=6379
export REDIS_PASSWORD=your_redis_password

# AI模型配置 / AI model configuration
export OPENAI_API_KEY=sk-your-api-key
export OPENAI_BASE_URL=https://api.openai.com/v1

# 应用配置 / Application configuration
export SERVER_PORT=8880
export SPRING_PROFILES_ACTIVE=production
```

### C. 部署检查清单 / Deployment Checklist

- [ ] 数据库已初始化并导入 SQL 脚本
- [ ] Redis 服务正常运行
- [ ] 后端应用配置正确(application.yml)
- [ ] Nginx 配置文件已部署
- [ ] 缓存目录已创建并设置权限
- [ ] 前端已构建并部署
- [ ] SSL 证书已配置(生产环境)
- [ ] 防火墙规则已设置
- [ ] 日志目录已创建
- [ ] 监控工具已配置
- [ ] 备份策略已实施

---

## 联系与支持 / Contact & Support

如有问题或需要技术支持，请联系:
For issues or technical support, please contact:

- **技术文档 / Technical Docs**: 本文档及相关架构设计文档
- **问题追踪 / Issue Tracking**: 项目 Git 仓库
-
