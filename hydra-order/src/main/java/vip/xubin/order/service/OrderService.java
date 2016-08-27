package vip.xubin.order.service;

import vip.xubin.common.utils.HydraResult;
import vip.xubin.order.pojo.Order;

/**
 * 订单Service
 *
 * @author 許彬.
 * @creater 2016-08-27 23:15
 */

public interface OrderService {

    HydraResult createOrder(Order order);
}
