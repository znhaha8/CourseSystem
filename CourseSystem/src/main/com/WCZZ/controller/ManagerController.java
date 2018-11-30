package main.com.WCZZ.controller;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.service.ManagerService;
import main.com.WCZZ.service.StudentService;
import main.com.WCZZ.service.UserService;
import main.com.WCZZ.util.excel.AS;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/self")
    @ResponseBody
    public Map<String,List<Manager>> querySelf(){
        Map<String, List<Manager>> resultMap = new HashMap<String, List<Manager>>();
        String manId = (String)SecurityUtils.getSubject().getPrincipal();
        resultMap.put("result", managerService.querySelf(manId));
        return resultMap;
    }

    @PutMapping(value = "/self")
    @ResponseBody
    public Map<String, String> modifyPhone(String phone){
        String manId = (String)SecurityUtils.getSubject().getPrincipal();
        Map<String,String> resultMap = managerService.modifyPhone(manId, phone);
        resultMap.put("result","success");
        if(!resultMap.get("msg").equals("success"))
            resultMap.put("result","fail");
        return resultMap;
    }

    @GetMapping(value = "/student")
    @ResponseBody
    public Map<String,List<Student>> queryStudent(Student student){
        Integer graName = querySelf().get("result").get(0).getGraName();
        Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
        List<Student> students = null;
        student.setGraName(graName);
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

    @PostMapping("/student/excel")
    @ResponseBody
    public Map<String,String> export(MultipartFile file){
        Map<String,String> resultMap = new HashMap<>();
        InputStream is = null;
        try {
            // MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            String fileName = file.getOriginalFilename();
            is = file.getInputStream();
            Workbook hssfWorkbook = null;
            if (fileName.endsWith("xlsx")) {
                hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
            } else if (fileName.endsWith("xls")) {
                hssfWorkbook = new HSSFWorkbook(is);//Excel 2003

            }
            List<Student> list = new ArrayList<Student>();
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    Row hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        Student student = new Student();
                        Cell stuName = hssfRow.getCell(0);
                        Cell sex = hssfRow.getCell(1);
                        Cell graName = hssfRow.getCell(2);
                        Cell acaName = hssfRow.getCell(3);
                        Cell proName = hssfRow.getCell(4);
                        Cell claName = hssfRow.getCell(5);
                        Cell phone = hssfRow.getCell(6);
                        stuName.setCellType(1);
                        sex.setCellType(1);
                        graName.setCellType(1);
                        acaName.setCellType(1);
                        proName.setCellType(1);
                        claName.setCellType(1);
                        phone.setCellType(1);
                        student.setStuName(stuName.toString());
                        student.setSex(sex.toString());
                        student.setGraName(Integer.parseInt(graName.toString()));
                        student.setAcaName(acaName.toString());
                        student.setProName(proName.toString());
                        student.setClaName(claName.toString());
                        student.setPhone(phone.toString());
                        list.add(student);
                    }
                }
                for (Student u : list
                        ) {
                    managerService.addStudent(u);
                }
            }
            resultMap.put("result","success");
            resultMap.put("msg","success");
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
            resultMap.put("msg", "IO异常");
        } catch (Exception e){
            resultMap.put("result", "fail");
            resultMap.put("msg", "异常");
            e.printStackTrace();
        }finally {

        }
        return resultMap;
    }

    @PutMapping(value = "/student")
    @ResponseBody
    public Map<String, String> modifyStudent(@RequestBody Student student){
        Map<String,String> resultMap = managerService.modifyStudent(student);
        if(!resultMap.get("msg").equals("success")){
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
        resultMap.put("result","fail");
        if (roles != null){
            resultMap.put("msg", "角色为空");
            return resultMap;
        }
        if(roles.contains("student")){
            resultMap.put("msg", "角色不包含学生");
            return resultMap;
        }
        if(userService.modifyPassword(username, password) != 0) {
            resultMap.put("result", "success");
            resultMap.put("msg", "success");
            return resultMap;
        }
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
        Map<String,String> resultMap = managerService.deleteChoice(choiceId);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @PostMapping(value = "/course")
    @ResponseBody
    public Map<String,String> addCourse(@RequestBody Course course){
        Map<String,String> resultMap = managerService.addCourse(course);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/course")
    @ResponseBody
    public Map<String,List<Course>> queryCourse(Integer couId, String couName){
        Map<String,List<Course>> resultMap = new HashMap<String,List<Course>>();
            resultMap.put("result", managerService.queryCourse(couId, couName));
            return resultMap;
    }



    @PutMapping(value = "/course")
    @ResponseBody
    public Map<String,String> modifyCourse(@RequestBody Course course){
        Map<String,String> resultMap = managerService.modifyCourse(course);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/course")
    @ResponseBody
    public Map<String,String> deleteCourse(Integer couId){
        Map<String,String> resultMap = managerService.deleteCourse(couId);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }


    @PostMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,String> addStuCourse(@RequestBody StudentCourse studentCourse){
        Map<String,String> resultMap = managerService.addStuCourse(studentCourse);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }


    @GetMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,List<StudentCourse>> queryStuCourse(Integer id, Integer graName, String proName, String couName){
        Map<String,List<StudentCourse>> resultMap = new HashMap<String,List<StudentCourse>>();
        resultMap.put("result", managerService.queryStuCourse(id,graName,proName,couName));
        return resultMap;
    }



    @PutMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,String> modifyStuCourse(@RequestBody StudentCourse studentCourse){
        Map<String,String> resultMap = managerService.modifyStuCourse(studentCourse);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/StuCourse")
    @ResponseBody
    public Map<String,String> deleteStuCourse(Integer stuCourseId){
        Map<String,String> resultMap = managerService.deleteStuCourse(stuCourseId);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }



    @PostMapping(value = "/time")
    @ResponseBody
    public Map<String,String> addTime(@RequestBody Time time){
        Map<String,String> resultMap = managerService.addTime(time);
        if(!resultMap.get("msg").equals("添加成功")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping(value = "/time")
    @ResponseBody
    public Map<String,List<Time>> queryTime(Integer graName, String type){
        Map<String,List<Time>> resultMap = new HashMap<String,List<Time>>();
        resultMap.put("result", managerService.queryTime(graName, type));
        return resultMap;
    }

    @PutMapping(value = "/time")
    @ResponseBody
    public Map<String,String> modifyTime(@RequestBody Time time){
        Map<String,String> resultMap = managerService.modifyTime(time);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping(value = "/time")
    @ResponseBody
    public Map<String,String> deleteTime(@RequestParam(value = "timeId") Integer timeId){
        Map<String,String> resultMap = managerService.deleteTime(timeId);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result", "fail");
            return resultMap;
        }
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping("/chooseNecessity")
    @ResponseBody
    public Map<String, String> chooseNecessity(){
        Map <String, String> resultMap = null;
        try{
            resultMap = managerService.aadNecessaryChoice();
        }catch (Exception e){
            e.printStackTrace();
            resultMap = new HashMap<>();
            resultMap.put("result", "fail");
            resultMap.put("msg","异常(确定是否还有学生需要选取必修课程)");
        }
        return resultMap;
    }
}
