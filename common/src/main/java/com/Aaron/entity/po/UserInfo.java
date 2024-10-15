package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("user_info")
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别 0:女  1:男  2:机器人
     */
    private Boolean sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 个人介绍
     */
    private String personIntroduction;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 状态 0:用户异常  1:正常
     */
    private Boolean status;

    /**
     * 经验值
     */
    private Integer experience;

    private String role;

    /**
     * 主题 1:亮色主题  2:暗色主题
     */
    private Boolean threme;



    @Override
    public String toString() {
        return "UserInfo{" +
            "userId = " + userId +
            ", nickName = " + nickName +
            ", userPassword = " + userPassword +
            ", email = " + email +
            ", sex = " + sex +
            ", birthday = " + birthday +
            ", personIntroduction = " + personIntroduction +
            ", avatar = " + avatar +
            ", createTime = " + createTime +
            ", lastLoginTime = " + lastLoginTime +
            ", lastLoginIp = " + lastLoginIp +
            ", status = " + status +
            ", experience = " + experience +
            ", threme = " + threme +
            "role = " + role +
        "}";
    }
}
