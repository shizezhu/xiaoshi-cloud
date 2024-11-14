package com.xiaoshi.framework.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaoshi.framework.common.exception.ServiceException;
import com.xiaoshi.framework.common.exception.builder.ErrorCodeBuilder;
import com.xiaoshi.framework.common.exception.enums.GlobalCodeConstants;
import com.xiaoshi.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.xiaoshi.framework.common.exception.pojo.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通用响应
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:42
 */
@Getter
@ToString
@Schema(description = "通用响应")
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     *
     * @see ErrorCode#getCode()
     */
    @Schema(description = "状态码(200代表成功)", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    private final int code;

    /**
     * 状态消息
     *
     * @see ErrorCode#getMsg()
     */
    @Schema(description = "状态消息", requiredMode = Schema.RequiredMode.REQUIRED, example = "Success")
    private final String msg;

    /**
     * 返回数据
     */
    @Schema(description = "响应数据", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private final T data;

    public Result() {
        this(GlobalErrorCodeConstants.SUCCESS.build(), null);
    }

    public Result(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public Result(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    public static <T> Result<T> success() {
        return of(GlobalErrorCodeConstants.SUCCESS.build());
    }

    public static <T> Result<T> success(T data) {
        return of(GlobalErrorCodeConstants.SUCCESS.build(), data);
    }

    public static <T> Result<T> of(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    public static <T> Result<T> of(ErrorCode errorCode, T data) {
        return new Result<>(errorCode, data);
    }

    @Schema(description = "是否成功：true/false", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    public boolean isSuccess() {
        return GlobalCodeConstants.CODE_SUCCESS == getCode();

    }

    @Schema(description = "是否失败：true/false", requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    public boolean isError() {
        return !isSuccess();
    }

    /**
     * 如果失败，抛出 {@link ServiceException} 异常
     */
    public void checkError() throws ServiceException {
        if (isSuccess()) {
            return;
        }
        // 业务异常
        throw new ServiceException(new ErrorCodeBuilder(getCode(), getMsg()).build());
    }

    /**
     * 如果失败，抛出 {@link ServiceException} 异常
     * 如果成功，返回 {@link #data} 数据
     */
    @JsonIgnore // 避免 jackson 序列化
    public T getCheckedData() {
        checkError();
        return getData();
    }
}
