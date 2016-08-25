package vip.xubin.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.mapper.TbUserMapper;
import vip.xubin.pojo.TbUser;
import vip.xubin.pojo.TbUserExample;
import vip.xubin.sso.service.JedisClient;
import vip.xubin.sso.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 許彬.
 * @creater 2016-08-25 18:01
 */

@Service
public class UserServiceImpl implements UserService {

    @Value("${USER_LOGIN}")
    private String USER_LOGIN;
    @Value("${REDIS_EXPIRE_TIME}")
    private Integer REDIS_EXPIRE_TIME;

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public HydraResult check(String param, Integer type) {

        if (param != null && type != null) {

            TbUserExample example = new TbUserExample();

            TbUserExample.Criteria criteria = example.createCriteria();

            //1、2、3分别代表username、phone、email
            switch (type) {

                case 1:
                    criteria.andUsernameEqualTo(param);
                    break;
                case 2:
                    criteria.andPhoneEqualTo(param);
                    break;
                case 3:
                    criteria.andEmailEqualTo(param);
                    break;
                default:
                    return HydraResult.build(400, "输入类型错误");

            }

            List<TbUser> users = userMapper.selectByExample(example);

            if (users != null && users.size() > 0) {

                return HydraResult.ok(false);

            }

        }

        return HydraResult.ok(true);
    }

    @Override
    public HydraResult insertUser(TbUser user) {

        user.setCreated(new Date());
        user.setUpdated(new Date());

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        try {

            userMapper.insert(user);
        } catch (Exception e) {
            return HydraResult.build(500, "注册失败. 请校验数据后请再提交数据.");
        }

        return HydraResult.ok();
    }

    @Override
    public HydraResult userLogin(String username, String password) {

        TbUserExample example = new TbUserExample();

        TbUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isBlank(username)) {

            criteria.andUsernameEqualTo(username);

        }  else {

            return HydraResult.build(400, "用户名或密码错误");

        }

        List<TbUser> users = userMapper.selectByExample(example);

        if (users != null && users.size() > 0){

            if (!StringUtils.isBlank(password)) {

                TbUser user = users.get(0);

                if (user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {

                    String token = UUID.randomUUID().toString().replaceAll("-","");

                    try {

                        String key = USER_LOGIN + ":" + token;

                        user.setPassword("");

                        jedisClient.set(key, JsonUtils.objectToJson(user));

                        jedisClient.expire(key, REDIS_EXPIRE_TIME);

                    } catch (Exception e) {
                        return HydraResult.build(500, "Redis出错！");
                    }

                    return HydraResult.ok(token);

                }  else {

                    return HydraResult.build(400, "用户名或密码错误");

                }

            }

        }

        return HydraResult.build(400, "用户名或密码错误");
    }

    @Override
    public HydraResult getUserByToken(String token) {

        if (!StringUtils.isBlank(token)) {

            String jsonUser = null;
            try {

                jsonUser = jedisClient.get(USER_LOGIN + ":" + token);

            } catch (Exception e) {
                return HydraResult.build(400,"请重新登录！");
            }

            TbUser user = JsonUtils.jsonToPojo(jsonUser, TbUser.class);

            return HydraResult.ok(user);

        }

        return HydraResult.build(400,"请重新登录！");
    }

    @Override
    public HydraResult logoutByToken(String token) {

        if (!StringUtils.isBlank(token)) {

            long del = jedisClient.del(USER_LOGIN + ":" + token);
            if (del != 0) {

                return HydraResult.ok();

            }

        }


        return HydraResult.build(400, "已经退出");
    }

}
