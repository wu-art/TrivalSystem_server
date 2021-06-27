package com.BsTravel.service;




import com.BsTravel.entity.Manager;

import java.util.List;

/**
 * 根据业务关系，编写相关业务逻辑。
 */

public interface ManagerService {
//    用户注册方法
    int addUser(String username, String password, String phone);

//    用户登录方法
    List<Manager> queryByName(String username);
}
