package vip.xubin.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.xubin.portal.service.AdService;

/**
 * 首页Controller
 *
 * @author 許彬.
 * @creater 2016-08-20 11:21
 */
@Controller
public class IndexController {

    @Autowired
    private AdService adService;

    @RequestMapping("/index")
    public String showIndex(Model model) {

        model.addAttribute("ad1", adService.getBigAd());

        return "index";
    }



}
