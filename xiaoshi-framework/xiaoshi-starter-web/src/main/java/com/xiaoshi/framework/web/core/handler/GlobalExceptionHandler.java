package com.xiaoshi.framework.web.core.handler;

import com.xiaoshi.framework.common.exception.ServerException;
import com.xiaoshi.framework.common.exception.ServiceException;
import com.xiaoshi.framework.common.exception.builder.ErrorCodeBuilder;
import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArg1Builder;
import com.xiaoshi.framework.common.exception.enums.GlobalCodeConstants;
import com.xiaoshi.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.xiaoshi.framework.common.exception.pojo.ErrorCode;
import com.xiaoshi.framework.common.pojo.Result;
import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashSet;

/**
 * 全局异常处理器
 *
 * @author xiaoshi
 * @since 2024/11/15 上午11:45
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一异常处理方法
     */
    public Result<?> handle(Throwable ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionHandler((MethodArgumentNotValidException) ex);
        }
        if (ex instanceof NoHandlerFoundException) {
            return noHandlerFoundExceptionHandler((NoHandlerFoundException) ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException) ex);
        }
        if (ex instanceof NoResourceFoundException) {
            return noResourceFoundExceptionHandler((NoResourceFoundException) ex);
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return httpMessageNotReadableExceptionHandler((HttpMessageNotReadableException) ex);
        }
        if (ex instanceof BindException) {
            return bindExceptionHandler((BindException) ex);
        }
        if (ex instanceof ConstraintViolationException) {
            return constraintViolationExceptionHandler((ConstraintViolationException) ex);
        }
        if (ex instanceof ServerException) {
            return serverExceptionHandler((ServerException) ex);
        }
        if (ex instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException) ex);
        }
        if (ex.getClass().getName().startsWith("io.github.resilience4j.")) {
            return resilience4jExceptionHandler(ex);
        }
        return defaultExceptionHandler(ex);
    }

    /**
     * 默认异常处理
     */
    public Result<?> defaultExceptionHandler(Throwable ex) {
        log.error("[defaultExceptionHandler]", ex);
        return Result.of(GlobalErrorCodeConstants.SERVER_ERROR.build());
    }

    /**
     * resilience4j异常处理
     */
    public Result<?> resilience4jExceptionHandler(Throwable ex) {
        log.warn("[resilience4jExceptionHandler]", ex);
        String exClassName = ex.getClass().getName();
        if (exClassName.contains(".ratelimiter.")) {
            return Result.of(GlobalErrorCodeConstants.REQUEST_FREQUENT.build());
        }
        if (exClassName.contains(".timelimiter.")) {
            return Result.of(GlobalErrorCodeConstants.REQUEST_TIMEOUT.build());
        }
        return Result.of(GlobalErrorCodeConstants.REQUEST_FAILED.build());
    }

    /**
     * 处理 SpringMVC 请求参数缺失
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build().formatMsg(ex.getParameterName()));
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.warn("[methodArgumentTypeMismatchExceptionHandler]", ex);
        return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build().formatMsg(ex.getName()));
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.warn("[methodArgumentNotValidExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError == null) {
            return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build());
        }
        String code = fieldError.getCode();
        String msg = fieldError.getDefaultMessage();
        if (CheckUtils.isEquals("typeMismatch", code) || CheckUtils.isEmpty(msg)) {
            return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build().formatMsg(fieldError.getField()));
        }
        if (msg.contains("{0}")) {
            return Result.of(new ErrorCodeMsgArg1Builder(GlobalCodeConstants.CODE_PARAM_INCORRECT, fieldError.getDefaultMessage(), fieldError.getField()).build());
        }
        return Result.of(new ErrorCodeBuilder(GlobalCodeConstants.CODE_PARAM_INCORRECT, fieldError.getDefaultMessage()).build());
    }

    /**
     * 处理 SpringMVC 参数绑定不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(BindException ex) {
        log.warn("[bindExceptionHandler]", ex);
        FieldError fieldError = ex.getFieldError();
        if (fieldError == null) {
            return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build());
        }
        String code = fieldError.getCode();
        String msg = fieldError.getDefaultMessage();
        if (CheckUtils.isEquals("typeMismatch", code) || CheckUtils.isEmpty(msg)) {
            return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build().formatMsg(fieldError.getField()));
        }
        if (msg.contains("{0}")) {
            return Result.of(new ErrorCodeMsgArg1Builder(GlobalCodeConstants.CODE_PARAM_INCORRECT, fieldError.getDefaultMessage(), fieldError.getField()).build());
        }
        return Result.of(new ErrorCodeBuilder(GlobalCodeConstants.CODE_PARAM_INCORRECT, fieldError.getDefaultMessage()).build());
    }

    /**
     * 处理 Validator 校验不通过异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().stream().filter(CheckUtils::notNull).findFirst().orElse(null);
        if (CheckUtils.isNull(constraintViolation)) {
            return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build());
        }
        String field = CheckUtils.ifIsEmptyGetNull(constraintViolation.getPropertyPath().toString());
        String msg = CheckUtils.ifIsEmptyGet(constraintViolation.getMessage(), constraintViolation.getMessageTemplate());
        if (CheckUtils.isEmpty(msg)) {
            return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build().formatMsg(field));
        }
        if (CheckUtils.isStartsWith(msg, "{") && CheckUtils.isEndsWith(msg, "}")) {
            return Result.of(new ErrorCodeMsgArg1Builder(GlobalCodeConstants.CODE_PARAM_INCORRECT, msg.substring(1, msg.length() - 1), field).build());
        }
        if (msg.contains("{0}")) {
            return Result.of(new ErrorCodeMsgArg1Builder(GlobalCodeConstants.CODE_PARAM_INCORRECT, msg, field).build());
        }
        return Result.of(new ErrorCodeBuilder(GlobalCodeConstants.CODE_PARAM_INCORRECT, msg).build());
    }

    /**
     * 处理 SpringMVC 请求地址不存在
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.warn("[noHandlerFoundExceptionHandler]", ex);
        return Result.of(GlobalErrorCodeConstants.REQUEST_NOT_FOUND.build().formatMsg(ex.getRequestURL()));
    }

    /**
     * 处理 SpringMVC 请求资源不正确
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public Result<?> noResourceFoundExceptionHandler(NoResourceFoundException ex) {
        log.warn("[noResourceFoundExceptionHandler]", ex);
        return Result.of(GlobalErrorCodeConstants.REQUEST_NOT_FOUND.build().formatMsg(ex.getResourcePath()));
    }

    /**
     * 处理 SpringMVC 请求body参数不正确
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        log.warn("[httpMessageNotReadableExceptionHandler]", ex);
        return Result.of(GlobalErrorCodeConstants.PARAM_INCORRECT.build());
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        String method = ex.getMethod();
        String supportedMethods = CheckUtils.ifIsNullGet(ex.getSupportedHttpMethods(), new HashSet<>()).toString();
        return Result.of(GlobalErrorCodeConstants.REQUEST_METHOD_INCORRECT.build().formatMsg(method, supportedMethods));
    }

    /**
     * 处理系统异常 ServerException
     */
    @ExceptionHandler(value = ServerException.class)
    public Result<?> serverExceptionHandler(ServerException ex) {
        log.error("[serverExceptionHandler]", ex);
        return Result.of(ex.getErrorCode());
    }

    /**
     * 处理业务异常 ServiceException
     */
    @ExceptionHandler(value = ServiceException.class)
    public Result<?> serviceExceptionHandler(ServiceException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        int code = errorCode.getCode();
        String msg = errorCode.getMsg();
        log.info("[serviceExceptionHandler] code: {}, msg: {}", code, msg);
        return Result.of(new ErrorCodeBuilder(code, msg).build());
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Throwable ex) {
        return handle(ex);
    }

}
