package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.commonResult;
import com.hwx.goodsSystem.entity.user;

import java.util.List;

public interface userService {
    /**
     * 增
     */
    commonResult<user>  createUser(user user);

    /**
     * 删
     */
    commonResult<Integer>   deleteUser(Integer  id);

    /**
     * 改(模糊修改)
     */
    commonResult<user> updateUser(user user);

    /**
     * 查
     */
    commonResult<user>  getUserById(Integer id);

    /**
     * 根据昵称查询
     */
    List<user> getUser(String userNme);
}
