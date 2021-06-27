package com.BsTravel;

import com.BsTravel.entity.Province;
import com.BsTravel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = Travel218Application.class)
public class TestProvince {

    @Autowired
    private ProvinceService provinceService;

    public void testFindByPage(){
        List<Province> list = provinceService.findByPage(1, 5);
        list.forEach( pr->{
            System.out.println(pr);
        });
    }
}
