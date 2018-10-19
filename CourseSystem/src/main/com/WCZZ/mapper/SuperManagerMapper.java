package main.com.WCZZ.mapper;

import main.com.WCZZ.entity.SuperManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperManagerMapper {
    List<SuperManager> query (@Param("supId") String supId, @Param("supName")String supName);
    Integer add(SuperManager superManager);
    Integer modify(SuperManager superManager);
    Integer delete(@Param("supId")String supId);
}
