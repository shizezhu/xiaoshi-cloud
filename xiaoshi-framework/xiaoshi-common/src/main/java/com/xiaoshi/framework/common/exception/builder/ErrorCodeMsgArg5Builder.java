package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCodeMsgArg5;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeMsgArg5Builder extends ErrorCodeMsgArgBuilder {

    public ErrorCodeMsgArg5Builder(int code, String msg) {
        super(code, msg);
    }

    public ErrorCodeMsgArg5Builder(int code, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        super(code, msg, arg1, arg2, arg3, arg4, arg5);
    }

    public ErrorCodeMsgArg5 build() {
        return new ErrorCodeMsgArg5(this);
    }
}
