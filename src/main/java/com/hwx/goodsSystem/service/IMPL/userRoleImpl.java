package com.hwx.goodsSystem.service.IMPL;


import com.hwx.goodsSystem.Dao.userRoleDao;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.userRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class userRoleImpl implements userRoleService {

    @Autowired
    private userRoleDao userRoleDao;


    @Override
    public Integer createUserRole(userRole userRole) {
        userRole.setCreateTime(new Date());
        userRole.setUpdateTime(new Date());
        return  userRoleDao.createUserRole(userRole);
    }

    @Override
    public Integer deleteUserRoleById(Integer id) {
       return  userRoleDao.deleteUserRoleById(id);
    }

    @Override
    public Integer updateUserRole(userRole userRole) {
        userRole.setUpdateTime(new Date());
       return userRoleDao.updateUserRole(userRole);
    }

    @Override
    public userRole getUserRole(Integer id) {
      return userRoleDao.getUserRole(id);
    }

    @Override
    public userRole getUserRoleByUserId(Integer id) {
        List<userRole> list=userRoleDao.getUserRoleByUserId(id);
        if(list.size()==0){
            return  null;
        }
        return list.get(0);
    }
}
