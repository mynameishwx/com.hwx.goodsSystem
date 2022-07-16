package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface keywordDao {

    /**
     * 增加标签
     * @param keyword
     * @return
     */
    Integer addKeyword(keyword keyword);

    /**
     * 根据删除标签
     * @param id
     * id
     * @return
     */
    Integer deleteKeywordByid(Integer id);

    /**
     * 根据标签删除
     * @param text
     * @return
     */
    Integer deleteKeywordByText(String text);

    /**
     * 模糊更改
     * @param Keyword
     * @return
     */
    Integer updateKeywordByid(keyword Keyword);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    keyword getKeywordById(Integer id);

    /**
     * 根据标签名查询
     */
    keyword getKeywordByText(String classText);


    /**
     * 查询所有标签
     * @return
     */
    List<keyword> getKeyword(keyword keyword);

}
