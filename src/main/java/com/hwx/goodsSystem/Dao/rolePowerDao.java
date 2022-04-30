package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.rolePower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface rolePowerDao {

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
