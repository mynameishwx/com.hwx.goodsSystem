package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.goodsMarkDao;
import com.hwx.goodsSystem.entity.goodsMark;
import com.hwx.goodsSystem.service.goodsMarkService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class goodsMarkimpl implements goodsMarkService {

    @Autowired
    private goodsMarkDao goodsMarkDao;

    @Override
    public Integer createGoodsMark(@NonNull goodsMark goodsMark) {
        return goodsMarkDao.createGoodsMark(goodsMark);
    }

    @Override
    public Integer deleteById(@NonNull Integer id) {
        return goodsMarkDao.deleteById(id);
    }

    @Override
    public Integer updateByid(@NonNull goodsMark goodsMark) {
        return goodsMarkDao.updateByid(goodsMark);
    }

    @Override
    public ArrayList<goodsMark> selectByid(@NonNull goodsMark goodsMark) {
        return goodsMarkDao.selectByid(goodsMark);
    }
}
