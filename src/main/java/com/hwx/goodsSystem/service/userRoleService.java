package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.userRole;

public interface userRoleService {
    /**
     * 添加一个用户权限
     */
    Integer createUserRole(userRole userRole);

    /**
     *  删除一个用户权限
     */
    Integer deleteUserRoleById(Integer id);

    /**
     * 修改一个用户权限
     */
    Integer updateUserRole(userRole userRole);

    /**
     * 查询一个用户权限
     */
    userRole getUserRole(Integer id);

    /**
     * 根据用户id查询用户权限
     */
    userRole getUserRoleByUserId(Integer id);

    /**
     * 根据用户id查找用户角色
     */

}
