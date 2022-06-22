package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class staff implements Serializable {

    private Integer id;

    /**
     * 员工id
     */
    @NonNull
    private Integer userId;

    /**
     * 员工所在店铺的拥有者ID
     */
    @NonNull
    private Integer shopId;

    /**
     * 员工职位, 1为销售，2为客服
     */
    private Integer roleId;

    /**
     * 是否同意成为员工(0为未确认，1为确认)
     */
    private Integer staffState;

    /**
     * 工资
     */
    private Integer money;

    /**
     * 到期时间
     */
    private Date outTime;


    /**
     * 扩展字段
     */
    private String extendOne;

    private Date createTime;

    private Date updateTime;

}
