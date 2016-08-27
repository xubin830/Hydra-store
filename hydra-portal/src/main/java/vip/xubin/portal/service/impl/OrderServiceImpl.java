package vip.xubin.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.CookieUtils;
import vip.xubin.common.utils.HttpClientUtil;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.portal.pojo.Order;
import vip.xubin.portal.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 許彬.
 * @creater 2016-08-28 0:46
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE}")
    private String ORDER_CREATE;

    @Override
    public HydraResult createOrder(Order order, HttpServletRequest request, HttpServletResponse response) {

        String string = null;
        try {
            string = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE, JsonUtils.objectToJson(order));
        } catch (Exception e) {
            e.printStackTrace();
            return HydraResult.build(500, "创建订单失败请稍后重试！");
        }

        CookieUtils.deleteCookie(request,response,"TT_CART");

        HydraResult hydraResult = HydraResult.format(string);


        return hydraResult;
    }
}
