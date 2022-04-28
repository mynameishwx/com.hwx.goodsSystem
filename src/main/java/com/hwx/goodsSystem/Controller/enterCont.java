package com.hwx.goodsSystem.Controller;

import com.hwx.goodsSystem.entity.session;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.roleService;
import com.hwx.goodsSystem.service.sessionService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.service.userService;
import com.hwx.goodsSystem.util.commonResult;
import com.hwx.goodsSystem.util.goodsJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/login")
public class enterCont {

    @Autowired
    private roleService roleService;

    @Autowired
    private userService userService;

    @Autowired
    private sessionService sessionService;

    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private goodsJWT goodsJWT;

    @GetMapping("")
    public String login(Map<String,String> map){
        map.put("name","");
        return "login";
    }

    @PostMapping("/enter")
    public String enter(@RequestParam("name")String name,
                                      @RequestParam("password")String password,
                                      HttpSession HttpSession,
                                      Map<String,String> map) throws  RuntimeException{
        Subject Subject= SecurityUtils.getSubject();
        /**
         *  获取正在登录的用户的信息
         */
        user user=new user();
        user= userService.getUser(name);
        if(user==null){
            map.put("Tshi","用户未注册,去注册");
            return  "login";
        }
        /**
         * shiro登录验证
         */
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);
        Subject.login(token);

        /**
         * 产生JWT令牌
         */
        String sessionUUID= UUID.randomUUID().toString();
        Map<String,String> StringMap=new HashMap<>();
        StringMap.put("session",sessionUUID);
        String session=goodsJWT.getJwt(StringMap);

        /**
         * 将jwt令牌当成session存入数据库
         */
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
        return  "redirect:/";
    }


    @ResponseBody
    @GetMapping("/Proven")
    public String loginProven(String name){
        if(userService.getUser(name)!=null){
            return  null;
        }else {
            return  name;
        }
    }
    @PostMapping("/enroll")
    public String enroll(@RequestParam(value = "name")String name,
                                       @RequestParam(value = "password")String password,
                                       @RequestParam(value = "passwordTwo")String passwordTwo,
                                       Map<String,String> map) {
        if (password.equals(passwordTwo)) {
            if (!name.equals("") && !password.equals("")) {
                user user = new user();
                user.setUserName(name);
                user.setUserPassword(password);
                userService.createUser(user);
            } else {
                map.put("Tshi","参数为空!");
                return "enroll";
            }

            return "redirect:/";
        }else {
            map.put("Tshi","密码不一致!");
            return  "enroll";
        }
    }

}