package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Course;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.StudentService;
import org.apache.shiro.SecurityUtils;
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
    public Map<String,List<Student>> query(){
        String stuId = (String)SecurityUtils.getSubject().getPrincipal();
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        resultMap.put("result", studentService.query(stuId));
        return resultMap;
    }


    @PutMapping("/self/modifyPhone")
    @ResponseBody
    public Map<String,String> modifyPhone(String phone){
        Map<String,String> resultMap = new HashMap<String,String>();
        String stuId = (String)SecurityUtils.getSubject().getPrincipal();
        if(studentService.modifyPhone(stuId, phone) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }


    @GetMapping(value = "/course")
    @ResponseBody
    public Map<String,List<Course>> chooseCourse(){
        Map<String,List<Course>> resultMap = new HashMap<String,List<Course>>();
        resultMap.put("result", studentService.queryCourse());
        return resultMap;
    }

    @GetMapping(value = "/choose")
    @ResponseBody
    public Map<String,String> chooseCourse(String couName){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(studentService.chooseCourse(couName) == 0){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/withdraw")
    @ResponseBody
    public Map<String,String> withdrawCourse(Integer choiceId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(studentService.withDrawCourse(choiceId) == 0){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/choice")
    @ResponseBody
    public Map<String,List<Choice>> queryChoice(){
        String stuId = (String)SecurityUtils.getSubject().getPrincipal();
        Map<String,List<Choice>> resultMap = new HashMap<String,List<Choice>>();
        List<Choice> choices  = studentService.queryChoice(stuId);
        resultMap.put("result", choices);
        return resultMap;
    }
}
