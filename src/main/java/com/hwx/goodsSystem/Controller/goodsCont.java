package com.hwx.goodsSystem.Controller;

import cn.hutool.core.io.FileUtil;
import com.hwx.goodsSystem.entity.goods;
import com.hwx.goodsSystem.entity.keyword;
import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.service.IMPL.shopGoodsIMPL;
import com.hwx.goodsSystem.service.goodsService;
import com.hwx.goodsSystem.service.keywordService;
import com.hwx.goodsSystem.service.shopService;
import com.hwx.goodsSystem.service.staffService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private shopService shopService;

    @Autowired
    private keywordService keywordService;

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
            if(FileUtil.getType(File).equals("jpg") || FileUtil.getType(File).equals("png") || FileUtil.getType(File).equals("jpeg")){

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
        goods.setShopId(shopService.getEnterByShop().getId());
        goodsService.createGoods(goods);

        /**
         * 记录:  未重定向，刷新之后直接添加一个商品
         */
        return  "redirect:/shop";
    }

    /**
     * 商品搜索
     * @param Search_Goods_text
     * 查询的字段
     * @return
     */
    @PostMapping("/SearchGoods")
    @RequiresPermissions("admin:shop:goods")
    @ResponseBody
    public List<goods> SearchGoods(@RequestParam("Search_Goods_text")String Search_Goods_text){

        return null;
    }

    /**
     * AJAX获取商品列表
     * @param start
     * @param stop
     * @return
     */
    @PostMapping("/showList")
    @RequiresPermissions("admin:shop:goods")
    @ResponseBody
    public List<goods> goodsList(Integer start,Integer stop){
        List<goods> goodsList=new ArrayList<>();
        //获取总条数
        goodsList=goodsService.getGoodsShopId(shopService.getEnterByShop().getId());
        int x=goodsList.size();
        goodsList=new ArrayList<>();
        //获取商品
        goodsList= goodsService.getGoodsLiMit(start,stop, shopService.getEnterByShop().getId());
        if(x==0){
            return null;
        }else {
            //将总商品数转递给前端
            goods  goods=new goods();
            goods=goodsList.get(0);
            goods.setExtend_One(x+"");

            //页数
            int y=0;
            if(x%5!=0){
                y=x/5+1;
            }else {
                y=x/5;
            }
            goods.setExtend_Two(y+"");
            goodsList.set(0,goods);
        }
        return  goodsList;
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
     * 商品类型获取
     * @return
     */
    @RequiresPermissions("admin:shop:goods")
    @PostMapping("/getKeyword")
    @ResponseBody
    public List<keyword> getKeyword(){
        /**
         * 获取商店信息
         */
        shop shop=new shop();
        shop=shopGoodsIMPL.shop(shop);
        /**
         *  根据上级标签和标签等级模糊查询
         */
        keyword keyword=new keyword();
        keyword.setSuperior(keywordService.getKeywordByText(shop.getShopName()).getSuperior());
        keyword.setExtend_One("2");
        List<keyword> keywordList=new ArrayList<>();
        keywordList=keywordService.getKeyword(keyword);
        System.out.println(keywordList.get(0).toString());
        return keywordList;
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
