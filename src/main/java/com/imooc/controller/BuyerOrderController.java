package com.imooc.controller;

import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
}
