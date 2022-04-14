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

    /**
     * 反馈消息
     */
    private String  message;

    /**
     * 返回的数据
     */
    private T  Data;

    /**
     * 两参构造方法
     * @param state
     * @param message
     */
    public commonResult(Integer state,String message){
         this(state,message,null);
    }

}
