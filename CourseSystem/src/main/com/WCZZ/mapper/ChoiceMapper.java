package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Choice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceMapper{
    Integer add(Choice choice);
    List<Choice> queryByIdAndName(@Param("stuId") String stuId, @Param("couName")String couName);
    Integer withdraw(Choice choice);
    List<Choice> queryByStuId(@Param("stuId") String stuId);
    Integer modifyByStuId(Choice choice);
    Integer delete(Choice choice);
}