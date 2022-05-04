package com.hwx.goodsSystem.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.log.Log;
import com.hwx.goodsSystem.entity.*;
import com.hwx.goodsSystem.service.IMPL.shopGoodsIMPL;
import com.hwx.goodsSystem.service.goodsService;
import com.hwx.goodsSystem.service.roleService;
import com.hwx.goodsSystem.service.shopService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/goods")
/**
 * 需要 user:*:* (已登录)权限
 */
@RequiresPermissions("user:*:*")
@Slf4j
public class goodsCont {

    /**
     * 商品图片存储位置
     */
    @Value("${goods.imgNameUrl}")
    private String goodsImgUrl;




    @Autowired
    private goodsService goodsService;

    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private roleService roleService;


    @Autowired
    private shopService shopService;

    @Autowired
    private shopGoodsIMPL shopGoodsIMPL;

    /**
     * 创建商品
     */
    @RequiresPermissions("admin:shop:goods")
    @RequestMapping("/create")
    public String createGoods(@RequestParam("goodsName") String goodsName,
                              @RequestParam("goodsPrice")Integer goodsMoney,
                              @RequestParam("goodsSuggest")String goodsSuggest,
                              @RequestParam("goodsImg") MultipartFile MultipartFile, Map<String,Object> map) throws IOException {

        /**
         * 头像名称
         */
        String UUid= UUID.randomUUID().toString();

        /**
         * 判断格式,并存入
         */
        FileOutputStream FileOutputStream=null;
        /**
         * 获取图片后缀名
         */
        log.warn(MultipartFile.getContentType());
        String[] imgType=MultipartFile.getContentType().split("/");
        if(imgType[1].equals("jpeg") || imgType[1].equals("png")){
            /**
             * 判断图片大小，以及是否为空
             */
            if(MultipartFile.getSize()<=10*1024*1024 && MultipartFile!=null){
                FileOutputStream=new FileOutputStream(goodsImgUrl+UUid+"."+imgType[1]);
                FileOutputStream.write(MultipartFile.getBytes());
            }else {
                map.put("Tshi","文件大小不符合要求!");
                log.warn("文件大小不符合要求");
                return "redirect:/shop";
            }
        }else {
            map.put("Tshi","文件格式不符合要求!");
            log.warn("文件格式不符合要求");
            return "redirect:/shop";
        }
        /**
         *   判断格式(通过Hutool工具包判断前面两位编码),格式不对则删除文件
         */
        File File=new File(goodsImgUrl+UUid+"."+imgType[1]);
        if(File.exists()){
            if(FileUtil.getType(File).equals("jpg") || FileUtil.getType(File).equals("png")){

            }else {
                File.delete();
                map.put("Tshi","文件格式不符合要求");
                log.warn("文件格式不符合要求！！！");
                return "redirect:/shop";
            }
        }else {
            map.put("Tshi","未知错误");
            log.warn("没有读取到存储的店铺头像！！！");
            return "redirect:/shop";
        }

        /**
         * 将商品信息存入数据库
         */
        goods goods=new goods();
        goods.setGoodsName(goodsName);  //商品名称
        goods.setGoodsMoney(goodsMoney); //商品价格
        goods.setGoodsSuggest(goodsSuggest); //商品介绍
        goods.setGoodsImageUrl(UUid+"."+imgType[1]);  //商品图片Url
        shopGoodsIMPL.createGoods(goods);
        map=shopGoodsIMPL.shopDao(map);
        return  "shop";


    }

    /**
     * 商品列表
     * @param start
     * 前一页还是后一页的信号量(值为-1或者1)
     * @param temp
     * 当前所在页数
     * @param map
     * @return
     */
    @GetMapping("/List")
    public String getGoodsList(@RequestParam(value = "start",defaultValue = "0") Integer start
            ,@RequestParam("temp")Integer temp,
                               Map<String,Object> map){
        List<goods> goods=new ArrayList<>();
        /**
         * 获取当前登录的角色信息
         */
        shop shop=new shop();
        /**
         * 将店铺信息持有
         */
        shop=shopService.getShopByAdminId(goodsThreadLocal.getUser().getId());
        user user=new user();
        user=shopGoodsIMPL.getShopAdminUser();
        map.put("shopAdminName",user.getUserName());
        goods=goodsService.getGoodsShopId(shop.getId());
        map.put("goodsSum",goods.size());
        if(goods.size()>5){
            if(start.equals(-1)){
                if(!temp.equals(1)){
                    goods=goodsService.getGoodsLiMit((temp-2)*5,5,shop.getId());
                    temp=temp-1;
                }else {
                    //首页无法前一页
                }
            }else if(start.equals(1)){
                /**
                 * 判断是否有余数,通过余数来确定页数，有余数则除数加1，否则则为除数
                 */
                if(goods.size()%5==0){
                    if(temp!=goods.size()/5){
                        goods=goodsService.getGoodsLiMit(temp*5,5,shop.getId());
                        temp++;
                    }else {
                        goods=goodsService.getGoodsLiMit((temp-1)*5,5,shop.getId());
                    }
                }else {
                    if(temp!=(goods.size()/5)+1){
                        goods=goodsService.getGoodsLiMit(temp*5,5,shop.getId());
                        temp++;
                    }else {
                        goods=goodsService.getGoodsLiMit((temp-1)*5,5,shop.getId());
                    }
                }
            }
        }
        for (int i = 0; i < goods.size(); i++) {
            map.put("goodsList"+i,goods.get(i));
        }
        map.put("size",goods.size());
        map.put("temp",temp);
        /**
         * 店铺信息
         */
        shop.setShopImgUrl("/shopImg/"+shop.getShopImgUrl());
        map.put("shop",shop);

        return "shop";
    }

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
