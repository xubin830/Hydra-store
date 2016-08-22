package vip.xubin.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.rest.service.JedisClient;
import vip.xubin.rest.service.RedisService;

/**
 * @author 許彬.
 * @creater 2016-08-22 17:46
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_AD_BIG}")
    private String INDEX_AD_BIG;

    @Value("${INDEX_AD_BIG_ID}")
    private String INDEX_AD_BIG_ID;

    @Override
    public HydraResult syncIndexBigAd() {

        jedisClient.hdel(INDEX_AD_BIG, INDEX_AD_BIG_ID);

        return HydraResult.ok();
    }
}
