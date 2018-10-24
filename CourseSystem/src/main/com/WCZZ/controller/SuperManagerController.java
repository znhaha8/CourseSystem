package main.com.WCZZ.controller;

import main.com.WCZZ.entity.Manager;
import main.com.WCZZ.entity.Student;
import main.com.WCZZ.entity.SuperManager;
import main.com.WCZZ.entity.Team;
import main.com.WCZZ.service.SuperManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(value = "/superManager")
public class SuperManagerController {

    @Autowired
    SuperManagerService superManagerService;

    @GetMapping(value = "/self")
    @ResponseBody
    public Map<String,List<SuperManager>> querySupManager(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            String supId,
            String supName){
        Map<String, List<SuperManager>> resultMap = new HashMap<String, List<SuperManager>>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
        resultMap.put("result", superManagerService.querySupManager(supId,supName));
        return resultMap;
    }


    @PostMapping(value = "/self")
    @ResponseBody
    public Map<String,String> addSupManager(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            @RequestBody SuperManager superManager){
        Map<String,String> resultMap = new HashMap<String,String>();
        /*if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        resultMap.put("result","success");
        if(superManagerService.addSupManager(superManager) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping(value = "/self")
    @ResponseBody
    public Map<String, String> modifySupManager(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody SuperManager superManager){
        Map<String,String> resultMap = new HashMap<String,String>();
        /*if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(superManagerService.modifySupManager(superManager) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @DeleteMapping(value = "/self")
    @ResponseBody
    public Map<String, String> deleteSupManager(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                String supId){
        Map<String,String> resultMap = new HashMap<String,String>();
        /*if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(superManagerService.deleteSupManager(supId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping(value = "/manager")
    @ResponseBody
    public Map<String,List<Manager>> queryManager(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            String manId,
            String manName,
            String graName){
        Map<String, List<Manager>> resultMap = new HashMap<String, List<Manager>>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
        resultMap.put("result", superManagerService.queryManager(manId,manName,graName));
        return resultMap;
    }


    @PostMapping(value = "/manager")
    @ResponseBody
    public Map<String,String> addManager(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            @RequestBody Manager manager){
        Map<String,String> resultMap = new HashMap<String,String>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        resultMap.put("result","success");
        if(superManagerService.addManager(manager) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping(value = "/manager")
    @ResponseBody
    public Map<String, String> modifyManager(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                                @RequestBody Manager manager){
        Map<String,String> resultMap = new HashMap<String,String>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(superManagerService.modifyManager(manager) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @DeleteMapping(value = "/manager")
    @ResponseBody
    public Map<String, String> deleteManager(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             String manId){
        Map<String,String> resultMap = new HashMap<String,String>();
        /*if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(superManagerService.deleteManager(manId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @GetMapping(value = "/team")
    @ResponseBody
    public Map<String,List<Team>> queryTeam(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            Integer claId,
            String graName,
            String acaName,
            String proName,
            String claName,
            String createDate){
        Map<String, List<Team>> resultMap = new HashMap<String, List<Team>>();
        /*if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", null);
            return resultMap;
        }*/
        Team team = new Team(claId, graName, acaName, proName, claName, createDate);
        resultMap.put("result", superManagerService.queryTeam(team));
        return resultMap;
    }


    @PostMapping(value = "/team")
    @ResponseBody
    public Map<String,String> addTeam(
            String sessionID, HttpServletRequest request, HttpServletResponse response,
            @RequestBody Team team){
        Map<String,String> resultMap = new HashMap<String,String>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        resultMap.put("result","success");
        if(superManagerService.addTeam(team) <= 0 )
            resultMap.put("result","fail");
        return resultMap;
    }

    @DeleteMapping(value = "/team")
    @ResponseBody
    public Map<String, String> deleteManager(String sessionID, HttpServletRequest request, HttpServletResponse response,
                                             Integer claId){
        Map<String,String> resultMap = new HashMap<String,String>();
       /* if(UserStatus.isAuthenticated(sessionID,request,response) == false){
            resultMap.put("result", "not login");
            return resultMap;
        }*/
        if(superManagerService.deleteTeam(claId) == 0){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }




}
