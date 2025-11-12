# 🚀 5 分钟快速开始指南

# 5-Minute Quick Start Guide

本指南帮助您快速部署和运行 AI 提示词工程管理系统。  
This guide helps you quickly deploy and run the AI Prompt Engineering Management System.

---

## ✅ 前置条件 / Prerequisites

确保以下软件已安装 / Ensure the following software is installed:

- ☑️ **Java 17+** - `java -version`
- ☑️ **Maven 3.8+** - `mvn -version`
- ☑️ **MySQL 8.0+** - `mysql --version`
- ☑️ **Redis 7.x** - `redis-cli --version`
- ☑️ **Node.js 16+** - `node --version`
- ☑️ **Nginx 1.24+** (可选) - `nginx -v`

---

## 📦 快速部署 / Quick Deployment

### 步骤 1: 数据库初始化 / Step 1: Database Initialization

```bash
# 1. 登录MySQL / Login to MySQL
mysql -u root -p

# 2. 创建数据库 / Create database (如果尚未创建)
CREATE DATABASE IF NOT EXISTS online_exam DEFAULT CHARACTER SET utf8mb4;

# 3. 导入表结构 / Import table structure
USE online_exam;
SOURCE OnlineExamAndIntelligentAnalysis/src/main/resources/sql/ai_prompt_template.sql;

# 4. 验证 / Verify
SHOW TABLES LIKE 'ai_%';
SELECT COUNT(*) FROM ai_prompt_template;  # 应该返回 4 条初始数据
```

### 步骤 2: 启动 Redis / Step 2: Start Redis

```bash
# 启动Redis / Start Redis
redis-server

# 验证运行 / Verify running
redis-cli ping  # 应返回 PONG
```

### 步骤 3: 配置应用 / Step 3: Configure Application

```bash
cd OnlineExamAndIntelligentAnalysis

# 编辑配置文件 / Edit configuration file
vim src/main/resources/application.yml
```

**最小化配置 / Minimal Configuration:**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/online_exam?useUnicode=true&characterEncoding=utf8
    username: root
    password: your_password

  data:
    redis:
      host: localhost
      port: 6379

ai:
  model:
    default: openai
```

### 步骤 4: 启动后端 / Step 4: Start Backend

```bash
# 构建项目 / Build project
mvn clean package -DskipTests

# 启动应用 / Start application
java -jar target/exam-system.jar --server.port=8880

# 验证启动 / Verify startup
curl http://localhost:8880/health
```

### 步骤 5: 启动前端 / Step 5: Start Frontend

```bash
cd ../online-exam-and-intelligent-analysis-ui

# 安装依赖 / Install dependencies
npm install

# 启动开发服务器 / Start dev server
npm run dev

# 访问 / Access: http://localhost:5173
```

---

## 🧪 功能测试 / Feature Testing

### 测试 1: 获取模板列表 / Test 1: Get Template List

```bash
curl http://localhost:8880/login/prompt-templates/list
```

**预期结果 / Expected Result:**

```json
{
  "code": 200,
  "data": [
    {
      "id": 1,
      "templateName": "考试分析模板1",
      "templateCode": "EXAM_ANALYSIS_1",
      "category": "EXAM_ANALYSIS",
      "isActive": true
    }
  ]
}
```

### 测试 2: 渲染模板 / Test 2: Render Template

```bash
curl -X POST http://localhost:8880/login/prompt-templates/EXAM_ANALYSIS_1/render \
  -H "Content-Type: application/json" \
  -d '{"examName":"期中考试","totalStudents":"100"}'
```

### 测试 3: 查看缓存统计 / Test 3: View Cache Statistics

```bash
curl http://localhost:8880/login/prompt-templates/cache/statistics
```

---

## 🌐 访问系统 / Access System

### 前端界面 / Frontend UI

- **开发环境 / Development**: http://localhost:5173
- **生产环境 / Production**: http://your-domain.com

### API 文档 / API Documentation

- **Swagger UI**: http://localhost:8880/doc.html
- **Health Check**: http://localhost:8880/health

### 默认路由 / Default Routes

- **提示词管理 / Prompt Management**: `/prompt-templates`
- **AI 分析 / AI Analysis**: `/ai-analysis`
- **缓存监控 / Cache Monitoring**: `/cache-stats`

---

## 🔧 常用操作 / Common Operations

### 创建新模板 / Create New Template

```bash
curl -X POST http://localhost:8880/login/prompt-templates/create \
  -H "Content-Type: application/json" \
  -d '{
    "templateName": "我的模板",
    "templateCode": "MY_TEMPLATE",
    "templateContent": "这是模板内容，变量：{{var1}}",
    "category": "GENERAL",
    "version": "1.0.0",
    "description": "测试模板",
    "isActive": true,
    "variables": {
      "var1": "默认值"
    }
  }'
```

### 清除缓存 / Clear Cache

```bash
# 清除所有缓存 / Clear all cache
curl -X POST http://localhost:8880/login/prompt-templates/cache/clear

