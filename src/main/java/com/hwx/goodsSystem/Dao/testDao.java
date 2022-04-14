package com.hwx.goodsSystem.Dao;

import com.hwx.goodsSystem.entity.test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface testDao {
    List<test> gettest();
}
