package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/studentAPI")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/student/{stuId}/{stuName}/{graName}/{acaName}/{proName}/{claName}")
    @ResponseBody
    public Map<String,List<Student>> query(@PathVariable(value = "stuId", required = false) Integer stuId,
                                           @PathVariable(value = "stuName", required = false) String stuName,
                                           @PathVariable(value = "graName", required = false) Integer graName,
                                           @PathVariable(value = "acaName", required = false) String acaName,
                                           @PathVariable(value = "proName", required = false) String proName,
                                           @PathVariable(value = "claName", required = false) Integer claName){
        Student student = new Student(stuId, stuName, graName, acaName, proName, claName);
        List<Student> students = null;
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        try {
            students = studentService.query(student);
        }catch (Exception e){
        }
        resultMap.put("result", students);
        return resultMap;
    }


    @PostMapping(value = "/student")
    @ResponseBody
    public Map<String,String> add(@RequestBody Student student){
        int affectRows = 0;
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("result","success");
        try {
            studentService.addStudent(student);
        }
        catch (Exception e){
            resultMap.put("result","fail");
        }
        if(affectRows <= 0 )resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping("/student")
    @ResponseBody
    public Map<String,String> modify(@RequestBody User user){
        Map<String,String> resultMap = new HashMap<String,String>();
        int affectRows = 0;
        resultMap.put("result","success");
        try {
                studentService.modify(user);
        }
        catch (Exception e){
            resultMap.put("result","fail");
        }
        if(affectRows <= 0 )resultMap.put("result","fail");
        return resultMap;
    }



}
