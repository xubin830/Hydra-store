package vip.xubin.portal.service;

import vip.xubin.common.utils.HydraResult;
import vip.xubin.portal.pojo.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单Service
 *
 * @author 許彬.
 * @creater 2016-08-28 0:45
 */

public interface OrderService {

    HydraResult createOrder(Order order, HttpServletRequest request, HttpServletResponse response);
}
