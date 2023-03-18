package com.pudge.cn.iot.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "globalIdGenerate", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 省
     */
    @TableField("provinceid")
    private String provinceid;

    /**
     * 市
     */
    @TableField("cityid")
    private String cityid;

    /**
     * 县/区
     */
    @TableField("areaid")
    private String areaid;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 是否是默认 1默认 0否
     */
    @TableField("is_default")
    private Integer isDefault;


}
