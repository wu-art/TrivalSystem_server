package com.BsTravel.controller;

import com.BsTravel.entity.Order;
import com.BsTravel.entity.Result;
import com.BsTravel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //查询所有订单
    @GetMapping("findOrder")
    public Map<String,Object> findByPage(Integer page,Integer rows){
        page = page==null?1:page;
        rows = rows==null?6:rows;
        HashMap<String,Object> map = new HashMap<>();
        //分页处理
        List<Order> order = orderService.findByPage(page,rows);
        //总页数计算
        Integer totals = orderService.findTotals();
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        map.put("order",order);  //订单列表
        map.put("totals",totals);  //总条数
        map.put("totalPage",totalPage); //总页数
        map.put("page",page); //当前页
        map.put("rows",rows);
        return map;
    }

    //添加订单
    @PostMapping("save")
    public Result save(@RequestBody Order order){
        Result result = new Result();
        try {
            orderService.save(order);
            result.setMsg("订单添加成功");
            result.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("订单添加失败");
            result.setState(false);
        }
        return result;
    }
    //删除
    @GetMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try {
            orderService.delete(id);
            result.setState(true);
            result.setMsg("订单删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false);
            result.setMsg("订单删除失败");
        }
        return result;
    }

    //编辑
    @PostMapping("update")
    public Result update(@RequestBody Order order){
        Result result = new Result();
        try {
            orderService.update(order);
            result.setState(true);
            result.setMsg("订单修改成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false);
            result.setMsg("订单修改失败");
        }
        return result;
    }
}
