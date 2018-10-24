package main.com.WCZZ.service;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.mapper.*;
import main.com.WCZZ.util.IdGenerator;
import main.com.WCZZ.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class ManagerService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ChoiceMapper choiceMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TimeMapper timeMapper;
    @Autowired
    UserMapper userMapper;


    public List<Student> queryStudent(Student student){
        List<Student> students = null;
        students = studentMapper.query(student);
        return students;
    }

    @Transactional
    public Integer addStudent(Student student){
        String stuId = IdGenerator.generateId();
        String password = IdGenerator.generatePassword(stuId);
        String createDate = TimeUtil.dateToString(new Date());
        student.setStuId(stuId);
        student.setCreateDate(createDate);
        int res = (userMapper.add(stuId,password,createDate) == 1 && studentMapper.add(student) == 1) ? 1 : 0;
        return res;
    }

    @Transactional
    public Integer modifyStudent(Student student){
        return studentMapper.modifyById(student);
    }
    @Transactional
    public Integer deleteStudent(String stuId){
        return studentMapper.delete(stuId);
    }

    public List<Choice> queryChoice(String stuId, String couName){
        return choiceMapper.queryByIdAndName(stuId,couName);
    }

    @Transactional
    public Integer modifyChoice(Choice choice){
        return choiceMapper.modifyByStuId(choice);
    }

    @Transactional
    public Integer deleteChoice(Choice choice){
        return choiceMapper.delete(choice);
    }

    @Transactional
    public Integer addCourse(Course course){
        return courseMapper.add(course);
    }

    public List<Course> queryCourse(String courseName){
        return courseMapper.query(courseName);
    }

    @Transactional
    public Integer modifyCourse(Course course){
        return courseMapper.modifyById(course);
    }

    @Transactional
    public Integer deleteCourse(Integer couId){
        return courseMapper.deleteById(couId);
    }

    @Transactional
    public Integer addTime(Time time){
        try {
            if (TimeUtil.stringToDate(time.getStart()).getTime() > TimeUtil.stringToDate(time.getEnd()).getTime())
                return 0;
            if(timeMapper.queryByGraAndType(time.getGraName(),time.getType()).size() != 0){
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return timeMapper.add(time);
    }

    public List<Time> queryTime(String graName, String type){
        return timeMapper.queryByGraAndType(graName,type);
    }

    @Transactional
    public Integer modifyTime(Time time){
        try {
            if (TimeUtil.stringToDate(time.getStart()).getTime() > TimeUtil.stringToDate(time.getEnd()).getTime())
                return 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return timeMapper.modify(time);
    }

    @Transactional
    public Integer deleteTime(Integer timeId){
        return timeMapper.deleteById(timeId);
    }

}
