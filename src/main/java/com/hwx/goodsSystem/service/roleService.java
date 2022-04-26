package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.role;

public interface roleService {
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
