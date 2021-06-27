package com.BsTravel.service;

import com.BsTravel.entity.Travels;
import java.util.List;

public interface TravelsService {

    List<Travels> findByProvinceIdPage(Integer page, Integer rows, String provinceId);

    Integer findByProvinceIdCounts( String provinceId);

    void save(Travels travels);

    void delete(String id);

    Travels findOne(String id);

    void update(Travels travels);
}
