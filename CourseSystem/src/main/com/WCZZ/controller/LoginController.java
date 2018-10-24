package main.com.WCZZ.controller;

import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;
    //实现用户登录
    @ResponseBody
    @PostMapping(value = "/login")
    public Map<String,String> Login(String username, String password){
        Map<String,String> resultMap = new HashMap<String,String>();
        User user=userService.findByUsername(username);
        if(user==null){
           resultMap.put("result","用户不存在");
           return resultMap;
        }
        if(!user.getPassword().equals(password)){
            resultMap.put("result","账号密码错误");
            return resultMap;
        }
        //登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject=SecurityUtils.getSubject();
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        //登录成功后会跳转到successUrl配置的链接，不用管下面返回的链接
        resultMap.put("result","登录成功");
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value = "/logout")
    public Map<String,String> logout(){
        Map<String,String> resultMap = new HashMap<String,String>();
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());//退出
        resultMap.put("result","退出成功");
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/check")
    public Map<String,String> check(){
        Map<String,String> resultMap = new HashMap<String,String>();
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        System.out.println(subject.hasRole("manager"));
        resultMap.put("result","成功");
        return resultMap;
    }

/*    @RequestMapping(value = "/subLogin", method = RequestMethod.POST)
    @ResponseBody
    public String subLogin(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.getMessage();
        }
        return "登录成功";
    }*/

}