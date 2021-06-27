package com.BsTravel.controller;

import com.BsTravel.entity.Province;
import com.BsTravel.entity.Result;
import com.BsTravel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    /*查询所有
    * page
    * rows
    */
    @GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer page,Integer rows){
        page = page==null?1:page;
        rows = rows==null?7:rows;
        HashMap<String,Object> map = new HashMap<>();
        //分页处理
        List<Province> province = provinceService.findByPage(page, rows);
        //计算总页数
        Integer totals = provinceService.findTotals();
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        map.put("province",province);
        map.put("totals",totals);
        map.put("totalPage",totalPage);
        map.put("page",page);
        return map;
    }

    /**
     * 修改省份信息方法
     */
    @PostMapping("update")
    public Result update(@RequestBody Province province) {
        Result result = new Result();
        try {
            provinceService.update(province);
            result.setMsg("修改省份信息成功");
            result.setState(true);
        }catch (Exception e){
            e.printStackTrace();
//            下行出错原因:可能是由于tomcat版本问题;
//            result.setState(false).setMsg(e.getMessage());
            result.setState(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 查询一个省份信息(根据name属性查询)
     */
    @GetMapping("findOne")
    public Province findOne(String name) {
        return provinceService.findOne(name);
    }
//    public Province findOne(String id) {
//
//        return provinceService.findOne(id);
//    }


    /**
     * 删除省份信息
     *
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id) {
        Result result = new Result();
        try {
            provinceService.delete(id);
            result.setMsg("删除省份信息成功");
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
//            result.setState(false).setMsg("删除省份信息失败!!!");
            result.setState(false);
            result.setMsg("删除省份信息失败!!!");
        }
        return result;
    }


    /**
     * 保存省份信息
     * @param province
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Province province) {
        Result result = new Result();
        try {
            provinceService.save(province);
            result.setMsg("保存省份信息成功");
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
//            result.setState(false).setMsg("保存省份信息失败!!!");
            result.setState(false);
            result.setMsg("保存省份信息失败!!!");
        }
        return result;
    }
}
