package com.xiaoshi.framework.web.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "web")
public class WebProperties {

    @NotNull(message = "客户端接口 API 未配置")
    private Api clientApi = new Api("/api", "com.xiaoshi.client.*.controller.**");

    @NotNull(message = "管理后台接口 API 未配置")
    private Api adminApi = new Api("/admin", "com.xiaoshi.admin.*.controller.**");

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Api {

        /**
         * API 前缀
         */
        @NotEmpty(message = "接口 API 前缀不能为空")
        private String prefix;

        /**
         * Controller 所在包的 Ant 路径规则
         */
        @NotEmpty(message = "Controller 所在包不能为空")
        private String controller;

    }

}
