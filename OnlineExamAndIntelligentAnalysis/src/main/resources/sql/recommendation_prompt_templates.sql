
-- =============================================
-- 智能推荐系统 AI 提示词模板 SQL
-- Intelligent Recommendation System AI Prompt Templates SQL
-- =============================================
-- 功能说明 / Function Description:
-- 本文件包含智能推荐系统所需的所有AI提示词模板
-- This file contains all AI prompt templates required by the intelligent recommendation system
--
-- 使用方法 / Usage:
-- 1. 确保 ai_prompt_template 表已创建
-- 2. 执行本SQL文件插入模板数据
-- 3. 系统会自动使用这些模板进行AI推荐
-- =============================================

-- 清理旧数据（可选）/ Clean old data (optional)
-- DELETE FROM ai_prompt_template WHERE template_key LIKE 'recommendation.%';

-- =============================================
-- 1. 资源推荐理由生成模板
-- Resource Recommendation Reason Generation Template
-- =============================================
INSERT INTO ai_prompt_template (template_key, template_name, template_content, category, model_type, description, is_active, created_at, updated_at)
VALUES (
    'recommendation.resource_reason',
    '学习资源推荐理由生成',
    '作为一名专业的教育顾问，请为以下学习资源推荐生成简洁的推荐理由（不超过150字）。

学生信息：
- 学生ID: {studentId}
- 当前薄弱知识点: {weakKnowledgePoints}
- 历史学习偏好: {learningPreferences}

推荐资源：
- 资源名称: {resourceTitle}
- 资源类型: {resourceType}
- 难度级别: {difficultyLevel}
- 关联知识点: {relatedKnowledgePoints}

推荐算法: {algorithmType}
置信度评分: {confidenceScore}

请从以下角度生成推荐理由：
1. 为什么这个资源适合该学生当前的学习需求
2. 该资源如何帮助提升薄弱知识点
3. 预期的学习效果

要求：
- 语言简洁专业，贴近学生理解水平
- 突出个性化匹配点
- 给出积极正面的学习建议',
    'RECOMMENDATION',
    'GENERAL',
    '为学习资源推荐生成个性化的推荐理由，解释为什么该资源适合学生',
    1,
    NOW(),
    NOW()
);

-- =============================================
-- 2. 学习路径规划模板
-- Learning Path Planning Template
-- =============================================
INSERT INTO ai_prompt_template (template_key, template_name, template_content, category, model_type, description, is_active, created_at, updated_at)
VALUES (
    'recommendation.learning_path',
    '个性化学习路径规划',
    '作为一名专业的学习规划师，请为学生制定个性化的学习路径建议。

学生画像：
- 学生ID: {studentId}
- 当前学习阶段: {currentStage}
- 薄弱知识点: {weakKnowledgePoints}
- 已掌握知识点: {masteredKnowledgePoints}
- 学习风格: {learningStyle}

可用资源列表：
{availableResources}

请生成一个分阶段的学习路径，包括：
1. 短期目标（1-2周）：优先攻克的薄弱点及推荐资源
2. 中期目标（1个月）：系统性提升计划
3. 长期目标（3个月）：知识体系完善建议

每个阶段请包括：
- 具体学习目标
- 推荐资源序列（按优先级排序）
- 预计学习时长
- 关键里程碑

要求：
- 循序渐进，由易到难
- 考虑知识点的依赖关系
- 符合学生的实际学习能力
- 每个阶段目标明确可量化',
    'RECOMMENDATION',
    'ADVANCED',
    '基于学生画像和知识图谱，生成个性化的分阶段学习路径',
    1,
    NOW(),
    NOW()
);

-- =============================================
-- 3. 自适应试卷组卷建议模板
-- Adaptive Paper Generation Suggestion Template
-- =============================================
INSERT INTO ai_prompt_template (template_key, template_name, template_content, category, model_type, description, is_active, created_at, updated_at)
VALUES (
    'recommendation.adaptive_paper',
    '自适应试卷组卷建议',
    '作为一名专业的试卷命题专家，请为学生生成自适应试卷的组卷建议。

学生能力评估：
- 学生ID: {studentId}
- 整体能力水平: {overallLevel}
- 知识点掌握度分布: {knowledgeMastery}
- 历史答题表现: {historicalPerformance}

组卷参数：
- 目标难度系数: {targetDifficulty}
- 题目总数: {totalQuestions}
- 题型分布: {questionTypeDistribution}
- 考察知识点: {targetKnowledgePoints}

请生成组卷建议，包括：
1. 题目难度分配策略
   - 简单题（60-70分）：巩固基础，建立信心
   - 中等题（70-85分）：主要考察区，测试掌握程度
   - 困难题（85-100分）：挑战题，区分优秀学生

2. 知识点覆盖建议
   - 薄弱知识点占比: 40-50%（重点提升）
   - 中等掌握知识点: 30-40%（巩固强化）
   - 已掌握知识点: 10-20%（保持水平）

3. 题型组合建议
   - 选择题：快速测评基础知识
   - 填空题：考察知识点记忆
   - 简答题：测试理解深度
   - 应用题：评估综合运用能力

4. 预期学习效果
   - 目标分数区间
   - 能力提升预期
   - 后续学习建议

要求：
- 确保试卷具有区分度和信度
- 难度曲线平滑过渡
- 知识点覆盖全面且有重点
- 符合学生当前能力水平',
    'RECOMMENDATION',
    'ADVANCED',
    '基于学生能力水平生成自适应试卷的组卷策略建议',
    1,
    NOW(),
    NOW()
);

