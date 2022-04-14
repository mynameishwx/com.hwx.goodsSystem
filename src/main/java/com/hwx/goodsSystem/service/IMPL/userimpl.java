package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.userDao;
import com.hwx.goodsSystem.entity.commonResult;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class userimpl implements userService {

    @Autowired
    private userDao userDao;

    @Override
    public  commonResult<user> createUser(user user) {
        if(userDao.createUser(user)!=0){
            return new commonResult<user>(200,"创建用户成功",user);
        }else{
            log.info("user创建用户失败  :"+user.toString());
            return  new  commonResult<user>(500,"创建用户失败",null);
        }
    }

    @Override
    public  commonResult<Integer> deleteUser(Integer id) {
        if(userDao.deleteUser(id)!=0){
            return new commonResult<Integer>(200,"删除用户成功",null);
        }else{
            log.info("user删除用户失败  id:"+id);
            return new commonResult<Integer>(500,"删除用户失败,id:"+id,id);
        }
    }

    @Override
    public commonResult<user> updateUser(user user) {
            boolean update=!user.getPetName().equals("") || !user.getAge().equals("") ||
                    !user.getDwell().equals("") || user.getSex()!=null ||
                    !user.getImageUrl().equals("") || !user.getSignature().equals("");

            if(update){
                if(userDao.updateUser(user)!=0){
                    return  new commonResult<user>(200,"修改成功");
                }else{
                    log.info("user未成功修改  :"+user.toString());
                    return new commonResult<user>(500,"未成功修改,参数为空:"+user);
                }
            }else{
                log.info("user未成功修改,参数为空:"+user.toString());
                return new commonResult<user>(400,"未成功修改,参数为空:"+user);
            }
    }

    @Override
    public commonResult<user> getUserById(Integer id) {
        user user=new user();
        user =userDao.getUserById(id);
        if(user!=null){
          new   commonResult<user>(200,"查询成功 :",user);
        }else {
            new commonResult<user>(200,"没有查询该数据,id:"+id,null);
        }
        return null;
    }

    @Override
    public List<user> getUser(String userNme) {
        return userDao.getUser(userNme);
    }
}
