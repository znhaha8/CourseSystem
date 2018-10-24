package main.com.WCZZ.service;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.Time;
import main.com.WCZZ.mapper.ChoiceMapper;
import main.com.WCZZ.mapper.StudentMapper;
import main.com.WCZZ.mapper.TimeMapper;
import main.com.WCZZ.mapper.UserMapper;
import main.com.WCZZ.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

   public List<Student> query(String stuId){
       Student student = new Student();
       student.setStuId(stuId);
       return studentMapper.query(student);
   }

   @Transactional
   public Integer chooseCourse(String graName, String stuId, String couName){
       if(choiceMapper.queryByIdAndName(stuId,couName) != null) return 0;
       Choice choice = new Choice();
       choice.setStuId(stuId);
       choice.setCouName(couName);
       Date now = new Date();
       Time chooseDate = timeMapper.queryByGraAndType(graName.trim(),"选课").get(0);
       try {
           if(now.getTime() < TimeUtil.stringToDate(chooseDate.getStart()).getTime() || now.getTime() > TimeUtil.stringToDate(chooseDate.getEnd()).getTime())
               return 0;
           choice.setChooseDate(TimeUtil.dateToString(now));
           return choiceMapper.add(choice);
       } catch (ParseException e) {
           e.printStackTrace();
           return 0;
       }
   }

    @Transactional
   public Integer withDrawCourse(String graName, String stuId, String couName){
       Choice choice = new Choice();
       choice.setStuId(stuId);
       choice.setCouName(couName);
       Date now = new Date();
       Time chooseDate = timeMapper.queryByGraAndType(graName.trim(),"退课").get(0);
       try {
           if(now.getTime() < TimeUtil.stringToDate(chooseDate.getStart()).getTime() || now.getTime() > TimeUtil.stringToDate(chooseDate.getEnd()).getTime())
               return 0;
           choice.setWithdrawDate(TimeUtil.dateToString(now));
           return choiceMapper.withdraw(choice);
       } catch (ParseException e) {
           e.printStackTrace();
           return 0;
       }
   }

   public List<Choice> queryChoice(String stuId){
       return choiceMapper.queryByStuId(stuId);
   }

    @Transactional
   public Integer modifyPhone(String stuId, String phone){
       return studentMapper.modifyPhone(stuId,phone);
   }

    @Transactional
   public Integer modifyPassword(String stuId, String password){
        return userMapper.modifyPassword(stuId,password);
   }

}
