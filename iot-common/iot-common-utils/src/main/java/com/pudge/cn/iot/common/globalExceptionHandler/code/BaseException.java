package com.pudge.cn.iot.common.globalExceptionHandler.code;

/**
 * 异常接口基类
 */
public interface BaseException {
    /**
     * 统一参数验证异常码
     */
    int BASE_VALID_PARAM = -9;
    /**
     * 异常编码
     *
     * @return
     */
    int getCode();

    /**
     * 异常消息
     * @return
     */
    String getMessage();
}
