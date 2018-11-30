package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Choice;
import main.com.WCZZ.entity.Team;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TeamMapper{
    Integer add(Team team);
    List<Team> query(Team team);
    List<Team> queryAC(Team team);
    Integer deleteById(@Param("claId") Integer claId);
    Set<String> queryProByGra(@Param("graName") Integer graName);
}