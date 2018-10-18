package main.com.WCZZ.service;

import main.com.WCZZ.entity.User;
import main.com.WCZZ.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService{
    @Autowired
    private UserMapper userMapper;
    public User getUserByUserId(String userId) {
        return userMapper.selectUserById(userId);
    }
}