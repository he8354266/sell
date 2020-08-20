package com.imooc.service;

import com.imooc.dto.OrderDtO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


public interface OrderService {
    @Transactional
    OrderDtO create(OrderDtO orderDtO);

    /*创建订单*/
    OrderDtO OrderDtO(OrderDtO orderDtO);

    /*查询单个订单*/
    OrderDtO findOne(String orderId);

    /*查询订单列表*/
    Page<OrderDtO> findList(String buyerOpenid, Pageable pageable);

    /*取消订单*/
    OrderDtO canel(OrderDtO orderDtO);

    /*完结订单*/
    OrderDtO finish(OrderDtO orderDtO);

    /* 支付订单 */
    OrderDtO paid(OrderDtO orderDTO);

    Page<OrderDtO> findList(Pageable pageable);
}
