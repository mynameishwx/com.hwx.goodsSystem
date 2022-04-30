package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.power;
import com.hwx.goodsSystem.entity.userRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userRoleDao {
    /**
     * 增
     */
    Integer createUserRole(userRole userRole);

    /**
     *  删
     */
    Integer deleteUserRoleById(Integer id);

    /**
     * 改
     */
    Integer updateUserRole(userRole userRole);

    /**
     * 根据id查询
     */
    userRole getUserRole(Integer id);

    /**
     * 根据用户id查询
     */
    List<userRole>  getUserRoleByUserId(Integer id);

    /**
     * 根据用户id查询他的权限
     */
    List<power> getPowerByUserId(Integer id);
}
