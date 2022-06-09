package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.roleDao;
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
     * 店铺信息抽离
     */
    public role getrole(){
        role role= new role();
        /**
         * 获取当前登录的角色信息
         */
        role= roleService.getRoleById(userRoleService.getUserRoleByUserId(goodsThreadLocal.getUser().getId()).getRoleId());
        return role;
    }





    /**
     * 前端页面(店铺页面公共持有)
     * @return
     */
    public Map<String,Object> shopDao(Map<String,Object> map){
        List<goods> goodsList=new ArrayList<>();
        shop shop=new shop();
        /**
         * 将店铺信息持有
         */
        shop=this.shop(shop);

        role role=new role();
        role=this.getrole();
        user user=new user();
        user=this.getShopAdminUser();

        /**
         * 员工信息
         */
        List<staff>  staffList=staffService.getStaffByShopId(shop.getShopAdmin());
        List<String> nameList=new ArrayList<>();
        if(staffList.size()!=0){
            staff staff=new staff();
            for (int i = 0; i < staffList.size(); i++) {
                staff=staffList.get(i);
                nameList.add(i,userService.getUserById(staff.getUserId()).getUserName());
            }
            map.put("nameList",nameList);
            map.put("staffList",staffList);
        }
        /**
         * 前端页面根据不同权限显示
         */
        if(role.getRoleName().equals("shopAdmin")){
            /**
             * 人员管理持有
             */
            map.put("staffCreate","yes");
            map.put("goodsCreate","yes");
            map.put("dataShow","yes");
        }if(role.getRoleName().equals("goodsAdmin")) {
            map.put("goodsCreate","yes");
            map.put("dataShow","yes");
        }if(role.getRoleName().equals("messageAdmin")){

        }
        /**
         * 店主管理持有
         */
        map.put("shopAdminName",user.getUserName());
        /**
         * 将请求通过静态资源映射，来获取实际地址
         */
        shop.setShopImgUrl("/shopImg/"+shop.getShopImgUrl());

        /**
         * 获取商品首页数据
         */
        goodsList=goodsService.getGoodsLiMit(0,5,shop.getId());
        for (int i = 0; i < goodsList.size(); i++) {
            map.put("goodsList"+i,goodsList.get(i));
        }
        map.put("size",goodsList.size());
        map.put("temp",1);

        /**
         * 持有店铺信息
         */
        map.put("shop",shop);

        /**
         * 查询总商品数
         */
        List<goods> goods=goodsService.getGoodsShopId(shop.getId());
        map.put("goodsSum",goods.size());

        return  map;
    }

    public user getShopAdminUser(){
        shop shop=new shop();
        /**
         * 将店铺信息持有
         */
        shop=shopService.getShopByAdminId(goodsThreadLocal.getUser().getId());
        /**
         * 获取店主信息
         */
        user shopAdmianUser =new user();

        if(shop==null){
            /**
             * 通过店员获取店主
             */
          List<staff> staffList=new ArrayList<>();
          staff staff=new staff();
          staff.setUserId(goodsThreadLocal.getUser().getId());
          staff.setStaffState(1);
          staff= staffService.getStaffByUserId(staff);
          if(staffList.size()!=0){
              shopAdmianUser=userService.getUserById(staffList.get(0).getShopId());
          }else  log.warn("在店员获取店主信息时获取为空!!");
        }else {
            /**
             * 店主信息
             */
            shopAdmianUser=userService.getUserById(goodsThreadLocal.getUser().getId());
        }
        return  shopAdmianUser;
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


    public Integer createGoods(goods goods){
        role role=new role();
        shop shop=new shop();
        role=this.getrole();
        /**
         * 将店铺信息持有
         */
        shop=this.shop(shop);
        goods.setShopId(shop.getId());       //店铺id
        goodsService.createGoods(goods);
        return 1;
    }
}
