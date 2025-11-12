package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.AiPromptTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * AI提示词模板Mapper接口 // AI Prompt Template Mapper Interface
 * 提供数据库操作方法 // Provides database operation methods
 * 
 * @author System
 * @date 2025-11-12
 */
@Mapper
public interface AiPromptTemplateMapper extends BaseMapper<AiPromptTemplate> {

    /**
     * 增加使用次数统计 // Increment usage count
     * @param templateCode 模板编码 // Template code
     */
    @Update("UPDATE ai_prompt_template SET usage_count = usage_count + 1, " +
            "last_used_at = NOW() WHERE template_code = #{templateCode}")
    void incrementUsageCount(@Param("templateCode") String templateCode);

    /**
     * 更新平均响应时间 // Update average response time
     * @param templateCode 模板编码 // Template code
     * @param responseTime 响应时间(ms) // Response time (ms)
     */
    @Update("UPDATE ai_prompt_template SET " +
            "avg_response_time = CASE " +
            "  WHEN usage_count = 0 THEN #{responseTime} " +
            "  ELSE (avg_response_time * usage_count + #{responseTime}) / (usage_count + 1) " +
            "END " +
            "WHERE template_code = #{templateCode}")
    void updateAvgResponseTime(@Param("templateCode") String templateCode, 
                               @Param("responseTime") Integer responseTime);
}