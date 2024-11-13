package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCodeMsgArg2;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeMsgArg2Builder extends ErrorCodeMsgArgBuilder {

    public ErrorCodeMsgArg2Builder(int code, String msg) {
        super(code, msg);
    }

    public ErrorCodeMsgArg2Builder(int code, String msg, Object arg1, Object arg2) {
        super(code, msg, arg1, arg2);
    }

    public ErrorCodeMsgArg2 build() {
        return new ErrorCodeMsgArg2(this);
    }
}
