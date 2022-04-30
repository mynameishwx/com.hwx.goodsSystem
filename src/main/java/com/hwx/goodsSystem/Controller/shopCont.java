package com.hwx.goodsSystem.Controller;

import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.service.shopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiresPermissions("user:*:*")
@RequestMapping("/shop")
public class shopCont {

    @Autowired
    private shopService shopService;

    @PostMapping("/esse")
    @ResponseBody
    public String  shopEsse(@RequestParam(value = "userId",defaultValue = "")Integer id){
        if(!id.equals("")){
            shop shop=new shop();
            shop=shopService.getShopByAdminId(id);
            if(shop==null){
                return null;
            }else {
                return "YES";
            }
        }else {
            return "id为空";
        }
    }

    @PostMapping("/create")
    public String shopCreate(@RequestParam("shopName")String shopName,
                             @RequestParam(value = "province",defaultValue = "")String AddressOne,
                             @RequestParam(value = "city",defaultValue = "")String AddressTwo,
                             @RequestParam(value = "area",defaultValue = "")String AddressThree){
        System.out.println(shopName);
        System.out.println(AddressOne);
        System.out.println(AddressTwo);
        System.out.println(AddressThree);
        return "redirect:/";
    }
}
