package com.xiaoshi.framework.web.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户端接口通用请求头参数
 *
 * @author xiaoshi
 * @since 2024/11/15 上午11:48
 */
@Getter
@AllArgsConstructor
public class ClientHeader {

    /**
     * 请求链路追踪编号
     */
    private String traceId;

    /**
     * 语言
     */
    private String language;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用版本
     */
    private String appVer;

    /**
     * 认证令牌
     */
    private String authToken;

    /**
     * 用户令牌
     */
    private String userToken;

}
