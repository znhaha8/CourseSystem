package main.com.WCZZ.service;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.mapper.ChoiceMapper;
import main.com.WCZZ.mapper.StudentMapper;
import main.com.WCZZ.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

   public List<Student> query(String stuId){
       Student student = new Student();
       student.setStuId(stuId);
       List<Student> students = null;
       students = studentMapper.query(student);
       return students;
   }

   public Integer chooseCourse(String stuId, Integer couId){
       if(choiceMapper.selectByIds(stuId,couId) != null) return 0;
       Choice choice = new Choice();
       choice.setStuId(stuId);
       choice.setCouId(couId);
       choice.setChooseDate(new Date());
       return choiceMapper.add(choice);
   }

   public Integer withDrawCourse(String stuId, Integer couId){
       Choice choice = new Choice();
       choice.setStuId(stuId);
       choice.setCouId(couId);
       choice.setWithdrawDate(new Date());
       return choiceMapper.withdraw(choice);
   }

   public Integer modifyPhone(String stuId, String phone){
       return studentMapper.modifyPhone(stuId,phone);
   }

   public Integer modifyPassword(String stuId, String password){
        return userMapper.modifyPassword(stuId,password);
   }

}
