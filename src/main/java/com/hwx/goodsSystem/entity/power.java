package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class power implements Serializable {

    private  Integer id;

    /**
     * 权限的url
     */
    private String  url;

    /**
     * 创建时间
     */
    private Date  createTime;

    /**
     * 修改时间
     */
    private Date  updateTime;
}
