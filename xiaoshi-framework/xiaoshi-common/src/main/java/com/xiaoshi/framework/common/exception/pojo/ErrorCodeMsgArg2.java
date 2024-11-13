package com.xiaoshi.framework.common.exception.pojo;

import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArg2Builder;

import java.io.Serial;

/**
 * 错误码对象，带2个占位符
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
public class ErrorCodeMsgArg2 extends ErrorCode {

    @Serial
    private static final long serialVersionUID = 1L;

    public ErrorCodeMsgArg2(ErrorCodeMsgArg2Builder builder) {
        super(builder);
    }

    public ErrorCode formatMsg(Object arg1, Object arg2) {
        setArgs(arg1, arg2);
        return this;
    }
}
