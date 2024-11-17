package com.xiaoshi.framework.web.config;

import com.xiaoshi.framework.common.enums.FilterOrderConstants;
import com.xiaoshi.framework.web.core.filter.RequestBodyCacheFilter;
import com.xiaoshi.framework.web.core.handler.GlobalExceptionHandler;
import com.xiaoshi.framework.web.core.pojo.WebPathPrefix;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
@EnableConfigurationProperties(WebProperties.class)
public class WebAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private WebProperties webProperties;

    /**
     * 应用名
     */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 配置路径匹配规则
     * <p>
     * 定制客户端接口与管理后台接口的请求前缀，并限制API接口包路径，防止乱建包
     */
    @Override
    public void configurePathMatch(@NonNull PathMatchConfigurer configurer) {
        // 为管理员API配置路径匹配规则
        configurePathMatch(configurer, webProperties.getAdminApi());
        // 为客户端API配置路径匹配规则
        configurePathMatch(configurer, webProperties.getClientApi());
    }

    private void configurePathMatch(PathMatchConfigurer configurer, WebProperties.Api api) {
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        configurer.addPathPrefix(api.getPrefix() + "/" + applicationName,
                clazz -> (clazz.isAnnotationPresent(RestController.class) || clazz.isAnnotationPresent(Controller.class))
                        && antPathMatcher.match(api.getController(), clazz.getPackage().getName())); // 仅仅匹配 controller 包
    }

    /**
     * 创建 WebPathPrefix Bean
     */
    @Bean
    public WebPathPrefix webPathPrefix() {
        return new WebPathPrefix(webProperties, applicationName);
    }

    /**
     * 创建全局异常处理器
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 创建 FilterRegistrationBean
     */
    private static <T extends Filter> FilterRegistrationBean<T> createFilterRegistrationBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

    /**
     * 处理跨域
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterBean() {
        //1. CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //是否发送 Cookie
        config.setAllowCredentials(true);
        //放行哪些原始域
        config.addAllowedOriginPattern("*");
        //放行哪些请求方式
        config.addAllowedMethod("*");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //2. 添加映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        //3. 注册CorsFilter
        return createFilterRegistrationBean(new CorsFilter(source), FilterOrderConstants.WEB_CORS_FILTER);
    }

    /**
     * 实现可重复读请求Body
     */
    @Bean
    public FilterRegistrationBean<RequestBodyCacheFilter> requestBodyCacheFilter() {
        return createFilterRegistrationBean(new RequestBodyCacheFilter(), FilterOrderConstants.REQUEST_BODY_CACHE_FILTER);
    }

}
