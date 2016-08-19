package vip.xubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.service.ItemParamService;

/**
 * 商品规格管理Controller
 *
 * @author 許彬.
 * @creater 2016-08-19 12:41
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService paramService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public HydraResult getParamByCid(@PathVariable Long itemCatId) {
        return paramService.getItemParamByCid(itemCatId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUIDataGridResult getParamList(Integer page, Integer rows) {

        return paramService.getItemList(page, rows);

    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public HydraResult saveItemParam(@PathVariable Long cid, String paramData) {

        return paramService.saveItemParam(cid, paramData);
    }

    @RequestMapping("/show/{itemId}")
    public String showItemParam(@PathVariable Long itemId, Model model) {
        String s = paramService.showItemParam(itemId);

        model.addAttribute("itemParam", s);

        return "itemParam";
    }
}
