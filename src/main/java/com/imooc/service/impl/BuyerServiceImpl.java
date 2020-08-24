package com.imooc.service.impl;

import com.imooc.dto.OrderDtO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.CategoryService;
import com.imooc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDtO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDtO cancelOrder(String openid, String orderId) {
        OrderDtO orderDtO = checkOrderOwner(openid,orderId);
        if(orderDtO==null){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        //取消订单
        return orderService.canel(orderDtO);
    }
    @Override
    public OrderDtO checkOrderOwner(String openid, String orderId){
        OrderDtO orderDtO = orderService.findOne(orderId);
        if (orderDtO==null){
            return null;
        }
        if(!orderDtO.getBuyerOpenid().equals(openid)){
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDtO;
    }
}
