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
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //实现用户登录
    @ResponseBody
    @PostMapping(value = "/login")
    public Map<String,String> Login(String username, String password){
        Map<String,String> resultMap = new HashMap<String,String>();
        UsernamePasswordToken token=new UsernamePasswordToken(username, password);
        Subject subject=SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.getMessage();
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public Map<String,String> logout(){
        Map<String,String> resultMap = new HashMap<String,String>();
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());//退出
        resultMap.put("result","success");
        return resultMap;
    }


    @RequestMapping(value = "/notLogin")
    @ResponseBody
    public Map<String,String> notLogin(){
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("result","not login");
        return resultMap;
    }


    @RequestMapping(value = "/unauthorized")
    @ResponseBody
    public Map<String,String> unauthorized(){
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("result","unauthorized");
        return resultMap;
    }

    @PutMapping(value = "/modifyPassword")
    @ResponseBody
    public Map<String, String> modifySelfPassword(String password){
        Map<String,String> resultMap = new HashMap<String,String>();
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        if( userService.modifyPassword(username,password)== 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }


}