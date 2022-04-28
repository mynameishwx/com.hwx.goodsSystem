package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface roleDao {
    /**
     * 增
     */
    Integer createRole(role role);

    /**
     * 删
     */
    Integer deleteRole(Integer id);

    /**
     * 改
     */
    Integer updateRole(role role);

    /**
     * 查
     */
    role  getRoleById(Integer id);
}
