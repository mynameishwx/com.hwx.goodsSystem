package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class goods implements Serializable {

    /**
     * 商品ID
     */
    private  Integer  id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品图片地址
     */
    private  String imageUrl;

    /**
     * 商品价格
     */
    private Integer money;

    /**
     * 商品上架时间
     */
    private Date createTime;

    /**
     * 商品信息修改时间
     */
    private Date updateTime;

}
