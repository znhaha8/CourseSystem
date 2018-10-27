package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuCourseMapper {
    List<StudentCourse> query(StudentCourse studentCourse);
    Integer add(StudentCourse studentCourse);
    Integer modify(StudentCourse studentCourse);
    Integer delete(@Param("id")Integer id);
}
