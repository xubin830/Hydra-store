package vip.xubin.rest.service;

import vip.xubin.pojo.TbItem;
import vip.xubin.pojo.TbItemDesc;
import vip.xubin.pojo.TbItemParamItem;

/**
 * 商品查询服务Service
 *
 * @author 許彬.
 * @creater 2016-08-24 21:34
 */

public interface ItemService {


    TbItem getItemById(long itemId);

    TbItemDesc getItemDescById(long itemId);

    TbItemParamItem getItemParamItemById(long itemId);


}
