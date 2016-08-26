package vip.xubin.portal.service;

import vip.xubin.common.utils.HydraResult;
import vip.xubin.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车Service
 *
 * @author 許彬.
 * @creater 2016-08-26 22:26
 */

public interface CartService {
    void cartAdd(long itemId,Integer num, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCart(HttpServletRequest request);

    HydraResult updateNum(HttpServletRequest request,HttpServletResponse response, Long itemId, Integer num);

    void delete(HttpServletRequest request, HttpServletResponse response, Long itemId);
}
