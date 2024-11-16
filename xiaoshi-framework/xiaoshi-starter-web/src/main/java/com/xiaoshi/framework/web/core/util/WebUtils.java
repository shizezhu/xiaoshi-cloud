package com.xiaoshi.framework.web.core.util;

import com.xiaoshi.framework.common.utils.json.JsonUtils;
import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.dromara.hutool.core.io.IoUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.http.server.servlet.ServletUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * web工具类
 *
 * @author xiaoshi
 * @since 2024/11/14 下午10:34
 */
@UtilityClass
public class WebUtils extends ServletUtil {

    private PathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * 获取 RequestAttributes
     *
     * @return RequestAttributes
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return HttpServletRequest
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取 attribute
     *
     * @param name  名称
     * @param clazz 类型
     * @return T 泛型
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public <T> T getAttribute(String name, Class<T> clazz) {
        return getAttribute(getRequest(), name, clazz);
    }

    /**
     * 获取 attribute
     *
     * @param request 请求
     * @param name    名称
     * @param clazz   类型
     * @return T 泛型
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public <T> T getAttribute(HttpServletRequest request, String name, Class<T> clazz) {
        if (request == null) {
            return null;
        }
        Object object = request.getAttribute(name);
        if (!clazz.isInstance(object)) {
            return null;
        }
        return (T) object;
    }

    /**
     * 获取请求路径
     *
     * @return URI 请求路径
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getRequestURI() {
        return getRequestURI(getRequest());
    }

    /**
     * 获取请求路径
     *
     * @param request 请求
     * @return URI 请求路径
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getRequestURI(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        return request.getRequestURI();
    }

    /**
     * 获取所有请求头信息
     *
     * @return 请求头Map
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public Map<String, String> getHeaderMap() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return new HashMap<>();
        }
        return getHeaderMap(request);
    }

    /**
     * 获取请求头的值
     *
     * @param name 名称
     * @return 请求头值
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getHeader(String name) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return getHeader(request, name, Charset.defaultCharset());
    }

    /**
     * 读取cookie对象
     *
     * @param name 名称
     * @return cookie对象
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public Cookie getCookie(String name) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return getCookie(request, name);
    }

    /**
     * 读取cookie值
     *
     * @param name 名称
     * @return cookie值
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    /**
     * 读取cookie值
     *
     * @param request 请求
     * @param name    名称
     * @return cookie值
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    /**
     * 返回JSON字符串
     *
     * @param response 响应
     * @param object   对象
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public void writeJSON(HttpServletResponse response, Object object) {
        String content = JsonUtils.toJsonString(object);
        write(response, content, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    /**
     * 返回附件
     *
     * @param response 响应
     * @param filename 文件名
     * @param content  附件内容
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public void writeAttachment(HttpServletResponse response, String filename, byte[] content) throws IOException {
        // 设置 header 和 contentType
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        // 输出附件
        IoUtil.write(response.getOutputStream(), false, content);
    }

    /**
     * 获取User-Agent
     *
     * @param request 请求
     * @return User-Agent
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getUserAgent(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        return CheckUtils.ifIsEmptyGet(request.getHeader(HttpHeaders.USER_AGENT), "");
    }

    /**
     * 获取User-Agent
     *
     * @return User-Agent
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getUserAgent() {
        return getUserAgent(getRequest());
    }

    /**
     * 是否JSON请求
     *
     * @return 结果
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public boolean isJsonRequest() {
        return isJsonRequest(getRequest());
    }

    /**
     * 是否JSON请求
     *
     * @return 结果
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public boolean isJsonRequest(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        return StrUtil.startWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 获取请求体数据
     *
     * @return 请求体内容
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getBody() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return getBody(request);
    }

    /**
     * 获取请求体数据
     *
     * @return 请求体字节数组
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public byte[] getBodyBytes() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return new byte[]{};
        }
        return getBodyBytes(request);
    }

    /**
     * 获取请求IP
     *
     * @return IP
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return getClientIP(request);
    }

    /**
     * 获取请求参数Map
     *
     * @return 请求参数Map
     * @author xiaoshi
     * @since 2024/11/14 下午10:34
     */
    public Map<String, String> getParamMap() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return new HashMap<>();
        }
        return getParamMap(request);
    }

    /**
     * 判断路径是否匹配
     *
     * @param path     路径
     * @param patterns 匹配模式
     * @return 结果
     * @author xiaoshi
     * @since 2024/11/15 下午6:07
     */
    public boolean pathMatch(String path, String... patterns) {
        if (CheckUtils.isEmpty(patterns)) {
            return false;
        }
        return pathMatch(path, Arrays.asList(patterns));
    }

    /**
     * 判断路径是否匹配
     *
     * @param path     路径
     * @param patterns 匹配模式
     * @return 结果
     * @author xiaoshi
     * @since 2024/11/15 下午6:07
     */
    public boolean pathMatch(String path, Collection<String> patterns) {
        if (CheckUtils.isEmpty(patterns)) {
            return false;
        }
        return patterns.stream().distinct().filter(CheckUtils::notEmpty).map(String::trim)
                .anyMatch(pattern -> PATH_MATCHER.match(pattern, path));
    }
}
