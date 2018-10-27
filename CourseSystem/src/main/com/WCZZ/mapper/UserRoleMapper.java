package main.com.WCZZ.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    Integer add(@Param("userId") String userId, @Param("roleId") Integer roleId);
    Integer delete(@Param("userId") String userId);
}
