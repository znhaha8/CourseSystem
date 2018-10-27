package main.com.WCZZ.service;

import main.com.WCZZ.entity.Manager;
import main.com.WCZZ.entity.SuperManager;
import main.com.WCZZ.entity.Team;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.mapper.*;
import main.com.WCZZ.util.IdGenerator;
import main.com.WCZZ.util.TimeUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Autowired
    UserRoleMapper userRoleMapper;

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
        Random random = new Random();
        String salt = String.valueOf(random.nextInt(10000));
        Md5Hash md5Hash = new Md5Hash(password,salt);
        password = md5Hash.toString();
        User user = new User(supId,supId,password,salt,createDate);
        int res = (userMapper.add(user) == 1
                && superManagerMapper.add(superManager) == 1
                && userRoleMapper.add(user.getId(),1) == 1) ? 1 : 0;
        return res;
    }

    @Transactional
    public Integer modifySupManager(SuperManager superManager){
        return superManagerMapper.modify(superManager);
    }

    @Transactional
    public Integer deleteSupManager(String supId){
        Integer res = (superManagerMapper.delete(supId) == 0
                        || userMapper.delete(supId)==0
                        || userRoleMapper.delete(supId) == 0)
                    ? 0 : 1;
        return res;
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
        Random random = new Random();
        String salt = String.valueOf(random.nextInt(10000));
        Md5Hash md5Hash = new Md5Hash(password,salt);
        password = md5Hash.toString();
        User user = new User(manId,manId,password,salt,createDate);
        int res = (userMapper.add(user) == 1
                && managerMapper.add(manager) == 1
                && userRoleMapper.add(user.getId(),2) == 1) ? 1 : 0;
        return res;
    }

    @Transactional
    public Integer modifyManager(Manager manager){
        return managerMapper.modify(manager);
    }

    @Transactional
    public Integer deleteManager(String manId){
        Integer res = (managerMapper.delete(manId) == 0
                || userMapper.delete(manId)==0
                || userRoleMapper.delete(manId) == 0)
                ? 0 : 1;
        return res;
    }

    @Transactional
    public Integer addTeam(Team team){
        if (teamMapper.query(team).size() != 0)
            return 0;
        team.setCreateDate(TimeUtil.dateToString(new Date()));
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
