package vip.xubin.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.CookieUtils;
import vip.xubin.common.utils.HttpClientUtil;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbUser;
import vip.xubin.portal.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 許彬.
 * @creater 2016-08-26 20:11
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    @Value("${SSO_GET_USER}")
    private String SSO_GET_USER;
    @Value("${SSO_LOGIN}")
    public String SSO_LOGIN;
    @Value("${SSO_LOGOUT}")
    public String SSO_LOGOUT;

    @Override
    public TbUser getUserByToken(String tt_token) {

        String userString = HttpClientUtil.doGet(SSO_BASE_URL + SSO_GET_USER + "/" + tt_token);

        HydraResult hydraResult = HydraResult.formatToPojo(userString, TbUser.class);

        if (hydraResult.getStatus() == 200) {

            TbUser user = (TbUser) hydraResult.getData();

            return user;
        }

        return null;
    }

    @Override
    public void logout(HttpServletRequest request) {

        String tt_token = CookieUtils.getCookieValue(request, "TT_TOKEN");

        try {
            HttpClientUtil.doGet(SSO_BASE_URL + SSO_LOGOUT + "/" + tt_token);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
