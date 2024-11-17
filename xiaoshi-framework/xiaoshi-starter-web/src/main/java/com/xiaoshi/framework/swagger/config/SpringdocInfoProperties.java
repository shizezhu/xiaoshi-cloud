package com.xiaoshi.framework.swagger.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * springdoc 明细配置
 *
 * @author xiaoshi
 * @since 2024/11/17 上午1:40
 */
@Data
@Validated
@ConfigurationProperties("springdoc.info")
public class SpringdocInfoProperties {

    /**
     * API文档标题
     */
    @NotEmpty(message = "springdoc.info.title 未配置")
    private String title;

    /**
     * API文档描述
     */
    private String description = "小石云";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务条款链接
     */
    private String termsOfService;

    /**
     * 联系人
     */
    @NotNull
    private Contact contact = new Contact();

    /**
     * 许可证
     */
    @NotNull
    private License license = new License();

    @Data
    public static class Contact {

        /**
         * 联系人姓名
         */
        private String name;

        /**
         * 联系人网址
         */
        private String url;

        /**
         * 联系人邮箱
         */
        private String email;

    }

    @Data
    public static class License {

        /**
         * 许可证名称
         */
        private String name;

        /**
         * 许可证网址
         */
        private String url;

    }
}
