package com.BsTravel.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T,K> {
    void save(T t);
    void update(T t);
    void delete(K k);
    T findOne(K k);
    List<T> findAll();

//    分页
    List<T> findByPage(@Param("start") Integer start,@Param("rows") Integer rows);
//    查询总条数
    Integer findTotals();
}
