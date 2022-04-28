package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class session implements Serializable {

    private  Integer id;

    private  Integer userId;

    private  String  session;

    private  Date createTime;

    private  Date updateTime;
}
