package vip.xubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.service.ItemCatService;

import java.util.List;

/**
 * 商品类目选择Controller
 *
 * @author 許彬.
 * @creater 2016-08-18 9:17
 */
@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("list")
    @ResponseBody
    public List getItemCatList (@RequestParam(value = "id",defaultValue = "0") Long parentId){
        return itemCatService.getItemCatList(parentId);
    }


}
