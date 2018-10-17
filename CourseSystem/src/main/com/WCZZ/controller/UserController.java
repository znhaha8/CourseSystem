/*
package main.com.WCZZ.controller;

import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.StudentService;
import main.com.WCZZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    Map<String,List<User>> login(){
        Map<String,List<User>> map = new HashMap<String,List<User>>();
        map.put("user",userService.get());
        return map;
    }
}
*/
