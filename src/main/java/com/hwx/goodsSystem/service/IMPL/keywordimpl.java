package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.keywordDao;
import com.hwx.goodsSystem.entity.keyword;
import com.hwx.goodsSystem.service.keywordService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class keywordimpl implements keywordService {

    @Autowired
    private keywordDao keywordDao;

    @Override
    public Integer addKeyword(keyword keyword){
        keyword.setCreateTime(new Date());
        keyword.setUpdateTime(new Date());
        return keywordDao.addKeyword(keyword);
    }

    @Override
    public Integer deleteKeywordByid(Integer id) {

        return keywordDao.deleteKeywordByid(id);
    }

    @Override
    public Integer deleteKeywordByText(String text) {
        return keywordDao.deleteKeywordByText(text);
    }

    @Override
    public Integer updateKeywordByid(keyword Keyword) {
        if(Keyword.getId()==null){
            log.warn("keywoed类的id为空，无法更改");
            return null;
        }
        Keyword.setUpdateTime(new Date());
        return keywordDao.updateKeywordByid(Keyword);
    }

    @Override
    public keyword getKeywordById(Integer id) {
        return keywordDao.getKeywordById(id);
    }

    @Override
    public keyword getKeywordByText(String classText) {
        if(classText.equals("")){
           log.warn("根据标签文本查询标签ID失败: 标签文本为空");
        }
        return keywordDao.getKeywordByText(classText);
    }

    @Override
    public List<keyword> getKeyword(keyword keyword) {
        return keywordDao.getKeyword(keyword);
    }
}
