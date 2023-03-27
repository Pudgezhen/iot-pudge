package com.pudge.cn.iot.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mu_zhen
 * @description 记录Jwt传递的用户信息的负载因子
 * @Date 2023/3/26 12:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthPayload implements Serializable {


    private static final long serialVersionUID = 4804562123508164483L;

    private String username;

    private String roleid;

    private String ip;


}
