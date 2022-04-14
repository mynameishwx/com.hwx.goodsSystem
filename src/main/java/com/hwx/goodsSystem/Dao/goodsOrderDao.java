package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.goodsOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface goodsOrderDao {

    /**
     * 增
     */
    Integer  createGoodsOrder(goodsOrder goodsOrder);

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
