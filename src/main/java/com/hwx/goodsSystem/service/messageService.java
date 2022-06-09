package com.hwx.goodsSystem.service;


import com.hwx.goodsSystem.entity.message;

import java.util.List;

public interface messageService {
    /**
     * 增加
     */
    Integer createMessage(message message);


    /**
     * 根据id删除
     * @param id
     * @return
     */
    Integer deleteMessage(Integer id);

    /**
     * 根据会话删除
     * @param logo
     * @return
     */
    Integer deleteMessageLogo(String logo);

    /**
     * 修改已读状态
     * @param message
     * @return
     */
    message updateMessageState(message message);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    message getMessageById(Integer id);

    /**
     * 根据会话查询
     */
    List<message> getMessageByLogo(message message);

    /**
     * 根据接收者id查询
     * @param id
     * @return
     */
    List<message> getMessageMy(Integer id);

    /**
     * 根据接收者id查询未读信息
     * @param id
     * @return
     */
    List<message> getMessageNoMy(Integer id);

}
