package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.goodsKeyword;

import java.util.List;

public interface goodsKeywordService {

    /**
     * 增
     * @param goodsKeyword
     * @return
     */
    Integer addGoodsKeyword(goodsKeyword goodsKeyword);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    Integer deleteGoodsKeywordByid(Integer id);


    /**
     * 模糊删除
     * @param goodsKeyword
     * @return
     */
    Integer deleteGoodsKeyword(goodsKeyword goodsKeyword);


    /**
     * 模糊更改
     * @param goodsKeyword
     * @return
     */
    Integer updateGoodsKeyword(goodsKeyword goodsKeyword);


    /**
     * 根据商品ID查询标签
     * @return
     */
    List<goodsKeyword> getGoodsKeywordByGoodsId(Integer goodsId);


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    goodsKeyword getGoodsKeywordByid(Integer id);
}
