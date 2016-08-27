package vip.xubin.order.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.mapper.TbOrderItemMapper;
import vip.xubin.mapper.TbOrderMapper;
import vip.xubin.mapper.TbOrderShippingMapper;
import vip.xubin.order.pojo.Order;
import vip.xubin.order.service.JedisClient;
import vip.xubin.order.service.OrderService;
import vip.xubin.pojo.TbOrder;
import vip.xubin.pojo.TbOrderItem;
import vip.xubin.pojo.TbOrderShipping;

import java.util.Date;
import java.util.List;

/**
 * @author 許彬.
 * @creater 2016-08-27 23:17
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ORDER_ID}")
    private String REDIS_ORDER_ID;
    @Value("${REDIS_ORDER_ID_KEY}")
    private String REDIS_ORDER_ID_KEY;
    @Value("${REDIS_ORDER_ITEM_ID}")
    private String REDIS_ORDER_ITEM_ID;
    @Value("${REDIS_ORDER_ITEM_ID_KEY}")
    private String REDIS_ORDER_ITEM_ID_KEY;

    @Override
    public HydraResult createOrder(Order order) {

        TbOrder newOrder = new TbOrder();

        String orderId = jedisClient.get(REDIS_ORDER_ID_KEY);

        if (StringUtils.isBlank(orderId)) {

            jedisClient.set(REDIS_ORDER_ID_KEY, REDIS_ORDER_ID);

        }

        orderId = jedisClient.incr(REDIS_ORDER_ID_KEY) + "";
        newOrder.setOrderId(orderId);
        newOrder.setPayment(order.getPayment());
        newOrder.setPostFee(order.getPostFee());
        newOrder.setUserId(order.getUserId());
        newOrder.setBuyerMessage(order.getBuyerMessage());
        newOrder.setBuyerNick(order.getBuyerNick());

        newOrder.setStatus(1);
        newOrder.setCreateTime(new Date());
        newOrder.setUpdateTime(new Date());
        newOrder.setBuyerRate(1);

        orderMapper.insert(newOrder);

        String orderItemId = jedisClient.get(REDIS_ORDER_ITEM_ID_KEY);
        if (StringUtils.isBlank(orderItemId)) {

            jedisClient.set(REDIS_ORDER_ITEM_ID_KEY, REDIS_ORDER_ITEM_ID);

        }
        List<TbOrderItem> orderItems = order.getOrderItems();

        for (TbOrderItem orderItem : orderItems) {

            orderItem.setId(jedisClient.incr(REDIS_ORDER_ITEM_ID_KEY) + "");

            orderItem.setOrderId(orderId);

            orderItemMapper.insert(orderItem);

        }

        TbOrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());

        orderShippingMapper.insert(orderShipping);

        return HydraResult.ok(orderId);
    }
}
