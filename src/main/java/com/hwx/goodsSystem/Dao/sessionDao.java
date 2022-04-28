package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface sessionDao {

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
    List<session> getSessionByUserId(Integer id);

    /**
     * 根据session查
     */
    List<session> getSessionBySession(String session);
}
