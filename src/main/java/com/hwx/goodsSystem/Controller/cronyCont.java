package com.hwx.goodsSystem.Controller;

import com.hwx.goodsSystem.entity.message;
import com.hwx.goodsSystem.entity.staff;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.service.IMPL.messageGoodsIMPL;
import com.hwx.goodsSystem.service.messageService;
import com.hwx.goodsSystem.service.staffService;
import com.hwx.goodsSystem.service.userService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 我的信息
 */
@Controller
@RequiresPermissions("user:*:*")
public class cronyCont {

    @Autowired
    private messageGoodsIMPL messageGoodsIMPL;

    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Autowired
    private staffService staffService;

    @Autowired
    private messageService messageService;

    @Autowired
    private userService userService;

    @GetMapping("/crony")
    public String crony(Map<String,Object>map){
        /**
         * 信息
         */
        map=messageGoodsIMPL.messageDaoCrony(map,1);
        return "crony";
    }

    @PostMapping("/crony/shopOK")
    @ResponseBody
    public String shopOk(@RequestParam("writeName")String writeName){
        /**
         * 发送人信息
         */
        user user=new user();
        user= userService.getUser(writeName);

        /**
         * 查询入职信息
         */
        message message=new message();
        if(user.getId()>goodsThreadLocal.getUser().getId()){
            message.setWriteRead(goodsThreadLocal.getUser().getId()+"_"+user.getId());
        }else {
            message.setWriteRead(user.getId()+"_"+goodsThreadLocal.getUser().getId());
        }
        message.setMessageClass(3);
        List<message> messageList= messageService.getMessageByLogo(message);

        /**
         *
         *
         *  错误在直接new对象，导致对象为空，角色权限未改变
         */
        /**
         * 员工确认入职
         */
        staff staff=new staff();
        staff.setUserId(goodsThreadLocal.getUser().getId());
        staff.setStaffState(0);
        staff=staffService.getStaffByUserId(staff);
        staff.setStaffState(1);
        staffService.updataStaffByUserId(staff);
        /**
         * 删除入职信息
         */
        int y= messageService.deleteMessage(messageList.get(0).getId());

        if( y==1){
            return  "成功入职";
        }else {
            return  "发送错误稍后再试!";
        }
    }

    /**
     * AJAX获取导航条信息总数
     * @return
     */
    @PostMapping("/crony/messageSize")
    @ResponseBody
    public String messageSize(){
        List<message> messageList=new ArrayList<>();
        messageList=messageService.getMessageNoMy(goodsThreadLocal.getUser().getId());
        return messageList.size()+"";
    }

    /**
     * AJAX获取每种信息未读数
     * @return
     */
    @PostMapping("/crony/messageSum")
    @ResponseBody
    public int[] messageSum(){
        int[] Re_message=new int[3];
        List<message> message=new ArrayList<>();
        message=messageService.getMessageNoMy(goodsThreadLocal.getUser().getId());
        Iterator<message> messageList = message.iterator();
        message messageT = new message();
        int my_message = 0;  //普通信息(含店铺招聘)
        int admin_message = 0; //  系统信息
        int cronyCreate_message = 0; //好友申请
        while (messageList.hasNext()) {
            messageT = messageList.next();
            switch (messageT.getMessageClass()) {
                case 0:
                    ++admin_message;
                    break;
                case 4:
                    ++cronyCreate_message;
                    break;
                default:
                    ++my_message;
            }
        }
        Re_message[0]=my_message;
        Re_message[1]=admin_message;
        Re_message[2]=cronyCreate_message;
        return Re_message;
    }

    /**
     * AJAX获取普通信息
     * @return
     */
    @PostMapping("/crony/my_message")
    @ResponseBody
    public List<message> get_my_message(){
        List<message> message=new ArrayList<>();
        message=messageService.getMessageNoMy(goodsThreadLocal.getUser().getId());
        Iterator<message> messageList = message.iterator();
        message messageT = new message();
        int my_message = 0;  //普通信息(含店铺招聘)
        List<message> my_messageList=new ArrayList<>();
        int admin_message = 0; //  系统信息
        List<message> admin_messageList=new ArrayList<>();
        int cronyCreate_message = 0; //好友申请
        List<message> cronyCreate_messageList=new ArrayList<>();
        while (messageList.hasNext()) {
            messageT = messageList.next();
            switch (messageT.getMessageClass()) {
                case 0:
                    ++admin_message;
                    admin_messageList.add(messageT);
                    break;
                case 4:
                    ++cronyCreate_message;
                    cronyCreate_messageList.add(messageT);
                    break;
                default:
                    ++my_message;
                    /**
                     * 获取发送人Name
                     */
                    messageT.setExtendOne(userService.getUserById(messageT.getWriteId()).getUserName());
                    my_messageList.add(messageT);
            }
        }
        return my_messageList;
    }

    /**
     * AJAX获取系统信息
     * @return
     */
    @PostMapping("/crony/admin_message")
    @ResponseBody
    public List<message> get_admin_message(){
        List<message> message=new ArrayList<>();
        message=messageService.getMessageNoMy(goodsThreadLocal.getUser().getId());
        Iterator<message> messageList = message.iterator();
        message messageT = new message();
        int my_message = 0;  //普通信息(含店铺招聘)
        List<message> my_messageList=new ArrayList<>();
        int admin_message = 0; //  系统信息
        List<message> admin_messageList=new ArrayList<>();
        int cronyCreate_message = 0; //好友申请
        List<message> cronyCreate_messageList=new ArrayList<>();
        while (messageList.hasNext()) {
            messageT = messageList.next();
            switch (messageT.getMessageClass()) {
                case 0:
                    ++admin_message;
                    admin_messageList.add(messageT);
                    break;
                case 4:
                    ++cronyCreate_message;
                    cronyCreate_messageList.add(messageT);
                    break;
                default:
                    ++my_message;
                    my_messageList.add(messageT);
            }
        }
        return admin_messageList;
    }

    /**
     * AJAX获取好友申请
     * @return
     */
    @PostMapping("/crony/cronyCreate_message")
    @ResponseBody
    public List<message> get_cronyCreate_message(){
        List<message> message=new ArrayList<>();
        message=messageService.getMessageNoMy(goodsThreadLocal.getUser().getId());
        Iterator<message> messageList = message.iterator();
        message messageT = new message();
        int my_message = 0;  //普通信息(含店铺招聘)
        List<message> my_messageList=new ArrayList<>();
        int admin_message = 0; //  系统信息
        List<message> admin_messageList=new ArrayList<>();
        int cronyCreate_message = 0; //好友申请
        List<message> cronyCreate_messageList=new ArrayList<>();
        while (messageList.hasNext()) {
            messageT = messageList.next();
            switch (messageT.getMessageClass()) {
                case 0:
                    ++admin_message;
                    admin_messageList.add(messageT);
                    break;
                case 4:
                    ++cronyCreate_message;
                    cronyCreate_messageList.add(messageT);
                    break;
                default:
                    ++my_message;
                    my_messageList.add(messageT);
            }
        }
        return cronyCreate_messageList;
    }
}

