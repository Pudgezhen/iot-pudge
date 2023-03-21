package com.pudge.cn.iot.common.golbalResponse;

/**
 * @author mu_zhen
 * @description 封装API的错误码
 * @Date 2023/2/23 16:51
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
