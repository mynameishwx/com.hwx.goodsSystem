package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.Dao.goodsOrderDao;
import com.hwx.goodsSystem.entity.commonResult;
import com.hwx.goodsSystem.entity.goodsOrder;

public interface goodsOrderService {
    /**
     * 增
     */
    commonResult<goodsOrder> createGoodsOrder(goodsOrder goodsOrder);

    /**
     * 删
     */
    commonResult<Integer> deleteGoodsOrder(Integer id);

    /**
     * 改
     */
    commonResult<goodsOrder>  updateGoodsOrder(goodsOrder goodsOrder);

    /**
     * 查
     */
    commonResult<goodsOrder> getGoodsOrder(Integer id);
}
