package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> queryByUserId(String userId);
}
