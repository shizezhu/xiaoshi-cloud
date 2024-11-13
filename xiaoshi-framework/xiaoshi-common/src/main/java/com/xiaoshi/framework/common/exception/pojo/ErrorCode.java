package com.xiaoshi.framework.common.exception.pojo;

import com.xiaoshi.framework.common.exception.builder.ErrorCodeBuilder;
import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArgBuilder;
import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * 错误码对象
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
@Slf4j
public class ErrorCode implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @Getter
    private final int code;

    /**
     * 错误消息
     */
    @Setter
    private String msg;

    /**
     * 占位符数据
     */
    private Object[] args;

    public ErrorCode(ErrorCodeBuilder builder) {
        this.code = builder.getCode();
        this.msg = builder.getMsg();
    }

    public ErrorCode(ErrorCodeMsgArgBuilder builder) {
        this.code = builder.getCode();
        this.msg = builder.getMsg();
        this.args = builder.getArgs();
    }

    public String getMsg() {
        String msg = getOriginMsg();
        if (CheckUtils.isEmpty(msg)) {
            return msg;
        }
        Object[] args = getArgs();
        try {
            msg = getFormatMsg(msg, args);
        } catch (Exception e) {
            log.error("ErrorCode获取格式化后msg: [{}] 异常", msg, e);
        }
        return msg.trim().replaceAll("\\s+", " ");
    }

    protected String getOriginMsg() {
        return this.msg;
    }

    protected String getFormatMsg(String msg, Object[] args) {
        if (CheckUtils.isEmpty(args)) {
            return msg;
        }
        return MessageFormat.format(msg, args);
    }

    protected ErrorCode setArgs(Object... args) {
        this.args = args;
        return this;
    }

    protected Object[] getArgs() {
        return this.args;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", msg='" + msg +
                '}';
    }
}
