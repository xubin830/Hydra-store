package vip.xubin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页跳转
 *
 * @author 許彬.
 * @creater 2016-08-17 16:42
 */
@Controller
public class PageController {

    /*
        跳转后台首页
     */
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }

    /*
        跳转其他页面
     */
    @RequestMapping("/{page}")
    public  String showPage(@PathVariable String page){
        return page;
    }
}