-- =============================================
-- 4. 学习效果分析模板
-- Learning Effect Analysis Template
-- =============================================
INSERT INTO ai_prompt_template (template_key, template_name, template_content, category, model_type, description, is_active, created_at, updated_at)
VALUES (
    'recommendation.learning_effect',
    '学习效果分析报告',
    '作为一名专业的教育数据分析师，请分析学生的学习效果并生成分析报告。

学习记录数据：
- 学生ID: {studentId}
- 学习时段: {learningPeriod}
- 学习资源使用记录: {resourceUsageHistory}
- 知识点掌握度变化: {masteryChanges}
- 测试成绩趋势: {scoresTrend}

推荐历史：
- 推荐资源数量: {recommendedCount}
- 实际学习数量: {studiedCount}
- 平均完成度: {averageCompletion}
- 反馈评分: {feedbackScores}

请生成学习效果分析报告，包括：

1. 学习进度评估
   - 整体学习进度（百分比）
   - 知识点掌握度提升情况
   - 学习速度分析

2. 学习行为分析
   - 学习习惯（时间分布、持续性）
   - 资源偏好（类型、难度）
   - 完成度分析

3. 学习效果评价
   - 知识点掌握提升明显的部分
   - 仍需加强的薄弱环节
   - 学习效率评估

4. 改进建议
   - 学习方法优化建议
   - 资源选择建议
   - 时间管理建议

5. 下一步行动计划
   - 优先学习事项
   - 推荐学习资源
   - 预期目标

要求：
- 数据分析客观准确
- 建议具体可执行
- 语言鼓励性和建设性
- 突出个性化特点',
    'RECOMMENDATION',
    'ADVANCED',
    '分析学生学习效果，生成个性化的学习分析报告',
    1,
    NOW(),
    NOW()
);

-- =============================================
-- 5. 知识点关联分析模板
-- Knowledge Point Correlation Analysis Template
-- =============================================
INSERT INTO ai_prompt_template (template_key, template_name, template_content, category, model_type, description, is_active, created_at, updated_at)
VALUES (
    'recommendation.knowledge_correlation',
    '知识点关联性分析',
    '作为一名学科教学专家，请分析知识点之间的关联关系，为推荐系统提供依据。

目标知识点：
- 知识点名称: {knowledgePointName}
- 所属章节: {chapterInfo}
- 难度级别: {difficultyLevel}

知识体系上下文：
- 前置知识点: {prerequisiteKnowledgePoints}
- 后续知识点: {followupKnowledgePoints}
- 同级关联点: {relatedKnowledgePoints}

学生掌握情况：
- 该知识点掌握度: {currentMastery}
- 前置知识点掌握度: {prerequisiteMastery}
- 相关知识点掌握度: {relatedMastery}

请进行知识点关联分析，包括：

1. 前置依赖分析
   - 必须先掌握的知识点
   - 建议先学习的知识点
   - 依赖关系强度评分

2. 后续关联分析
   - 学会后可以继续学习的知识点
   - 知识延伸方向
   - 应用场景

3. 横向关联分析
   - 同难度相关知识点
   - 可以并行学习的内容
   - 综合应用的可能性

4. 学习建议
   - 当前知识点的学习策略
   - 推荐的学习顺序
   - 资源选择建议

要求：
- 分析基于学科知识体系
- 考虑学生实际掌握情况
- 给出具体的学习路径建议
- 关联强度量化评估',
    'RECOMMENDATION',
    'ADVANCED',
    '分析知识点之间的关联关系，为学习路径规划提供依据',
    1,
    NOW(),
    NOW()
);

