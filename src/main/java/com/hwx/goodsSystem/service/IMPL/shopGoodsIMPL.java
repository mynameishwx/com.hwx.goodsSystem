package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.entity.*;
import com.hwx.goodsSystem.service.*;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 店铺信息持有的公共类抽离
 */
@Service
@Slf4j
public class shopGoodsIMPL {

    @Autowired
    private roleService roleService;

    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Autowired
    private shopService shopService;

    @Autowired
    private userService userService;

    @Autowired
    private goodsService goodsService;

    @Autowired
    private staffService staffService;


    /**
     * 店铺页的权限限定
     * @return
     */
    public Map<String,Object> shop_power(Map<String,Object> map){
        /**
         * 查询当前用户的用户角色信息
         */
        userRole userRole=new userRole();
        userRole= userRoleService.getUserRoleByUserId(goodsThreadLocal.getUser().getId());
        /**
         * 根据用户角色信息查询角色
         */
        role role=new role();
        role=roleService.getRoleById(userRole.getRoleId());

        /**
         * 根据角色分配不同的权限
         */
        if(role.getRoleName().equals("shopAdmin")){
            /**
             * 人员管理权限
             */
            map.put("staffCreate","yes");
            map.put("goodsCreate","yes");
            map.put("dataShow","yes");
        }if(role.getRoleName().equals("goodsAdmin")) {
            map.put("goodsCreate","yes");
            map.put("dataShow","yes");
        }if(role.getRoleName().equals("serverAdmin")){

        }
        return map;
    }



    public shop shop(shop shop){
        user user=new user();
        /**
         * 将店铺信息持有
         */
        shop=shopService.getShopByAdminId(goodsThreadLocal.getUser().getId());
        if(shop==null){
            /**
             * 通过店员获取店主
             */
            staff staff=new staff();
            staff.setUserId(goodsThreadLocal.getUser().getId());
            staff.setStaffState(1);
            staff= staffService.getStaffByUserId(staff);
            if(staff!=null){
                user=userService.getUserById(staff.getShopId());
                shop=shopService.getShopByAdminId(user.getId());
            }else  log.warn("在店员获取店主信息时获取为空!!");
        }else {
            /**
             * 店主信息
             */
            user=userService.getUserById(goodsThreadLocal.getUser().getId());
            shop=shopService.getShopByAdminId(user.getId());
        }
        return  shop;
    }


//    public Integer createGoods(goods goods){
//        role role=new role();
//        shop shop=new shop();
//        role=this.getrole();
//        /**
//         * 将店铺信息持有
//         */
//        shop=this.shop(shop);
//        goods.setShopId(shop.getId());       //店铺id
//        goodsService.createGoods(goods);
//        return 1;
//    }
}
