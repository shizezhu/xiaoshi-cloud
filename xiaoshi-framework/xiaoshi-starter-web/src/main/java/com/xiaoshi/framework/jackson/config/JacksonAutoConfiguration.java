package com.xiaoshi.framework.jackson.config;

import com.xiaoshi.framework.common.utils.json.JsonUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class JacksonAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return JsonUtils.jackson2ObjectMapperBuilderCustomizer();
    }
}
