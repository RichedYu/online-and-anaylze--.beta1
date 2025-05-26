package com.exam.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:CM
 * Date: 2024/10/8 17:40
 */

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(
                new Info()
                        .title("在线考试及智能分析系统API")
                        .version("1.0")
                        .description("在线考试及智能分析系统API"));
    }

    @Bean
    public GroupedOpenApi allAPI() {

        return GroupedOpenApi.builder().group("在线考试及智能分析系统API").
                pathsToMatch(
                        "/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi adminAPI() {

        return GroupedOpenApi.builder().group("管理员端").
                pathsToMatch(
                        "/admin/**",
                        "/common/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi studentAPI() {

        return GroupedOpenApi.builder().group("学生端").
                pathsToMatch(
                        "/student/**",
                        "/common/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi teacherAPI() {

        return GroupedOpenApi.builder().group("教师端").
                pathsToMatch(
                        "/teacher/**",
                        "/common/**"
                ).
                build();
    }

}
