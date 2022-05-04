package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.goodsDao;
import com.hwx.goodsSystem.entity.goods;
import com.hwx.goodsSystem.service.goodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class goodsimpl implements goodsService {

    @Autowired
    private goodsDao goodsDao;

    @Override
    public Integer createGoods(goods goods) {
        goods.setCreateTime(new Date());
        goods.setUpdateTime(new Date());
        return goodsDao.createGoods(goods);
    }

    @Override
    public Integer deleteGoods(Integer id) {
        return goodsDao.deleteGoods(id);
    }

    @Override
    public goods updateGoods(goods goods) {
        goods.setUpdateTime(new Date());
        return goodsDao.updateGoods(goods);
    }

    @Override
    public goods getGoodsById(Integer id) {
        return goodsDao.getGoodsById(id);
    }

    @Override
    public List<goods> getGoodsByName(String goodsName) {
        return goodsDao.getGoodsByName(goodsName);
    }

    @Override
    public List<goods> getGoodsShopId(Integer shopId) {
        return goodsDao.getGoodsShopId(shopId);
    }

    @Override
    public List<goods> getGoodsLiMit(Integer start, Integer qty,Integer shopId) {
        return goodsDao.getGoodsLiMit(start,qty,shopId);
    }
}
