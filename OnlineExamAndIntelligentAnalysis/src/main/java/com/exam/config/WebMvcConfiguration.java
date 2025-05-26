package com.exam.config;

import com.exam.config.interceptor.AuthenticationInterceptor;
import com.exam.converter.StringToBaseEnumConverter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * Author:CM
 * Date: 2024/10/11 16:21
 * springMVC转换器、拦截器基本配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private StringToBaseEnumConverter stringToBaseEnumConverter;
    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(this.stringToBaseEnumConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authenticationInterceptor)
                .addPathPatterns("/exam/**")
                .addPathPatterns("/analysis/**")
                .excludePathPatterns("/exam/login/**");

    }






}
