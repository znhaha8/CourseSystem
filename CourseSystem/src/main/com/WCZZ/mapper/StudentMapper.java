package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentMapper{
    List<Student> query(Student student);
    Integer add(Student student);
    Integer modifyPhone(@Param("stuId") String stuId, @Param("phone") String phone);
    Integer delete(@Param("stuId")String stuId);
    Integer modifyById(Student student);
}