package com.imooc.service.impl;

import com.imooc.exception.SellException;
import com.imooc.service.RedisLock;
import com.imooc.service.SecKillService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CodingSir
 * @Title: project
 * @Package ========
 * @Description: ========
 * @date 2020/8/28  10:55
 */
@Service
public class SecKillServiceImpl implements SecKillService {
    private static final int TIMEOUT = 10 * 1000; //超时时间 10s
    @Autowired
    private RedisLock redisLock;
    /**
     * 国庆活动，皮蛋粥特价，限量100000份
     */
    static Map<String, Integer> products = new HashMap<>();
    static Map<String, Integer> stock = new HashMap<>();
    static Map<String, String> orders = new HashMap<String, String>();

    static {
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {
        return "国庆活动，皮蛋粥特价，限量份" + products.get(productId) + " 还剩：" + stock.get(productId) + " 份"
                + " 该商品成功下单用户数目："
                + orders.size() + " 人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "人太多了，换个姿势试试");
        }
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            //下单
            orders.put(KeyUtil.genUniqueKey(), productId);
            //减库存
            stockNum -= 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
        redisLock.unlock(productId, String.valueOf(time));
    }
}
