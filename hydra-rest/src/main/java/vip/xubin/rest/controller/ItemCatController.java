package vip.xubin.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.rest.pojo.CatResult;
import vip.xubin.rest.service.ItemCatService;

/**
 * 首页商品类别
 *
 * @author 許彬.
 * @creater 2016-08-20 15:42
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object showCatList(String callback) {

        CatResult list = itemCatService.getItemCatList();

        MappingJacksonValue jacksonValue = new MappingJacksonValue(list);

        jacksonValue.setJsonpFunction(callback);

        return jacksonValue;
    }
}
