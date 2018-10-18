package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Choice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceMapper{
    Integer add(Choice choice);
    Choice selectByIds(@Param("stuId") String stuId, @Param("couId")Integer couId);
    Integer withdraw(Choice choice);

}