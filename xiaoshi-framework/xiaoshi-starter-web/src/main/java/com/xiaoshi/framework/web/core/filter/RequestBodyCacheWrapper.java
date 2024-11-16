package com.xiaoshi.framework.web.core.filter;

import com.xiaoshi.framework.web.core.util.WebUtils;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * RequestBody缓存
 *
 * @author xiaoshi
 * @since 2024/11/15 下午5:15
 */
public class RequestBodyCacheWrapper extends HttpServletRequestWrapper {

    /**
     * 缓存的Body数据
     */
    private final byte[] body;

    public RequestBodyCacheWrapper(HttpServletRequest request) {
        super(request);
        body = WebUtils.getBodyBytes(request);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body), StandardCharsets.UTF_8));
    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int available() {
                return body.length;
            }

        };
    }

}
