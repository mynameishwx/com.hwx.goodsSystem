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
}
