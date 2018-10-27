package main.com.WCZZ.service;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.mapper.*;
import main.com.WCZZ.util.IdGenerator;
import main.com.WCZZ.util.TimeUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    StuCourseMapper stuCourseMapper;


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
        Random random = new Random();
        String salt = String.valueOf(random.nextInt(10000));
        Md5Hash md5Hash = new Md5Hash(password,salt);
        password = md5Hash.toString();
        User user = new User(stuId,stuId,password,salt,createDate);
        int res = (userMapper.add(user) == 1
                && studentMapper.add(student) == 1
                && userRoleMapper.add(user.getId(),3) == 1)
                ? 1 : 0;
        return res;
    }

    @Transactional
    public Integer modifyStudent(Student student){
        return studentMapper.modifyById(student);
    }

    @Transactional
    public Integer deleteStudent(String stuId){
        Integer res = (studentMapper.delete(stuId) == 0
                || userMapper.delete(stuId)==0
                || userRoleMapper.delete(stuId) == 0)
                ? 0 : 1;
        return res;
    }

    public List<Choice> queryChoice(Integer choiceId, String stuId, String couName){
        return choiceMapper.queryByIdAndName(choiceId,stuId,couName);
    }


    @Transactional
    public Integer deleteChoice(Integer choiceId){
        return choiceMapper.delete(choiceId);
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
    public Integer addStuCourse(StudentCourse studentCourse){
        if(stuCourseMapper.query(studentCourse).size() != 0)
            return 0;
        if(courseMapper.query(studentCourse.getCouName()).size() == 0)
            return 0;
        return stuCourseMapper.add(studentCourse);
    }

    public List<StudentCourse> queryStuCourse(String graName, String proName, String couName){
        StudentCourse studentCourse = new StudentCourse(graName, proName, couName);
        return stuCourseMapper.query(studentCourse);
    }

    @Transactional
    public Integer modifyStuCourse(StudentCourse studentCourse){
        if(courseMapper.query(studentCourse.getCouName()).size() == 0)
            return 0;
        return stuCourseMapper.modify(studentCourse);
    }

    @Transactional
    public Integer deleteStuCourse(Integer stuCourseId){
        return stuCourseMapper.delete(stuCourseId);
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
