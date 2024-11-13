package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCode;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeBuilder {

    private final int code;

    private final String msg;

    public ErrorCodeBuilder(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorCode build() {
        return new ErrorCode(this);
    }
}
