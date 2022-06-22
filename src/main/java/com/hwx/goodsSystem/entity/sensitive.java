package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class sensitive implements Serializable {

    /**
     * id
     */
    private  Integer id;

    /**
     * 敏感词
     */
    private  String sensitiveText;

    /**
     * 修改人
     */
    private  Integer updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private  Date updateTime;
}
