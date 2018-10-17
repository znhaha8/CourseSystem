package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.Manager;
import main.com.WCZZ.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ManagerMapper{
    List<Manager> query(Manager manager);
    Integer add(Manager manager);
    Integer modifyPhone(Integer manId, String phone);
    Integer modifyPassword(User user);
    Integer delete(Integer manId);
}