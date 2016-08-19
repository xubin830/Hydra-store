package vip.xubin.service;

import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbItem;

/**
 * Created by cynic on 2016/8/17.
 */
public interface ItemService {

    TbItem getItemById(Long itemId);

    EUIDataGridResult getItemList(Integer page,Integer rows);

    HydraResult createItem(TbItem item, String desc,String itemParams) throws Exception;
}
