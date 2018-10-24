package main.com.WCZZ.service;

import main.com.WCZZ.entity.User;
import main.com.WCZZ.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;
    public User getUserByUserId(String userId) {
        return userMapper.selectUserById(userId);
    }

    public Set<String> findRoles(String username){
        return userMapper.findRoles(username);
    }

    public Set<String> findPermissions(String username){
        return userMapper.findPermissions(username);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}