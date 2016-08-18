package vip.xubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbItem;
import vip.xubin.service.ItemService;

/**
 * 商品管理Controller
 *
 * @author 許彬.
 * @creater 2016-08-17 13:53
 */
@RequestMapping("/item")
@Controller
public class ItemController {
    @Autowired
    private ItemService itemservice;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemservice.getItemById(itemId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUIDataGridResult getItemList(Integer page, Integer rows) {
        return itemservice.getItemList(page, rows);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public HydraResult createItem(TbItem item){
        return itemservice.createItem(item);
    }
}
