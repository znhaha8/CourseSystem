package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Manager;
import main.com.WCZZ.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ManagerMapper{
    List<Manager> query(@Param("manId") String manId, @Param("manName")String manName, @Param("graName")String graName);
    Integer add(Manager manager);
    Integer modifyPhone(@Param("manId")String manId, @Param("phone")String phone);
    Integer modifyPassword(User user);
    Integer modify(Manager manager);
    Integer delete(@Param("manId")String manId);
}