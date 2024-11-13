package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCodeMsgArg7;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeMsgArg7Builder extends ErrorCodeMsgArgBuilder {

    public ErrorCodeMsgArg7Builder(int code, String msg) {
        super(code, msg);
    }

    public ErrorCodeMsgArg7Builder(int code, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        super(code, msg, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    public ErrorCodeMsgArg7 build() {
        return new ErrorCodeMsgArg7(this);
    }
}
