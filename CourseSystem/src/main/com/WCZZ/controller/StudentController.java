package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.StudentService;
import main.com.WCZZ.util.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/{stuId}")
    @ResponseBody
    public Map<String,List<Student>> query(@PathVariable(value = "stuId") String stuId){
        List<Student> students = null;
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        students = studentService.query(stuId);
        resultMap.put("result", students);
        return resultMap;
    }

    @PutMapping("/modify")
    @ResponseBody
    public Map<String,String> modifyPassword(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             String stuId, String password){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(studentService.modifyPassword(stuId, password) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping("/modify/{stuId}/{phone}")
    @ResponseBody
    public Map<String,String> modifyPhone(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                          @PathVariable(value = "stuId") String stuId,
                                          @PathVariable(value = "phone") String phone){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(studentService.modifyPhone(stuId, phone) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }


    @GetMapping(value = "/choose/{stuId}/{couName}")
    @ResponseBody
    public Map<String,String> chooseCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable(value = "stuId") String stuId,
                                           @PathVariable(value = "couName") String couName){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(studentService.chooseCourse(stuId,couName) == 0){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/withdraw/{stuId}/{couName}")
    @ResponseBody
    public Map<String,String> withdrawCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable(value = "stuId") String stuId,
                                           @PathVariable(value = "couName") String couName){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(studentService.withDrawCourse(stuId,couName) == 0){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/choice/{stuId}")
    @ResponseBody
    public Map<String,List<Choice>> queryChoice(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                   @PathVariable(value = "stuId") String stuId){
        Map<String,List<Choice>> resultMap = new HashMap<String,List<Choice>>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }
        List<Choice> choices  = studentService.queryChoice(stuId);
        resultMap.put("result", choices);
        return resultMap;
    }
}
