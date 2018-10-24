package main.com.WCZZ.mapper;
import main.com.WCZZ.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface UserMapper{
    /**
     * 根据用户名查询用户的所有信息
     * @param userId
     * @return
     */
    User selectUserById(@Param("userId") String userId);
    Integer add(@Param("userId") String userId, @Param("password") String password, @Param("createDate") String createDate);
    Integer modifyPassword(@Param("userId") String userId, @Param("password") String password);
    Integer delete(@Param("userId") String userId);

    Set<String> findRoles(@Param("username") String username);

    Set<String> findPermissions(@Param("username")String username);

    User findByUsername(@Param("username")String username);
}