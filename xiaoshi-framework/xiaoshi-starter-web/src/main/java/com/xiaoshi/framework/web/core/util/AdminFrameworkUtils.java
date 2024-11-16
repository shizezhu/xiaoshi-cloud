package com.xiaoshi.framework.web.core.util;

import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import com.xiaoshi.framework.log.core.utils.LogFrameworkUtils;
import com.xiaoshi.framework.web.core.pojo.AdminHeader;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 专属于 admin 包的工具类
 *
 * @author xiaoshi
 * @since 2024/11/15 下午12:26
 */
public class AdminFrameworkUtils {

    public static final String HEADER_AUTHORIZATION = "authorization";

    public static String getTraceId() {
        return getTraceId(WebUtils.getRequest());
    }

    public static String getTraceId(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String traceId = request.getHeader(LogFrameworkUtils.TRACE_ID);
        return CheckUtils.ifIsEmptyGet(traceId, "");
    }

    public static String getAuthorization() {
        return getAuthorization(WebUtils.getRequest());
    }

    public static String getAuthorization(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String authorization = request.getHeader(HEADER_AUTHORIZATION);
        return CheckUtils.ifIsEmptyGet(authorization, "");
    }

    public static AdminHeader getAdminHeader() {
        return new AdminHeader(getTraceId(), getAuthorization());
    }
}
