package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class goodsOrder implements Serializable {

    /**
     * 订单ID
     */
    private  Integer  id;

    /**
     * 用户id
     */
    private  Integer  userId;

    /**
     * 商品ID
     */
    private Integer  goodsId;

    /**
     * 订单编号
     */
    private  String  goodsNumber;

    /**
     * 订单创建时间
     */
    private  Date goodsGetTime;

    /**
     * 支付状态
     */
    private Integer  goodsPaymentState;

    /**
     * 支付时间
     */
    private Date goodsPaymentTime;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单消息修改时间
     */
    private Date updateTime;
}
