package com.hwx.goodsSystem.util;

import com.hwx.goodsSystem.entity.user;
import org.springframework.stereotype.Component;

/**
 * 线程分离
 */
@Component
public class goodsThreadLocal{


    private  ThreadLocal<user> ThreadUser=new ThreadLocal<>();

    /**
     * 获取当前存放在线程的user数据
     * @return
     */
    public  user  getUser(){
        return  ThreadUser.get();
    }

    /**
     * 向当前线程存入user数据 (线程分离)
     * @param user
     */
    public  void  setUser(user user){
        ThreadUser.set(user);
    }

    /**
     * 删除当前存放在线程的user数据
     */
    public  void  delete(){
         ThreadUser.remove();
    }
}
