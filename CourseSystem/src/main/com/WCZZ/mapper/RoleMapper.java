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
    @Select("select * from role where role_id in (select role_id from r_user_role where user_id=#{uerId})")
    @Results({
            @Result(id=true,column = "user_id",property = "userId"),
            @Result(column = "name",property = "name"),
            @Result(column = "description",property = "description")
    })
    List<Role> selectByUserId(@Param("userId") String userId);
}