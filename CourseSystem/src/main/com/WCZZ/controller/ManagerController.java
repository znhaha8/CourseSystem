package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Course;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.Time;
import main.com.WCZZ.service.ManagerService;
import main.com.WCZZ.service.StudentService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @GetMapping(value = "/student")
    @ResponseBody
    public Map<String,List<Student>> queryStudent(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            Student student){
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
        List<Student> students = null;
        students = managerService.queryStudent(student);
        resultMap.put("result", students);
        return resultMap;
    }

    @PostMapping(value = "/student")
    @ResponseBody
    public Map<String,String> addStudent(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                         @RequestBody Student student){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        resultMap.put("result","success");
        if(managerService.addStudent(student) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping(value = "/student/modify")
    @ResponseBody
    public Map<String, String> modifyStudent(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody Student student){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.modifyStudent(student) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @DeleteMapping(value = "/student")
    @ResponseBody
    public Map<String, String> deleteStudent(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             String stuId){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.deleteStudent(stuId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping(value = "/choice")
    @ResponseBody
    public Map<String,List<Choice>> queryChoice(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                String stuId,
                                                String couName){
        Map<String,List<Choice>> resultMap = new HashMap<String,List<Choice>>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
        List<Choice> choices  = managerService.queryChoice(stuId,couName);
        resultMap.put("result", choices);
        return resultMap;
    }

    @PostMapping(value = "/choice")
    @ResponseBody
    public Map<String,String> modifyChoice(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                   @RequestBody Choice choice){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.modifyChoice(choice) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/choice")
    @ResponseBody
    public Map<String, String> deleteChoice(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody Choice choice){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.deleteChoice(choice) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @PostMapping(value = "/course")
    @ResponseBody
    public Map<String,String> addCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody Course course){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.addCourse(course) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/course")
    @ResponseBody
    public Map<String,List<Course>> queryCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                String couName){
        Map<String,List<Course>> resultMap = new HashMap<String,List<Course>>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
            resultMap.put("result", managerService.queryCourse(couName));
            return resultMap;
    }

    @PutMapping(value = "/course")
    @ResponseBody
    public Map<String,String> modifyCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody Course course){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.modifyCourse(course) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/course")
    @ResponseBody
    public Map<String,String> deleteCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           Integer couId){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.deleteCourse(couId) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @PostMapping(value = "/time")
    @ResponseBody
    public Map<String,String> addTime(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody Time time){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.addTime(time) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/time")
    @ResponseBody
    public Map<String,List<Time>> queryTime(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                        String graName,
                                        String type){
        Map<String,List<Time>> resultMap = new HashMap<String,List<Time>>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
        resultMap.put("result", managerService.queryTime(graName, type));
        return resultMap;
    }

    @PutMapping(value = "/time")
    @ResponseBody
    public Map<String,String> modifyTime(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody Time time){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.modifyTime(time) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/time")
    @ResponseBody
    public Map<String,String> deleteTime(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value = "timeId") Integer timeId){
        Map<String,String> resultMap = new HashMap<String,String>();
/*        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(managerService.deleteTime(timeId) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }




}
