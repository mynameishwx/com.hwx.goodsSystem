package com.hwx.goodsSystem.Controller;

import com.hwx.goodsSystem.entity.goods;
import com.hwx.goodsSystem.entity.session;
import com.hwx.goodsSystem.entity.user;
import com.hwx.goodsSystem.entity.userRole;
import com.hwx.goodsSystem.service.IMPL.enterIMPL;
import com.hwx.goodsSystem.service.sessionService;
import com.hwx.goodsSystem.service.userRoleService;
import com.hwx.goodsSystem.service.userService;
import com.hwx.goodsSystem.util.goodsJWT;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 登录，注册，首页
 */
@Slf4j
@Controller
public class enterCont {


    @Autowired
    private goodsThreadLocal goodsThreadLocal;


    @Autowired
    private userService userService;

    @Autowired
    private sessionService sessionService;

    @Autowired
    private userRoleService userRoleService;

    @Autowired
    private goodsJWT goodsJWT;


    private enterIMPL enterIMPL;

    /**
     * set导入
     *
     * @param enterIMPL
     */
    @Autowired
    public void setEnterIMPL(enterIMPL enterIMPL) {
        this.enterIMPL = enterIMPL;
    }


    /**
     * 进入首页
     *
     * @return
     */
    @GetMapping("/")
    public String index_two(Map<String, Object> map) {

        /**
         * 目的是让其走拦截器，不直接到静态页面
         */
        return  "index";
    }
    /**
     * 进入登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(HttpSession HttpSession){
        user user=new user();
        user=goodsThreadLocal.getUser();
        if(user!=null){
            /**
             * 已经登录，不进入登录页面
             */
            return "redirect:/";
        }else{
            /**
             * 未登录，进去登录页面
             */
            return "login";
        }
    }

    /**
     * 进入注册页面
     * @return
     */
    @GetMapping("/enroll")
    public String enroll(){
        user user=new user();
        user=goodsThreadLocal.getUser();
        if(user!=null){
            /**
             * 已经登录，不进入注册页面
             */
            return "redirect:/";
        }else{
            /**
             * 未登录，进去注册页面
             */
            return "enroll";
        }
    }

    /**
     * 退出登录
     */
    @RequestMapping("/login/loginOut")
    public String loginOut(HttpSession HttpSession){
        user user=new user();
        user=goodsThreadLocal.getUser();
        if(user!=null){
           HttpSession.removeAttribute("session");
           sessionService.deleteSessionByUserId(user.getId());
        }
        goodsThreadLocal.delete();
        return "redirect:/";
    }
    /**
     * 登录
     * @param name
     * @param password
     * @param HttpSession
     * @param map
     * @return
     * @throws RuntimeException
     */
    @PostMapping("/login/enter")
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
        String sessionUUID = UUID.randomUUID().toString();
        Map<String, String> StringMap = new HashMap<>();
        StringMap.put("session", sessionUUID);
        String session = goodsJWT.getJwt(StringMap);

        /**
         * 将jwt令牌当成session存入数据库
         */
        session sessionEntity = new session();
        sessionEntity.setUserId(user.getId());
        List<session> sessionList = new ArrayList<>();

        /**
         * 根据用户ID查询Session
         */
        sessionList = sessionService.getSession(sessionEntity);

        /**
         * 没有session则直接存入
         */
        if (sessionList.size() == 0) {
            sessionService.createSession(new session(null, user.getId(), session, 0, null, null));
        } else {
            /**
             * 将之前的session状态改为1(掉线状态)
             */
            Iterator<session> sessionIt = sessionList.iterator();
            while (sessionIt.hasNext()) {
                sessionEntity = sessionIt.next();
                if (sessionEntity.getSession() == session) {
                    break;
                }
                /**
                 * 将这个用户session状态不为0的删除
                 */
                if (sessionEntity.getExist() != 0) {
                    sessionService.deleteSessionByid(sessionEntity.getId());
                }
                sessionEntity.setExist(1);
                sessionService.updateSession(sessionEntity);
            }
            sessionService.createSession(new session(null, user.getId(), session, 0, null, null));
        }

        /**
         * 将产生的UUID放到session中
         */
        HttpSession.setAttribute("session", session);


        /**
         *  判断返回普通页面还是管理员界面
         */
        userRole userRolle = userRoleService.getUserRoleByUserId(user.getId());
        log.info("成功登录");
        return "redirect:/";
    }

    /**
     * 判断是否异地登录
     *
     * @return
     */
    @PostMapping("/enter/error")
    @ResponseBody
    public String enterError() {
        user user = new user();
        user = goodsThreadLocal.getUser();
        List<session> sessionList = new ArrayList<>();
        if (user != null) {
            sessionList = sessionService.getSessionByUserId(user.getId());
        }
        Iterator<session> sessionIterator = sessionList.iterator();
        session sesseion = new session();
        while (sessionIterator.hasNext()) {
            sesseion = sessionIterator.next();
            if (sesseion.getExist() == 2) {
                goodsThreadLocal.setUser(null);
                sessionService.deleteSessionByid(sesseion.getId());
                return "2";
            } else {
                return "1";
            }
        }
        log.warn("未登录");
        return "0";
    }


    /**
     * 登录时查询用户是否已注册(ajax)
     *
     * @param name
     * @return
     */
    @ResponseBody
    @GetMapping("/login/Proven")
    public String loginProven(String name){
        if(userService.getUser(name)!=null){
            return  null;
        }else {
            return  name;
        }
    }

    /**
     * 注册
     * @param name
     * @param password
     * @param passwordTwo
     * @param map
     * @return
     */
    @PostMapping("/login/enroll")
    public String enroll(@RequestParam(value = "name")String name,
                         @RequestParam(value = "password")String password,
                         @RequestParam(value = "passwordTwo")String passwordTwo,
                         Map<String,String> map, HttpSession HttpSession) {
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
            /**
             * 登录
             */
            this.enter(name, password, HttpSession, map);
            return "redirect:/";
        } else {
            map.put("Tshi", "密码不一致!");
            return "enroll";
        }
    }

    /**
     * 首页获取不同商品
     *
     * @param start
     * @param stop
     * @return
     */
    @ResponseBody
    @PostMapping("/index/getGoodsList")
    public List<goods> index_getGoodsList(Integer start, Integer stop, Integer goodsClass) {
        if (stop > start && start >= 0 && stop > 0 && goodsClass >= 0) {
            return enterIMPL.enterGoodsMark(start, stop, goodsClass);
        }
        return null;
    }
}