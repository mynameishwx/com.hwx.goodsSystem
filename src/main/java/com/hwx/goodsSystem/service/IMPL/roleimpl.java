package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.roleDao;
import com.hwx.goodsSystem.entity.role;
import com.hwx.goodsSystem.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class roleimpl implements roleService {

    @Autowired
    private roleDao roleDao;

    @Override
    public Integer createRole(role role) {
        return roleDao.createRole(role);
    }

    @Override
    public Integer deleteRole(Integer id) {
        return roleDao.deleteRole(id);
    }

    @Override
    public Integer updateRole(role role) {
        return updateRole(role);
    }

    @Override
    public role getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }
}
