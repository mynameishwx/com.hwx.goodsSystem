package com.hwx.goodsSystem.service.IMPL;

import cn.hutool.log.Log;
import com.hwx.goodsSystem.Dao.staffDao;
import com.hwx.goodsSystem.entity.*;
import com.hwx.goodsSystem.service.messageService;
import com.hwx.goodsSystem.service.staffService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class satffimpl implements staffService {

    @Autowired
    private staffDao staffDao;

    @Autowired
    private messageService messageService;

    @Autowired
    private userService userService;

    @Autowired
    private userRoleService userRoleService;

    /**
     * 事务处理，同时添加新员工，以及信息
     * @param staff
     * @param shop
     * @return
     */
    @Override
    @Transactional
    public Integer createStaff(staff staff, shop shop) {
        staff.setCreateTime(new Date());
        staff.setUpdateTime(new Date());

        /**
         * 创建的同时，发送信息给用户
         */
        message message=new message();
        message.setWriteId(staff.getShopId());  //发送人id
        message.setReadId(staff.getUserId());  //接收人id
        message.setMessagePower(1);  //信息权限为1(用户权限)
        message.setMessageState(0);  //0为未读,为已读

        /**
         * 会话拼接规则  (id更小的在前面)
         */
        if(staff.getShopId()>staff.getUserId()){
            message.setWriteRead(staff.getUserId()+"_"+staff.getShopId());
        }else {
            message.setWriteRead(staff.getShopId()+"_"+staff.getUserId());
        }
        /**
         * 获取店长的用户名
         */
        user user=userService.getUserById(staff.getShopId());
        /**
         * 编写信息,1为销售，2为客服
         */
        if(staff.getRoleId().equals("1")){
            message.setMessage(user.getUserName()+"想应聘您为"+shop.getShopName()+"的销售"+"月工资为"+
                    staff.getMoney());
        }else {
            message.setMessage(user.getUserName()+"想应聘您为"+shop.getShopName()+"的客服"+"月工资为"+
                    staff.getMoney());
        }
        /**
         * 信息类型
         * 0:系统信息
         * 1:用户聊天信息(文本)
         * 2:用户聊天信息(图片)
         * 3:店铺类员工信息
         * 4:好友申请信息
         */
        message.setMessageClass(3);

        messageService.createMessage(message);
        return staffDao.createStaff(staff);
    }

    @Override
    public Integer deleteStaffById(Integer id) {
        return staffDao.deleteStaffById(id);
    }

    @Override
    public Integer deleteStaffByState(Integer state) {
        return staffDao.deleteStaffByState(state);
    }

    @Override
    public Integer updateStaff(staff staff) {
        staff.setUpdateTime(new Date());
        return staffDao.updateStaff(staff);
    }

    @Override
    public staff getStaffById(Integer id) {
        return staffDao.getStaffById(id);
    }

    @Override
    public List<staff> getStaffByShopId(Integer shopId) {
        return staffDao.getStaffByShopId(shopId);
    }

    @Override
    public staff getStaffByUserId(staff staff) {
        if(staff.getStaffState()==null){
            log.warn("根据userID查询员工失败: 员工状态为空!");
            return null;
        }
        List<staff> staffList=new ArrayList<>();
        staffList=staffDao.getStaffByUserId(staff);
        if(staffList.size()==0){
            return null;
        }else {
            staff=staffList.get(0);
            return staff;
        }
    }

    @Override
    public Integer updataStaffByUserId(staff staff) {
        /**
         * 确认入职,增加用户商品管理权限
         */
        if(staff.getStaffState()!=null){
            userRole userRole=new userRole();
            /**
             * 获取用户权限
             */
            userRole= userRoleService.getUserRoleByUserId(staff.getUserId());
            /**
             * 判断是客服还是销售,修改用户权限
             */
            if(staff.getRoleId().equals(1)){
                userRole.setRoleId(3);
            }else {
                userRole.setRoleId(6);
            }
            userRole.setUpdateTime(new Date());
            userRoleService.updateUserRole(userRole);
        }
        staff.setUpdateTime(new Date());
        return staffDao.updateStaffByUserId(staff);
    }
}
