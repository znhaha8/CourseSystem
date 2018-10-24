package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    /**
     * 查询一个角色的所有权限
     * @param roleId
     * @return
     */

    List<Permission> selectPermissionByRoleId(@Param("roleId") Integer roleId);
}