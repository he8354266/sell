package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.mapper.ProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CodingSir
 * @Title: project
 * @Package ========
 * @Description: ========
 * @date 2020/8/27  18:04
 */
@RestController
@RequestMapping("/seller")
@Slf4j
public class MybatisController {
    @Autowired
    public ProductCategoryMapper productCategoryMapper;

    @GetMapping("/insert")
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        Date D = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("categoryName", "师兄不爱");
        map.put("categoryType", 102);
        map.put("createTime", sdf.format(D));
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @GetMapping("/insertObj")
    public void insertObj() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师兄最不爱");
        productCategory.setCategoryType(103);
        productCategory.setCreateTime(sdf.format(date));
        productCategoryMapper.insert(productCategory);

    }

    @GetMapping("/selectType")
    public void findByCategoryType() {
        List<ProductCategory> productCategoryList = productCategoryMapper.findByCategoryType(String.valueOf(103));
        log.info("productCategoryList=={}", productCategoryList);
    }

    @GetMapping("/selectName")
    public void findByCategoryName() {
        List<ProductCategory> productCategoryList = productCategoryMapper.findByCategoryName("师兄最不爱");
        log.warn("productCategoryList=={}", productCategoryList);
    }

    @GetMapping("/update")
    public void updateByCategoryType() {
        int result = productCategoryMapper.updateByCategoryType("师兄不爱11111", 103);
        Assert.assertEquals(1, result);
    }

    @GetMapping("/updateObj")
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师兄不爱1111");
        productCategory.setCategoryType(102);
        int result = productCategoryMapper.updateByObject(productCategory);
        log.info("result={}", result);
    }

    @GetMapping("/delete")
    public void delete() {
        int result = productCategoryMapper.deleteByCategoryType(104);
        log.info("result={}", result);
    }
    @GetMapping("/select2")
    public void selectByCategoryType(){
        ProductCategory productCategory  = productCategoryMapper.selectByCategoryType(102);
        log.info("result={}",productCategory);
    }
}
