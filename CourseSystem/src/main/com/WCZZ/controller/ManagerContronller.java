package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Course;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.Time;
import main.com.WCZZ.service.ManagerService;
import main.com.WCZZ.service.StudentService;
import main.com.WCZZ.util.UserStatus;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagerContronller {
    @Autowired
    ManagerService managerService;

    @GetMapping(value = "/student/{stuId}/{stuName}/{graName}/{acaName}/{proName}/{claName}")
    @ResponseBody
    public Map<String,List<Student>> queryStudent(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            @PathVariable(value = "stuId") String stuId,
            @PathVariable(value = "stuName") String stuName,
            @PathVariable(value = "graName") String graName,
            @PathVariable(value = "acaName") String acaName,
            @PathVariable(value = "proName") String proName,
            @PathVariable(value = "claName") String claName){
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }
        Student student = new Student(stuId.trim(), stuName.trim(), graName.trim(), acaName.trim(), proName.trim(), claName.trim());
        System.out.println(student);
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

    @PutMapping(value = "/student/modify")
    @ResponseBody
    public Map<String, String> modifyStudent(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody Student student){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(managerService.modifyStudent(student) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @DeleteMapping(value = "/student/{stuId}")
    @ResponseBody
    public Map<String, String> deleteStudent(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             @PathVariable String stuId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(managerService.deleteStudent(stuId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping(value = "/choice/{stuId}/{couName}")
    @ResponseBody
    public Map<String,List<Choice>> queryChoice(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                   @PathVariable(value = "stuId") String stuId,
                                                   @PathVariable(value = "couName") String couName){
        Map<String,List<Choice>> resultMap = new HashMap<String,List<Choice>>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }
        List<Choice> choices  = managerService.queryChoice(stuId.trim(),couName.trim());
        resultMap.put("result", choices);
        return resultMap;
    }

    @PostMapping(value = "/choice")
    @ResponseBody
    public Map<String,String> modifyChoice(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                   @RequestBody Choice choice){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
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
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
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
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(managerService.addCourse(course) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/course/{couName}")
    @ResponseBody
    public Map<String,List<Course>> queryCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable(value = "couName") String couName){
        Map<String,List<Course>> resultMap = new HashMap<String,List<Course>>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }
            resultMap.put("result", managerService.queryCourse(couName.trim()));
            return resultMap;
    }

    @PutMapping(value = "/course")
    @ResponseBody
    public Map<String,String> modifyCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody Course course){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(managerService.modifyCourse(course) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/course/{couId}")
    @ResponseBody
    public Map<String,String> deleteCourse(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable(value = "couId") Integer couId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
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
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(managerService.addTime(time) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/time/{graName}/{type}")
    @ResponseBody
    public Map<String,List<Time>> queryTime(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable(value = "graName") String graName,
                                        @PathVariable(value = "type") String type){
        Map<String,List<Time>> resultMap = new HashMap<String,List<Time>>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }
        resultMap.put("result", managerService.queryTime(graName.trim(), type.trim()));
        return resultMap;
    }

    @PutMapping(value = "/time")
    @ResponseBody
    public Map<String,String> modifyTime(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody Time time){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(managerService.modifyTime(time) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/time/{timeId}")
    @ResponseBody
    public Map<String,String> deleteTime(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable(value = "timeId") Integer timeId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }
        if(managerService.deleteTime(timeId) ==0 ){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }




}
