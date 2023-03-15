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
 * 行政区域县区信息表
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("areas")
public class Areas implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @TableId(value = "areaid", type = IdType.ASSIGN_ID)
    private String areaid;

    /**
     * 区域名称
     */
    @TableField("area")
    private String area;

    /**
     * 城市ID
     */
    @TableField("cityid")
    private String cityid;


}
