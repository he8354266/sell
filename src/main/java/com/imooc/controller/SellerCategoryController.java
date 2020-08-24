package com.imooc.controller;

import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.service.CategoryService;
import com.imooc.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    /*类目列表*/
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        System.out.println("===========");
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/second")

    public String get() {
        System.out.println("===========");
        return "haha";
    }
    /*展示*/
    @GetMapping("/index")
    public ResultVO index(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        Map<String, Object> param = new HashMap<>();
        if (categoryId != null) {
            ProductCategory productCategory =categoryService.findOne(categoryId);
            param.put("category",productCategory);
        }
        return ResultVOUtil.success(param);
    }
    /*保存/更新*/
    public ResultVO save(){

        return null;
    }
}
