package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class rolePower implements Serializable {

    /**
     * 角色权限表id
     */
    private  Integer id;

    /**
     * 角色id
     */
    private  Integer roleId;

    /**
     * 权限id
     */
    private  Integer powerId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
