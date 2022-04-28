package com.hwx.goodsSystem.Component;

import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.service.userService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class goodsRealm extends AuthorizingRealm {

    @Autowired
    private userService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
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
