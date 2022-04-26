package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.power;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface powerDao {

    /**
     * 增
     */
    Integer createPower(power power);

    /**
     * 删
     */
    Integer deletePower(Integer id);

    /**
     * 改
     */
    Integer updatePower(power power);

    /**
     * 查
     */
    power  getPowerById(Integer id);
}
