package com.hwx.goodsSystem.service;

import com.hwx.goodsSystem.entity.session;

import java.util.List;

public interface sessionService {

    /**
     * 增
     */
    Integer createSession(session session);


    /**
     * 删
     */
    Integer deleteSessionByid(Integer id);

    /**
     * 根据用户id删除
     */
    Integer deleteSessionByUserId(Integer id);


    /**
     * 改
     */
    Integer updateSession(session session);


    /**
     * 查
     */
    Integer getSessionByid(Integer id);


    /**
     * 根据用户id查询
     */
    session getSessionByUserId(Integer id);

    /**
     * 根据session查
     */
    session getSessionBySession(String session);
}
