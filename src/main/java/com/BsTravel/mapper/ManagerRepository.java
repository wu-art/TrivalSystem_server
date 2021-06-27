package com.BsTravel.mapper;

import com.BsTravel.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ManagerRepository {
//    注册用户方法
    int addUser(Manager manager);

//    用户登录方法
    List<Manager> queryByName(String username);
}
