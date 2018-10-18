package main.com.WCZZ.service;

import main.com.WCZZ.entity.Permission;
import main.com.WCZZ.entity.Role;
import main.com.WCZZ.entity.User;
import main.com.WCZZ.mapper.PermissionMapper;
import main.com.WCZZ.mapper.RoleMapper;
import main.com.WCZZ.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
public class PermissionService{
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    public List<Permission> getPermissionByUserId(String userid) {
        List<Role> list=roleMapper.selectByUserId(userid);
        List<Permission> permissions=new ArrayList<Permission>();
        for(Role role:list){
            List<Permission> permissionList=permissionMapper.selectPermissionByRoleId(role.getRoleId());
            permissions.addAll(permissionList);
        }
        return permissions;
    }

    public List<String> getTheUrl(String userid) {
        User user=userMapper.selectUserById(userid);
        List<Permission> permissions=getPermissionByUserId(user.getUserId());
        List<String> listUrl=new ArrayList<String>();
        for(Permission permission:permissions){
            listUrl.add(permission.getTheurl());
        }
        return listUrl;
    }
}