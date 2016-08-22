package vip.xubin.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.rest.service.ADService;
import vip.xubin.rest.service.RedisService;

import java.util.List;

/**
 * 首页广告Controller
 *
 * @author 許彬.
 * @creater 2016-08-22 0:25
 */
@Controller
public class ADController {

    @Autowired
    private ADService adService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/getAD/Big")
    @ResponseBody
    public List getBigAd  (){

        return adService.getBigAd();

    }

    @RequestMapping("/sync/Index/BigAD")
    @ResponseBody
    public HydraResult syncIndexBigAD() {

        return redisService.syncIndexBigAd();

    }

}
