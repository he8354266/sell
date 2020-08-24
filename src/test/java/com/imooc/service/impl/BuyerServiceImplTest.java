package com.imooc.service.impl;

import com.imooc.service.BuyerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private BuyerService buyerService;

    private final String BUYER_OPENID = "1101110";

    private final String ORDER_ID = "1597808236205399050";
    @Test
    public void findOrderOne(){
    buyerService.findOrderOne(BUYER_OPENID,ORDER_ID);
    }
}
