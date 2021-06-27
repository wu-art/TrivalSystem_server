package com.BsTravel.mapper;

import com.BsTravel.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRepository extends BaseMapper<Order,String> {
}
