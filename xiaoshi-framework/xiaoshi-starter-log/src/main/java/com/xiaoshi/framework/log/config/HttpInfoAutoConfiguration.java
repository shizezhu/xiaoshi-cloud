package com.xiaoshi.framework.log.config;

import com.xiaoshi.framework.common.enums.FilterOrderConstants;
import com.xiaoshi.framework.log.core.web.HttpInfoFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Http Info 日志配置类
 *
 * @author xiaoshi
 * @since 2024/11/14 下午6:37
 */
@AutoConfiguration
@ConditionalOnClass(DispatcherServlet.class)
@ConditionalOnProperty(prefix = "xiaoshi.log.http-info.enable", value = "true", matchIfMissing = true)
public class HttpInfoAutoConfiguration {

    /**
     * 创建 HttpInfoFilter 过滤器
     */
    @Bean
    public FilterRegistrationBean<HttpInfoFilter> httpInfoFilter() {
        FilterRegistrationBean<HttpInfoFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpInfoFilter());
        registrationBean.setOrder(FilterOrderConstants.HTTP_INFO_LOG_FILTER);
        return registrationBean;
    }

}