package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.goodsKeywordDao;
import com.hwx.goodsSystem.entity.goodsKeyword;
import com.hwx.goodsSystem.service.goodsKeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class goodsKeywordimpl implements goodsKeywordService {

    @Autowired
    private goodsKeywordDao goodsKeywordDao;

    @Override
    public Integer addGoodsKeyword(goodsKeyword goodsKeyword) {
        goodsKeyword.setCreateTime(new Date());
        goodsKeyword.setUpdateTime(new Date());
        return goodsKeywordDao.addGoodsKeyword(goodsKeyword);
    }

    @Override
    public Integer deleteGoodsKeywordByid(Integer id) {
        return goodsKeywordDao.deleteGoodsKeywordByid(id);
    }

    @Override
    public Integer deleteGoodsKeyword(goodsKeyword goodsKeyword) {
        if(goodsKeyword.getId()==null){
            log.warn("删除goodsKeyword失败: ID为空");
            return  null;
        }
        return goodsKeywordDao.deleteGoodsKeyword(goodsKeyword);
    }

    @Override
    public Integer updateGoodsKeyword(goodsKeyword goodsKeyword) {
        goodsKeyword.setUpdateTime(new Date());
        return goodsKeywordDao.updateGoodsKeyword(goodsKeyword);
    }

    @Override
    public List<goodsKeyword> getGoodsKeywordByGoodsId(Integer goodsId) {
        return goodsKeywordDao.getGoodsKeywordByGoodsId(goodsId);
    }

    @Override
    public goodsKeyword getGoodsKeywordByid(Integer id) {
        return goodsKeywordDao.getGoodsKeywordByid(id);
    }
}
