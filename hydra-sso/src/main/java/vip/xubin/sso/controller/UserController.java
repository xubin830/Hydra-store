package vip.xubin.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbUser;
import vip.xubin.sso.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户操作Controller
 *
 * @author 許彬.
 * @creater 2016-08-25 17:56
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check/{param}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public Object check(@PathVariable String param, @PathVariable Integer type,String callback) {

        HydraResult hydraResult = userService.check(param, type);

        if (!StringUtils.isBlank(callback)) {

            MappingJacksonValue jacksonValue = new MappingJacksonValue(hydraResult);

            jacksonValue.setJsonpFunction(callback);

            return jacksonValue;

        }

        return hydraResult;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public HydraResult register(TbUser user) {

        HydraResult hydraResult = userService.insertUser(user);

        return hydraResult;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public HydraResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        return userService.userLogin(username, password, request, response);

    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,String callback) {

        HydraResult hydraResult = userService.getUserByToken(token);

        if (!StringUtils.isBlank(callback)) {

            MappingJacksonValue jacksonValue = new MappingJacksonValue(hydraResult);

            jacksonValue.setJsonpFunction(callback);

            return jacksonValue;

        }

        return hydraResult;

    }

    @RequestMapping(value = "/logout/{token}", method = RequestMethod.GET)
    @ResponseBody
    public Object logout(@PathVariable String token,String callback,HttpServletRequest request, HttpServletResponse response) {

        HydraResult hydraResult = userService.logoutByToken(token, request, response);

        if (!StringUtils.isBlank(callback)) {

            MappingJacksonValue jacksonValue = new MappingJacksonValue(hydraResult);

            jacksonValue.setJsonpFunction(callback);

            return jacksonValue;

        }

        return hydraResult;

    }

    @RequestMapping("/showLogin")
    public String showLogin(String redirect, Model model){

        model.addAttribute("redirect", redirect);

        return "login";

    }

    @RequestMapping("/showRegister")
    public String showRegister(){

        return "register";

    }


}
