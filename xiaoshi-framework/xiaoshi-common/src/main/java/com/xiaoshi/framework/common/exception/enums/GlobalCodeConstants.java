package com.xiaoshi.framework.common.exception.enums;

/**
 * 全局错误码
 * 0-999 系统异常编码保留
 *
 * @author xiaoshi
 * @since 2024/11/13 下午11:27
 */
public interface GlobalCodeConstants {

    /**
     * 成功
     */
    int CODE_SUCCESS = 200;
    /**
     * 参数不正确
     */
    int CODE_PARAM_INCORRECT = 400;
    /**
     * 账号未登录
     */
    int CODE_ACCOUNT_NOT_LOGGED = 401;
    /**
     * 账号已禁用
     */
    int CODE_ACCOUNT_DISABLED = 402;
    /**
     * 操作禁止
     */
    int CODE_OPERATE_FORBIDDEN = 403;
    /**
     * 请求未找到
     */
    int CODE_REQUEST_NOT_FOUND = 404;
    /**
     * 请求方法不正确
     */
    int CODE_REQUEST_METHOD_INCORRECT = 405;
    /**
     * 需要确认
     */
    int CODE_OPERATION_CONFIRM = 406;
    /**
     * 校验不通过
     */
    int CODE_VERIFY_FAILED = 407;
    /**
     * 数据不正确
     */
    int CODE_DATA_INCORRECT = 410;
    /**
     * 并发锁
     */
    int CODE_CONCURRENT_LOCKED = 423;
    /**
     * 请求超时
     */
    int CODE_REQUEST_TIMEOUT = 408;
    /**
     * 重复请求
     */
    int CODE_REQUEST_REPEATED = 424;
    /**
     * 请求太频繁
     */
    int CODE_REQUEST_FREQUENT = 429;

    /**
     * 请求失败
     */
    int CODE_REQUEST_FAILED = 499;

    // 系统异常相关

    /**
     * 系统错误
     */
    int CODE_SERVER_ERROR = 500;
    /**
     * 系统配置错误
     */
    int CODE_SERVER_CONFIG_ERROR = 501;
    /**
     * 远程系统错误
     */
    int CODE_REMOTE_SERVER_ERROR = 502;

}
