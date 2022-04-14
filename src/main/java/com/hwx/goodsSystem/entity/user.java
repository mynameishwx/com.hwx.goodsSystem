package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user implements Serializable {

    /**
     * 用户id
     */
    private  Integer  id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户性别 0为男,1为女
     */
    private Integer sex;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 用户昵称
     */
    private String petName;

    /**
     * 用户头像地址
     */
    private String imageUrl;

    /**
     * 用户地址
     */
    private String dwell;

    /**
     * 用户个人签名
     */
    private String signature;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户消息修改时间
     */
    private Date updateTime;
}
