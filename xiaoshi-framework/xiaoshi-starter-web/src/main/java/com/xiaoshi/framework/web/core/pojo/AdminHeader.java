package com.xiaoshi.framework.web.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 管理后台接口通用请求头参数
 *
 * @author xiaoshi
 * @since 2024/11/15 上午11:48
 */
@Getter
@AllArgsConstructor
public class AdminHeader {

    /**
     * 请求链路追踪编号
     */
    private String traceId;

    /**
     * 用户登录令牌
     */
    private String authorization;

}
