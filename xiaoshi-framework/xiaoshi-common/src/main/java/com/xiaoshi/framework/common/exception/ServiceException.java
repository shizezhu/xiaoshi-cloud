package com.xiaoshi.framework.common.exception;

import com.xiaoshi.framework.common.exception.pojo.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 通用业务异常
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:30
 */
@Getter
public final class ServiceException extends RuntimeException {

    private final ErrorCode errorCode;

    public ServiceException(@NotNull ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public static ServiceException of(@NotNull ErrorCode errorCode) {
        return new ServiceException(errorCode);
    }

}
