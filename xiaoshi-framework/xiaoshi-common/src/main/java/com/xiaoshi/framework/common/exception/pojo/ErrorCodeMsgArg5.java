package com.xiaoshi.framework.common.exception.pojo;

import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArg5Builder;

import java.io.Serial;

/**
 * 错误码对象，带5个占位符
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
public class ErrorCodeMsgArg5 extends ErrorCode {

    @Serial
    private static final long serialVersionUID = 1L;

    public ErrorCodeMsgArg5(ErrorCodeMsgArg5Builder builder) {
        super(builder);
    }

    public ErrorCode formatMsg(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        setArgs(arg1, arg2, arg3, arg4, arg5);
        return this;
    }
}
