package com.xiaoshi.framework.swagger.config;

import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import com.xiaoshi.framework.swagger.pojo.SwaggerConfig;
import com.xiaoshi.framework.swagger.util.SwaggerFrameworkUtils;
import com.xiaoshi.framework.web.core.pojo.WebPathPrefix;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.Parameter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Swagger 自动配置类
 *
 * @author xiaoshi
 * @since 2024/11/17 上午12:06
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass({OpenAPI.class})
@EnableConfigurationProperties({SpringdocInfoProperties.class})
// 配置了 swagger.enabled.enable=true 或者无配置的情况下才生效
@ConditionalOnProperty(prefix = "springdoc.api-docs", name = "enabled", havingValue = "true", matchIfMissing = true)
// 设置为 false 时，禁用
public class SwaggerAutoConfiguration {

    @Resource
    private WebPathPrefix webPathPrefix;

    @Resource
    private SpringdocInfoProperties springDocInfoProperties;

    @Bean
    public OpenAPI createApi() {
        return new OpenAPI().info(buildInfo(springDocInfoProperties));
    }

    /**
     * SpringDoc 明细信息
     */
    private Info buildInfo(SpringdocInfoProperties springDocInfoProperties) {
        return new Info()
                .title(springDocInfoProperties.getTitle())
                .description(springDocInfoProperties.getDescription())
                .version(springDocInfoProperties.getVersion())
                .termsOfService(springDocInfoProperties.getTermsOfService())
                .contact(new Contact()
                        .name(springDocInfoProperties.getContact().getName())
                        .url(springDocInfoProperties.getContact().getUrl())
                        .email(springDocInfoProperties.getContact().getEmail()))
                .license(new License()
                        .name(springDocInfoProperties.getLicense().getName())
                        .url(springDocInfoProperties.getLicense().getUrl()));
    }

    /**
     * 所有模块的 API 分组
     */
    @Bean
    public GroupedOpenApi allGroupedOpenApi(ApplicationContext applicationContext) {
        SwaggerConfig swaggerConfig = getSwaggerConfig(applicationContext);
        final String customAdminPrefix = getAdminPrefix(swaggerConfig);
        final List<Parameter> customAdminParamList = getAdminParameter(swaggerConfig);
        final String customClientPrefix = getClientPrefix(swaggerConfig);
        final List<Parameter> customClientParamList = getClientParameter(swaggerConfig);
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(openApi -> {
                    Paths paths = new Paths();
                    openApi.getPaths().forEach((path, pathItem) -> {
                        final String finalPath = path;
                        pathItem.readOperations().forEach(operation -> {
                            List<Parameter> parameters = operation.getParameters();
                            if (parameters == null) {
                                parameters = new ArrayList<>();
                            }
                            if (CheckUtils.notEmpty(customAdminParamList)
                                    && finalPath.startsWith(webPathPrefix.getAdminApi())) {
                                parameters.addAll(customAdminParamList);
                            }
                            if (CheckUtils.notEmpty(customClientParamList)
                                    && finalPath.startsWith(webPathPrefix.getClientApi())) {
                                parameters.addAll(customClientParamList);
                            }
                            parameters.sort((v1, v2) ->
                                    Integer.compare(getParameterPriority(v2), getParameterPriority(v1)));
                            operation.setParameters(parameters);
                        });
                        if (CheckUtils.notEmpty(customAdminPrefix)
                                && finalPath.startsWith(webPathPrefix.getAdminApi())) {
                            path = customAdminPrefix + finalPath;
                        }
                        if (CheckUtils.notEmpty(customClientPrefix)
                                && finalPath.startsWith(webPathPrefix.getClientApi())) {
                            path = customClientPrefix + finalPath;
                        }
                        paths.addPathItem(path, pathItem);
                    });
                    openApi.setPaths(paths);
                }).build();
    }

    /**
     * 管理后台模块的 API 分组
     */
    @Bean
    public GroupedOpenApi adminGroupedOpenApi(ApplicationContext applicationContext) {
        SwaggerConfig swaggerConfig = getSwaggerConfig(applicationContext);
        final String customPrefix = getAdminPrefix(swaggerConfig);
        final List<Parameter> customParamList = getAdminParameter(swaggerConfig);
        return GroupedOpenApi.builder()
                .group(webPathPrefix.getAdminApi().substring(1))
                .pathsToMatch(webPathPrefix.getAdminApi() + "/**")
                .addOpenApiCustomizer(openApi -> openApiCustomizer(openApi, customPrefix, customParamList))
                .build();
    }

