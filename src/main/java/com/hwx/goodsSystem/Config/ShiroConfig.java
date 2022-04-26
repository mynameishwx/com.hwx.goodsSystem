package com.hwx.goodsSystem.Config;

import com.hwx.goodsSystem.Component.goodsRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * shiro的过滤器工厂
     * @param DefaultWebSecurityManager
     * @return
     */
 @Bean
 public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager DefaultWebSecurityManager){
     ShiroFilterFactoryBean ShiroFilterFactoryBean=new ShiroFilterFactoryBean();
     ShiroFilterFactoryBean.setSecurityManager(DefaultWebSecurityManager);

     Map<String,String> String=new HashMap<>();


     return ShiroFilterFactoryBean;
 }

    /**
     * 默认的安全经理
     * @param Realm
     * @return
     */
 @Bean
 public DefaultWebSecurityManager DefaultWebSecurityManager(Realm Realm){
     DefaultWebSecurityManager DefaultWebSecurityManager=new DefaultWebSecurityManager();
     DefaultWebSecurityManager.setRealm(Realm);
     return DefaultWebSecurityManager;
 }

 @Bean
 public Realm Realm(){
     goodsRealm goodsRealm=new goodsRealm();

     /**
      * 创建一个哈希凭证匹配程序
      */
     HashedCredentialsMatcher HashedCredentialsMatcher=new HashedCredentialsMatcher();

     /**
      * 加密方法
      */
     HashedCredentialsMatcher.setHashAlgorithmName("MD5");

     /**
      * 散列次数
      */
     HashedCredentialsMatcher.setHashIterations(206);

     /**
      * 将匹配机制给到Realm
      */
     goodsRealm.setCredentialsMatcher(HashedCredentialsMatcher);

     return  goodsRealm;
 }
}
