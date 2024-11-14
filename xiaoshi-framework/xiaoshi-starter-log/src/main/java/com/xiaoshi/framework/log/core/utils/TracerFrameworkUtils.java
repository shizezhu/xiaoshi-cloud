package com.xiaoshi.framework.log.core.utils;

import org.slf4j.MDC;

public class TracerFrameworkUtils {

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
