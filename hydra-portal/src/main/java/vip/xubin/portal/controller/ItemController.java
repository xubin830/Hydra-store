package vip.xubin.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.pojo.TbItemDesc;
import vip.xubin.portal.pojo.Item;
import vip.xubin.portal.service.ItemService;

/**
 * 商品展示Controller
 *
 * @author 許彬.
 * @creater 2016-08-24 21:09
 */

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")//147791199346325
    public String getItemById(@PathVariable long itemId, Model model) {

        Item item = itemService.getItemById(itemId);

        if (item != null) {
            model.addAttribute("item", item);

            return "item";

        } else {

            return "item";
        }


    }


    @RequestMapping(value = "/item/desc/{itemId}",produces =  MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemDescById(@PathVariable long itemId) {

        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        if (itemDesc != null) {

            String desc = itemDesc.getItemDesc();

            return desc;
        }

        return null;
    }

    @RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemParamById(@PathVariable long itemId) {

        String string = itemService.getItemParamById(itemId);

        return string;
    }


}