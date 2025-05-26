package com.exam.config.minio;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Author:CM
 * Date: 2024/10/12 22:19
 */
@Configuration
@ConfigurationPropertiesScan("com.exam.config.minio")
@ConditionalOnProperty(name = "minio.endpoint")
public class MinioConfiguration {

    @Resource
    private MinioProperties properties;

    @Bean
    //@ConfigurationProperties()
    public MinioClient minioClient(){
        return MinioClient.builder().endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(),properties.getSecretKey())
                .build();
    }

}
