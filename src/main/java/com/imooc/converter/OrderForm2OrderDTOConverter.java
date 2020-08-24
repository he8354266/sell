package com.imooc.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDtO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {
    public static OrderDtO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDtO orderDtO = new OrderDtO();
        orderDtO.setBuyerAddress(orderForm.getAddress());
        orderDtO.setBuyerOpenid(orderForm.getOpenid());
        orderDtO.setBuyerName(orderForm.getName());
        orderDtO.setBuyerPhone(orderForm.getPhone());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {

            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDtO.setOrderDetailList(orderDetailList);
        return orderDtO;
    }
}
