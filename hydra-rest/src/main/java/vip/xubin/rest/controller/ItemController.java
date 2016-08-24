package vip.xubin.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbItem;
import vip.xubin.pojo.TbItemDesc;
import vip.xubin.pojo.TbItemParamItem;
import vip.xubin.rest.service.ItemService;

/**
 * 商品查询服务Controller
 *
 * @author 許彬.
 * @creater 2016-08-24 21:33
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public HydraResult getItem(@PathVariable long itemId) {

        TbItem tbItem = itemService.getItemById(itemId);

        return HydraResult.ok(tbItem);

    }

    @RequestMapping("/itemDesc/{itemId}")
    @ResponseBody
    public HydraResult getItemDesc(@PathVariable long itemId) {

        TbItemDesc itemDesc = itemService.getItemDescById(itemId);

        return HydraResult.ok(itemDesc);

    }

    @RequestMapping("/itemParamItem/{itemId}")
    @ResponseBody
    public HydraResult getItemParamItem(@PathVariable long itemId) {

        TbItemParamItem itemParamItem = itemService.getItemParamItemById(itemId);

        return HydraResult.ok(itemParamItem);

    }


}
