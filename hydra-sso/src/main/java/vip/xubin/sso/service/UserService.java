package vip.xubin.sso.service;

import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户操作Service
 *
 * @author 許彬.
 * @creater 2016-08-25 17:57
 */

public interface UserService {

    HydraResult check(String param, Integer type);

    HydraResult insertUser(TbUser user);

    HydraResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    HydraResult getUserByToken(String token);

    HydraResult logoutByToken(String token, HttpServletRequest request, HttpServletResponse response);
}
