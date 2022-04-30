package com.hwx.goodsSystem.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
/**
 * 需要 user:*:* (已登录)权限
 */
@RequiresPermissions("user:*:*")
public class goodsCont {

    /**
     * 订单详情页
     * @return
     */
    @RequestMapping("/showGoods")
    public String showGoods(){

        return  "goodsS";
    }

    /**
     * 店铺主页面,需要 admin:shop:* (店主)权限
     */
    @RequestMapping("/shop")
    @RequiresPermissions("admin:shop:*")
    public String shopGoods(){
        return  "shop";
    }


    /**
     * 商品管理,需要 admin:goods:* (店铺管理员)权限
     */
    @RequiresPermissions("admin:goods:*")
    @RequestMapping("/goods")
    public  String goods(){

        return  "goods";
    }
}
