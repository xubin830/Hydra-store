package vip.xubin.portal.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.portal.pojo.CartItem;
import vip.xubin.portal.pojo.Order;
import vip.xubin.portal.service.CartService;
import vip.xubin.portal.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单Controller
 *
 * @author 許彬.
 * @creater 2016-08-28 0:34
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, Model model) {

        List<CartItem> cartItems = cartService.getCart(request);

        model.addAttribute("cartList", cartItems);

        return "order-cart";
    }

    @RequestMapping("/create")
    public String createOrder(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {

        HydraResult hydraResult = orderService.createOrder(order,request,response);

        if (hydraResult.getStatus() == 200) {

            model.addAttribute("orderId", hydraResult.getData());
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));

            return "success";
        } else if (hydraResult.getStatus() == 500) {

            model.addAttribute("message", hydraResult.getMsg());

            return "error/exception";
        } else {

            model.addAttribute("message", hydraResult.getMsg());

            return "error/exception";
        }


    }


}
