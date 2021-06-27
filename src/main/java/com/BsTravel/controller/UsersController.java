package com.BsTravel.controller;

import com.BsTravel.entity.Users;
import com.BsTravel.mapper.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

//    查询所有的用户
    @GetMapping("/findAll")
    public List<Users> findAll(){
        return userRepository.findAll();
    }

//    分页功能(获取数据接口:http://localhost:8081/users/findAll/页码/数据量)
    @GetMapping("/findAll/{page}/{size}")
    public Page<Users> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return userRepository.findAll(pageable);
    }

//    添加用户 post方法获取(获取添加用户数据接口:http://localhost:8081/api/users/save)
    @PostMapping("/save")
    public String save(@RequestBody Users users){
        Users result = userRepository.save(users);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

//    编辑用户/查询用户id/反馈给前端(获取编辑用户数据接口:http://localhost:8081/api/users//findById/id)
    @GetMapping("/findById/{id}")
    public Users findById(@PathVariable("id") Integer id){
        return userRepository.findById(id).get();
    }

//    编辑用户/将修改后得信息反馈给数据库
    @PutMapping("/update")
    public String update(@RequestBody Users users){
        Users result = userRepository.save(users);
        if (result != null){
            return "success";
        }else {
            return "error";
        }
    }

//    删除用户数据
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }
}
