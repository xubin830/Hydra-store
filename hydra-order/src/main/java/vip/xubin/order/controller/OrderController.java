package vip.xubin.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.order.pojo.Order;
import vip.xubin.order.service.OrderService;

/**
 * 订单Controller
 *
 * @author 許彬.
 * @creater 2016-08-27 22:50
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public HydraResult createOrder(@RequestBody Order order) {

        HydraResult hydraResult = null;
        try {
            hydraResult = orderService.createOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            return HydraResult.build(500, "创建订单失败");
        }


        return hydraResult;
    }


}
