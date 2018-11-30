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
import javax.servlet.http.HttpSession;
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

    @GetMapping(value = "/selves")
    @ResponseBody
    public Map<String,List<SuperManager>> querySupManager(String supId, String supName){
        Map<String, List<SuperManager>> resultMap = new HashMap<String, List<SuperManager>>();
        resultMap.put("result", superManagerService.querySupManager(supId,supName));
        return resultMap;
    }

    @GetMapping(value = "/self")
    @ResponseBody
    public Map<String,List<SuperManager>> querySelf(){
        Map<String, List<SuperManager>> resultMap = new HashMap<String, List<SuperManager>>();
        String supId = (String)SecurityUtils.getSubject().getPrincipal();
        resultMap.put("result", superManagerService.querySupManager(supId,null));
        return resultMap;
    }


    @PostMapping(value = "/self")
    @ResponseBody
    public Map<String,String> addSupManager(@RequestBody SuperManager superManager){
        String createBy = (String)SecurityUtils.getSubject().getPrincipal();
        superManager.setCreateBy(createBy);
        Map<String,String> resultMap = superManagerService.addSupManager(superManager);
        resultMap.put("result","success");
        if(!resultMap.get("msg").equals("success"))
            resultMap.put("result","fail");
        return resultMap;
    }

    @PutMapping(value = "/self")
    @ResponseBody
    public Map<String, String> modifyPhone(@RequestBody SuperManager superManager){
        String supId = (String)SecurityUtils.getSubject().getPrincipal();   //id和用户名相同
        superManager.setSupId(supId);
        Map<String,String> resultMap = superManagerService.modifySupManager(superManager);

        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

    @DeleteMapping(value = "/self")
    @ResponseBody
    public Map<String, String> deleteSupManager(){
        String supId = (String)SecurityUtils.getSubject().getPrincipal();
        Map<String,String> resultMap = superManagerService.deleteSupManager(supId);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        SecurityUtils.getSubject().logout();
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
        Map<String,String> resultMap = superManagerService.addManager(manager);
        resultMap.put("result","success");
        if(!resultMap.get("msg").equals("success")) {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }

    @PutMapping(value = "/manager")
    @ResponseBody
    public Map<String, String> modifyManager(@RequestBody Manager manager){
        Map<String,String> resultMap = superManagerService.modifyManager(manager);
        if(!resultMap.get("msg").equals("success")){
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
        if (roles != null){
            resultMap.put("result","fail");
            resultMap.put("msg","对象用户角色为空");
            return resultMap;
        }
        if(roles.contains("manager")){
            resultMap.put("result","fail");
            resultMap.put("msg","对象用户不持有管理员角色");
            return resultMap;
        }
        if(userService.modifyPassword(username, password) != 0) {
            resultMap.put("result", "success");
            resultMap.put("msg","success");
            return resultMap;
        }
        resultMap.put("result","fail");
        resultMap.put("result", "确认用户名是否正确和密码长度是否过长");
        return resultMap;
    }

    @DeleteMapping(value = "/manager")
    @ResponseBody
    public Map<String, String> deleteManager(String manId){
        Map<String,String> resultMap = superManagerService.deleteManager(manId);
        if(!resultMap.get("msg").equals("success")){
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
        Map<String,String> resultMap = superManagerService.addTeam(team);
        resultMap.put("result","success");
        if(!resultMap.get("msg").equals("success"))
            resultMap.put("result","fail");
        return resultMap;
    }

    @DeleteMapping(value = "/team")
    @ResponseBody
    public Map<String, String> deleteManager(Integer claId){
        Map<String,String> resultMap = superManagerService.deleteTeam(claId);
        if(!resultMap.get("msg").equals("success")){
            resultMap.put("result","fail");
            return resultMap;
        }
        resultMap.put("result","success");
        return resultMap;
    }

}
