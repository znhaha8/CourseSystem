package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper{
    Integer add(Course course);
    List<Course> query(@Param("couId") Integer couId, @Param("couName") String couName);
    List<Course> stuQuery(@Param("graName") Integer graName, @Param("proName") String proName, @Param("stuId")String stuId);
    Integer modifyById(Course course);
    Integer deleteById(@Param("couId") Integer couId);
}