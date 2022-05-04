package com.hwx.goodsSystem.Component;

import com.hwx.goodsSystem.entity.*;
import com.hwx.goodsSystem.service.*;
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
    private powerService powerService;

    @Autowired
    private rolePowerService rolePowerService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo  SimpleAuthorizationInfo=new SimpleAuthorizationInfo();
        /**
         * 通过userid查询权限，并将查询到的权限添加到用户
         */
       if(goodsThreadLocal.getUser()!=null){

          userRole userRole= userRoleService.getUserRoleByUserId(goodsThreadLocal.getUser().getId());
          List<rolePower> rolePowerList= rolePowerService.getByRoleId(userRole.getRoleId());
          Iterator<rolePower> rolePower=rolePowerList.iterator();
          power power=new power();
          while (rolePower.hasNext()){
              power=powerService.getPowerById(rolePower.next().getPowerId());
              SimpleAuthorizationInfo.addStringPermission(power.getUrl());
              log.info("用户:"+goodsThreadLocal.getUser().getId()+"  添加了"+power.getUrl()+"权限");
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
