package com.BsTravel.service.impl;

import com.BsTravel.entity.Order;
import com.BsTravel.mapper.OrderRepository;
import com.BsTravel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return orderRepository.findByPage(start,rows);
    }

    @Override
    public Integer findTotals() {
        return orderRepository.findTotals();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(String id) {
        orderRepository.delete(id);
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

}
