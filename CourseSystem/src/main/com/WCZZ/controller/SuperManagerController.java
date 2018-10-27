package main.com.WCZZ.controller;

import main.com.WCZZ.entity.*;
import main.com.WCZZ.service.SuperManagerService;
import main.com.WCZZ.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@CrossOrigin
@RequestMapping(value = "/superManager")
public class SuperManagerController {

    @Autowired
    SuperManagerService superManagerService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/self")
    @ResponseBody
    public Map<String,List<SuperManager>> querySupManager(String supId, String supName){
        Map<String, List<SuperManager>> resultMap = new HashMap<String, List<SuperManager>>();
        resultMap.put("result", superManagerService.querySupManager(supId,supName));
        return resultMap;
    }


    @PostMapping(value = "/self")
    @ResponseBody
    public Map<String,String> addSupManager(@RequestBody SuperManager superManager){
        Map<String,String> resultMap = new HashMap<String,String>();
        String createBy = (String)SecurityUtils.getSubject().getPrincipal();
        superManager.setCreateBy(createBy);
        resultMap.put("result","success");
        if(superManagerService.addSupManager(superManager) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping(value = "/self")
    @ResponseBody
    public Map<String, String> modifySupManager(@RequestBody SuperManager superManager){
        Map<String,String> resultMap = new HashMap<String,String>();
        String supId = (String)SecurityUtils.getSubject().getPrincipal();   //id和用户名相同
        superManager.setSupId(supId);
        if(superManagerService.modifySupManager(superManager) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @DeleteMapping(value = "/self")
    @ResponseBody
    public Map<String, String> deleteSupManager(){
        Map<String,String> resultMap = new HashMap<String,String>();
        String supId = (String)SecurityUtils.getSubject().getPrincipal();
        if(superManagerService.deleteSupManager(supId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping(value = "/manager")
    @ResponseBody
    public Map<String,List<Manager>> queryManager(String manId,String manName,String graName){
        Map<String, List<Manager>> resultMap = new HashMap<String, List<Manager>>();
        resultMap.put("result", superManagerService.queryManager(manId,manName,graName));
        return resultMap;
    }


    @PostMapping(value = "/manager")
    @ResponseBody
    public Map<String,String> addManager(@RequestBody Manager manager){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("result","success");
        if(superManagerService.addManager(manager) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping(value = "/manager")
    @ResponseBody
    public Map<String, String> modifyManager(@RequestBody Manager manager){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(superManagerService.modifyManager(manager) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @PutMapping(value = "/manager/modifyPassword")
    @ResponseBody
    public Map<String, String> modifyStudentPassword(String username, String password){
        Map<String,String> resultMap = new HashMap<String,String>();
        Set<String> roles = userService.findRoles(username);
        if (roles != null && roles.contains("manager") && userService.modifyPassword(username, password) != 0) {
            resultMap.put("result", "success");
            return resultMap;
        }
        resultMap.put("result","fail");
        return resultMap;
    }

    @DeleteMapping(value = "/manager")
    @ResponseBody
    public Map<String, String> deleteManager(String manId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(superManagerService.deleteManager(manId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping(value = "/team")
    @ResponseBody
    public Map<String,List<Team>> queryTeam(Integer claId, String graName,
                                            String acaName, String proName,
                                            String claName, String createDate){
        Map<String, List<Team>> resultMap = new HashMap<String, List<Team>>();
        Team team = new Team(claId, graName, acaName, proName, claName, createDate);
        resultMap.put("result", superManagerService.queryTeam(team));
        return resultMap;
    }


    @PostMapping(value = "/team")
    @ResponseBody
    public Map<String,String> addTeam(@RequestBody Team team){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("result","success");
        if(superManagerService.addTeam(team) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @DeleteMapping(value = "/team")
    @ResponseBody
    public Map<String, String> deleteManager(Integer claId){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(superManagerService.deleteTeam(claId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

}
