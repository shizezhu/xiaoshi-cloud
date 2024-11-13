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
public class ErrorCodeMsgArgBuilder extends ErrorCodeBuilder {

    private final Object[] args;

    public ErrorCodeMsgArgBuilder(int code, String msg) {
        this(code, msg, null, null);
    }

    public ErrorCodeMsgArgBuilder(int code, String msg, Object... args) {
        super(code, msg);
        this.args = args;
    }

    public ErrorCode build() {
        return new ErrorCode(this);
    }
}
