package main.com.WCZZ.controller;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.service.ManagerService;
import main.com.WCZZ.service.StudentService;
import main.com.WCZZ.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@CrossOrigin
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/student")
    @ResponseBody
    public Map<String,List<Student>> queryStudent(Student student){
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        List<Student> students = null;
        students = managerService.queryStudent(student);
        resultMap.put("result", students);
        return resultMap;
    }

    @PostMapping(value = "/student")
    @ResponseBody
    public Map<String,String> addStudent(@RequestBody Student student){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("result","success");
        if(managerService.addStudent(student) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping(value = "/student")
    @ResponseBody
    public Map<String, String> modifyStudent(@RequestBody Student student){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.modifyStudent(student) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @PutMapping(value = "/student/modifyPassword")
    @ResponseBody
    public Map<String, String> modifyStudentPassword(String username, String password){
        Map<String,String> resultMap = new HashMap<String,String>();
        Set<String> roles = userService.findRoles(username);
        if (roles != null && roles.contains("student") && userService.modifyPassword(username, password) != 0) {
            resultMap.put("result", "success");
            return resultMap;
        }
        resultMap.put("result","fail");
        return resultMap;
    }

    @DeleteMapping(value = "/student")
    @ResponseBody
    public Map<String, String> deleteStudent(String stuId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.deleteStudent(stuId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping(value = "/choice")
    @ResponseBody
    public Map<String,List<Choice>> queryChoice(Integer choiceId, String stuId, String couName){
        Map<String,List<Choice>> resultMap = new HashMap<String,List<Choice>>();
        List<Choice> choices  = managerService.queryChoice(choiceId,stuId,couName);
        resultMap.put("result", choices);
        return resultMap;
    }


    @DeleteMapping(value = "/choice")
    @ResponseBody
    public Map<String, String> deleteChoice(Integer choiceId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.deleteChoice(choiceId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @PostMapping(value = "/course")
    @ResponseBody
    public Map<String,String> addCourse(@RequestBody Course course){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.addCourse(course) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/course")
    @ResponseBody
    public Map<String,List<Course>> queryCourse(String couName){
        Map<String,List<Course>> resultMap = new HashMap<String,List<Course>>();
            resultMap.put("result", managerService.queryCourse(couName));
            return resultMap;
    }



    @PutMapping(value = "/course")
    @ResponseBody
    public Map<String,String> modifyCourse(@RequestBody Course course){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.modifyCourse(course) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/course")
    @ResponseBody
    public Map<String,String> deleteCourse(Integer couId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.deleteCourse(couId) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }


    @PostMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,String> addStuCourse(@RequestBody StudentCourse studentCourse){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.addStuCourse(studentCourse) == 0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }


    @GetMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,List<StudentCourse>> queryStuCourse(String graName, String proName, String couName){
        Map<String,List<StudentCourse>> resultMap = new HashMap<String,List<StudentCourse>>();
        resultMap.put("result", managerService.queryStuCourse(graName,proName,couName));
        return resultMap;
    }



    @PutMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,String> modifyStuCourse(@RequestBody StudentCourse studentCourse){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.modifyStuCourse(studentCourse) == 0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,String> deleteStuCourse(Integer stuCourseId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.deleteStuCourse(stuCourseId) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }



    @PostMapping(value = "/time")
    @ResponseBody
    public Map<String,String> addTime(@RequestBody Time time){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.addTime(time) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/time")
    @ResponseBody
    public Map<String,List<Time>> queryTime(String graName, String type){
        Map<String,List<Time>> resultMap = new HashMap<String,List<Time>>();
        resultMap.put("result", managerService.queryTime(graName, type));
        return resultMap;
    }

    @PutMapping(value = "/time")
    @ResponseBody
    public Map<String,String> modifyTime(@RequestBody Time time){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.modifyTime(time) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/time")
    @ResponseBody
    public Map<String,String> deleteTime(@RequestParam(value = "timeId") Integer timeId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(managerService.deleteTime(timeId) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

}
