package com.xiaoshi.framework.common.exception.pojo;

import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArg1Builder;

import java.io.Serial;

/**
 * 错误码对象，带1个占位符
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
public class ErrorCodeMsgArg1 extends ErrorCode {

    @Serial
    private static final long serialVersionUID = 1L;

    public ErrorCodeMsgArg1(ErrorCodeMsgArg1Builder builder) {
        super(builder);
    }

    public ErrorCode formatMsg(Object arg1) {
        setArgs(arg1);
        return this;
    }
}
