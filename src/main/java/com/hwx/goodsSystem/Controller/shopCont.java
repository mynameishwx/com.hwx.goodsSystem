package com.hwx.goodsSystem.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.hwx.goodsSystem.entity.*;
import com.hwx.goodsSystem.service.*;
import com.hwx.goodsSystem.service.IMPL.messageGoodsIMPL;
import com.hwx.goodsSystem.service.IMPL.shopGoodsIMPL;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 店铺首页
 */
@Controller
@Slf4j
@RequiresPermissions("user:*:*")
public class shopCont {

    @Autowired
    private messageService messageService;

    @Autowired
    private staffService  staffService;

    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Autowired
    private userService userService;

    @Autowired
    private shopService shopService;

    @Autowired
    private shopGoodsIMPL shopGoodsIMPL;

    @Autowired
    private keywordService keywordService;



    /**
     * 导入配置文件中的商铺头像存放地址
     */
    @Value("${shop.imgNameUrl}")
    private String shopImgNameUrl;



    @GetMapping("/shop")
    public String shop(Map<String,Object> map){
        /**
         * 权限限定
         */
        map=shopGoodsIMPL.shop_power(map);
        return "shop";
    }

    /**
     * 判断是否是店主
     * @param id
     * @return
     */
    @PostMapping("/shop/esse")
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

    /**
     * 创建店铺
     * @param shopName
     * 店铺名
     * @param shopClass
     * 店铺类型
     * @param MultipartFile
     * 店铺头像
     * @return
     * @throws IOException
     */
    @PostMapping("/shop/create")
    public String shopCreate(@RequestParam("shopName")String shopName,
                             @RequestParam("province")String shopClass,
                             @RequestParam("shopImg")MultipartFile MultipartFile,
                             Map<String,String> map) throws IOException {
        /**
         * 产生头像名
         */
        String shopUrl=UUID.randomUUID().toString();

        /**
         * 将头像文件存入
         */

        FileOutputStream FileOutputStream=null;
        String[] ImgType=MultipartFile.getContentType().split("/");
        if(ImgType[1].equals("jpeg") || ImgType[1].equals("png")){
            /**
             * 限制文件大小为10MB
             */
           if(MultipartFile.getSize()<=10*1024*1024 && MultipartFile!=null){
               FileOutputStream=new FileOutputStream(shopImgNameUrl+shopUrl+"."+ImgType[1]);
               FileOutputStream.write(MultipartFile.getBytes());
           }else {
               map.put("Tshi","文件大小不符合要求!");
               log.warn("文件大小不符合要求!");
               return "redirect:/setting";
           }
        }else {
            map.put("Tshi","图片格式错误！");
            log.warn("图片格式错误!");
            return "redirect:/setting";
        }
        /**
         * 判断文件格式
         */
        File File=new File(shopImgNameUrl+shopUrl+"."+ImgType[1]);
        if(File.exists()){
            /**
             * 判断文件格式是否一致，不一致则删除
             */
            System.out.println(FileUtil.getType(File));
            if(FileUtil.getType(File).equals("jpg") || FileUtil.getType(File).equals("png")){

            }else {
                File.delete();
                map.put("Tshi","请上传正确的文件格式!");
                log.warn("请上传正确的文件格式!");
                return "redirect:/setting";
            }
        }
        /**
         * 将存放地址URL及商铺信息存入数据库
         */
        shop  shop=new shop();
        shop.setShopAdmin(goodsThreadLocal.getUser().getId());

        /**
         * 根据标签名查询标签ID
         */
        int keywordId=keywordService.getKeywordByText(shopClass).getId();
        /**
         * 暂时设置为0，测试
         */
        shop.setShopName(shopName);       //名字
        shop.setShopImgUrl(shopUrl+"."+ImgType[1]);  //头像地址
        shop.setShopClass(keywordId);     //店铺类型
        shop.setShopAddress("");   //店铺地址
        shop.setShopConcern(0);    //店铺关注
        shop.setShopState(0);     //店铺状态
        userRole userRole=new userRole();
        userRole=userRoleService.getUserRoleByUserId(goodsThreadLocal.getUser().getId());
        shopService.createShop(shop,userRole);

        return "redirect:/";
    }

