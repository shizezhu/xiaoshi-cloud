package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCodeMsgArg8;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeMsgArg8Builder extends ErrorCodeMsgArgBuilder {

    public ErrorCodeMsgArg8Builder(int code, String msg) {
        super(code, msg);
    }

    public ErrorCodeMsgArg8Builder(int code, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) {
        super(code, msg, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    public ErrorCodeMsgArg8 build() {
        return new ErrorCodeMsgArg8(this);
    }
}
