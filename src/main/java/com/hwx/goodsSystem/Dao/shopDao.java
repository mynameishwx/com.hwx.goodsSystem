package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface shopDao {

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
     * 修改店铺关注
     * @param shop
     * @return
     */
    Integer  updateShop(shop shop);

    /**
     * 根据店铺id查找
     * @param id
     * @return
     */
    shop  getShopById(Integer id);

    /**
     * 根据店主id查询
     * @param id
     * @return
     */
    List<shop> getShopByAdminId(Integer id);
}
