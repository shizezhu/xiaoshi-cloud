package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCodeMsgArg4;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeMsgArg4Builder extends ErrorCodeMsgArgBuilder {

    public ErrorCodeMsgArg4Builder(int code, String msg) {
        super(code, msg);
    }

    public ErrorCodeMsgArg4Builder(int code, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        super(code, msg, arg1, arg2, arg3, arg4);
    }

    public ErrorCodeMsgArg4 build() {
        return new ErrorCodeMsgArg4(this);
    }
}
