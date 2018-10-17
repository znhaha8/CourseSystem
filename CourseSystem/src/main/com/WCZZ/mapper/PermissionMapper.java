package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Permission;

import java.util.List;

public interface PermissionMapper {

    List<Permission> queryByRoleId(Integer roleId);
}
