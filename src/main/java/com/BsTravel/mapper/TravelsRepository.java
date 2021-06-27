package com.BsTravel.mapper;

import com.BsTravel.entity.Travels;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelsRepository extends BaseMapper<Travels,String> {
//    @Param("provinceId") String provinceId参数
    List<Travels> findByProvinceIdPage(@Param("start") Integer start, @Param("rows") Integer rows,@Param("provinceId") String provinceId);

//    Integer findByProvinceIdCounts(String provinceId);
    Integer findByProvinceIdCounts(String provinceId);
}
