package main.com.WCZZ.service;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.mapper.*;
import main.com.WCZZ.util.IdGenerator;
import main.com.WCZZ.util.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

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
    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    TeamMapper teamMapper;


    public List<Manager> querySelf(String manId) {
        return managerMapper.query(manId, null, null);
    }

    public Map<String ,String> modifyPhone(String manId, String phone) {
        Map<String ,String> result = new HashMap<>();
        if(phone.length() > 16){
            result.put("msg", " 电话长度大于16");
            return result;
        }
        if(managerMapper.modifyPhone(manId, phone) == 0){
            result.put("msg", "id不存在");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    public List<Student> queryStudent(Student student) {
        List<Student> students = null;
        students = studentMapper.query(student);
        return students;
    }

    @Transactional
    public Integer addStudent(Student student) {
        String stuId = IdGenerator.generateId();
        String password = IdGenerator.generatePassword(stuId);
        String createDate = TimeUtil.dateToString(new Date());
        student.setStuId(stuId);
        student.setCreateDate(createDate);
        Random random = new Random();
        String salt = String.valueOf(random.nextInt(10000));
        Md5Hash md5Hash = new Md5Hash(password, salt);
        password = md5Hash.toString();
        User user = new User(stuId, stuId, password, salt, createDate);
        int res = (userMapper.add(user) == 1
                && studentMapper.add(student) == 1
                && userRoleMapper.add(user.getId(), 3) == 1)
                ? 1 : 0;
        return res;
    }

    @Transactional
    public Map<String, String> modifyStudent(Student student) {
        Map<String, String> result = new HashMap<>();
        if(studentMapper.modifyById(student) == 0){
            result.put("msg" , "该学生id不存在");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Integer deleteStudent(String stuId) {
        Integer res = (studentMapper.delete(stuId) == 0
                || userMapper.delete(stuId) == 0
                || userRoleMapper.delete(stuId) == 0)
                ? 0 : 1;
        return res;
    }

    public List<Choice> queryChoice(Integer choiceId, String stuId, String couName) {
        return choiceMapper.queryByIdAndName(choiceId, stuId, couName);
    }


    @Transactional
    public Map<String, String> deleteChoice(Integer choiceId) {
        Map<String, String> result = new HashMap<>();
        if(choiceMapper.delete(choiceId) == 0){
            result.put("msg" , "该选课记录id不存在");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Map<String, String> addCourse(Course course) {
        Map<String, String> result = new HashMap<>();
        if(courseMapper.query(null,course.getCouName()).size() != 0){
            result.put("msg", "该课程名已存在");
            return result;
        }
        if(courseMapper.add(course) == 0 ){
            result.put("msg", "fail");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    public List<Course> queryCourse(Integer couId, String courseName) {
        return courseMapper.query(couId,courseName);
    }

    @Transactional
    public Map<String, String> modifyCourse(Course course) {
        Map<String, String > result = new HashMap<>();
        if(courseMapper.modifyById(course) == 0){
            result.put("msg", "id不存在或字段长度不符合");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Map<String, String> deleteCourse(Integer couId) {
        Map<String, String> result = new HashMap<>();
        if(courseMapper.deleteById(couId) == 0){
            result.put("msg", "id不存在");
        }else {
            result.put("msg", "success");
        }
        return result;
    }


    @Transactional
    public Map<String, String> addStuCourse(StudentCourse studentCourse) {
        Map<String, String> result = new HashMap<>();
        if (stuCourseMapper.query(studentCourse).size() != 0) {
            result.put("msg", "该记录已存在");
            return result;
        }
        if (courseMapper.query(null, studentCourse.getCouName()).size() == 0) {
            result.put("msg", "学生可选课表不存在改课程");
        }
        if (stuCourseMapper.add(studentCourse) == 0) {
            result.put("msg", "fail");
        } else {
            result.put("msg", "success");
        }
        return result;
    }

    public List<StudentCourse> queryStuCourse(Integer id, Integer graName, String proName, String couName) {
        StudentCourse studentCourse = new StudentCourse(id, graName, proName, couName);
        return stuCourseMapper.query(studentCourse);
    }

    @Transactional
    public Map<String, String> modifyStuCourse(StudentCourse studentCourse) {
        Map<String, String> result = new HashMap();
        if (courseMapper.query(null, studentCourse.getCouName()).size() == 0) {
            result.put("msg", "学生可选课表不存在该课程");
            return result;
        }
        if (stuCourseMapper.modify(studentCourse) == 0) {
            result.put("msg", "fail");
        } else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Map<String, String> deleteStuCourse(Integer stuCourseId) {
        Map<String, String> result = new HashMap<>();
        if(stuCourseMapper.delete(stuCourseId) == 0){
            result.put("msg" , "id不存在");
        }else {
            result.put("msg", "success");
        }
        return result;
    }


    @Transactional
    public Map<String, String> addTime(Time time) {
        Map<String, String> result = new HashMap();
        try {
            if (TimeUtil.stringToDate(time.getStart()).getTime() > TimeUtil.stringToDate(time.getEnd()).getTime()) {
                result.put("msg", "开始时间不能大于结束时间");
                return result;
            }
            if (timeMapper.queryByGraAndType(time.getGraName(), time.getType()).size() != 0) {
                result.put("msg", "时间表已存在同年级同类型的时间");
                return result;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            result.put("msg", "时间解析异常");
            return result;
        }
        if (timeMapper.add(time) == 0) {
            result.put("msg", "fail");
        } else {
            result.put("msg", "success");
        }
        return result;
    }

    public List<Time> queryTime(Integer graName, String type) {
        return timeMapper.queryByGraAndType(graName, type);
    }

    @Transactional
    public Map<String, String> modifyTime(Time time) {
        Map<String, String> result = new HashMap();
        try {
            if (TimeUtil.stringToDate(time.getStart()).getTime() > TimeUtil.stringToDate(time.getEnd()).getTime()) {
                result.put("msg", "开始时间不能大于结束时间");
                return result;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            result.put("msg", "时间解析异常");
            return result;
        }
        if (timeMapper.modify(time) == 0) {
            result.put("msg", "fail");
        } else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Map<String, String> deleteTime(Integer timeId) {
        Map<String, String> result = new HashMap<>();
        if(timeMapper.deleteById(timeId) == 0){
            result.put("msg" , "id不存在");
        }else {
            result.put("msg", "success");
        }
        return result;
    }


    public Map<String,String> aadNecessaryChoice() {
        Map<String, String> result = new HashMap<>();
        String manId = (String) SecurityUtils.getSubject().getPrincipal();
        Integer graName = querySelf(manId).get(0).getGraName();
        List<StudentCourse> studentCourses = stuCourseMapper.query(new StudentCourse(null, graName, null, null));
        List<Choice> choices = new ArrayList<>();
        String chooseDate = TimeUtil.dateToString(new Date());
        for (StudentCourse studentCourse : studentCourses) {
            if (courseMapper.query(null, studentCourse.getCouName()).get(0).getNecessity() == 1) {
                List<Student> students = queryStudent(new Student(null, null, graName, null, studentCourse.getProName(), null));
                for (Student student : students) {
                    if (choiceMapper.queryByIdAndName(null,student.getStuId(),studentCourse.getCouName()).size() ==0 )
                        choices.add(new Choice(student.getStuId(), studentCourse.getCouName(), chooseDate));
                }
            }
        }
        result.put("result", "success");
        result.put("msg", "操作记录数量为" + choiceMapper.chooseNecessity(choices));
        return result;
    }

}
