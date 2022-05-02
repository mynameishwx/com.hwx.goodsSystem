package com.hwx.goodsSystem.Controller;

import cn.hutool.core.lang.UUID;
import com.hwx.goodsSystem.entity.role;
import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.roleService;
import com.hwx.goodsSystem.service.shopService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
@RequiresPermissions("user:*:*")
@RequestMapping("/shop")
public class shopCont {
    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Autowired
    private roleService roleService;

    /**
     * 导入配置文件中的商铺头像存放地址
     */
    @Value("${shop.imgNameUrl}")
    private String shopImgNameUrl;

    @Autowired
    private shopService shopService;

    @GetMapping("")
    public String shop(Map<String,String> map){
        role role= new role();

        role= roleService.getRoleById(userRoleService.getUserRoleByUserId(goodsThreadLocal.getUser().getId()).getRoleId());
        if(role.getRoleName().equals("shopAdmin")){
            map.put("class","yes");
        }else {
            map.put("class","no");
        }
        return "shop";
    }

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
                             @RequestParam("province")String shopClass,
                             @RequestParam("shopImg")MultipartFile[] MultipartFile) throws IOException {
        /**
         * 产生头像名
         */
        String shopUrl=UUID.randomUUID().toString();

        /**
         * 将头像文件存入
         */
        FileOutputStream FileOutputStream=null;
        FileOutputStream=new FileOutputStream(shopImgNameUrl+shopUrl+".jpg");
        FileOutputStream.write(MultipartFile[0].getBytes());
        /**
         * 将存放地址URL及商铺信息存入数据库
         */
        shop  shop=new shop();
        shop.setShopAdmin(goodsThreadLocal.getUser().getId());
        /**
         * 暂时设置为0，测试
         */
        shop.setShopName(shopName);
        shop.setShopClass(0);
        shop.setShopAddress("");
        shop.setShopConcern(0);
        shop.setShopState(0);
        userRole userRole=new userRole();
        userRole=userRoleService.getUserRoleByUserId(goodsThreadLocal.getUser().getId());
        shopService.createShop(shop,userRole);

        System.out.println(shopName);
        return "redirect:/";
    }
}
