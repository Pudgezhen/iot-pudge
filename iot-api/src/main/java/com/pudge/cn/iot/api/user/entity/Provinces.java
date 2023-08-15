package com.pudge.cn.iot.api.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 省份信息表
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("provinces")
public class Provinces implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 省份ID
     */
    @TableId(value = "province_id", type = IdType.ASSIGN_ID)
    private String provinceId;

    /**
     * 省份名称
     */
    @TableField("province")
    private String province;


}
