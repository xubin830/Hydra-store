package vip.xubin.portal.service;

import vip.xubin.pojo.TbItemDesc;
import vip.xubin.portal.pojo.Item;

/**
 * 商品展示Service
 *
 * @author 許彬.
 * @creater 2016-08-24 21:26
 */

public interface ItemService {

    Item getItemById(long itemId);

    TbItemDesc getItemDescById(long itemId);

    String getItemParamById(long itemId);
}
