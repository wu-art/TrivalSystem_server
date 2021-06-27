package com.BsTravel.service.impl;

import com.BsTravel.entity.Province;
import com.BsTravel.mapper.ProvinceRepository;
import com.BsTravel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
//    @Resource
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return provinceRepository.findByPage(start,rows);
    }

    @Override
    public Integer findTotals() {
        return provinceRepository.findTotals();
    }

    @Override
    public void save(Province province) {
        //景点个数为零
        province.setPlacecounts(0);
        provinceRepository.save(province);
    }

    @Override
    public void delete(String id) {
        provinceRepository.delete(id);
    }

    @Override
    public Province findOne(String name) {
        return provinceRepository.findOne(name);
    }

    @Override
    public void update(Province province) {
        provinceRepository.update(province);
    }
}
