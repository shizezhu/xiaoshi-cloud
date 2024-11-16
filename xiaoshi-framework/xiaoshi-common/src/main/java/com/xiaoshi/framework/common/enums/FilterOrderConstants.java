package com.xiaoshi.framework.common.enums;

/**
 * Filter 过滤器顺序枚举
 *
 * @author xiaoshi
 * @since 2024/11/14 下午6:40
 */
public interface FilterOrderConstants {

    /**
     * 跨域拦截器
     */
    int WEB_CORS_FILTER = 0;

    /**
     * Http明细日志拦截器
     */
    int LOG_HTTP_INFO_FILTER = WEB_CORS_FILTER + 1;

    /**
     * 请求Body缓存拦截器
     */
    int REQUEST_BODY_CACHE_FILTER = LOG_HTTP_INFO_FILTER + 1;

}
