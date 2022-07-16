package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.sessionDao;
import com.hwx.goodsSystem.entity.session;
import com.hwx.goodsSystem.service.sessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class sessionimpl implements sessionService {

    @Autowired
    private sessionDao sessionDao;

    @Override
    public Integer createSession(session session) {
        session.setExist(0);
        session.setCreateTime(new Date());
        session.setUpdateTime(new Date());
        return sessionDao.createSession(session);
    }

    @Override
    public Integer deleteSessionByid(Integer id) {
        return sessionDao.deleteSessionByid(id);
    }

    @Override
    public Integer deleteSessionByUserId(Integer id) {
        return sessionDao.deleteSessionByUserId(id);
    }

    @Override
    public Integer updateSession(session session) {
        session.setUpdateTime(new Date());
        return sessionDao.updateSession(session);
    }

    @Override
    public Integer getSessionByid(Integer id) {
        return sessionDao.getSessionByid(id);
    }

    @Override
    public List<session> getSession(session session) {
        return sessionDao.getSession(session);
    }

    @Override
    public List<session> getSessionByUserId(Integer id) {
        List<session> sessions = new ArrayList<>();
        sessions = sessionDao.getSessionByUserId(id);
        if (sessions.size() == 0) {
            log.error("未根据用户ID+" + id + "查找session");
            return null;
        } else {
            return sessions;
        }
    }

    @Override
    public session getSessionBySession(String session) {
        List<session> sessions=new ArrayList<>();
        sessions= sessionDao.getSessionBySession(session);
        if(sessions.size()!=0){
            return sessions.get(0);
        }else return null;
    }
}
