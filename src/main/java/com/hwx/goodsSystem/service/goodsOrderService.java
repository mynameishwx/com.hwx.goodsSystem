package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.goodsOrder;

public interface goodsOrderService {
    /**
     * 增
     */
    Integer createGoodsOrder(goodsOrder goodsOrder);

    /**
     * 删
     */
    Integer deleteGoodsOrder(Integer id);

    /**
     * 改
     */
    Integer  updateGoodsOrder(goodsOrder goodsOrder);

    /**
     * 查
     */
    goodsOrder getGoodsOrder(Integer id);
}
