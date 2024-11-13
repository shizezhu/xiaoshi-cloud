package com.xiaoshi.framework.common.exception.pojo;

import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArg8Builder;

import java.io.Serial;

/**
 * 错误码对象，带8个占位符
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
public class ErrorCodeMsgArg8 extends ErrorCode {

    @Serial
    private static final long serialVersionUID = 1L;

    public ErrorCodeMsgArg8(ErrorCodeMsgArg8Builder builder) {
        super(builder);
    }

    public ErrorCode formatMsg(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) {
        setArgs(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        return this;
    }
}
