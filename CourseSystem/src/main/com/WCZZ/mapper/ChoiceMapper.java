package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Choice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceMapper{
    Integer add(Choice choice);
    List<Choice> queryByIdAndName(@Param("choiceId") Integer choiceId, @Param("stuId") String stuId, @Param("couName")String couName);
    Integer withdraw(@Param("choiceId") Integer choiceId, @Param("withdrawDate") String withdrawDate);
    List<Choice> queryByStuId(@Param("stuId") String stuId);
    Integer delete(@Param("choiceId") Integer choiceId);
    Integer chooseNecessity(@Param("choices") List<Choice> choices);
}