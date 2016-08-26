package vip.xubin.portal.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import vip.xubin.common.utils.CookieUtils;
import vip.xubin.pojo.TbUser;
import vip.xubin.portal.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 强制用户登录拦截器
 *
 * @author 許彬.
 * @creater 2016-08-26 20:05
 */

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String tt_token = CookieUtils.getCookieValue(httpServletRequest, "TT_TOKEN");

        if (StringUtils.isBlank(tt_token)) {

            httpServletResponse.sendRedirect(userService.SSO_BASE_URL + userService.SSO_LOGIN + "?redirect=" + httpServletRequest.getRequestURL());
            return false;
        }

        TbUser user = userService.getUserByToken(tt_token);

        if (user == null) {

            httpServletResponse.sendRedirect(userService.SSO_BASE_URL + userService.SSO_LOGIN + "?redirect=" + httpServletRequest.getRequestURL());

            return false;

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
