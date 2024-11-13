package com.xiaoshi.framework.common.exception.builder;

import com.xiaoshi.framework.common.exception.pojo.ErrorCodeMsgArg1;
import lombok.Getter;

/**
 * 错误码构建器
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Getter
public class ErrorCodeMsgArg1Builder extends ErrorCodeMsgArgBuilder {

    public ErrorCodeMsgArg1Builder(int code, String msg) {
        super(code, msg);
    }

    public ErrorCodeMsgArg1Builder(int code, String msg, Object arg1) {
        super(code, msg, arg1);
    }

    public ErrorCodeMsgArg1 build() {
        return new ErrorCodeMsgArg1(this);
    }
}
