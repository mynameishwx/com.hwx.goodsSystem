package com.hwx.goodsSystem.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hwx.goodsSystem.entity.role;
import com.hwx.goodsSystem.entity.session;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.service.roleService;
import com.hwx.goodsSystem.service.sessionService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.service.userService;
import com.hwx.goodsSystem.util.goodsJWT;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class goodsPand implements HandlerInterceptor {

    /**
     * 导入JWT通用包
     */
    @Autowired
    private goodsJWT goodsJWT;

    @Autowired
    private sessionService sessionService;

    @Autowired
    private userService userService;

    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private roleService roleService;
    /**
     * 导入ThreadLocal
     */
    @Autowired
    private goodsThreadLocal goodsThreadLocal;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

      Object  sessiont=new Object();
      sessiont= request.getSession().getAttribute("session");
      String token="";
        if(sessiont!=null){
          token= (String) sessiont;
            if(!token.equals("")){
                try {
                    /**
                     * 验证JWT令牌
                     */
                    DecodedJWT validate = goodsJWT.validate(token);
                    /**
                     * 查询session
                     */
                    session session=new session();
                    session=sessionService.getSessionBySession(token);
                    if(session!=null){
                        /**
                         * 将查询到的user装入ThreadLocal
                         */
                        goodsThreadLocal.setUser(userService.getUserById(session.getUserId()));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    /**
                     * 令牌有问题，或者没有查询到session，则将user信息卸掉和将过期的session删除
                     */
                    goodsThreadLocal.setUser(null);
                    session session=new session();
                    session=sessionService.getSessionBySession(token);
                    if(session!=null){
                        sessionService.deleteSessionByid(session.getId());
                    }
                }
            }
       }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        user user=new user();
        user= goodsThreadLocal.getUser();
        if(user!=null && modelAndView!=null){
            /**
             * 将密码与盐隐藏
             */
            user.setUserPassword(null);
            if(user.getImageUrl()==null){
                user.setImageUrl("/img/hwx.png");
            }
            /**
             * 将角色传递给前端
             */
            role role= new role();

            role=roleService.getRoleById(userRoleService.getUserRoleByUserId(user.getId()).getRoleId());
            if(role==null){
                log.warn("查询用户权限为空 , 用户id :"+user.getId());
            }
            if(!role.getRoleName().equals("user")){
                modelAndView.addObject("role","shopAdmin");
            }
            log.warn("用户角色为："+role.getRoleName());
            /**
             * 置空
             */
            user.setSalt(null);
            /**
             * 将user信息持有
             */
            modelAndView.addObject("userLogin",user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
              goodsThreadLocal.delete();
    }
}
