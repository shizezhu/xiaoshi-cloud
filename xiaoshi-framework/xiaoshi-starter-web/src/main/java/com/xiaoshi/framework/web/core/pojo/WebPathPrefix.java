package com.xiaoshi.framework.web.core.pojo;

import com.xiaoshi.framework.web.config.WebProperties;
import lombok.AllArgsConstructor;

/**
 * Api请求前缀
 * <p>
 * 方便在一些拦截器中使用
 *
 * @author xiaoshi
 * @since 2024/11/15 上午11:40
 */
@AllArgsConstructor
public class WebPathPrefix {

    private final WebProperties webProperties;

    private final String applicationName;

    public String getClientApi() {
        return webProperties.getClientApi().getPrefix();
    }

    public String getAdminApi() {
        return webProperties.getAdminApi().getPrefix();
    }

    public String getClientApplicationApi() {
        return webProperties.getClientApi().getPrefix() + "/" + applicationName;
    }

    public String getAdminApplicationApi() {
        return webProperties.getAdminApi().getPrefix() + "/" + applicationName;
    }
}
