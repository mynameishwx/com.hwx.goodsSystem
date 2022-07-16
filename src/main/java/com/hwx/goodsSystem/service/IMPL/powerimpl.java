package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.powerDao;
import com.hwx.goodsSystem.entity.power;
import com.hwx.goodsSystem.service.powerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class powerimpl implements powerService {

    @Autowired
    private powerDao powerDao;
    @Override
    public Integer createPower(power power) {
        power.setCreateTime(new Date());
        power.setUpdateTime(new Date());
        return powerDao.createPower(power);
    }

    @Override
    public Integer deletePower(Integer id) {
        return powerDao.deletePower(id);
    }

    @Override
    public Integer updatePower(power power) {
        return powerDao.updatePower(power);
    }

    @Override
    public power getPowerById(Integer id) {
        return powerDao.getPowerById(id);
    }
}
