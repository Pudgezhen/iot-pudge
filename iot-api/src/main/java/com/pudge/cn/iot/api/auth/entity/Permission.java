package com.pudge.cn.iot.api.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author pudge
 * @since 2023-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名字
     */
    @TableField("source_name")
    private String sourceName;

    /**
     * 权限访问地址，支持通配符
     */
    @TableField("url")
    private String url;

    /**
     * 匹配方式：0 完全匹配   1 通配符匹配
     */
    @TableField("url_match")
    private Integer urlMatch;

    /**
     * 服务名字
     */
    @TableField("service_name")
    private String serviceName;

    /**
     * GET/POST/PUT/OPTIONS/DELETE/*
     */
    @TableField("method")
    private String method;


}
