package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class keyword implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 标签文本
     */
    private String classText;

    /**
     * 上级标签ID
     */
    private Integer superior;

    /**
     * 扩展字段一
     */
    private String extend_One;

    /**
     * 扩展字段二
     */
    private String extend_Two;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
