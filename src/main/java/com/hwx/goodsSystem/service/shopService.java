package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.entity.userRole;
import io.swagger.models.auth.In;

public interface shopService {
    /**
     * 增
     * @param shop
     * @return
     */
    Integer  createShop(shop shop, userRole userRole);

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
    Integer updateShop(shop shop);

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
