package main.com.WCZZ.service;

import main.com.WCZZ.entity.Manager;
import main.com.WCZZ.entity.SuperManager;
import main.com.WCZZ.entity.Team;
import main.com.WCZZ.mapper.ManagerMapper;
import main.com.WCZZ.mapper.SuperManagerMapper;
import main.com.WCZZ.mapper.TeamMapper;
import main.com.WCZZ.mapper.UserMapper;
import main.com.WCZZ.util.IdGenerator;
import main.com.WCZZ.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SuperManagerService {
    @Autowired
    private SuperManagerMapper superManagerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private UserMapper userMapper;

    public List<SuperManager> querySupManager(String supId,String supName){
        return superManagerMapper.query(supId,supName);
    }

    @Transactional
    public Integer addSupManager(SuperManager superManager){
        String supId = IdGenerator.generateId();
        String password = IdGenerator.generatePassword(supId);
        String createDate = TimeUtil.dateToString(new Date());
        superManager.setSupId(supId);
        superManager.setCreateDate(createDate);
        int res = (userMapper.add(supId,password,createDate) == 1 && superManagerMapper.add(superManager) == 1) ? 1 : 0;
        return res;
    }

    @Transactional
    public Integer modifySupManager(SuperManager superManager){
        return superManagerMapper.modify(superManager);
    }

    @Transactional
    public Integer deleteSupManager(String supId){
        return superManagerMapper.delete(supId);
    }

    public List<Manager> queryManager(String manId, String manName, String graName){
        return managerMapper.query(manId, manName, graName);
    }

    @Transactional
    public Integer addManager(Manager manager){
        String manId = IdGenerator.generateId();
        String password = IdGenerator.generatePassword(manId);
        String createDate = TimeUtil.dateToString(new Date());
        manager.setManId(manId);
        manager.setCreateDate(createDate);
        int res = (userMapper.add(manId,password,createDate) == 1 && managerMapper.add(manager) == 1) ? 1 : 0;
        return res;
    }

    @Transactional
    public Integer modifyManager(Manager manager){
        return managerMapper.modify(manager);
    }

    @Transactional
    public Integer deleteManager(String manId){
        return managerMapper.delete(manId);
    }

    @Transactional
    public Integer addTeam(Team team){

        if (teamMapper.query(team).size() != 0)
            return 0;
        return teamMapper.add(team);
    }

    public List<Team> queryTeam(Team team){

        return teamMapper.query(team);
    }

    @Transactional
    public Integer deleteTeam(Integer claId){
        return teamMapper.deleteById(claId);
    }



}
