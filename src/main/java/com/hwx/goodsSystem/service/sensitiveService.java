package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.sensitive;

import java.util.List;

public interface sensitiveService {
    /**
     * 插入一条敏感词
     * @param sensitive
     * @return
     */
    Integer addSenitive(sensitive sensitive);

    /**
     * 根据id删除敏感词
     * @param id
     * @return
     */
    Integer deleteSensitive(Integer id);

    /**
     * 根据文本删除敏感词
     * @param text
     * 敏感词文本
     * @return
     */
    Integer deleteByText(String text);

    /**
     * 根据id修改敏感词
     * @param sensitive
     * @return
     */
    Integer updateByid(sensitive sensitive);

    /**
     * 根据id查询敏感词
     * @param id
     * @return
     */
    sensitive getSensitiveByid(Integer id);

    /**
     * 根据文本查询敏感词
     * @param text
     * @return
     */
    sensitive getSensitiveByText(String text);

    /**
     * 查询所有敏感词
     * @return
     */
    List<sensitive> getSensitive();
}
