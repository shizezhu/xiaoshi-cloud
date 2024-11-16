package com.xiaoshi.framework.log.core.web;

import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import com.xiaoshi.framework.log.core.utils.LogFrameworkUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

/**
 * Http 请求详情日志过滤器
 *
 * @author xiaoshi
 * @since 2024/11/14 下午6:47
 */
@Slf4j
public class HttpInfoFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 记录开始时间戳
        long startTime = System.currentTimeMillis();
        // 先从Header获取traceId
        String traceId = request.getHeader(LogFrameworkUtils.TRACE_ID);
        // 如果没有就生成一个
        if (CheckUtils.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        // 将traceId放入MDC
        LogFrameworkUtils.setTraceId(traceId);
        // 将traceId放入响应Header
        response.addHeader(LogFrameworkUtils.TRACE_ID, traceId);
        // 打印请求明细日志
        requestLog(request, traceId);
        // 继续过滤
        chain.doFilter(request, response);
        // 计算请求耗时
        long duration = System.currentTimeMillis() - startTime;
        // 打印响应明细日志
        responseLog(response, duration, traceId);
        // 移除MDC中的traceId
        MDC.remove(LogFrameworkUtils.TRACE_ID);
    }

    private void requestLog(HttpServletRequest request, String traceId) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("== REQUEST ==")
                .append("\nTRACE-ID: ").append(traceId)
                .append("\nMETHOD: ").append(request.getMethod())
                .append("\nURL: ").append(request.getRequestURI())
                .append("\nHEADERS:");
        // 处理请求头
        Collections.list(request.getHeaderNames()).forEach(headerName -> {
            String headerValue = request.getHeader(headerName);
            logMessage.append("\n\t").append(headerName).append(": ").append(headerValue);
        });
        logMessage.append("\nPARAMETERS:");
        // 处理请求参数
        Collections.list(request.getParameterNames()).forEach(paramName -> {
            String paramValue = request.getParameter(paramName);
            logMessage.append("\n\t").append(paramName).append(": ").append(paramValue);
        });
        log.info(logMessage.toString());
    }

    private void responseLog(HttpServletResponse response, long duration, String traceId) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("== RESPONSE ==")
                .append("\nTRACE-ID: ").append(traceId)
                .append("\nSTATUS: ").append(response.getStatus())
                .append("\nDURATION: ").append(duration).append("ms");
        log.info(logMessage.toString());
    }
}
