package main.com.WCZZ.service;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.mapper.*;
import main.com.WCZZ.util.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.Subject;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ChoiceMapper choiceMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TimeMapper timeMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    StuCourseMapper stuCourseMapper;

   public List<Student> query(String stuId){
       Student student = new Student();
       student.setStuId(stuId);
       return studentMapper.query(student);
   }

   @Transactional
   public Map<String, String> chooseCourse(String couName){
       Map<String, String> result = new HashMap<>();
       String stuId = (String)SecurityUtils.getSubject().getPrincipal();
       Student student = new Student();
       student.setStuId(stuId);
       Time time = null;
       try {
           Integer graName = studentMapper.query(student).get(0).getGraName();
           if(choiceMapper.queryByIdAndName(null,stuId,couName).size() != 0){
               result.put("msg","已经选过改课程");
               return result;
           }
           time = timeMapper.queryByGraAndType(graName,"选课").get(0);
       } catch (IndexOutOfBoundsException e) {
           System.out.println("时间表不存在");
           result.put("msg","请确认年级选课日期");
           return result;
       }
       Date now = new Date();
       try {
           if(now.getTime() < TimeUtil.stringToDate(time.getStart()).getTime()
                   || now.getTime() > TimeUtil.stringToDate(time.getEnd()).getTime()){
               result.put("msg", "日期不在选课日期内");
               return result;
           }

       } catch (ParseException e) {
           System.out.println("时间格式错误");
           result.put("msg", "时间格式解析异常");
           return result;
       }
           Choice choice = new Choice();
           choice.setStuId(stuId);
           choice.setCouName(couName);
           choice.setChooseDate(TimeUtil.dateToString(now));
           if(choiceMapper.add(choice) == 0 ){
               result.put("msg", "fail");
           }else {
               result.put("msg", "success");
           }
           return result;
   }

    @Transactional
   public Map<String, String> withDrawCourse(Integer choiceId){
        Map<String, String> result = new HashMap<>();
        String stuId = (String)SecurityUtils.getSubject().getPrincipal();
        Student student = new Student();
        student.setStuId(stuId);
        Time time = null;
        try {
            Integer graName = studentMapper.query(student).get(0).getGraName();
            time = timeMapper.queryByGraAndType(graName,"退课").get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("时间表不存在");
            result.put("msg","请确认改年级退课时间");
            return result;
        }
        Date now = new Date();
        try {
           if(now.getTime() < TimeUtil.stringToDate(time.getStart()).getTime()
                   || now.getTime() > TimeUtil.stringToDate(time.getEnd()).getTime()){
               result.put("msg", "日期不在退课时间日期");
               return result;
           }
           if(choiceMapper.withdraw(choiceId,TimeUtil.dateToString(now)) == 0){
               result.put("msg", "fail");
           }else {
               result.put("msg", "success");
           }
           return result;
       } catch (ParseException e) {
            System.out.println("时间格式错误");
            result.put("msg", "日期格式解析异常");
            return result;
       }
   }

   public List<Course> queryCourse(){
       String stuId = (String)SecurityUtils.getSubject().getPrincipal();
       Student student = new Student();
       student.setStuId(stuId);
       try {
           student = studentMapper.query(student).get(0);
       } catch (NullPointerException e) {
           e.printStackTrace();
           return null;
       }
       return courseMapper.stuQuery(student.getGraName(), student.getProName(), stuId);
   }

   public List<Choice> queryChoice(String stuId){
       return choiceMapper.queryByStuId(stuId);
   }

    @Transactional
   public Map<String, String> modifyPhone(String stuId, String phone){
       Map<String, String> result = new HashMap<>();
       if(phone.length() > 16){
           result.put("msg", "电话号码长度大于16");
           return result;
       }
       if(studentMapper.modifyPhone(stuId,phone) == 0){
           result.put("msg", "fail");
       }else {
           result.put("msg", "success");
       }
       return result;
   }


}
