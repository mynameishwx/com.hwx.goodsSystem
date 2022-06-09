package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.shop;
import com.hwx.goodsSystem.entity.staff;

import java.util.List;

public interface staffService {
    /**
     * 增
     */
    Integer createStaff(staff staff, shop shop);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Integer deleteStaffById(Integer id);

    /**
     * 根据状态确认是否删除
     * @param state
     * 对方是否同意
     * @return
     */
    Integer deleteStaffByState(Integer state);

    /**
     * 改(状态,工资，职位，到期时间)
     */
    Integer updateStaff(staff staff);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    staff getStaffById(Integer id);

    /**
     * 根据店铺id查询
     * @param shopId
     * 店铺id
     * @return
     */
    List<staff> getStaffByShopId(Integer shopId);


    /**
     * 根据用户id查询
     * @param userId
     * 用户id
     * @return
     */
    staff getStaffByUserId(staff staff);

    /**
     * 根据用户id确认入职状态
     * @param staff
     * @return
     */
    Integer updataStaffByUserId(staff staff);
}
