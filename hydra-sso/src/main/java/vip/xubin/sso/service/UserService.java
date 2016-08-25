package vip.xubin.sso.service;

import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbUser;

/**
 * 用户操作Service
 *
 * @author 許彬.
 * @creater 2016-08-25 17:57
 */

public interface UserService {

    HydraResult check(String param, Integer type);

    HydraResult insertUser(TbUser user);

    HydraResult userLogin(String username, String password);

    HydraResult getUserByToken(String token);

    HydraResult logoutByToken(String token);
}
