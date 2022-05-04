package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface goodsDao {

    /**
     * 增
     */
    Integer  createGoods(goods goods);

    /**
     * 删
     */
    Integer deleteGoods(Integer  id);

    /**
     * 改
     */
    goods updateGoods(goods goods);

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
    List<goods> getGoodsLiMit(@Param("start") Integer start,
                              @Param("qty") Integer qty,
                              @Param("shopId")Integer shopId);
}
