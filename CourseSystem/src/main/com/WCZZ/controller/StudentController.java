package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/self")
    @ResponseBody
    public Map<String,List<Student>> query( String stuId){
        List<Student> students = null;
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        students = studentService.query(stuId);
        resultMap.put("result", students);
        return resultMap;
    }

    @PutMapping("/self/modifyPassword")
    @ResponseBody
    public Map<String,String> modifyPassword(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             String stuId, String password){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(studentService.modifyPassword(stuId, password) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @PutMapping("/self/modifyPhone")
    @ResponseBody
    public Map<String,String> modifyPhone(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                          String stuId,
                                          String phone){
        Map<String,String> resultMap = new HashMap<String,String>();
        /*if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(studentService.modifyPhone(stuId, phone) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }


    @GetMapping(value = "/choose")
    @ResponseBody
    public Map<String,String> chooseCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           String graName,
                                           String stuId,
                                           String couName){
        Map<String,String> resultMap = new HashMap<String,String>();
        /*if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(studentService.chooseCourse(graName, stuId,couName) == 0){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/withdraw")
    @ResponseBody
    public Map<String,String> withdrawCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             String graName,
                                             String stuId,
                                             String couName){
        Map<String,String> resultMap = new HashMap<String,String>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(studentService.withDrawCourse(graName,stuId,couName) == 0){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/choice")
    @ResponseBody
    public Map<String,List<Choice>> queryChoice(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                String stuId){
        Map<String,List<Choice>> resultMap = new HashMap<String,List<Choice>>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
        List<Choice> choices  = studentService.queryChoice(stuId);
        resultMap.put("result", choices);
        return resultMap;
    }
}
