package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.entity.message;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.service.messageService;
import com.hwx.goodsSystem.service.userService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 信息类的公共抽离
 */
@Service
public class messageGoodsIMPL {
    @Autowired
    private userService userService;


    @Autowired
    private goodsThreadLocal goodsThreadLocal;


    @Autowired
    private messageService messageService;



    /**
     * 信息页面专用
     * @param map
     * @return
     */
    public Map<String, Object> messageDaoCrony(Map<String, Object> map,Integer pen) {
        user user=new user();
        user=goodsThreadLocal.getUser();
        if (user != null) {
            /**
             * 获取该用户所有未读信息
             */
            List<message> message = messageService.getMessageNoMy(user.getId());
            if (message.size() != 0) {
                map.put("sum_message", message.size());
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
                if (my_message != 0) {
                    /**
                     * 发送人信息
                     */
                    user WriteUser=new user();
                    /**
                     * 信息条数
                     */
                    map.put("my_message", my_message);
                    /**
                     * 每页信息条数
                     */
                    int x=0;
                    /**
                     * 每页信息
                     */
                    for (int i = (pen-1)*6; i < (pen*6); i++) {
                       if((i+1)>my_message) break;
                        /**
                         * 获取发送人信息
                         */
                        WriteUser=userService.getUserById(my_messageList.get(i).getWriteId());

                        /**
                         * 发送人头像以及用户名
                         */
                        if(WriteUser.getImageUrl()==null){
                            map.put("WriteUserImg"+i,"/img/hwx.png");
                        }else {
                            map.put("WriteUserImg"+i,"/userImg/"+WriteUser.getImageUrl());
                        }
                        map.put("WriteUserName"+i,WriteUser.getUserName());
                        /**
                         * 信息
                         */
                        map.put("my_message"+i,my_messageList.get(i));
                        ++x;
                    }
                    /**
                     * 发送这一页条数
                     */
                    map.put("my_message_size",x);

                }
                if (admin_message != 0) {
                    map.put("admin_message", admin_message);
                    map.put("admin_messageList",admin_messageList);
                }
                if (cronyCreate_message != 0) {
                    map.put("cronyCreate_message", cronyCreate_message);
                    map.put("cronyCreate_message",cronyCreate_messageList);
                }
            }
        }
        return  map;
    }
}

