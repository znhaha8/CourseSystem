package main.com.WCZZ.service;

import main.com.WCZZ.entity.Role;
import main.com.WCZZ.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    public List<Role> getRoles(String userId) {
        return roleMapper.selectByUserId(userId);
    }
}