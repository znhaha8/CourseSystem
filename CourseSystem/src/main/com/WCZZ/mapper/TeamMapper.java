package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper{
    Integer add(Team team);
    List<Team> query(Team team);
    Integer deleteById(String claId);
}