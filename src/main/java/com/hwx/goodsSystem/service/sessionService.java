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
}
