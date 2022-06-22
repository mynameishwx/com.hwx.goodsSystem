package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
     * 商铺id
     */
    @NonNull
    private Integer shopId;
    /**
     * 商品名
     */
    @NonNull
    private String goodsName;

    /**
     * 商品介绍
     */
    private String goodsSuggest;
    /**
     * 商品图片地址
     */
    private  String goodsImageUrl;

    /**
     * 商品价格
     */
    @NonNull
    private Integer goodsMoney;

    /**
     * 商品关键字
     */
    private String  keyword;

    /**
     * 扩展字段一
     */
    private String extend_One;

    /**
     * 扩展字段二
     */
    private String extend_Two;

    /**
     * 商品上架时间
     */
    private Date createTime;

    /**
     * 商品信息修改时间
     */
    private Date updateTime;

}
