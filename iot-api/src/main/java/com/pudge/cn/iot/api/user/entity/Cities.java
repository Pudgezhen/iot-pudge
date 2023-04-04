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
 * 行政区域地州市信息表
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cities")
public class Cities implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市ID
     */
    @TableId(value = "cityid", type = IdType.ASSIGN_ID)
    private String cityid;

    /**
     * 城市名称
     */
    @TableField("city")
    private String city;

    /**
     * 省份ID
     */
    @TableField("provinceid")
    private String provinceid;


}
