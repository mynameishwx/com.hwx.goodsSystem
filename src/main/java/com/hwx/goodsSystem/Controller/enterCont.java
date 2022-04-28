package com.hwx.goodsSystem.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hwx.goodsSystem.entity.session;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.roleService;
import com.hwx.goodsSystem.service.sessionService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.service.userService;
import com.hwx.goodsSystem.util.commonResult;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@RestController
/**
 * 解决跨域问题，这个类所有接口同意 http://127.0.0.1:8080 跨域访问
 */
@CrossOrigin(origins ="http://127.0.0.1:8080")

@RequestMapping("/Login")
public class enterCont {

    @Autowired
    private roleService roleService;

    @Autowired
    private userService userService;

    @Autowired
    private sessionService sessionService;

    @Autowired
    private userRoleService userRoleService;

    @PostMapping("/enter")
    public commonResult<user> enter(@RequestParam("name")String name,
                                      @RequestParam("password")String password,
                                      HttpSession HttpSession) throws  RuntimeException{
        Subject Subject= SecurityUtils.getSubject();
        /**
         *  获取正在登录的用户的信息
         */
        user user=new user();
        user= userService.getUser(name);
        if(user==null){
            return     new commonResult<user>(500,"用户未注册",null);
        }
        /**
         * shiro登录验证
         */
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);
        Subject.login(token);

        /**
         * 将session存储
         */
        String session= UUID.randomUUID().toString();
        sessionService.createSession(new session(null,user.getId(),session,null,null));

        /**
         * 将产生的UUID放到session中
         */
        HttpSession.setAttribute("session",session);


        /**
         *  判断返回普通页面还是管理员界面
         */
        userRole userRolle=userRoleService.getUserRoleByUserId(user.getId());
        /**
         * 查询role名称
         */
        String roleType=roleService.getRoleById(userRolle.getRoleId()).getRoleName();
        log.info("成功登录");
        /**
         * 将关键信息置空返回
         */
        user.setPetName(null);
        user.setSalt(null);
        user.setUpdateTime(null);
        user.setCreateTime(null);
        return  new commonResult<user>(200,"成功登录",user);
    }


    @PostMapping("/Proven")
    public commonResult<String> loginProven(@RequestParam("name")String name){
        if(userService.getUser(name)!=null){
            return  new commonResult<>(20,null);
        }else {
            return  new commonResult<>(304,"用户名未注册");
        }
    }
    @PostMapping("/enroll")
    public commonResult<String> enroll(@RequestParam(value = "name")String name,
                                       @RequestParam(value = "password")String password,
                                       @RequestParam(value = "passwordTwo")String passwordTwo) {
        if (password.equals(passwordTwo)) {
            if (!name.equals("") && !password.equals("")) {
                user user = new user();
                user.setUserName(name);
                user.setUserPassword(password);
                userService.createUser(user);
            } else {
                return new commonResult<>(500, "参数为空");
            }

            return new commonResult<>(200, "注册成功");
        }else {
            return  new commonResult<>(500, "密码不一致");
        }
    }

}