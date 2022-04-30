package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.rolePowerDao;
import com.hwx.goodsSystem.entity.rolePower;
import com.hwx.goodsSystem.service.rolePowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class rolePowerimpl implements rolePowerService {

    @Autowired
    private rolePowerDao rolePowerDao;

    @Override
    public Integer createRolePower(rolePower rolePower) {
        rolePower.setCreateTime(new Date());
        rolePower.setUpdateTime(new Date());
        return  rolePowerDao.createRolePower(rolePower);
    }

    @Override
    public Integer deleteRolePower(Integer id) {
        return rolePowerDao.deleteRolePower(id);
    }

    @Override
    public rolePower updateRolePower(rolePower rolePower) {
        rolePower.setUpdateTime(new Date());
        return rolePowerDao.updateRolePower(rolePower);
    }

    @Override
    public rolePower getByid(Integer id) {
        rolePower rolePower=new rolePower();
        rolePower=rolePowerDao.getByid(id);
        return rolePower;
    }

    @Override
    public List<rolePower> getByRoleId(Integer roleId) {
        rolePower rolePower=new rolePower();
        List<rolePower> rolePowerList=new ArrayList<>();
        rolePowerList=rolePowerDao.getByRoleId(roleId);
        return   rolePowerList;
    }


}
