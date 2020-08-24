package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);
    //查询所有在架商品列表
    List<ProductInfo> findUpAll();
    //分页查询所有数据
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    ProductInfo onSale(String productId);

    ProductInfo offSale(@RequestParam("productId") String productId);

    //加库存
    //void increaseStock(List<>)
}
