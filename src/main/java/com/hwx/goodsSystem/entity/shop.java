package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class shop implements Serializable {
    /**
     * 店铺id
     */
    private  Integer  id;

    /**
     * 店铺名称
     */
    private String  shopName;

    /**
     * 店铺类型
     */
    private Integer shopClass;

    /**
     * 店主id
     */
    private Integer shopAdmin;

    /**
     * 店铺状态
     */
    private Integer shopState;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 店铺关注度
     */
    private Integer shopConcern;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
