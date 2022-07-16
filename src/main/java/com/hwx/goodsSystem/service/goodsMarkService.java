package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.goodsMark;

import java.util.ArrayList;

public interface goodsMarkService {
    /**
     * 增
     *
     * @param goodsMark
     * @return
     */
    Integer createGoodsMark(goodsMark goodsMark);


    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);


    /**
     * 模糊修改
     *
     * @param goodsMark
     * @return
     */
    Integer updateByid(goodsMark goodsMark);

    /**
     * 模糊查询
     *
     * @param goodsMark
     * @return
     */
    ArrayList<goodsMark> selectByid(goodsMark goodsMark);
}
