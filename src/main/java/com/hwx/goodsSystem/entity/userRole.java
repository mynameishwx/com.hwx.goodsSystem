package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userRole implements Serializable {

    private  Integer  id;

    /**
     * 用户id
     */
    private  Integer userId;

    /**
     * 角色id
     */
    private  Integer roleId;

    /**
     * 用户角色表创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date  updateTime;
}
