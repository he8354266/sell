package com.imooc.controller;

import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import com.imooc.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /*列表*/
    @GetMapping("/list")
    public ResultVO<Map<String, Object>> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> param = new HashMap<>();
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        param.put("totalPage", productInfoPage.getTotalPages());
        param.put("list", productInfoPage.getContent());
        param.put("total", productInfoPage.getTotalElements());
        return ResultVOUtil.success(param);
    }

    //商品上架
    @RequestMapping("/on_sale")
    public ResultVO on_Sale(@RequestParam("productId") String productId) {
        productService.onSale(productId);
        return ResultVOUtil.success();
    }
    //商品下架
    @RequestMapping("/off_sale")
    public ResultVO off_Sale(@RequestParam("productId") String productId){
        productService.offSale(productId);
        return ResultVOUtil.success();
    }
    @GetMapping("/index")
    public ResultVO index(@RequestParam(value = "productId",required = false)String productId){
        Map<String,Object> param=  new HashMap<>();
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            param.put("productInfo",productInfo);
        }
        //查询所有类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        param.put("categoryList",productCategoryList);
        return ResultVOUtil.success(param);
    }
    /*保存更新*/
    @PostMapping("/save")
    @CacheEvict(cacheNames = "product",allEntries = true,beforeInvocation = true)
    public ResultVO save(@Valid ProductForm productForm, BindingResult bindingResult){
        Map<String,Object> param = new HashMap<>();
        if(bindingResult.hasErrors()){
            param.put("msg",bindingResult.getFieldError().getDefaultMessage());
            ResultVOUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        ProductInfo productInfo = new ProductInfo();
        //如果productId为空，说明是新增
        if(!StringUtils.isEmpty(productForm.getProductId())){
            productInfo =productService.findOne(productForm.getProductId());
        }else{
            productForm.setProductId(KeyUtil.genUniqueKey());
        }
        BeanUtils.copyProperties(productForm,productInfo);
        productService.save(productInfo);
        return ResultVOUtil.success();
    }
}
