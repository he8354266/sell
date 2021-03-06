package com.imooc.service;

import com.imooc.dto.OrderDtO;

public interface BuyerService {
    //查询一个订单
    OrderDtO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDtO cancelOrder(String openid, String orderId);

    OrderDtO checkOrderOwner(String openid, String orderId);
}
