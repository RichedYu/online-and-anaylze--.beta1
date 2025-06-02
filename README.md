# 智能在线考试与学习分析系统 (在线考试及智能分析系统)

**项目状态：** Beta 1 (持续开发与优化中)
**主要技术栈：** Java, Spring Boot, MyBatis-Plus, MySQL, Vue.js, Element Plus, Weka, Spring AI (规划中), MinIO
**团队项目 | 荣获 [请填写完整竞赛名称，例如：XX省大学生计算机设计大赛] 省三等奖**

## 项目简介 (Project Overview)

本项目旨在构建一个现代化、智能化的在线考试与学习分析平台。系统不仅提供传统在线考试的核心功能（如用户管理、题库管理、试卷生成、在线答题、自动判分客观题），更集成了人工智能技术，重点实现对主观题的智能辅助批改和对学生学习状态、知识点掌握程度的多维度智能分析，从而提升教学效率与个性化辅导水平。

我们致力于通过技术创新，优化传统考试与学习分析流程，为教育者和学习者提供更高效、更精准的工具。

## 当前核心功能 (Current Core Features)

* **多角色用户系统：**
    * **学生端：** 在线考试、成绩查询、错题回顾、初步的学情分析报告。
    * **教师端：** 课程管理、题库建设（支持多种题型）、试卷创建与发布、客观题自动阅卷、主观题在线批改、班级成绩管理与统计。
    * **管理员端：** 学院、专业、班级、用户（教师/学生）管理、系统参数配置。
* **考试管理：**
    * 灵活的试卷创建（手动组卷/基于知识点和难度的智能抽题规划中）。
    * 限时考试与自动提交。
* **题库管理：**
    * 支持单选题、多选题、判断题、填空题、简答题、应用题等多种题型。
    * 题目与章节、知识点、难度等级关联。
* **智能分析基础模块 (初步实现)：**
    * 学生个人成绩趋势分析。
    * 班级整体学情概览。
    * 基于Weka决策树（J48）的知识点掌握程度初步预测模型。
* **文件服务：** 集成 MinIO 实现试卷、答案图片等静态资源存储。
* **基础架构：** 基于 Spring Boot 和 MyBatis-Plus 构建的稳定后端，Vue.js 和 Element Plus 构建的模块化前端。

## AI 相关贡献与优化 (My AI Contributions & Optimizations)

作为团队核心成员，我在项目的AI智能化方面做出了以下主要贡献，并持续推动相关模块的优化：

1.  **知识点掌握度预测模型的设计与实现：**
    * **数据结构优化与特征工程：**
        * 主导设计并迭代了用于模型训练的特征集。原始数据包含学生ID、题目类型、单题得分、答题耗时、题目难度等级。
        * 通过分析，将特征集从初期构想的3个聚合指标优化为更细致的5个核心预测特征 (`student_id`, `question_type`, `score`, `time_spent`, `difficulty_level`)，以更全面地捕捉影响学生掌握程度的因素，并以 `mastery_level`（优秀、良好、一般、需提高）作为预测目标。
        * 负责 `dataset.csv` 数据集的清洗、格式化 (UTF-8编码，确保分隔符一致性) 和维护，确保Weka模型训练的数据质量。
    * **机器学习模型训练与集成 (Weka J48)：**
        * 使用Weka工具套件，负责J48决策树模型的选型、训练、参数调优（如置信因子、最小叶节点实例数）和评估。
        * 通过10折交叉验证等方法评估模型性能，分析准确率、召回率、F1值等关键指标。
        * 将训练好的Weka模型 (`tree_model.model`) 序列化，并集成到Spring Boot后端服务 (`KnowledgeMasteryServiceImpl.java`) 中，实现了从classpath动态加载模型和数据集结构。
        * 设计并实现了服务降级逻辑：在预训练模型缺失时，系统能够回退到基于预设规则的预测模式，保证服务的可用性。
    * **模型优化方向探索：** 初步调研了随机森林 (Random Forest) 等集成学习算法，作为未来提升模型预测准确性和泛化能力的潜在方向。

