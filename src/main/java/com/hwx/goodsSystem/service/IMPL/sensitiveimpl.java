package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.sensitiveDao;
import com.hwx.goodsSystem.entity.sensitive;
import com.hwx.goodsSystem.service.sensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class sensitiveimpl implements sensitiveService {

    @Autowired
    private sensitiveDao sensitiveDao;

    @Override
    public Integer addSenitive(sensitive sensitive) {
        sensitive.setCreateTime(new Date());
        sensitive.setUpdateTime(new Date());
        return sensitiveDao.addSenitive(sensitive);
    }

    @Override
    public Integer deleteSensitive(Integer id) {

        return sensitiveDao.deleteSensitive(id);
    }

    @Override
    public Integer deleteByText(String text) {
        return sensitiveDao.deleteByText(text);
    }

    @Override
    public Integer updateByid(sensitive sensitive) {
        sensitive.setUpdateTime(new Date());
        return sensitiveDao.updateByid(sensitive);
    }

    @Override
    public sensitive getSensitiveByid(Integer id) {
        return sensitiveDao.getSensitiveByid(id);
    }

    @Override
    public sensitive getSensitiveByText(String text) {
        return sensitiveDao.getSensitiveByText(text);
    }

    @Override
    public List<sensitive> getSensitive() {
        return sensitiveDao.getSensitive();
    }
}