    /**
     * 客户端接口模块的 API 分组
     */
    @Bean
    public GroupedOpenApi clientGroupedOpenApi(ApplicationContext applicationContext) {
        SwaggerConfig swaggerConfig = getSwaggerConfig(applicationContext);
        final String customPrefix = getClientPrefix(swaggerConfig);
        final List<Parameter> customParamList = getClientParameter(swaggerConfig);
        return GroupedOpenApi.builder()
                .group(webPathPrefix.getClientApi().substring(1))
                .pathsToMatch(webPathPrefix.getClientApi() + "/**")
                .addOpenApiCustomizer(openApi -> openApiCustomizer(openApi, customPrefix, customParamList))
                .build();
    }

    private void openApiCustomizer(OpenAPI openApi, final String customPrefix, final List<Parameter> customParamList) {
        Paths paths = new Paths();
        openApi.getPaths().forEach((path, pathItem) -> {
            pathItem.readOperations().forEach(operation -> {
                List<Parameter> parameters = operation.getParameters();
                if (parameters == null) {
                    parameters = new ArrayList<>();
                }
                if (CheckUtils.notEmpty(customParamList)) {
                    parameters.addAll(customParamList);
                }
                parameters.sort((v1, v2) ->
                        Integer.compare(getParameterPriority(v2), getParameterPriority(v1)));
                operation.setParameters(parameters);
            });
            if (CheckUtils.notEmpty(customPrefix)) {
                path = customPrefix + path;
            }
            paths.addPathItem(path, pathItem);
        });
        openApi.setPaths(paths);
    }

    private SwaggerConfig getSwaggerConfig(ApplicationContext applicationContext) {
        Map<String, SwaggerConfig> configMap = applicationContext.getBeansOfType(SwaggerConfig.class);
        if (configMap.isEmpty()) {
            return null;
        }
        if (configMap.size() == 1) {
            return configMap.values().iterator().next();
        }
        return configMap.getOrDefault("swaggerConfig", configMap.values().iterator().next());
    }

    private String getAdminPrefix(SwaggerConfig swaggerConfig) {
        if (swaggerConfig == null || swaggerConfig.getAdminConfig() == null) {
            return null;
        }
        return swaggerConfig.getAdminConfig().getUrlPrefix();
    }

    private String getClientPrefix(SwaggerConfig swaggerConfig) {
        if (swaggerConfig == null || swaggerConfig.getClientConfig() == null) {
            return null;
        }
        return swaggerConfig.getClientConfig().getUrlPrefix();
    }

    private List<Parameter> getAdminParameter(SwaggerConfig swaggerConfig) {
        if (swaggerConfig == null || swaggerConfig.getAdminConfig() == null) {
            return getAdminParameterDefault();
        }
        return swaggerConfig.getAdminConfig().getParamList();
    }

    private List<Parameter> getClientParameter(SwaggerConfig swaggerConfig) {
        if (swaggerConfig == null || swaggerConfig.getClientConfig() == null) {
            return getClientParameterDefault();
        }
        return swaggerConfig.getClientConfig().getParamList();
    }

    private List<Parameter> getAdminParameterDefault() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(SwaggerFrameworkUtils.buildTraceIdHeaderParameter());
        parameterList.add(SwaggerFrameworkUtils.buildAdminAuthorizationHeaderParameter());
        return parameterList;
    }

    private List<Parameter> getClientParameterDefault() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(SwaggerFrameworkUtils.buildTraceIdHeaderParameter());
        parameterList.add(SwaggerFrameworkUtils.buildClientLanguagHeaderParameter());
        parameterList.add(SwaggerFrameworkUtils.buildClientDeviceIdHeaderParameter());
        parameterList.add(SwaggerFrameworkUtils.buildClientAppIdHeaderParameter());
        parameterList.add(SwaggerFrameworkUtils.buildClientAppVerHeaderParameter());
        parameterList.add(SwaggerFrameworkUtils.buildClientAuthTokenHeaderParameter());
        parameterList.add(SwaggerFrameworkUtils.buildClientUserTokenHeaderParameter());
        return parameterList;
    }

    private int getParameterPriority(Parameter parameter) {
        int required = (CheckUtils.notNull(parameter.getRequired()) && parameter.getRequired() ? 1 : 0);
        if (CheckUtils.isEqualsIgnoreCase("header", parameter.getIn())) {
            return 40 + required;
        } else if (CheckUtils.isEqualsIgnoreCase("cookie", parameter.getIn())) {
            return 30 + required;
        } else if (CheckUtils.isEqualsIgnoreCase("query", parameter.getIn())) {
            return 20 + required;
        } else if (CheckUtils.isEqualsIgnoreCase("body", parameter.getIn())) {
            return 10 + required;
        } else {
            return required;
        }
    }

}

