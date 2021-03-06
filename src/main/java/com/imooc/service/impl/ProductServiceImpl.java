package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@CacheConfig(cacheNames = "products")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;


    @Override
    @Cacheable(key = "123")
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    @Cacheable(key = "456")
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> productInfoPage = repository.findAll(pageable);
//        productInfoPage.getContent().stream()
//                .forEach(e -> e.addImageHost(upYunConfig.getImageHost()));
        return productInfoPage;
    }

    @Override
    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        if (productInfo.getProductStatus().equals(ProductStatusEnum.UP)) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(@RequestParam("productId") String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN)) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
