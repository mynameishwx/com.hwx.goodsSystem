package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.userDao;
import com.hwx.goodsSystem.Dao.userRoleDao;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class userimpl implements userService {

    @Autowired
    private userDao userDao;

    @Autowired
    private userRoleDao userRoleDao;

    @Override
    /**
     * 事务处理
     * 用户注册和用户角色创建(默认为普通用户)
     */
    @Transactional
    public  Integer createUser(user user) {
        /**
         * 密码MD5加密
         */
        user=this.ShiroMd5(user);
        /**
         * 添加时间
         */
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        userRole userrole=new userRole();
        /**
         * 用户注册
         */
        userDao.createUser(user);

        /**
         * 创建普通用户
         */
        userrole.setUserId(user.getId());
        userrole.setRoleId(1);
        userrole.setCreateTime(new Date());
        userrole.setUpdateTime(new Date());
        userRoleDao.createUserRole(userrole);

        return  1;
    }

    @Override
    public  Integer deleteUser(Integer id) {
       return  userDao.deleteUser(id);
    }

    @Override
    public Integer updateUser(user user) {
        /**
         * 将修改时间更新
         */
        user.setUpdateTime(new Date());
        return  userDao.updateUser(user);
    }

    @Override
    public user getUserById(Integer id) {

        return userDao.getUserById(id);
    }

    @Override
    public user getUser(String userNme) {
        List<user> user=userDao.getUser(userNme);
        if(user.size()==0){
            return null;
        }
        return user.get(0);
    }


    public user ShiroMd5(user user){
        /**
         * 拿到密码
         */
        String password=user.getUserPassword();
        /**
         * 获取 1到6 长度不定的盐
         */
        String saltShiro="b819e0acTc2#P3460*b755^5b@a1%b9&58f";
        Random Random=new Random();
        int  x= Random.nextInt(5)+1;
        String salt="";
        for (int i = 0; i <x; i++) {
            int p= Random.nextInt(30)+1;
            salt= String.valueOf(saltShiro.charAt(p))+salt;
        }
        /**
         * 进行MD5加密，这里的散列次数要与配置的散列次数一致
         */
        Md5Hash Md5Hash=new Md5Hash(password,salt,206);

        user.setUserPassword(Md5Hash.toString());
        user.setSalt(salt);
        return user;
    }

}
