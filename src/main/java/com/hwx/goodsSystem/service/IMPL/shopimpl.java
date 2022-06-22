package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.shopDao;
import com.hwx.goodsSystem.Dao.userDao;
import com.hwx.goodsSystem.Dao.userRoleDao;
import com.hwx.goodsSystem.entity.keyword;
import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.entity.staff;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.keywordService;
import com.hwx.goodsSystem.service.shopService;
import com.hwx.goodsSystem.service.staffService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class shopimpl implements shopService {

    @Autowired
    private userRoleDao userRoleDao;

    @Autowired
    private shopDao shopDao;

    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Autowired
    private staffService staffService;

    @Autowired
    private keywordService keywordService;

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

        /**
         * 将店铺名加入到标签中
         */
        keyword keyword=new keyword();
        keyword.setClassText(shop.getShopName());
        keyword.setSuperior(shop.getShopClass());
        keywordService.addKeyword(keyword);
        return shopDao.createShop(shop);
    }

    @Override
    public Integer deleteShop(Integer id) {
        return shopDao.deleteShop(id);
    }

    @Override
    public Integer updateShop(shop shop) {
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

    @Override
    public shop getEnterByShop() {
        shop shop=new shop();
        shop=this.getShopByAdminId(goodsThreadLocal.getUser().getId());
        /**
         * 将店铺信息持有
         */
        if(shop==null){
            staff staff=new staff();
            staff.setUserId(goodsThreadLocal.getUser().getId());
            staff.setStaffState(1);
            staff=staffService.getStaffByUserId(staff);
            shop=this.getShopByAdminId(staff.getShopId());
            if(shop==null){
                log.warn("当前用户没有所属店铺!");
                return null;
            }
        }
        return shop;
    }
}
