package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.rolePower;

import java.util.List;

public interface rolePowerService {
    /**
     * 增加一个用户权限
     * @param rolePower
     * @return
     */
    Integer createRolePower(rolePower rolePower);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    Integer deleteRolePower(Integer id);

    /**
     * 修改角色权限
     * @param rolePower
     * @return
     */
    rolePower updateRolePower(rolePower rolePower);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    rolePower getByid(Integer id);

    /**
     * 通过角色id查询
     * @param roleId
     * @return
     */
    List<rolePower> getByRoleId(Integer roleId);
}
