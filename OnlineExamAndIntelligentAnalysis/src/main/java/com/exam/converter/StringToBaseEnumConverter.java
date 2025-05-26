package com.exam.converter;

import com.exam.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * Author:CM
 * Date: 2024/10/11 17:22
 * 类型转换逻辑一样可以继承到父类，构建类型转换工厂，同样需要注册
 * MVC枚举转换器工厂
 */
@Component
public class StringToBaseEnumConverter implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String source) {
                T[] enumConstants = targetType.getEnumConstants();
                for (T enumConstant : enumConstants) {
                    if(enumConstant.getCode().equals(String.valueOf(source))){
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("code:"+source+"非法！");
            }
        };
    }
}
