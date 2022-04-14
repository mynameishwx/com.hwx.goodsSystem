package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userDao {

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
    Integer  updateUser(user user);

    /**
     * 查
     */
    user  getUserById(Integer id);

    /**
     * 根据昵称查询
     */
    List<user> getUser(String userNme);

}
