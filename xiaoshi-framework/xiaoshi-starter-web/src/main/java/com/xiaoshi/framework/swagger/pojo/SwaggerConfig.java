package com.xiaoshi.framework.swagger.pojo;

import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Swagger自定义配置
 *
 * @author xiaoshi
 * @since 2024/11/17 上午12:08
 */
@Data
public class SwaggerConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Config adminConfig;

    private Config clientConfig;

    @Getter
    public static final class Config {

        /**
         * 请求地址前缀
         */
        @Setter
        private String urlPrefix;

        /**
         * 参数
         */
        private final List<Parameter> paramList;

        public Config() {
            this.paramList = new ArrayList<>();
        }

        public Config addParam(Parameter... params) {
            if (params != null) {
                return addParam(Arrays.asList(params));
            }
            return this;
        }

        public Config addParam(Collection<Parameter> params) {
            if (params != null) {
                this.paramList.addAll(params);
            }
            return this;
        }
    }
}
