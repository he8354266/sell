package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void findAll() {
        Optional<SellerInfo> sellerInfo = repository.findById("12");
        System.out.println(sellerInfo);
    }
    @Test
    public void saveTest(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("1111");
        sellerInfo.setPassword("222222");
        sellerInfo.setUsername("333333");
        sellerInfo.setSellerId("44444");
        repository.save(sellerInfo);
    }
}