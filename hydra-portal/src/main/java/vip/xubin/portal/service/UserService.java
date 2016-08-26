package vip.xubin.portal.service;

import vip.xubin.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户操作Service
 *
 * @author 許彬.
 * @creater 2016-08-26 20:08
 */

public interface UserService {

    TbUser getUserByToken(String tt_token);

    void logout(HttpServletRequest request);
}
