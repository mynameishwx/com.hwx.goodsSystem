package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class message implements Serializable {

    private Integer id;

    /**
     * 发送人id
     */
    private Integer writeId;

    /**
     * 接收者Id
     */
    private Integer readId;

    /**
     * 信息权限
     */
    private Integer messagePower;


    /**
     * 信息类型
     * 0:系统信息
     * 1:用户聊天信息(文本)
     * 2:用户聊天信息(图片)
     * 3:店铺类员工信息
     * 4:好友申请信息
     */
    private Integer messageClass;

    /**
     * 信息状态(0为未读，1为已读)
     */
    private Integer messageState;

    /**
     * 会话简写
     */
    private String writeRead;

    /**
     * 信息
     */
    private String message;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
