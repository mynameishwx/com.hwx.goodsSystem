package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.goodsKeyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface goodsKeywordDao {

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
