package com.xiaoshi.framework.web.core.pojo;

import com.xiaoshi.framework.web.config.XiaoshiWebProperties;
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

    private final XiaoshiWebProperties xiaoshiWebProperties;

    private final String applicationName;

    public String getClientApi() {
        return xiaoshiWebProperties.getClientApi().getPrefix();
    }

    public String getAdminApi() {
        return xiaoshiWebProperties.getAdminApi().getPrefix();
    }

    public String getClientApplicationApi() {
        return xiaoshiWebProperties.getClientApi().getPrefix() + "/" + applicationName;
    }

    public String getAdminApplicationApi() {
        return xiaoshiWebProperties.getAdminApi().getPrefix() + "/" + applicationName;
    }
}
