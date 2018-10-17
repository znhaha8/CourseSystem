package main.com.WCZZ.service;

import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class ManagerService {
    @Autowired
    StudentMapper studentMapper;
    public List<Student> query(Student student){
        List<Student> students = null;
        students = studentMapper.query(student);
        return students;
    }

    @Transactional
    public Integer addStudent(Student student)throws Exception{
        if(student.getStuName().isEmpty()
                || student.getGraName() == null
                || student.getAcaName().isEmpty()
                || student.getProName().isEmpty()
                || student.getClaName() == null)

            return 0;

        student.setCreateTime(new Date());
        return studentMapper.add(student);
    }

}
