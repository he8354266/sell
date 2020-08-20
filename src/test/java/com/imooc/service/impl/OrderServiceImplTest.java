package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDtO;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.OrderService;
import com.imooc.service.impl.OrderServiceImpl;
import com.imooc.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1101110";

    private final String ORDER_ID = "1597808236205399050";

    @Test
    public void create(){
        OrderDtO orderDtO =new OrderDtO();
        orderDtO.setBuyerName("廖师兄");
        orderDtO.setBuyerAddress("幕课网");
        orderDtO.setBuyerAddress("幕课网");
        orderDtO.setBuyerPhone("123456789012");
        orderDtO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);
        orderDtO.setOrderDetailList(orderDetailList);
        OrderDtO result =   orderService.create(orderDtO);
    }
    @Test
    public  void  findOne(){
        orderService.findOne(ORDER_ID);
    }
    @Test
    public void findList(){
        PageRequest request = PageRequest.of(0,2);
        orderService.findList(request);
    }
    @Test
    public void canel(){
       OrderDtO orderDtO = orderService.findOne(ORDER_ID);
       orderService.canel(orderDtO);
    }
    @Test
    public void finish(){
        OrderDtO orderDtO = orderService.findOne(ORDER_ID);
        orderService.finish(orderDtO);
    }
    @Test
    public void paid(){
        OrderDtO orderDtO = orderService.findOne(ORDER_ID);
        orderService.paid(orderDtO);
    }
}
