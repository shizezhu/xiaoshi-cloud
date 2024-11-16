package com.xiaoshi.framework.log.core.utils;

import org.slf4j.MDC;

/**
 * 日志组件工具
 *
 * @author xiaoshi
 * @since 2024/11/14 下午10:07
 */
public class LogFrameworkUtils {

    public static final String TRACE_ID = "trace-id";

    /**
     * 获取TraceId
     *
     * @return TraceId
     * @author xiaoshi
     * @since 2024/11/14 下午1:36
     */
    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    /**
     * 设置TraceId
     *
     * @param traceId TraceId
     * @author xiaoshi
     * @since 2024/11/14 下午1:37
     */
    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

}
