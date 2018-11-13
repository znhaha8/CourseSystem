package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Time;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeMapper{
    Integer add(Time time);
    List<Time> queryByGraAndType(@Param("graName") Integer graName, @Param("type") String type);
    Integer modify(Time time);
    Integer deleteById(@Param("timeId") Integer timeId);
}