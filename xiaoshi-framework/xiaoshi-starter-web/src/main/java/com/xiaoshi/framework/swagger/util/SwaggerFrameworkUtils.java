package com.xiaoshi.framework.swagger.util;

import com.xiaoshi.framework.log.core.utils.LogFrameworkUtils;
import com.xiaoshi.framework.web.core.util.AdminFrameworkUtils;
import com.xiaoshi.framework.web.core.util.ClientFrameworkUtils;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

public class SwaggerFrameworkUtils {

    /**
     * 构建请求日志 TraceId 请求头参数
     */
    public static Parameter buildTraceIdHeaderParameter() {
        return new Parameter()
                .name(LogFrameworkUtils.TRACE_ID) // header 名
                .description("接口请求id") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(
                        new StringSchema()._default("d2a90c42sfe57acb1563228479093")
                                .name(LogFrameworkUtils.TRACE_ID)
                                .description("接口请求id")
                );
    }

    /**
     * 构建客户端接口 语言 请求头参数
     */
    public static Parameter buildClientLanguagHeaderParameter() {
        return new Parameter()
                .name(ClientFrameworkUtils.HEADER_LANGUAGE) // header 名
                .description("语言") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(
                        new StringSchema()._default("en")
                                .name(ClientFrameworkUtils.HEADER_LANGUAGE)
                                .description("语言")
                );
    }

    /**
     * 构建客户端接口 APP ID 请求头参数
     */
    public static Parameter buildClientAppIdHeaderParameter() {
        return new Parameter()
                .name(ClientFrameworkUtils.HEADER_APP_ID) // header 名
                .description("appId") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(
                        new IntegerSchema()._default(1001)
                                .name(ClientFrameworkUtils.HEADER_APP_ID)
                                .description("appId")
                );
    }

    /**
     * 构建客户端接口 Device ID 请求头参数
     */
    public static Parameter buildClientDeviceIdHeaderParameter() {
        return new Parameter()
                .name(ClientFrameworkUtils.HEADER_DEVICE_ID) // header 名
                .description("uid") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(
                        new StringSchema()._default("1000000000000001")
                                .name(ClientFrameworkUtils.HEADER_DEVICE_ID)
                                .description("uid")
                );
    }

    /**
     * 构建客户端接口 AuthToke 请求头参数
     */
    public static Parameter buildClientAuthTokenHeaderParameter() {
        return new Parameter()
                .name(ClientFrameworkUtils.HEADER_AUTH_TOKEN) // header 名
                .description("auth-token") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(
                        new StringSchema()._default("d2a90c42sfe57acb156322847ac2a9093")
                                .name(ClientFrameworkUtils.HEADER_AUTH_TOKEN)
                                .description("auth-token")
                );
    }

    /**
     * 构建客户端接口 APP 版本号 请求头参数
     */
    public static Parameter buildClientAppVerHeaderParameter() {
        return new Parameter()
                .name(ClientFrameworkUtils.HEADER_APP_VER) // header 名
                .description("app版本号") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(
                        new StringSchema()._default("1.0")
                                .name(ClientFrameworkUtils.HEADER_APP_VER)
                                .description("app版本号")
                );
    }

    /**
     * 构建客户端接口 user-token 请求头参数
     */
    public static Parameter buildClientUserTokenHeaderParameter() {
        return new Parameter()
                .name(ClientFrameworkUtils.HEADER_USER_TOKEN) // header 名
                .description("用户token") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(
                        new StringSchema()._default("f7a01b2dfd865cb15a32d8b63a3cda34")
                                .name(ClientFrameworkUtils.HEADER_USER_TOKEN)
                                .description("用户token"));
    }

    /**
     * 构建管理后台 Authorization 认证请求头参数
     */
    public static Parameter buildAdminAuthorizationHeaderParameter() {
        return new Parameter()
                .name(AdminFrameworkUtils.HEADER_AUTHORIZATION) // header 名
                .description("认证 Token") // 描述
                .in("header") // 请求 header
                .required(true)
                .schema(new StringSchema()._default("374ad3f293s85fc83d85b92ac7a2398")
                        .name(AdminFrameworkUtils.HEADER_AUTHORIZATION).description("认证 Token"));
    }
}
