package vip.xubin.service;

import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.utils.HydraResult;

/**
 * 商品规格参数
 * Created by cynic on 2016/8/19.
 */
public interface ItemParamService {

    HydraResult getItemParamByCid(Long cid);

    EUIDataGridResult getItemList(Integer page, Integer rows);

    HydraResult saveItemParam(Long cid, String paramData);

    String showItemParam(Long itemId);
}
