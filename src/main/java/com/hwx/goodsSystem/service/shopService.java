package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.shop;

public interface shopService {
    /**
     * 增
     * @param shop
     * @return
     */
    Integer  createShop(shop shop);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Integer  deleteShop(Integer id);

    /**
     * 更改
     * @param shop
     * @return
     */
    shop  updateShop(shop shop);

    /**
     * 根据店铺id查找
     * @param id
     * @return
     */
    shop  getShopById(Integer id);

    /**
     * 根据店主id查询
     * @param id
     * 店铺创建者id
     * @return
     */
    shop getShopByAdminId(Integer id);
}
