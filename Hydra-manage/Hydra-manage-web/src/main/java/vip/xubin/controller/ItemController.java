package vip.xubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.pojo.TbItem;
import vip.xubin.service.ItemService;

/**
 * 商品管理Controller
 *
 * @author 許彬.
 * @creater 2016-08-17 13:53
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemservice;

    @RequestMapping("item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem item = itemservice.getItemById(itemId);
        return item;
    }

}
