package com.imooc.controller;

import com.imooc.VO.ResultVO;
import com.imooc.converter.OrderForm2OrderDTOConverter;
import com.imooc.dto.OrderDtO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVOUtil;
import com.lly835.bestpay.rest.type.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDtO orderDtO = OrderForm2OrderDTOConverter.convert(orderForm);
        OrderDtO createOrder = orderService.create(orderDtO);
        Map<String, String> parm = new HashMap<>();
        parm.put("orderId", createOrder.getOrderId());
        return ResultVOUtil.success(parm);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDtO>> list(@RequestParam("openid") String openid, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDtO> orderDtOPage = orderService.findList(openid, pageRequest);
        return ResultVOUtil.success(orderDtOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDtO> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId) {
        OrderDtO orderDtO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDtO);
    }
    //取消订单
    @GetMapping("/cancel")
    public  ResultVO cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}
