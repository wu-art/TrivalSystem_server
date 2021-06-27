package com.BsTravel.controller;

import com.BsTravel.entity.Result;
import com.BsTravel.entity.Travels;
import com.BsTravel.service.TravelsService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/travels")
public class TravelsController {
    @Autowired
    private TravelsService travelsService;

    @Value("${upload.dir}")
    private String realPath;

    /**
     * 修改景点信息
     */
    @PostMapping("update")
    public Result update(@RequestBody Travels travels) {
        Result result = new Result();
        try {
            travelsService.update(travels);
            result.setMsg("修改景点信息成功");
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
     * 查询景点信息
     */
    @GetMapping("findOne")
    public Travels findOne(String id){
        return travelsService.findOne(id);
    }

    /**
     * 删除景点信息
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id) {
        Result result = new Result();
        try {
            travelsService.delete(id);
            result.setMsg("删除景点信息成功");
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
//            result.setState(false).setMsg("删除省份信息失败!!!");
            result.setState(false);
            result.setMsg("删除景点信息失败!!!");
        }
        return result;
    }

    /**
     * 保存景点信息
     *添加景点
     * @param
     * @return
     */
    @PostMapping("save")
//    @ResponseBody
    public Result save(MultipartFile picfile ,Travels travels) {
//        System.out.println("aa:" + picfile.getOriginalFilename());
        Result result = new Result();
        try {
            //base64编码处理
            travels.setPicpath(Base64Utils.encodeToString(picfile.getBytes()));
            //文件上传
            String extension = FilenameUtils.getExtension(picfile.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + extension;
            //文件上传
            File file = new File(realPath);
            picfile.transferTo(new File(file,newFileName));
//            保存travel对象
            travelsService.save(travels);
            result.setMsg("添加景点信息成功");
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
//            result.setState(false).setMsg("保存景点信息失败!!!");
            result.setState(false);
            result.setMsg("添加景点信息失败!!!");
        }
        return result;
    }


    /*根据省份id查询景点方法*/
    @GetMapping("findAllTravels")
    @ResponseBody
//    String provinceId参数
    public Map<String,Object> findAllTravels(Integer page,Integer rows,String provinceId){
//        System.out.println("provinceID:"+ provinceId);
        HashMap<String, Object> map = new HashMap<>();
        page = page==null?1:page;
        rows = rows==null?3:rows;
        /*景点集合*/
//        provinceId参数
        List<Travels> travels = travelsService.findByProvinceIdPage(page, rows,provinceId);

        /*分页处理  counts-总条数*/
//        provinceId参数
        Integer counts = travelsService.findByProvinceIdCounts(provinceId);
        //总页数 (rows-每页数据量)
        Integer totalPage = counts%rows==0?counts/rows:counts/rows+1;
        map.put("travels",travels);
        map.put("page",page);
        map.put("counts",counts);
        map.put("totalPage",totalPage);
        return map;
    }
}
