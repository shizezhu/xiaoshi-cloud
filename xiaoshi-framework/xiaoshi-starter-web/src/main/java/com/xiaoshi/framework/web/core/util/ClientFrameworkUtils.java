package com.xiaoshi.framework.web.core.util;

import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import com.xiaoshi.framework.log.core.utils.LogFrameworkUtils;
import com.xiaoshi.framework.web.core.pojo.ClientHeader;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;

/**
 * 专属于 client 包的工具类
 *
 * @author xiaoshi
 * @since 2024/11/15 下午12:20
 */
@UtilityClass
public class ClientFrameworkUtils {

    public final String HEADER_LANGUAGE = "language";

    public final String HEADER_DEVICE_ID = "device-id";

    public final String HEADER_APP_ID = "app-id";

    public final String HEADER_APP_VER = "app-ver";

    public final String HEADER_AUTH_TOKEN = "auth-token";

    public final String HEADER_USER_TOKEN = "user-token";

    public String getTraceId() {
        return getTraceId(WebUtils.getRequest());
    }

    public String getTraceId(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String traceId = request.getHeader(LogFrameworkUtils.TRACE_ID);
        return CheckUtils.ifIsEmptyGet(traceId, "");
    }

    public String getLanguage() {
        return getLanguage(WebUtils.getRequest());
    }

    public String getLanguage(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String language = request.getHeader(HEADER_LANGUAGE);
        if (CheckUtils.notEmpty(language)) {
            return language;
        }
        language = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        return CheckUtils.ifIsEmptyGet(language, "");
    }

    public String getDeviceId() {
        return getDeviceId(WebUtils.getRequest());
    }

    public String getDeviceId(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String deviceId = request.getHeader(HEADER_DEVICE_ID);
        return CheckUtils.ifIsEmptyGet(deviceId, "");
    }

    public String getAppId() {
        return getAppId(WebUtils.getRequest());
    }

    public String getAppId(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String appId = request.getHeader(HEADER_APP_ID);
        return CheckUtils.ifIsEmptyGet(appId, "");
    }

    public String getAppVer() {
        return getAppVer(WebUtils.getRequest());
    }

    public String getAppVer(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String appVer = request.getHeader(HEADER_APP_VER);
        return CheckUtils.ifIsEmptyGet(appVer, "");
    }

    public String getAuthToken() {
        return getAuthToken(WebUtils.getRequest());
    }

    public String getAuthToken(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String authToken = request.getHeader(HEADER_AUTH_TOKEN);
        return CheckUtils.ifIsEmptyGet(authToken, "");
    }

    public String getUserToken() {
        return getUserToken(WebUtils.getRequest());
    }

    public String getUserToken(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String userToken = request.getHeader(HEADER_USER_TOKEN);
        return CheckUtils.ifIsEmptyGet(userToken, "");
    }

    public ClientHeader getClientHeader() {
        return new ClientHeader(getTraceId(), getLanguage(), getDeviceId(), getAppId(),
                getAppVer(), getAuthToken(), getUserToken());
    }
}
