package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.user;

import java.util.List;

public interface userService {
    /**
     * 增
     */
    Integer  createUser(user user);

    /**
     * 删
     */
    Integer   deleteUser(Integer  id);

    /**
     * 改(模糊修改)
     */
    Integer updateUser(user user);

    /**
     * 查
     */
    user  getUserById(Integer id);

    /**
     * 根据昵称查询
     */
    user getUser(String userNme);
}
