package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Student;
import main.com.WCZZ.service.ManagerService;
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
@RequestMapping("/manager")
public class ManagerContronller {
    @Autowired
    ManagerService managerService;

    @GetMapping(value = "/student/{stuId}/{stuName}/{graName}/{acaName}/{proName}/{claName}")
    @ResponseBody
    public Map<String,List<Student>> query(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            @PathVariable(value = "stuId", required = false) String stuId,
            @PathVariable(value = "stuName", required = false) String stuName,
            @PathVariable(value = "graName", required = false) String graName,
            @PathVariable(value = "acaName", required = false) String acaName,
            @PathVariable(value = "proName", required = false) String proName,
            @PathVariable(value = "claName", required = false) String claName){
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }
        Student student = new Student(stuId.trim(), stuName.trim(), graName.trim(), acaName.trim(), proName.trim(), claName.trim());
        System.out.println(student);
        List<Student> students = null;
        students = managerService.query(student);
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
            managerService.addStudent(student);
        }
        catch (Exception e){
            e.printStackTrace();
            resultMap.put("result","fail");
        }
        if(affectRows <= 0 )resultMap.put("result","fail");
        return resultMap;
    }
}
