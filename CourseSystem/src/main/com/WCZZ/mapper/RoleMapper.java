package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    /**
     * 查询一个用户的所有角色
     * @param userId
     * @return
     */

    List<Role> selectByUserId(@Param("userId") String userId);

    List<Integer> queryRoleIdByUserId (@Param("userId") String userId);
}