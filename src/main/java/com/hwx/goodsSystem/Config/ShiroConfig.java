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
     /*
  anno	不需要授权、登录就可以访问。eg:/index
authc	 需要登录授权才能访问。eg：/用户中心
authcBasic	Basic HTTP身份验证拦截器
logout	退出拦截器。退出成功后，会 redirect到设置的/URI
noSessionCreation	不创建会话连接器
perms	授权拦截器:perm['user:create']
port	端口拦截器.eg:port[80]
rest	rest风格拦截器
roles	角色拦截器。eg：role[administrator]
ssl	ssl拦截器。通过https协议才能通过
user	用户拦截器。eg：登录后（authc），第二次没登陆但是有记住我(remmbner)都可以访问
 */
     /**
      * 商品界面
      */
     String.put("/goods/create","authc");
     String.put("/goods/List","authc");

     String.put("/static/**","anon");

     /**
      * 商铺管理
      */
     String.put("/shop/**", "authc");
     /**
      * 设置管理
      */
     String.put("/setting/**", "authc");

     /**
      * 订单管理
      */
     String.put("/order/**", "authc");


     /**
      * 好友管理
      */
     String.put("/crony/**", "authc");


     /**
      * 放行静态资源
      */
     String.put("**/css/**","anon");
     String.put("**/js/**","anon");
     String.put("**/img/**","anon");
     String.put("**/shopImg/**","anon");

     ShiroFilterFactoryBean.setLoginUrl("/login");
     ShiroFilterFactoryBean.setFilterChainDefinitionMap(String);
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
