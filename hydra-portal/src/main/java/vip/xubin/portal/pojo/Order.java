package vip.xubin.portal.pojo;

import vip.xubin.pojo.TbOrder;
import vip.xubin.pojo.TbOrderItem;
import vip.xubin.pojo.TbOrderShipping;

import java.util.List;

/**
 * 订单POJO
 *
 * @author 許彬.
 * @creater 2016-08-27 23:10
 */

public class Order extends TbOrder{

    private List<TbOrderItem> orderItems;

    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
