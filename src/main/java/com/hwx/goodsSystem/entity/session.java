package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class session implements Serializable {

    /**
     * id
     */
    private  Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * session
     */
    private String session;

    /**
     * 判断session存活状态
     * 默认为0,当前存在的session
     * 1:掉线状态
     * 2:异地登录状态
     */
    private Integer exist;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
