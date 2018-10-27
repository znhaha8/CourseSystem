package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper{
    Integer add(Course course);
    List<Course> query(@Param("couName") String couName);
    List<Course> stuQuery(@Param("graName") String graName, @Param("proName") String proName);
    Integer modifyById(Course course);
    Integer deleteById(@Param("couId") Integer couId);
}