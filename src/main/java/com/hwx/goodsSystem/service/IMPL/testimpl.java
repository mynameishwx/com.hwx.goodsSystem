package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.testDao;
import com.hwx.goodsSystem.entity.test;
import com.hwx.goodsSystem.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class testimpl implements testService {

    @Autowired
    private testDao testDao;

    @Override
    public List<test> gettest() {
        return testDao.gettest();
    }
}
