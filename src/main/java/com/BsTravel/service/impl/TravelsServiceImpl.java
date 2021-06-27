package com.BsTravel.service.impl;

import com.BsTravel.entity.Province;
import com.BsTravel.entity.Travels;
import com.BsTravel.mapper.TravelsRepository;
import com.BsTravel.service.ProvinceService;
import com.BsTravel.service.TravelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravelsServiceImpl implements TravelsService {

    @Autowired
    private TravelsRepository travelsRepository;

    @Autowired
    private ProvinceService provinceService;

    @Override
    public List<Travels> findByProvinceIdPage(Integer page, Integer rows, String provinceId ) {
        int start = (page-1)*rows;
        return travelsRepository.findByProvinceIdPage(start,rows,provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts( String provinceId) {
        return travelsRepository.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void update(Travels travels) {
        travelsRepository.update(travels);
    }

    @Override
    public Travels findOne(String id) {
        return travelsRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        //直接删除景点  更新省份景点个数 删除景点
        Travels travels = travelsRepository.findOne(id);
        Province province = provinceService.findOne(travels.getProvinceid());
//        province.setPlacecounts(province.getPlacecounts()-1);
        provinceService.update(province);
        //删除景点信息
        travelsRepository.delete(id);
    }
    //添加景点
    @Override
    public void save(Travels travels) {
        //保存景点
        travelsRepository.save(travels);
        //查询原始省份信息
        Province province = provinceService.findOne(travels.getProvinceid());
        //更新省份信息的景点个数
//        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.update(province);
    }
}
