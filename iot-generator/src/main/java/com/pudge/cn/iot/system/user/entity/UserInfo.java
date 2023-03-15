package com.pudge.cn.iot.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableId(value = "username", type = IdType.ASSIGN_ID)
    private String username;

    /**
     * 密码，加密存储
     */
    @TableField("password")
    private String password;

    /**
     * 注册手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 注册邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 创建时间
     */
    @TableField("created")
    private Date created;

    /**
     * 修改时间
     */
    @TableField("updated")
    private Date updated;

    /**
     * 会员来源：1:PC，2：H5，3：Android，4：IOS
     */
    @TableField("source_type")
    private String sourceType;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 真实姓名
     */
    @TableField("name")
    private String name;

    /**
     * 使用状态（1正常 0非正常）
     */
    @TableField("status")
    private String status;

    /**
     * 头像地址
     */
    @TableField("head_pic")
    private String headPic;

    /**
     * QQ号码
     */
    @TableField("qq")
    private String qq;

    /**
     * 手机是否验证 （0否  1是）
     */
    @TableField("is_mobile_check")
    private String isMobileCheck;

    /**
     * 邮箱是否检测（0否  1是）
     */
    @TableField("is_email_check")
    private String isEmailCheck;

    /**
     * 性别，1男，0女
     */
    @TableField("sex")
    private String sex;

    /**
     * 会员等级
     */
    @TableField("user_level")
    private Integer userLevel;

    /**
     * 积分
     */
    @TableField("points")
    private Integer points;

    /**
     * 经验值
     */
    @TableField("experience_value")
    private Integer experienceValue;

    /**
     * 出生年月日
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 省份ID
     */
    @TableField("provinceid")
    private String provinceid;

    /**
     * 城市ID
     */
    @TableField("cityid")
    private String cityid;

    /**
     * 地区ID
     */
    @TableField("areaid")
    private String areaid;


}
