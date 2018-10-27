package main.com.WCZZ.service;

import main.com.WCZZ.entity.User;
import main.com.WCZZ.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    public Set<String> findRoles(String username){
        return userMapper.findRoles(username);
    }

    public Set<String> findPermissions(String username){
        return userMapper.findPermissions(username);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public Integer modifyPassword(String username, String password){
        User user = findByUsername(username);
        Md5Hash md5Hash = new Md5Hash(password,user.getSalt());
        password = md5Hash.toString();
        return userMapper.modifyPassword(username, password);
    }

}