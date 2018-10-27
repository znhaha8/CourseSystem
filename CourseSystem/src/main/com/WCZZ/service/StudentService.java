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
import java.util.List;

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
   public Integer chooseCourse(String couName){
       String stuId = (String)SecurityUtils.getSubject().getPrincipal();
       Student student = new Student();
       student.setStuId(stuId);
       Time time = null;
       try {
           String graName = studentMapper.query(student).get(0).getGraName();
           if(choiceMapper.queryByIdAndName(null,stuId,couName).size() != 0) return 0;
           time = timeMapper.queryByGraAndType(graName.trim(),"选课").get(0);
       } catch (IndexOutOfBoundsException e) {
           System.out.println("时间表不存在");
           return 0;
       }
       Date now = new Date();
       try {
           if(now.getTime() < TimeUtil.stringToDate(time.getStart()).getTime() || now.getTime() > TimeUtil.stringToDate(time.getEnd()).getTime())
               return 0;
       } catch (ParseException e) {
           System.out.println("时间格式错误");
           return 0;
       }
           Choice choice = new Choice();
           choice.setStuId(stuId);
           choice.setCouName(couName);
           choice.setChooseDate(TimeUtil.dateToString(now));
           return choiceMapper.add(choice);
   }

    @Transactional
   public Integer withDrawCourse(Integer choiceId){
        String stuId = (String)SecurityUtils.getSubject().getPrincipal();
        Student student = new Student();
        student.setStuId(stuId);

        Time time = null;
        try {
            String graName = studentMapper.query(student).get(0).getGraName();
            time = timeMapper.queryByGraAndType(graName.trim(),"退课").get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("时间表不存在");
            return 0;
        }
        Date now = new Date();
        try {
           if(now.getTime() < TimeUtil.stringToDate(time.getStart()).getTime() || now.getTime() > TimeUtil.stringToDate(time.getEnd()).getTime())
               return 0;
           return choiceMapper.withdraw(choiceId,TimeUtil.dateToString(now));
       } catch (ParseException e) {
            System.out.println("时间格式错误");
           return 0;
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
       return courseMapper.stuQuery(student.getGraName(), student.getProName());
   }

   public List<Choice> queryChoice(String stuId){
       return choiceMapper.queryByStuId(stuId);
   }

    @Transactional
   public Integer modifyPhone(String stuId, String phone){
       return studentMapper.modifyPhone(stuId,phone);
   }


}
