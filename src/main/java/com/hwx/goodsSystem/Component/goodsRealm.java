package com.hwx.goodsSystem.Component;

import com.hwx.goodsSystem.entity.power;
import com.hwx.goodsSystem.entity.role;
import com.hwx.goodsSystem.entity.rolePower;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.service.rolePowerService;
import com.hwx.goodsSystem.service.roleService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.service.userService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
public class goodsRealm extends AuthorizingRealm {


    @Autowired
    private  userService userService;

    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Autowired
    private roleService roleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo  SimpleAuthorizationInfo=new SimpleAuthorizationInfo();
        /**
         * 通过userid查询权限，并将查询到的权限添加到用户
         */
       if(goodsThreadLocal.getUser()!=null){
           List<power> powerList=new ArrayList<>();
           int userID=goodsThreadLocal.getUser().getId();
           if(userID==0){
               log.warn("用户未登录");
               return  SimpleAuthorizationInfo;
           }
           powerList=userRoleService.getPowerByUserId(userID);
           if(powerList.size()!=0){
               Iterator<power> power=powerList.iterator();
               String powerUrl="";
               while (power.hasNext()){
                   powerUrl=power.next().getUrl();
                   SimpleAuthorizationInfo.addStringPermission(powerUrl);
                   log.info("用户:"+goodsThreadLocal.getUser().getId()+"  添加了"+powerUrl+"权限");
               }
           }else {
               log.warn("权限认证错误:  未查找到用户权限!");
           }
       }
        return SimpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken UsernamePasswordToken= (UsernamePasswordToken) token;
        String name=UsernamePasswordToken.getUsername();
        String pass= String.valueOf(UsernamePasswordToken.getPassword());

        user user=userService.getUser(name);
        return new SimpleAuthenticationInfo(
                name,
                user.getUserPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
    }
}
