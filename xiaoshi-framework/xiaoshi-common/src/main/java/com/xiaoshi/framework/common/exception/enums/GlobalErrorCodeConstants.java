package com.xiaoshi.framework.common.exception.enums;

import com.xiaoshi.framework.common.exception.builder.ErrorCodeBuilder;
import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArg1Builder;
import com.xiaoshi.framework.common.exception.builder.ErrorCodeMsgArg2Builder;

/**
 * 全局错误
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
public interface GlobalErrorCodeConstants extends GlobalCodeConstants {

    /**
     * 成功
     */
    ErrorCodeBuilder SUCCESS = new ErrorCodeBuilder(CODE_SUCCESS, "成功");
    /**
     * 参数不正确
     */
    ErrorCodeMsgArg1Builder PARAM_INCORRECT = new ErrorCodeMsgArg1Builder(CODE_PARAM_INCORRECT, "{0}不正确", "参数");
    /**
     * 参数必须大于
     */
    ErrorCodeMsgArg2Builder PARAM_GREATER = new ErrorCodeMsgArg2Builder(CODE_PARAM_INCORRECT, "{0}必须大于{1}", "参数", "?");
    /**
     * 参数必须小于
     */
    ErrorCodeMsgArg2Builder PARAM_LESS = new ErrorCodeMsgArg2Builder(CODE_PARAM_INCORRECT, "{0}必须小于{1}", "参数", "?");
    /**
     * 参数必须大于或等于
     */
    ErrorCodeMsgArg2Builder PARAM_GREATER_EQUAL = new ErrorCodeMsgArg2Builder(CODE_PARAM_INCORRECT, "{0}必须大于或等于{1}", "参数", "?");
    /**
     * 参数必须小于或等于
     */
    ErrorCodeMsgArg2Builder PARAM_LESS_EQUAL = new ErrorCodeMsgArg2Builder(CODE_PARAM_INCORRECT, "{0}必须小于或等于{1}", "参数", "?");
    /**
     * 参数A和参数B必须有一个
     */
    ErrorCodeMsgArg2Builder PARAM_HAVE_ONE = new ErrorCodeMsgArg2Builder(CODE_PARAM_INCORRECT, "{0}和{1}必须有一个", "参数1", "参数2");
    /**
     * 参数已存在
     */
    ErrorCodeMsgArg1Builder PARAM_EXIST = new ErrorCodeMsgArg1Builder(CODE_PARAM_INCORRECT, "{0}已存在", "参数");
    /**
     * 参数不存在
     */
    ErrorCodeMsgArg1Builder PARAM_NOT_EXIST = new ErrorCodeMsgArg1Builder(CODE_PARAM_INCORRECT, "{0}不存在", "参数");
    /**
     * 参数已被禁用
     */
    ErrorCodeMsgArg1Builder PARAM_DISABLED = new ErrorCodeMsgArg1Builder(CODE_PARAM_INCORRECT, "{0}已被禁用", "参数");
    /**
     * 参数已过期
     */
    ErrorCodeMsgArg1Builder PARAM_EXPIRED = new ErrorCodeMsgArg1Builder(CODE_PARAM_INCORRECT, "{0}已过期", "参数");
    /**
     * 参数配置错误
     */
    ErrorCodeMsgArg1Builder CONFIG_PARAM_INCORRECT = new ErrorCodeMsgArg1Builder(CODE_PARAM_INCORRECT, "{0}配置不正确", "参数");
    /**
     * 账号未登录
     */
    ErrorCodeBuilder ACCOUNT_NOT_LOGGED = new ErrorCodeBuilder(CODE_ACCOUNT_NOT_LOGGED, "账号未登录");
    /**
     * 账号已禁用
     */
    ErrorCodeBuilder ACCOUNT_DISABLED = new ErrorCodeBuilder(CODE_ACCOUNT_DISABLED, "账号已禁用");
    /**
     * 没有操作权限
     */
    ErrorCodeBuilder OPERATE_FORBIDDEN = new ErrorCodeBuilder(CODE_OPERATE_FORBIDDEN, "没有操作权限");
    /**
     * 请求未找到
     */
    ErrorCodeMsgArg1Builder REQUEST_NOT_FOUND = new ErrorCodeMsgArg1Builder(CODE_REQUEST_NOT_FOUND, "没有找到请求路径 {0}");
    /**
     * 请求方法不正确
     */
    ErrorCodeMsgArg2Builder REQUEST_METHOD_INCORRECT = new ErrorCodeMsgArg2Builder(CODE_REQUEST_METHOD_INCORRECT, "不支持请求方法 {0}，支持的方法有 {1}");
    /**
     * 此操作需要确认
     */
    ErrorCodeBuilder OPERATION_CONFIRM = new ErrorCodeBuilder(CODE_OPERATION_CONFIRM, "此操作需要确认");
    /**
     * 校验未通过
     */
    ErrorCodeMsgArg1Builder VERIFY_FAILED = new ErrorCodeMsgArg1Builder(CODE_VERIFY_FAILED, "{0}校验未通过", "安全");
    /**
     * 数据不正确
     */
    ErrorCodeMsgArg1Builder DATA_INCORRECT = new ErrorCodeMsgArg1Builder(CODE_DATA_INCORRECT, "{0}不正确", "数据");
    /**
     * 数据已存在
     */
    ErrorCodeMsgArg1Builder DATA_EXIST = new ErrorCodeMsgArg1Builder(CODE_DATA_INCORRECT, "{0}已存在", "数据");
    /**
     * 数据不存在
     */
    ErrorCodeMsgArg1Builder DATA_NOT_EXIST = new ErrorCodeMsgArg1Builder(CODE_DATA_INCORRECT, "{0}不存在", "数据");
    /**
     * 数据已禁用
     */
    ErrorCodeMsgArg1Builder DATA_DISABLED = new ErrorCodeMsgArg1Builder(CODE_DATA_INCORRECT, "{0}已被禁用", "数据");
    /**
     * 请求失败，请稍后重试(不允许并发请求)
     */
    ErrorCodeBuilder CONCURRENT_LOCKED = new ErrorCodeBuilder(CODE_CONCURRENT_LOCKED, "请求失败，请稍候重试");
    /**
     * 请求超时，请稍后重试
     */
    ErrorCodeBuilder REQUEST_TIMEOUT = new ErrorCodeBuilder(CODE_REQUEST_TIMEOUT, "请求超时，请稍候再试");
    /**
     * 重复请求，请稍后重试
     */
    ErrorCodeBuilder REQUEST_REPEATED = new ErrorCodeBuilder(CODE_REQUEST_REPEATED, "重复请求，请稍候再试");
    /**
     * 请求太频繁，请稍后重试
     */
    ErrorCodeBuilder REQUEST_FREQUENT = new ErrorCodeBuilder(CODE_REQUEST_FREQUENT, "请求太频繁，请稍后重试");
    /**
     * 请求失败，请稍后重试
     */
    ErrorCodeBuilder REQUEST_FAILED = new ErrorCodeBuilder(CODE_REQUEST_FAILED, "请求失败，请稍候重试");

    // 系统异常相关
    /**
     * 系统异常
     */
    ErrorCodeBuilder SERVER_ERROR = new ErrorCodeBuilder(CODE_SERVER_ERROR, "系统异常");
    /**
     * 系统配置错误
     */
    ErrorCodeBuilder SERVER_CONFIG_ERROR = new ErrorCodeBuilder(CODE_SERVER_CONFIG_ERROR, "系统配置错误");
    /**
     * 远程系统异常
     */
    ErrorCodeBuilder REMOTE_SERVER_ERROR = new ErrorCodeBuilder(CODE_REMOTE_SERVER_ERROR, "远程系统异常");

}
