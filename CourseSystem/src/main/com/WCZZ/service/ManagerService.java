package main.com.WCZZ.service;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.mapper.ChoiceMapper;
import main.com.WCZZ.mapper.CourseMapper;
import main.com.WCZZ.mapper.StudentMapper;
import main.com.WCZZ.mapper.TimeMapper;
import main.com.WCZZ.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public List<Student> queryStudent(Student student){
        List<Student> students = null;
        students = studentMapper.query(student);
        return students;
    }

    @Transactional
    public Integer addStudent(Student student){
        if(student.getStuName().isEmpty()
                || student.getGraName() == null
                || student.getAcaName().isEmpty()
                || student.getProName().isEmpty()
                || student.getClaName() == null)

            return 0;
        student.setCreateDate(TimeUtil.DateFormat());
        return studentMapper.add(student);
    }

    @Transactional
    public Integer modifyStudent(Student student){
        return studentMapper.modifyById(student);
    }

    public Integer deleteStudent(String stuId){
        return studentMapper.delete(stuId);
    }

    public List<Choice> queryChoice(String stuId, String couName){
        return choiceMapper.queryByIdAndName(stuId,couName);
    }

    public Integer modifyChoice(Choice choice){
        return choiceMapper.modifyByStuId(choice);
    }

    public Integer deleteChoice(Choice choice){
        return choiceMapper.delete(choice);
    }

    public Integer addCourse(Course course){
        return courseMapper.add(course);
    }

    public List<Course> queryCourse(String courseName){
        return courseMapper.query(courseName);
    }

    public Integer modifyCourse(Course course){
        return courseMapper.modifyById(course);
    }

    public Integer deleteCourse(Integer couId){
        return courseMapper.deleteById(couId);
    }

    public Integer addTime(Time time){
        return timeMapper.add(time);
    }

    public List<Time> queryTime(String graName, String type){
        return timeMapper.queryByGraAndType(graName,type);
    }

    public Integer modifyTime(Time time){
        return timeMapper.modify(time);
    }

    public Integer deleteTime(Integer timeId){
        return timeMapper.deleteById(timeId);
    }

}