2.  **AI辅助主观题批改模块规划与技术预研 (未来集成)：**
    * **OCR技术集成规划：** 规划引入OCR服务（如Google Cloud Vision API或阿里云OCR），实现从学生上传的图片格式答案中自动提取文本。
    * **LLM (如Gemini) 智能批改预研：**
        * 负责调研和设计通过Spring AI框架调用大型语言模型（如Gemini Pro）进行主观题智能评分和评语生成的方案。
        * 重点研究Prompt Engineering技术，设计引导LLM输出结构化（JSON格式）评分结果（包含建议分数、优点、不足、改进建议）的提示词模板。
        * 规划教师复核与修正AI批改结果的交互流程。

3.  **AI Agent 与 MCP 服务协议集成规划 (未来架构演进)：**
    * 深入学习并规划引入模型上下文协议 (MCP) 理念，将OCR、LLM智能批改、知识点掌握度分析等核心AI功能封装为标准化的、可独立部署和调用的AI Agent工具。
    * 目标是构建一个更灵活、可扩展、易于维护的AI服务架构，提升系统整体效率和未来新AI能力的集成速度。主应用将作为MCP客户端与这些AI Agent工具进行交互。

## 项目优化亮点 (Key Optimizations)

* **数据驱动的AI分析：** 从简单的统计分析升级到基于机器学习的预测模型，为个性化学习提供更深层次的洞察。
* **资源加载优化：** 改进了Weka模型和数据集的加载方式，从硬编码的绝对路径改为更健壮的classpath资源加载，提升了应用的可移植性和部署灵活性。
* **服务健壮性：** 为AI分析服务增加了初始化检查和降级机制，确保在模型文件缺失等异常情况下，核心应用仍能稳定运行。
* **前端体验（规划中）：** 计划通过集成Tailwind CSS对前端UI进行全面现代化改造，提升用户体验和界面美观度。
* **后端架构（规划中）：** 计划通过引入异步处理和MCP理念，优化后端AI任务的处理效率和系统整体架构。

## 技术栈 (Technology Stack)

* **后端：** Java, Spring Boot, Spring MVC, MyBatis-Plus, Spring AI (规划中)
* **数据库：** MySQL, Redis (用于缓存和会话管理)
* **前端：** Vue.js 3, Vue Router, Vuex, Element Plus, Axios, ECharts
* **AI/机器学习：** Weka (J48 Decision Tree), Python (备选，用于更复杂的模型训练和数据处理)
* **API与文档：** RESTful API, Knife4j (Swagger)
* **文件存储：** MinIO
* **版本控制：** Git, GitHub
* **部署（示例）：** Docker (规划中), Nginx

## 如何运行 (How to Run)

### 后端 (OnlineExamAndIntelligentAnalysis)

1.  确保已安装 Java 17 (或更高版本) 和 Maven。
2.  配置 `application.yml` 中的数据库连接、Redis连接、MinIO服务地址及凭证、阿里云短信服务凭证（如果需要）、以及AI中转服务 (`cm.aicore`) 的URL和Key。
3.  在MySQL中执行 `online_exam.sql` 创建数据库和表结构。
4.  将训练好的Weka模型文件 `tree_model.model` 和训练数据集 `dataset.csv` 放置在 `src/main/resources/` 目录下。
5.  使用Maven运行项目：`mvn spring-boot:run`
6.  API文档访问地址 (启动后): `http://localhost:8880/doc.html` (端口号以`application.yml`配置为准)

### 前端 (online-exam-and-intelligent-analysis-ui)

1.  确保已安装 Node.js 和 npm/yarn。
2.  进入 `online-exam-and-intelligent-analysis-ui` 目录。
3.  安装依赖：`npm install` (或 `yarn install`)
4.  启动开发服务器：`npm run serve` (或 `yarn serve`)
5.  在浏览器中打开 `http://localhost:10000` (端口号以 `vue.config.js` 配置为准)。

## 未来工作与展望 (Future Work & Outlook)

* **完善AI智能批改：** 全面集成OCR和基于LLM的主观题智能批改，并实现教师复核与反馈闭环。
* **深化数据分析与可视化：** 提供更丰富的学情分析维度和更直观的可视化报表。
* **MCP与AI Agent架构落地：** 将核心AI能力逐步重构为符合MCP规范的AI Agent服务。
* **移动端适配/App开发：** 提升移动端用户体验，或开发原生App。
* **系统性能与安全性增强：** 持续进行性能监控与优化，加强安全防护。
* **模型迭代与优化：** 收集更多数据，尝试更先进的机器学习算法，持续提升AI分析的准确性。


