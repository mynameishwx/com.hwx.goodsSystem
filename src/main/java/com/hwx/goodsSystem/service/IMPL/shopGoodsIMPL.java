package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.roleDao;
import com.hwx.goodsSystem.entity.goods;
import com.hwx.goodsSystem.entity.role;
import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.service.*;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
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
     * 店铺商品首页数据方法抽离
     * @return
     */
    public Map<String,Object> shopDao(Map<String,Object> map){
        List<goods> goodsList=new ArrayList<>();
        shop shop=new shop();
        /**
         * 将店铺信息持有
         */
        shop=shopService.getShopByAdminId(goodsThreadLocal.getUser().getId());
        role role=new role();
        role=this.getrole();
        user user=new user();
        user=this.getShopAdminUser();

        if(role.getRoleName().equals("shopAdmin")){
            map.put("shopAdminName",user.getUserName());
            map.put("class","yes");
        }else {
            map.put("class","no");
        }
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
        shopAdmianUser=userService.getUserById(shop.getShopAdmin());
        return  shopAdmianUser;
    }
    public Integer createGoods(goods goods){
        role role=new role();
        shop shop=new shop();
        role=this.getrole();
        /**
         * 将店铺信息持有
         */
        shop=shopService.getShopByAdminId(goodsThreadLocal.getUser().getId());
        goods.setShopId(shop.getId());       //店铺id
        goodsService.createGoods(goods);
        return 1;
    }
}