-- =============================================
-- 6. 推荐结果解释模板
-- Recommendation Explanation Template
-- =============================================
INSERT INTO ai_prompt_template (template_key, template_name, template_content, category, model_type, description, is_active, created_at, updated_at)
VALUES (
    'recommendation.explain_result',
    '推荐结果解释说明',
    '作为一名教育推荐系统的解释专家，请用通俗易懂的语言解释推荐结果。

推荐场景：
- 学生ID: {studentId}
- 推荐触发原因: {triggerReason}
- 使用的推荐算法: {algorithmType}

推荐结果概览：
- 推荐资源数量: {resourceCount}
- 平均置信度: {averageConfidence}
- 主要推荐方向: {mainDirection}

算法参数：
- 协同过滤权重: {collaborativeWeight}
- 内容相似度权重: {contentWeight}
- AI推荐权重: {aiWeight}

请生成推荐结果解释，包括：

1. 推荐依据说明（80-100字）
   - 为什么推荐这些资源
   - 推荐算法的工作原理（简化版）
   - 个性化匹配的关键因素

2. 推荐质量说明（50-60字）
   - 置信度的含义
   - 如何理解推荐排序
   - 结果的可信度

3. 使用建议（80-100字）
   - 如何选择最适合的资源
   - 学习顺序建议
   - 反馈的重要性

4. 常见问题解答
   Q: 为什么推荐这些资源？
   A: [简洁回答]
   
   Q: 置信度分数代表什么？
   A: [简洁回答]
   
   Q: 如何提高推荐精准度？
   A: [简洁回答]

要求：
- 语言通俗易懂，避免专业术语
- 突出个性化特点
- 增强用户信任感
- 鼓励用户反馈',
    'RECOMMENDATION',
    'GENERAL',
    '用通俗语言解释推荐结果，帮助用户理解推荐系统',
    1,
    NOW(),
    NOW()
);

-- 
-- =============================================
-- 7. 反馈收集与改进建议模板
-- Feedback Collection and Improvement Suggestion Template
-- =============================================
INSERT INTO ai_prompt_template (template_key, template_name, template_content, category, model_type, description, is_active, created_at, updated_at)
VALUES (
    'recommendation.feedback_analysis',
    '用户反馈分析与改进',
    '作为一名推荐系统优化专家，请分析用户反馈并提供改进建议。

反馈数据统计：
- 时间范围: {timeRange}
- 总反馈数: {totalFeedback}
- 正面反馈率: {positiveRate}
- 负面反馈率: {negativeRate}
- 平均评分: {averageRating}

典型反馈案例：
{feedbackCases}

推荐效果数据：
- 推荐接受率: {acceptanceRate}
- 资源完成率: {completionRate}
- 学习效果提升: {effectImprovement}

请生成反馈分析报告，包括：

1. 反馈趋势分析
   - 用户满意度趋势
   - 主要问题类型分布
   - 改进效果评估

2. 问题识别
   - 推荐不准确的主要原因
   - 用户体验痛点
   - 技术局限性

3. 改进建议
   - 算法优化方向
   - 数据收集改进
   - 用户体验提升
   - 个性化增强措施

4. 实施计划
   - 短期改进措施（1-2周）
   - 中期优化计划（1个月）
   - 长期发展方向（3个月）

要求：
- 基于数据的客观分析
- 建议具体可执行
- 考虑实施成本和效益
- 持续迭代优化思路',
    'RECOMMENDATION',
    'ADVANCED',
    '分析用户反馈，为推荐系统改进提供数据驱动的建议',
    1,
    NOW(),
    NOW()
);

-- =============================================
-- 验证插入结果 / Verify insertion results
-- =============================================
-- 查询所有推荐相关的模板
-- Query all recommendation-related templates
-- SELECT template_key, template_name, category, is_active 
-- FROM ai_prompt_template 
-- WHERE template_key LIKE 'recommendation.%'
-- ORDER BY created_at;

-- =============================================
-- 使用说明 / Usage Instructions
-- =============================================
-- 1. 模板变量说明 / Template Variables Description:
--    所有模板中的 {variableName} 格式变量需要在运行时替换为实际值
--    All variables in {variableName} format need to be replaced with actual values at runtime
--
-- 2. 调用示例 / Invocation Example:
--    ```java
--    PromptTemplateVo template = promptTemplateService.getTemplate("recommendation.resource_reason");
--    Map<String, Object> variables = new HashMap<>();
--    variables.put("studentId", "123");
--    variables.put("resourceTitle", "Java基础教程");
--    // ... 填充其他变量
--    String finalPrompt = templateService.renderTemplate(template, variables);
--    ```
--
-- 3. 模板更新 / Template Update:
--    如需修改模板内容，可以直接UPDATE对应的记录
--    To modify template content, directly UPDATE the corresponding record
--    ```sql
--    UPDATE ai_prompt_template 
--    SET template_content = '新的模板内容'
--    WHERE template_key = 'recommendation.resource_reason';
--    ```
--
-- 4. 模板禁用 / Template Disable:
--    临时禁用某个模板，可设置 is_active = 0
--    To temporarily disable a template, set is_active = 0
--    ```sql
--    UPDATE ai_prompt_template 
--    SET is_active = 0 
--    WHERE template_key = 'recommendation.resource_reason';
--    ```
--
-- 5. 性能优化建议 / Performance Optimization Suggestions:
--    - 推荐使用缓存机制，避免频繁查询数据库
--    - 模板渲染应在应用层完成，减少数据库压力
--    - 定期分析模板使用频率，优化常用模板
--    - Use caching mechanism to avoid frequent database queries
--    - Template rendering should be done at application layer
--    - Regularly analyze template usage frequency and optimize frequently used templates
-- =============================================

COMMIT;