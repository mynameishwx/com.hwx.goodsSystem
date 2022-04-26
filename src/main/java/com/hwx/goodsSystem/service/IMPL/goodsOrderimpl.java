package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.goodsOrderDao;
import com.hwx.goodsSystem.entity.goodsOrder;
import com.hwx.goodsSystem.service.goodsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class goodsOrderimpl implements goodsOrderService {

    @Autowired
    private goodsOrderDao goodsOrderDao;

    @Override
    public Integer createGoodsOrder(goodsOrder goodsOrder) {
        goodsOrder.setCreateTime(new Date());
        goodsOrder.setUpdateTime(new Date());
       return goodsOrderDao.createGoodsOrder(goodsOrder);
    }

    @Override
    public Integer deleteGoodsOrder(Integer id) {
        return  goodsOrderDao.deleteGoodsOrder(id);
    }

    @Override
    public Integer updateGoodsOrder(goodsOrder goodsOrder) {
        goodsOrder.setUpdateTime(new Date());
       return  goodsOrderDao.updateGoodsOrder(goodsOrder);
    }

    @Override
    public goodsOrder getGoodsOrder(Integer id) {
        return  goodsOrderDao.getGoodsOrder(id);
    }
}
