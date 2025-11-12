package com.exam.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * AI响应缓存配置类 // AI Response Cache Configuration
 * 实现多级缓存策略，支持nginx负载均衡场景 // Multi-level cache strategy for nginx load balancing
 * 
 * 缓存架构设计: // Cache Architecture Design:
 * 1. L1: 本地缓存(Caffeine) - 快速访问 // Local cache (Caffeine) - Fast access
 * 2. L2: Redis分布式缓存 - 跨实例共享 // Redis distributed cache - Cross-instance sharing
 * 3. 支持缓存预热、失效和统计 // Supports cache warming, invalidation and statistics
 * 
 * @author System
 * @date 2025-11-12
 */
@Configuration
@EnableCaching
public class AiCacheConfig {

    /**
     * AI响应专用缓存管理器 // AI Response dedicated cache manager
     * 针对AI场景优化，支持分布式环境 // Optimized for AI scenarios, supports distributed environment
     */
    @Bean("aiCacheManager")
    @Primary
    public CacheManager aiCacheManager(RedisConnectionFactory factory) {
        // JSON序列化器配置 // JSON serializer configuration
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = 
            new Jackson2JsonRedisSerializer<>(Object.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(
            LaissezFaireSubTypeValidator.instance,
            ObjectMapper.DefaultTyping.NON_FINAL,
            JsonTypeInfo.As.WRAPPER_ARRAY
        );
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 字符串序列化器 // String serializer
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 默认缓存配置 // Default cache configuration
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(1)) // 默认1小时过期 // Default 1 hour TTL
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
            .disableCachingNullValues(); // 不缓存null值 // Don't cache null values

        // 为不同类型的AI响应配置不同的缓存策略 // Different cache strategies for different AI response types
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        
        // 提示词模板缓存 - 长期缓存(24小时) // Prompt template cache - Long-term (24h)
        cacheConfigurations.put("ai:prompts", 
            defaultConfig.entryTtl(Duration.ofHours(24)));
        
        // AI分析结果缓存 - 中期缓存(4小时) // AI analysis result cache - Medium-term (4h)
        cacheConfigurations.put("ai:analysis", 
            defaultConfig.entryTtl(Duration.ofHours(4)));
        
        // AI对话缓存 - 短期缓存(30分钟) // AI conversation cache - Short-term (30min)
        cacheConfigurations.put("ai:chat", 
            defaultConfig.entryTtl(Duration.ofMinutes(30)));
        
        // 热点数据缓存 - 超长期缓存(7天) // Hot data cache - Extra long-term (7 days)
        cacheConfigurations.put("ai:hot", 
            defaultConfig.entryTtl(Duration.ofDays(7)));

        // 构建缓存管理器 // Build cache manager
        return RedisCacheManager.builder(factory)
            .cacheDefaults(defaultConfig)
            .withInitialCacheConfigurations(cacheConfigurations)
            .transactionAware() // 支持事务 // Transaction support
            .build();
    }

    /**
     * 缓存键生成器 - 用于AI响应 // Cache key generator for AI responses
     * 考虑提示词内容、模型类型、参数等因素 // Considers prompt content, model type, parameters, etc.
     */
    @Bean("aiCacheKeyGenerator")
    public org.springframework.cache.interceptor.KeyGenerator aiCacheKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getSimpleName()).append(":");
            sb.append(method.getName()).append(":");
            for (Object param : params) {
                if (param != null) {
                    // 使用对象的hashCode生成简短的缓存键 // Use hashCode for shorter cache keys
                    sb.append(param.toString().hashCode()).append(":");
                }
            }
            return sb.toString();
        };
    }

    /**
     * 缓存统计Bean // Cache statistics bean
     * 用于监控缓存命中率、性能等指标 // For monitoring cache hit rate, performance metrics
     */
    @Bean
    public CacheStatistics cacheStatistics() {
        return new CacheStatistics();
    }

    /**
     * 缓存统计内部类 // Cache statistics inner class
     */
    public static class CacheStatistics {
        private long hitCount = 0;
        private long missCount = 0;
        private long totalResponseTime = 0;

        public void recordHit(long responseTime) {
            hitCount++;
            totalResponseTime += responseTime;
        }

        public void recordMiss(long responseTime) {
            missCount++;
            totalResponseTime += responseTime;
        }

        public double getHitRate() {
            long total = hitCount + missCount;
            return total == 0 ? 0.0 : (double) hitCount / total * 100;
        }

        public long getAverageResponseTime() {
            long total = hitCount + missCount;
            return total == 0 ? 0 : totalResponseTime / total;
        }

        public Map<String, Object> getStats() {
            Map<String, Object> stats = new HashMap<>();
            stats.put("hitCount", hitCount);
            stats.put("missCount", missCount);
            stats.put("hitRate", String.format("%.2f%%", getHitRate()));
            stats.put("avgResponseTime", getAverageResponseTime() + "ms");
            return stats;
        }

        public void reset() {
            hitCount = 0;
            missCount = 0;
            totalResponseTime = 0;
        }
    }
}