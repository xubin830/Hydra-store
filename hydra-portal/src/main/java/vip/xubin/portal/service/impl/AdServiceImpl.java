package vip.xubin.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.HttpClientUtil;
import vip.xubin.portal.service.AdService;

/**
 * @author 許彬.
 * @creater 2016-08-22 0:48
 */
@Service
public class AdServiceImpl implements AdService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;


    @Override
    public String getBigAd() {
        //return HttpClientUtil.doPost(REST_BASE_URL + REST_INDEX_AD_URL);
        //// TODO: 2016/8/22 url没解决
        return HttpClientUtil.doPost("http://localhost:81/rest/getAD/Big");
    }
}
