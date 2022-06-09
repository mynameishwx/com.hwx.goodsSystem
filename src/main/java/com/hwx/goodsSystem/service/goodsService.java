package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.goods;

import java.util.List;

public interface goodsService {
    /**
     * 增
     */
    Integer  createGoods(goods goods);

    /**
     * 删
     */
    Integer deleteGoods(Integer id);

    /**
     * 改
     */
    Integer updateGoods(goods goods);

    /**
     * 查
     */
    goods getGoodsById(Integer id);

    /**
     * 根据商品名称查询
     */
    List<goods> getGoodsByName(String goodsName);

    /**
     * 根据商铺id查询
     */
    List<goods> getGoodsShopId(Integer shopId);


    /**
     * LiMit获取
     * @param start
     * @param qty
     * @return
     */
    List<goods> getGoodsLiMit(Integer start, Integer qty,Integer shopId);


    /**
     * 获取所有记录数
     * @return
     */
    Integer getGoods();
}
