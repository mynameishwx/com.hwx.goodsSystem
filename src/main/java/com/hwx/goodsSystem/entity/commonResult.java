package com.hwx.goodsSystem.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Result封装类
 * @param <T>
 */
@Data
@NoArgsConstructor    //空参构造函数
@AllArgsConstructor  //全参构造函数
public class commonResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer  state;


    private String  message;

    private T  t;


    public commonResult(Integer state,String message){
         this(state,message,null);
    }

}
