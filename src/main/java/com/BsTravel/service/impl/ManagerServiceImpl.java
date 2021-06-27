package com.BsTravel.service.impl;

import com.BsTravel.entity.Manager;
import com.BsTravel.mapper.ManagerRepository;
import com.BsTravel.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository managerRepository;

//    用户注册方法实现
    @Override
    public int addUser(String username,String password,String phone) {
        Manager manager = new Manager(username, password, phone);
        int isadd = managerRepository.addUser(manager);
        return isadd;
    }


    @Override
    public List<Manager> queryByName(String username){
        List<Manager> managerList = managerRepository.queryByName(username);
        return managerList;
    }
}
