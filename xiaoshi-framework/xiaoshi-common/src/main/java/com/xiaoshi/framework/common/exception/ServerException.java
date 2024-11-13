package com.xiaoshi.framework.common.exception;

import com.xiaoshi.framework.common.exception.pojo.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 通用服务器异常
 *
 * @author xiaoshi
 * @since 2024/11/13 下午7:05
 */
@Getter
public final class ServerException extends RuntimeException {

    private final ErrorCode errorCode;

    public ServerException(@NotNull ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public ServerException(@NotNull ErrorCode errorCode, Throwable cause) {
        super(errorCode.toString(), cause);
        this.errorCode = errorCode;
    }

    public static ServerException of(@NotNull ErrorCode errorCode) {
        return new ServerException(errorCode);
    }

    public static ServerException of(@NotNull ErrorCode errorCode, Throwable cause) {
        return new ServerException(errorCode, cause);
    }

}
