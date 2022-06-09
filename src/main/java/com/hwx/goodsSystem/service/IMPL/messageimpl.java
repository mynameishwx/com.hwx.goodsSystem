package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.messageDao;
import com.hwx.goodsSystem.entity.message;
import com.hwx.goodsSystem.service.messageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class messageimpl implements messageService {

    @Autowired
    private messageDao messageDao;

    @Override
    public Integer createMessage(message message) {
        message.setCreateTime(new Date());
        message.setUpdateTime(new Date());
        return messageDao.createMessage(message);
    }

    @Override
    public Integer deleteMessage(Integer id) {
        return messageDao.deleteMessage(id);
    }

    @Override
    public Integer deleteMessageLogo(String logo) {
        return messageDao.deleteMessageLogo(logo);
    }

    @Override
    public message updateMessageState(message message) {
        message.setUpdateTime(new Date());
        return messageDao.updateMessageState(message);
    }

    @Override
    public message getMessageById(Integer id) {
        return messageDao.getMessageById(id);
    }

    @Override
    public List<message> getMessageByLogo(message message) {
        return messageDao.getMessageByLogo(message);
    }

    @Override
    public List<message> getMessageMy(Integer id) {
        return messageDao.getMessageMy(id);
    }

    @Override
    public List<message> getMessageNoMy(Integer id) {
        return messageDao.getMessageNoMy(id);
    }
}