# 清除Redis缓存 / Clear Redis cache
redis-cli FLUSHDB
```

### 缓存预热 / Cache Warm-up

```bash
# 预热常用模板 / Warm up common templates
curl -X POST http://localhost:8880/login/prompt-templates/cache/warm-up
```

---

## 🚦 健康检查 / Health Checks

```bash
# 后端健康检查 / Backend health check
curl http://localhost:8880/health

# Redis连接检查 / Redis connection check
redis-cli ping

# MySQL连接检查 / MySQL connection check
mysql -u root -p -e "SELECT 1"

# 查看应用日志 / View application logs
tail -f logs/application.log
```

---

## 📊 监控指标 / Monitoring Metrics

### 查看缓存命中率 / View Cache Hit Rate

```bash
curl http://localhost:8880/login/prompt-templates/cache/statistics | jq '{
  hitRate: .hitRate,
  totalHits: .totalHits,
  totalMisses: .totalMisses
}'
```

### 查看系统指标 / View System Metrics

```bash
# JVM内存使用 / JVM memory usage
curl http://localhost:8880/actuator/metrics/jvm.memory.used

# HTTP请求统计 / HTTP request stats
curl http://localhost:8880/actuator/metrics/http.server.requests
```

---

## 🔥 性能优化快速配置 / Quick Performance Tuning

### 1. Redis 内存优化 / Redis Memory Optimization

```bash
redis-cli CONFIG SET maxmemory 2gb
redis-cli CONFIG SET maxmemory-policy allkeys-lru
```

### 2. JVM 参数优化 / JVM Parameter Optimization

```bash
java -jar \
  -Xms2g -Xmx2g \
  -XX:+UseG1GC \
  -XX:MaxGCPauseMillis=200 \
  target/exam-system.jar
```

### 3. 数据库索引 / Database Indexes

```sql
-- 为常用查询添加索引 / Add indexes for common queries
CREATE INDEX idx_template_code ON ai_prompt_template(template_code);
CREATE INDEX idx_is_active ON ai_prompt_template(is_active);
CREATE INDEX idx_usage_count ON ai_prompt_template(usage_count DESC);
```

---

## ⚠️ 常见问题 / Common Issues

### 问题 1: 端口被占用 / Port Already in Use

```bash
# 查找占用端口的进程 / Find process using port
lsof -i :8880

# 杀死进程 / Kill process
kill -9 <PID>

# 或使用其他端口 / Or use another port
java -jar target/exam-system.jar --server.port=8881
```

### 问题 2: Redis 连接失败 / Redis Connection Failed

```bash
# 检查Redis是否运行 / Check if Redis is running
redis-cli ping

# 启动Redis / Start Redis
redis-server

# 检查防火墙 / Check firewall
sudo ufw allow 6379
```

### 问题 3: 数据库连接失败 / Database Connection Failed

```bash
# 检查MySQL是否运行 / Check if MySQL is running
sudo systemctl status mysql

# 启动MySQL / Start MySQL
sudo systemctl start mysql

# 验证凭据 / Verify credentials
mysql -u root -p
```

---

## 📚 下一步 / Next Steps

### 进阶配置 / Advanced Configuration

1. **负载均衡部署 / Load Balancer Deployment**

   - 参见: [Nginx 负载均衡配置](./NGINX-LOAD-BALANCER-CONFIG.md)
   - 启动多个后端实例
   - 配置 Nginx 反向代理

2. **生产环境部署 / Production Deployment**

   - 参见: [完整部署文档](./AI-PROMPT-SYSTEM-DEPLOYMENT.md)
   - HTTPS 配置
   - 安全加固
   - 监控告警

3. **架构深入了解 / Deep Dive into Architecture**
   - 参见: [架构设计总结](./ARCHITECTURE-DESIGN-SUMMARY.md)
   - 系统架构图
   - 数据流程
   - 性能优化

### 学习资源 / Learning Resources

- **API 文档**: http://localhost:8880/doc.html
- **源码注释**: 所有代码包含中英文注释
- **示例代码**: 查看测试类中的用例

---

## 🎉 快速开始完成！/ Quick Start Complete!

恭喜！您已成功启动 AI 提示词工程管理系统。  
Congratulations! You have successfully started the AI Prompt Engineering Management System.

**系统功能 / System Features:**

- ✅ 提示词模板管理 / Prompt Template Management
- ✅ AI 响应缓存 / AI Response Caching
- ✅ 实时监控统计 / Real-time Monitoring
- ✅ 前端可视化界面 / Visual Frontend Interface

**访问地址 / Access URLs:**

- 前端 / Frontend: http://localhost:5173
- 后端 API / Backend API: http://localhost:8880
- API 文档 / API Docs: http://localhost:8880/doc.html

**需要帮助? / Need Help?**

- 查看完整文档: [文档中心](./README.md)
- 常见问题: [故障排查](./AI-PROMPT-SYSTEM-DEPLOYMENT.md#故障排查)
- 性能优化: [性能优化章节](./AI-PROMPT-SYSTEM-DEPLOYMENT.md#性能优化)

---

**文档版本 / Document Version**: v1.0.0  
**最后更新 / Last Updated**: 2024-11-12  
**预计完成时间 / Estimated Time**: 5-10 分钟
