package vip.xubin.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.xubin.portal.pojo.CartItem;
import vip.xubin.portal.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车Controller
 *
 * @author 許彬.
 * @creater 2016-08-26 22:24
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String cartAdd(@PathVariable long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response) {

        cartService.cartAdd(itemId, num, request, response);

        return "redirect:/cart/addSuccess.html";
    }


    @RequestMapping("/addSuccess")
    public String cartSuccess() {
        return "cartSuccess";
    }

    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, Model model) {

        List<CartItem> cartItems = cartService.getCart(request);

        model.addAttribute("cartList", cartItems);

        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    public void updateNum(HttpServletRequest request,HttpServletResponse response, @PathVariable Long itemId, @PathVariable Integer num) {

        cartService.updateNum(request,response, itemId, num);

    }

    @RequestMapping("/delete/{itemId}")
    public String delete(HttpServletRequest request,HttpServletResponse response, @PathVariable Long itemId) {

        cartService.delete(request,response, itemId);

        return "redirect:/cart/cart.html";
    }

}

