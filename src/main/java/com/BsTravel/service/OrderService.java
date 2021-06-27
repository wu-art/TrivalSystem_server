package com.BsTravel.service;

import com.BsTravel.entity.Order;

import java.util.List;

public interface OrderService {
    //    参数1:当前页 / 参数2:每页显示记录数
    List<Order> findByPage(Integer page,Integer rows);

    //查询总条数
    Integer findTotals();

    //添加订单
    void save(Order order);

    //删除订单
    void delete(String id);

    //编辑订单
    void update(Order order);

}
