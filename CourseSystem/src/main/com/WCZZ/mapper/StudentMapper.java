package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentMapper{
    List<Student> query(Student student);
    Integer add(Student student);
    Integer modifyPhone(String stuId, String phone);
    Integer modifyPassword(User user);
    Integer delete(String stuId);
}