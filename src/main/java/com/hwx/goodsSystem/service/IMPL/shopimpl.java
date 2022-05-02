package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.shopDao;
import com.hwx.goodsSystem.Dao.userDao;
import com.hwx.goodsSystem.Dao.userRoleDao;
import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.shopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class shopimpl implements shopService {

    @Autowired
    private userRoleDao userRoleDao;

    @Autowired
    private shopDao shopDao;

    @Override
    @Transactional
    public Integer createShop(shop shop, userRole userRole) {
        shop.setCreateTime(new Date());
        shop.setUpdateTime(new Date());
        /**
         * 将用户角色改为店铺主
         */
        userRole.setRoleId(4);
        userRole.setUpdateTime(new Date());
        userRoleDao.updateUserRole(userRole);
        return shopDao.createShop(shop);
    }

    @Override
    public Integer deleteShop(Integer id) {
        return shopDao.deleteShop(id);
    }

    @Override
    public shop updateShop(shop shop) {
        shop.setUpdateTime(new Date());
        return shopDao.updateShop(shop);
    }

    @Override
    public shop getShopById(Integer id) {
        return shopDao.getShopById(id);
    }

    @Override
    public shop getShopByAdminId(Integer id) {
        List<shop> shopList=new ArrayList<>();
        shopList=shopDao.getShopByAdminId(id);
        if(shopList.size()!=0){
            return shopList.get(0);
        }
        return null;
    }
}
