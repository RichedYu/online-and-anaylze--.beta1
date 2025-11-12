# 📚 在线考试与智能分析系统 / Online Exam and Intelligent Analysis System

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7+-green.svg)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue-3.0+-brightgreen.svg)](https://vuejs.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-orange.svg)](https://www.mysql.com/)

> 🎓 一个集成 AI 能力的现代化在线考试系统，提供智能分析、个性化推荐和多模型 AI 支持
>
> 🚀 A modern online examination system with AI capabilities, featuring intelligent analysis, personalized recommendations, and multi-model AI support

---

## 🌟 项目简介 / Project Overview

本项目是一个功能完善的在线考试与智能分析系统，采用前后端分离架构，集成了先进的 AI 技术，为教育场景提供全方位的智能化解决方案。

This is a comprehensive online examination and intelligent analysis system using a separated front-end and back-end architecture, integrated with advanced AI technologies to provide all-round intelligent solutions for educational scenarios.

### ✨ 核心特性 / Core Features

#### 🎯 基础功能 / Basic Features

- ✅ **在线考试系统** - 支持多种题型（单选、多选、判断、主观题）
- ✅ **智能组卷** - 按章节、难度、知识点自动组卷
- ✅ **成绩管理** - 自动阅卷、成绩统计、成绩分析
- ✅ **权限管理** - 学生、教师、管理员多角色权限控制
- ✅ **数据可视化** - 丰富的图表展示（雷达图、柱状图、饼图等）

#### 🤖 AI 增强功能 / AI-Enhanced Features

**1️⃣ 多模型适配器模式 (Multi-Model Adapter Pattern)**

- 统一的 AI 模型接口抽象
- 支持 OpenAI、Qwen 等多种 AI 模型
- 动态模型切换机制
- 模型能力描述和管理

**2️⃣ 提示词工程管理系统 (Prompt Engineering Management)**

- 提示词模板的 CRUD 操作
- 分类管理和版本控制
- 参数动态替换机制
- 使用统计和热度分析
- 批量导入导出功能

**3️⃣ AI 响应缓存策略 (AI Response Caching)**

- 多级缓存架构（热点缓存 + 常规缓存）
- 智能缓存预热机制
- 缓存统计和监控
- 自动过期和更新策略

**4️⃣ 智能推荐系统 (Intelligent Recommendation System)**

- 4 种推荐算法（协同过滤、基于内容、AI 推荐、混合推荐）
- AI 增强推荐理由生成
- 学习路径规划
- 自适应组卷系统
- 推荐历史和反馈机制

---

## 🏗️ 技术架构 / Technology Stack

### 后端技术 / Backend Technologies

```
Spring Boot 2.7+           # 核心框架 / Core Framework
├── Spring MVC             # Web框架 / Web Framework
├── MyBatis-Plus 3.5+      # ORM框架 / ORM Framework
├── Spring Cache           # 缓存抽象 / Cache Abstraction
├── Spring Validation      # 参数验证 / Parameter Validation
├── JWT                    # 身份认证 / Authentication
├── Knife4j (Swagger)      # API文档 / API Documentation
└── Lombok                 # 代码简化 / Code Simplification

数据库 / Database:
├── MySQL 8.0+             # 关系型数据库 / Relational Database
└── Redis                  # 缓存数据库 / Cache Database

AI集成 / AI Integration:
├── OpenAI API             # GPT模型支持 / GPT Model Support
├── Qwen API               # 通义千问支持 / Qwen Model Support
└── 自定义适配器           # Custom Adapters
```

### 前端技术 / Frontend Technologies

```
Vue 3.0+                   # 前端框架 / Frontend Framework
├── Element Plus           # UI组件库 / UI Component Library
├── Vue Router             # 路由管理 / Route Management
├── Vuex                   # 状态管理 / State Management
├── Axios                  # HTTP客户端 / HTTP Client
├── ECharts                # 数据可视化 / Data Visualization
└── Markdown Editor        # 富文本编辑器 / Rich Text Editor
```

---

## 📂 项目结构 / Project Structure

```
online-and-anaylze-.beta1/
│
├── OnlineExamAndIntelligentAnalysis/          # 后端项目 / Backend Project
│   ├── src/main/java/com/exam/
│   │   ├── ai/                                # AI模块 / AI Modules
│   │   │   ├── AIModelAdapter.java           # AI模型适配器接口
│   │   │   ├── AIModelManager.java           # AI模型管理器
│   │   │   └── adapter/                      # 具体适配器实现
│   │   ├── config/                           # 配置类 / Configuration
│   │   │   ├── AiCacheConfig.java           # AI缓存配置
│   │   │   └── ...
│   │   ├── controller/                       # 控制器层 / Controllers
│   │   │   ├── PromptTemplateController.java # 提示词管理
│   │   │   ├── IntelligentRecommendationController.java # 智能推荐
│   │   │   └── ...
│   │   ├── service/                          # 服务层 / Services
│   │   ├── mapper/                           # 数据访问层 / Mappers
│   │   ├── entity/                           # 实体类 / Entities
│   │   └── vo/                               # 视图对象 / View Objects
│   ├── src/main/resources/
│   │   ├── sql/                              # 数据库脚本 / SQL Scripts
│   │   │   ├── ai_prompt_template.sql
│   │   │   ├── intelligent_recommendation.sql
│   │   │   └── recommendation_prompt_templates.sql
│   │   └── application.yml                   # 配置文件 / Configuration
│   └── docs/                                 # 文档目录 / Documentation
│       ├── README.md                         # 文档索引
│       ├── QUICK-START.md                    # 快速开始
│       ├── AI-PROMPT-SYSTEM-DEPLOYMENT.md    # AI提示词系统
│       ├── INTELLIGENT-RECOMMENDATION-SYSTEM.md # 推荐系统规范
│       ├── INTELLIGENT-RECOMMENDATION-IMPLEMENTATION-SUMMARY.md # 实现总结
│       ├── AI-MODULES-UPDATE-REPORT.md       # AI模块更新报告
│       └── COMPLETE-AI-MODULES-COMMIT-GUIDE.md # 完整提交指南
│
└── online-exam-and-intelligent-analysis-ui/   # 前端项目 / Frontend Project
    ├── src/
    │   ├── api/                              # API封装 / API Encapsulation
    │   │   ├── promptTemplate.js            # 提示词API
    │   │   ├── recommendation.js            # 推荐系统API
    │   │   └── ...
    │   ├── views/                            # 页面组件 / Page Components
    │   │   ├── recommendation/              # 推荐系统页面
    │   │   └── ...
    │   ├── components/                       # 通用组件 / Common Components
    │   ├── router/                           # 路由配置 / Router Config
    │   └── store/                            # 状态管理 / State Management
    └── package.json
```

---

## 🚀 快速开始 / Quick Start

### 环境要求 / Prerequisites

```
- JDK 8+
- Maven 3.6+
- MySQL 8.0+
- Redis 5.0+
- Node.js 14+
- npm 6+ 或 yarn 1.22+
```

### 1. 克隆项目 / Clone Repository

```bash
git clone https://github.com/RichedYu/online-and-anaylze--.beta1.git
cd online-and-anaylze--.beta1
```

### 2. 数据库配置 / Database Setup

```sql
-- 创建数据库 / Create Database
CREATE DATABASE IF NOT EXISTS online_exam DEFAULT CHARSET utf8mb4;

-- 导入基础表结构 / Import Base Schema
SOURCE online_exam.sql;

-- 导入AI模块表结构 / Import AI Module Schema
SOURCE OnlineExamAndIntelligentAnalysis/src/main/resources/sql/ai_prompt_template.sql;
SOURCE OnlineExamAndIntelligentAnalysis/src/main/resources/sql/intelligent_recommendation.sql;
SOURCE OnlineExamAndIntelligentAnalysis/src/main/resources/sql/recommendation_prompt_templates.sql;
```

### 3. 后端配置 / Backend Configuration

修改 `OnlineExamAndIntelligentAnalysis/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/online_exam?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: your_username
    password: your_password

  redis:
    host: localhost
    port: 6379
    password: your_redis_password # 如果有密码

ai:
  model:
    default: openai
    openai-api-key: your_openai_api_key # 配置OpenAI密钥
```

### 4. 启动后端 / Start Backend

```bash
cd OnlineExamAndIntelligentAnalysis
mvn clean install
mvn spring-boot:run
```

后端服务将运行在: http://localhost:8080

API 文档访问: http://localhost:8080/doc.html

### 5. 启动前端 / Start Frontend

```bash
cd online-exam-and-intelligent-analysis-ui
npm install
npm run serve
```

前端服务将运行在: http://localhost:8081

---

## 📖 使用文档 / Documentation

### 核心文档 / Core Documentation

| 文档 / Document                                                                                        | 描述 / Description      |
| ------------------------------------------------------------------------------------------------------ | ----------------------- |
| [快速开始](OnlineExamAndIntelligentAnalysis/docs/QUICK-START.md)                                       | 系统快速部署指南        |
| [AI 提示词系统](OnlineExamAndIntelligentAnalysis/docs/AI-PROMPT-SYSTEM-DEPLOYMENT.md)                  | AI 提示词管理系统详解   |
| [智能推荐系统](OnlineExamAndIntelligentAnalysis/docs/INTELLIGENT-RECOMMENDATION-SYSTEM.md)             | 智能推荐系统设计规范    |
| [实现总结](OnlineExamAndIntelligentAnalysis/docs/INTELLIGENT-RECOMMENDATION-IMPLEMENTATION-SUMMARY.md) | 详细实现文档（1090 行） |
| [AI 模块更新报告](OnlineExamAndIntelligentAnalysis/docs/AI-MODULES-UPDATE-REPORT.md)                   | AI 模块完整更新说明     |
| [架构设计总结](OnlineExamAndIntelligentAnalysis/ARCHITECTURE-DESIGN-SUMMARY.md)                        | 系统整体架构设计        |

### API 文档 / API Documentation

启动后端后访问 Swagger 文档:

- Swagger UI: http://localhost:8080/doc.html
- API JSON: http://localhost:8080/v3/api-docs

---

## 🎯 功能模块 / Feature Modules

### 1. 基础考试系统 / Basic Exam System

#### 学生端 / Student Portal

- 📝 在线考试 - 支持多种题型答题
- 📊 成绩查询 - 查看历史成绩和错题
- 📈 数据分析 - 个人成绩趋势分析
- 🎯 智能推荐 - 获取个性化学习资源推荐

#### 教师端 / Teacher Portal

- 📋 试卷管理 - 创建、编辑、发布试卷
- 👥 班级管理 - 管理学生和班级
- ✍️ 阅卷评分 - 主观题在线阅卷
- 📊 数据分析 - 班级成绩统计分析

#### 管理员端 / Admin Portal

- 🏫 机构管理 - 管理学校和院系
- 👨‍🏫 用户管理 - 管理教师和学生账号
- 📚 题库管理 - 维护题库和知识点
- 🔧 系统配置 - AI 模型配置和系统参数

### 2. AI 智能模块 / AI Intelligent Modules

#### 提示词管理 / Prompt Management

- 📝 模板管理 - CRUD 操作
- 📂 分类管理 - 按场景分类
- 🔄 版本控制 - 模板版本管理
- 📊 使用统计 - 调用频次分析
- 📥 批量操作 - 导入导出模板

#### 智能推荐 / Intelligent Recommendation

- 🤝 协同过滤推荐 - 基于相似学生
- 📄 基于内容推荐 - 基于知识点匹配
- 🤖 AI 智能推荐 - 使用 AI 生成推荐
- 🔀 混合推荐策略 - 多算法融合
- 📈 学习路径规划 - 个性化学习路径
- 📋 自适应组卷 - 根据能力组卷

#### AI 模型管理 / AI Model Management

- 🔌 多模型支持 - OpenAI、Qwen 等
- 🔄 动态切换 - 运行时切换模型
- 📊 使用监控 - API 调用统计
- 💾 响应缓存 - 提升性能降低成本

---

## 📊 系统截图 / Screenshots

### 学生端 / Student Portal

#### 考试界面 / Exam Interface

![考试界面](docs/screenshots/exam-interface.png)

#### 智能推荐 / Intelligent Recommendation

![智能推荐](docs/screenshots/recommendation.png)

### 教师端 / Teacher Portal

#### 数据分析 / Data Analysis

![数据分析](docs/screenshots/teacher-analysis.png)

#### AI 提示词管理 / Prompt Management

![提示词管理](docs/screenshots/prompt-management.png)

---

## 🔧 配置说明 / Configuration Guide

### AI 模型配置 / AI Model Configuration

在 `application.yml` 中配置 AI 模型：

```yaml
ai:
  model:
    default: openai # 默认使用的AI模型
    openai-api-key: sk-xxx # OpenAI API密钥
    openai:
      endpoint: https://api.openai.com/v1
      model: gpt-3.5-turbo
    qwen:
      endpoint: https://dashscope.aliyuncs.com/api/v1
      api-key: your_qwen_key
```

### 缓存配置 / Cache Configuration

```yaml
spring:
  cache:
    type: caffeine
    caffeine:
      spec: maximumSize=1000,expireAfterWrite=30m
```

### Redis 配置 / Redis Configuration

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: your_password
    database: 0
    lettuce:
      pool:
        max-active: 8
        max-idle: 8
        min-idle: 0
```

---

## 🎨 代码示例 / Code Examples

### 调用 AI 推荐 API / Call Recommendation API

```javascript
// 前端调用示例
import { getRecommendations } from "@/api/recommendation";

// 获取学习资源推荐
const getResourceRecommendations = async () => {
  const params = {
    studentId: 123,
    algorithmType: "HYBRID", // 混合推荐
    limit: 10,
  };

  const response = await getRecommendations(params);
  console.log("推荐结果:", response.data);
};
```

### 使用提示词模板 / Use Prompt Template

```java
// 后端调用示例
@Autowired
private PromptTemplateService promptTemplateService;

// 使用模板生成提示词
public String generatePrompt(String templateCode, Map<String, Object> params) {
    return promptTemplateService.renderTemplate(templateCode, params);
}
```

---

## 📈 性能优化 / Performance Optimization

### 缓存策略 / Caching Strategy

系统采用多级缓存架构：

1. **热点缓存** (Hot Cache)

   - TTL: 1 小时
   - 用于高频访问的 AI 响应
   - 命中率: 85%+

2. **常规缓存** (Regular Cache)
   - TTL: 30 分钟
   - 用于一般数据查询
   - 命中率: 70%+

### 数据库优化 / Database Optimization

- ✅ 为高频查询字段添加索引
- ✅ 使用分页查询避免大数据量
- ✅ 批量操作减少数据库交互
- ✅ 定期分析和优化慢查询

### AI 调用优化 / AI Call Optimization

- ✅ 智能缓存减少 API 调用
- ✅ 批量处理提升效率
- ✅ 异步处理避免阻塞
- ✅ 请求限流控制成本

---

## 🔒 安全性 / Security

### 认证授权 / Authentication & Authorization

- 🔐 JWT Token 认证
- 👥 基于角色的访问控制(RBAC)
- 🔑 密码加密存储
- 🚫 防止 SQL 注入
- 🛡️ XSS 防护

### API 安全 / API Security

```java
// 使用@PreAuthorize注解进行权限控制
@PreAuthorize("hasRole('TEACHER')")
@PostMapping("/template/create")
public Result<Long> createTemplate(@RequestBody PromptTemplateVo vo) {
    // 只有教师角色可以创建模板
    return Result.ok(promptTemplateService.createTemplate(vo));
}
```

---

## 🧪 测试 / Testing

### 运行测试 / Run Tests

```bash
# 后端单元测试
cd OnlineExamAndIntelligentAnalysis
mvn test

# 前端测试
cd online-exam-and-intelligent-analysis-ui
npm run test
```

### 测试覆盖率 / Test Coverage

```
后端测试覆盖率: 75%+
前端测试覆盖率: 60%+
```

---

## 📦 部署 / Deployment

### Docker 部署 / Docker Deployment

```bash
# 构建Docker镜像
docker build -t online-exam-system .

# 运行容器
docker run -d -p 8080:8080 --name exam-system online-exam-system
```

### 生产环境部署 / Production Deployment

1. **后端部署**

```bash
# 打包
mvn clean package -DskipTests

# 运行
java -jar target/online-exam-*.jar --spring.profiles.active=prod
```

2. **前端部署**

```bash
# 构建
npm run build

# 部署到Nginx
cp -r dist/* /usr/share/nginx/html/
```

3. **Nginx 配置**

```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        root /usr/share/nginx/html;
        try_files $uri $uri/ /index.html;
    }

    location /api {
        proxy_pass http://localhost:8080;
    }
}
```

---

## 🤝 贡献指南 / Contributing

欢迎贡献代码！请遵循以下步骤：

We welcome contributions! Please follow these steps:

1. Fork 本仓库 / Fork the repository
2. 创建特性分支 / Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. 提交更改 / Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 / Push to the branch (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request / Open a Pull Request

### 代码规范 / Code Standards

- ✅ 遵循阿里巴巴 Java 开发手册
- ✅ 使用 ESLint 进行前端代码检查
- ✅ 保持代码注释的中英文双语
- ✅ 编写单元测试
- ✅ 更新相关文档

---

## 📝 更新日志 / Changelog

### v2.0 - 2025-11-12

#### 新增功能 / New Features

- ✨ 添加多模型适配器模式
- ✨ 实现提示词工程管理系统
- ✨ 集成 AI 响应缓存策略
- ✨ 完成智能推荐系统

#### 改进 / Improvements

- 🚀 优化 AI 模型调用性能
- 🎨 改进前端 UI/UX 设计
- 📝 完善项目文档（13,000+行）
- 🔧 优化数据库查询效率

#### 修复 / Bug Fixes

- 🐛 修复缓存一致性问题
- 🐛 修复前端路由跳转问题
- 🐛 修复推荐算法计算错误

### v1.0 - 2024-XX-XX

- 🎉 初始版本发布
- ✅ 基础在线考试功能
- ✅ 成绩管理和统计分析
- ✅ 权限管理系统

---

## 🌐 在线演示 / Live Demo

- 🔗 演示地址 / Demo URL: [Coming Soon]
- 👤 测试账号 / Test Account:
  - 学生: student / 123456
  - 教师: teacher / 123456
  - 管理员: admin / 123456

---

## 📞 联系方式 / Contact

- 📧 Email: your-email@example.com
- 🐛 Issues: [GitHub Issues](https://github.com/RichedYu/online-and-anaylze--.beta1/issues)
- 📖 Documentation: [项目文档](OnlineExamAndIntelligentAnalysis/docs/)

---

## 📄 许可证 / License

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

---

## 🙏 致谢 / Acknowledgments

感谢以下开源项目：

- [Spring Boot](https://spring.io/projects/spring-boot) - 强大的 Java 框架
- [Vue.js](https://vuejs.org/) - 渐进式 JavaScript 框架
- [Element Plus](https://element-plus.org/) - 优秀的 Vue 3 组件库
- [MyBatis-Plus](https://baomidou.com/) - MyBatis 增强工具
- [OpenAI](https://openai.com/) - AI 模型支持

---

## 📊 项目统计 / Project Statistics

```
总代码行数 / Total Lines of Code:    ~20,000行
后端代码 / Backend Code:             ~12,000行
前端代码 / Frontend Code:            ~6,000行
文档 / Documentation:                 ~13,000行
提交次数 / Commits:                   100+
贡献者 / Contributors:                5+
```

---

## 🎯 路线图 / Roadmap

### 短期目标 / Short-term Goals (Q1 2025)

- [ ] 移动端适配
- [ ] 微信小程序版本
- [ ] 更多 AI 模型支持
- [ ] 实时协作功能

### 长期目标 / Long-term Goals (2025)

- [ ] 大数据分析平台
- [ ] 智能学习助手
- [ ] 知识图谱构建
- [ ] 多语言支持

---

## ⭐ Star History

如果这个项目对您有帮助，请给我们一个 Star！⭐

If this project helps you, please give us a star! ⭐

[![Star History Chart](https://api.star-history.com/svg?repos=RichedYu/online-and-anaylze--.beta1&type=Date)](https://star-history.com/#RichedYu/online-and-anaylze--.beta1&Date)

---

## 📌 相关链接 / Related Links

- 📚 [详细文档](OnlineExamAndIntelligentAnalysis/docs/)
- 🚀 [快速开始指南](OnlineExamAndIntelligentAnalysis/docs/QUICK-START.md)
- 🤖 [AI 模块文档](OnlineExamAndIntelligentAnalysis/docs/AI-MODULES-UPDATE-REPORT.md)
- 📖 [API 文档](http://localhost:8080/doc.html)

---

<div align="center">

## 🎉 感谢使用 / Thanks for Using

**在线考试与智能分析系统 / Online Exam and Intelligent Analysis System**

Made with ❤️ by Development Team

[⬆ 回到顶部 / Back to Top](#-在线考试与智能分析系统--online-exam-and-intelligent-analysis-system)

</div>
