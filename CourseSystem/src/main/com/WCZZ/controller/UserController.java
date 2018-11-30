package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.UserService;
import main.com.WCZZ.util.excel.AS;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //实现用户登录
    @ResponseBody
    @PostMapping(value = "/login")
    public Map<String, String> login(HttpSession session, String username, String password) {
        Map<String, String> resultMap = new HashMap<String, String>();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        System.out.println(session.getId());
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
            return resultMap;
        }
        Set<String> roles = userService.findRoles(username);
        for (String role : roles) {
            resultMap.put("msg",role);
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public Map<String, String> logout() {
        Map<String, String> resultMap = new HashMap<String, String>();
        Subject subject = SecurityUtils.getSubject();
        SecurityUtils.getSecurityManager().logout(subject);//退出
        resultMap.put("result", "success");
        return resultMap;
    }


    @RequestMapping(value = "/notLogin")
    @ResponseBody
    public Map<String, String> notLogin() {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "not login");
        return resultMap;
    }


    @RequestMapping(value = "/unauthorized")
    @ResponseBody
    public Map<String, String> unauthorized() {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "unauthorized");
        return resultMap;
    }

    @PutMapping(value = "/modifyPassword")
    @ResponseBody
    public Map<String, String> modifySelfPassword(String password) {
        Map<String, String> resultMap = new HashMap<String, String>();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if (userService.modifyPassword(username, password) == 0) {
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @RequestMapping("/excel")
    public String creaExcel(HttpServletResponse response) throws IOException {
        AS as = new AS();
        HSSFWorkbook sheets = as.outExcel();
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        sheets.write(output);
        return null;
    }

}