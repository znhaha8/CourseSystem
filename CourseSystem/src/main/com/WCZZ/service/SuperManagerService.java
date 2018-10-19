package main.com.WCZZ.service;

import main.com.WCZZ.entity.Manager;
import main.com.WCZZ.entity.SuperManager;
import main.com.WCZZ.entity.Team;
import main.com.WCZZ.mapper.ManagerMapper;
import main.com.WCZZ.mapper.SuperManagerMapper;
import main.com.WCZZ.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperManagerService {
    @Autowired
    private SuperManagerMapper superManagerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private TeamMapper teamMapper;
    public List<SuperManager> querySupManager(String supId,String supName){
        return superManagerMapper.query(supId,supName);
    }
    public Integer addSupManager(SuperManager superManager){
        return superManagerMapper.add(superManager);
    }
    public Integer modifySupManager(SuperManager superManager){
        return superManagerMapper.modify(superManager);
    }
    public Integer deleteSupManager(String supId){
        return superManagerMapper.delete(supId);
    }

    public List<Manager> queryManager(String manId, String manName, String graName){
        return managerMapper.query(manId, manName, graName);
    }

    public Integer addManager(Manager manager){
        return managerMapper.add(manager);
    }

    public Integer deleteManager(String manId){
        return managerMapper.delete(manId);
    }

    public Integer addTeam(Team team){
        return teamMapper.add(team);
    }

    public List<Team> queryTeam(Team team){
        return teamMapper.query(team);
    }

    public Integer deleteTeam(Integer claId){
        return teamMapper.deleteById(claId);
    }



}
