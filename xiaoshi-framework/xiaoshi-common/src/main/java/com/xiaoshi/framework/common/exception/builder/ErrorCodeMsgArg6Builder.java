package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCodeMsgArg6;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeMsgArg6Builder extends ErrorCodeMsgArgBuilder {

    public ErrorCodeMsgArg6Builder(int code, String msg) {
        super(code, msg);
    }

    public ErrorCodeMsgArg6Builder(int code, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
        super(code, msg, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    public ErrorCodeMsgArg6 build() {
        return new ErrorCodeMsgArg6(this);
    }
}