    /**
     * 查询商店标签列表
     * @return
     */
    @PostMapping("/shop/getKeyword")
    @ResponseBody
    public List<keyword> getKeywordList(){
        keyword keyword=new keyword();
        List<keyword> keywordList=new ArrayList<>();
        List<keyword> one_List=keywordService.getKeyword(null);
        Iterator<keyword> keywordIterator=one_List.iterator();
        while (keywordIterator.hasNext()){
            keyword=keywordIterator.next();
            if(keyword.getSuperior()==null){
                keywordList.add(keyword);
            }
        }
        return  keywordList;
    }

    /**
     * AJAX商店页面的商店信息获取
     * @return
     */
    @PostMapping("/shop/shopMessage")
    @ResponseBody
    public shop shop_shopMessage(){
        shop shop=new shop();
        shop= shopService.getShopByAdminId(shopService.getEnterByShop().getShopAdmin());
        return  shop;
    }

    /**
     * 获取店主信息(商品增加模块使用)
     * @return
     */
    @PostMapping("/shop/adminMessage")
    @ResponseBody
    public String getShop_createGoods_shopAdmin(){
        user user=new user();
        user=userService.getUserById(shopService.getEnterByShop().getShopAdmin());
        if(user==null){
            log.warn("获取店主信息为空!!!");
            return "";
        }else {
            return user.getUserName();
        }
    }


    /**
     * 添加新员工
     * @param userName
     * 员工id
     * @param hat
     * 职位
     * @param money
     * 工资
     * @param map
     * @return
     */
    @PostMapping("/shop/createStaff")
    @ResponseBody
    public  String createStaff(@RequestParam("staff")String userName,
                               @RequestParam("hat")String hat,
                               @RequestParam("money")Integer money,
                               Map<String,Object>map){
        if(money<=0){
            return "添加失败,工资不能小于0";
        }

        /**
         * 将店铺信息持有
         */
        shop shop=new shop();
        shop=shopService.getShopByAdminId(goodsThreadLocal.getUser().getId());

        /**
         * 查询用户id
         */
        user user=userService.getUser(userName);

        /**
         * 查询是否已经发送入职信息
         */
        message message=new message();
        if(goodsThreadLocal.getUser().getId()>user.getId()){
            message.setWriteRead(user.getId()+"_"+goodsThreadLocal.getUser().getId());
        } else {
            message.setWriteRead(goodsThreadLocal.getUser().getId()+"_"+user.getId());
        }
        message.setMessageClass(3);
        List<message> messageList= messageService.getMessageByLogo(message);
        if(messageList.size()!=0){
            return "您已经向他发送了招聘信息，请勿重复发送!";
        }
        /**
         * 查询是否已经入职
         */
        staff staff=new staff();
        staff.setUserId(user.getId());
        staff.setStaffState(1);
        if(staffService.getStaffByUserId(staff)!=null){
            return "对方已经入职!";
        }
        /**
         * 判断是否已经是店主
         */
         if(shopService.getShopByAdminId(user.getId())!=null){
             return "对方为店主!";
         }
        /**
         * 存入数据库
         */
        staff=new staff();
        staff.setMoney(money);   //工资
        staff.setShopId(shop.getShopAdmin());  //店铺所有者id
        staff.setUserId(user.getId());  //用户id
        staff.setStaffState(0);   //用户确认状态 ,0为未确认
        if(hat.equals("销售")){
            staff.setRoleId(1);  //职位 , 1为销售，2为客服
        }else {
           if(!hat.equals("")){
               staff.setRoleId(2);
           }
        }
        if(staffService.createStaff(staff,shop)==1){
            return "添加成功,待对方同意";
        }else
        return "添加失败!";
    }

    /**
     * AJAX展示员工列表
     * @param start
     * @param stop
     * @return
     */
    @RequiresPermissions("admin:shop:*")
    @PostMapping("/shop/staffList")
    @ResponseBody
    public List<staff>  showStaff(Integer start, Integer stop){
          List<staff> staffList=new ArrayList<>();
          staffList= staffService.getStaffByShopId(goodsThreadLocal.getUser().getId());
          staff staff=new staff();
        for (int i = 0; i < staffList.size(); i++) {
            staff=staffList.get(i);
            /**
             * 剔除未确认入职的员工
             */
            if(staff.getStaffState()==0){
                staffList.remove(i);
                continue;
            }
            staff.setExtendOne(userService.getUserById(staff.getUserId()).getUserName());
            staffList.set(i,staff);
        }
          return staffList;
    }
}
