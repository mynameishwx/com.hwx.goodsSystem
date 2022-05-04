package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.power;

public interface powerService {

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
