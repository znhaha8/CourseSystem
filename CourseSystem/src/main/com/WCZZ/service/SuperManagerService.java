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

import java.util.*;

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
    public Map<String, String> addSupManager(SuperManager superManager){
        Map<String, String> result = new HashMap<>();
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
        if(res == 0){
            result.put("msg","fail");
        }else {
            result.put("msg","success");
            result.put("supId",supId);
        }
        return result;
    }

    @Transactional
    public Map<String, String> modifySupManager(SuperManager superManager){
        Map<String, String> result = new HashMap<>();
        if(superManagerMapper.modify(superManager) == 0){
            result.put("msg","fail");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Map<String, String> deleteSupManager(String supId){
        Map<String, String> result = new HashMap<>();
        Integer res = (superManagerMapper.delete(supId) == 0
                        || userMapper.delete(supId)==0
                        || userRoleMapper.delete(supId) == 0)
                    ? 0 : 1;
        if(res == 0){
            result.put("msg", "fail");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    public List<Manager> queryManager(String manId, String manName, String graName){
        return managerMapper.query(manId, manName, graName);
    }

    @Transactional
    public Map<String, String> addManager(Manager manager){
        Map<String, String> result = new HashMap<>();
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
        if(res == 0){
            result.put("msg","fail");
        }else {
            result.put("msg","success");
            result.put("supId",manId);
        }
        return result;
    }

    @Transactional
    public Map<String, String> modifyManager(Manager manager){
        Map<String, String> result = new HashMap<>();
        if(managerMapper.modify(manager) == 0 ){
            result.put("msg", "操作数量为 0");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Map<String, String> deleteManager(String manId){
        Map<String, String> result = new HashMap<>();
        Integer res = (managerMapper.delete(manId) == 0
                || userMapper.delete(manId)==0
                || userRoleMapper.delete(manId) == 0)
                ? 0 : 1;
        if(res == 0){
            result.put("msg", "fail");
        }else {
            result.put("msg", "success");
        }
        return result;
    }

    @Transactional
    public Map<String, String> addTeam(Team team){
        Map<String, String> result = new HashMap<>();
        if (teamMapper.queryAC(team).size() != 0) {
            result.put("msg", "班级已存在");
            return result;
        }
        team.setCreateDate(TimeUtil.dateToString(new Date()));
        if(teamMapper.add(team) == 0){
            result.put("msg","fail");
            return result;
        }
        result.put("msg","success");
        return result;
    }

    public List<Team> queryTeam(Team team){
        return teamMapper.query(team);
    }

    @Transactional
    public Map<String, String> deleteTeam(Integer claId){
        Map<String, String> result = new HashMap<>();
        if(teamMapper.deleteById(claId) == 0){
            result.put("msg", "id不存在或者异常");
        }else {
            result.put("msg", "success");
        }
        return result;
    }



}
