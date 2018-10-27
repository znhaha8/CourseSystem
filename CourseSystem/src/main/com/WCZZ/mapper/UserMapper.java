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

    Integer add(User user);
    Integer modifyPassword(@Param("username") String username, @Param("password") String password);
    Integer delete(@Param("id") String id);

    Set<String> findRoles(@Param("username") String username);

    Set<String> findPermissions(@Param("username")String username);

    User findByUsername(@Param("username")String username);
}