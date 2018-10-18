package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Time;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeMapper{
    Integer add(Time time);
    Time queryByGraAndType(@Param("graName") String graName, @Param("type") String type);
    Integer modifyById(@Param("timeId") String timeId);
    Integer deleteById(@Param("timeId") String timeId);
}