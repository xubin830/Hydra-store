package vip.xubin.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.mapper.TbContentMapper;
import vip.xubin.pojo.TbContent;
import vip.xubin.pojo.TbContentExample;
import vip.xubin.rest.service.ADService;
import vip.xubin.rest.service.JedisClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 許彬.
 * @creater 2016-08-22 0:28
 */
@Service
public class ADServiceImpl implements ADService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_AD_BIG}")
    private String INDEX_AD_BIG;

    @Value("${INDEX_AD_BIG_ID}")
    private String INDEX_AD_BIG_ID;


    @Override
    public List getBigAd() {

        try {
            String hget = jedisClient.hget(INDEX_AD_BIG, INDEX_AD_BIG_ID);

            if (!StringUtils.isBlank(hget)) {
                List list = JsonUtils.jsonToPojo(hget, List.class);

                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();

        List<TbContent> list = contentMapper.selectByExample(example);

        List<Map<String, Object>> lists = new ArrayList<>();

        for (TbContent content : list) {

            HashMap<String, Object> map = new HashMap<>();

            map.put("srcB", content.getPic2());
            map.put("height", 240);
            map.put("alt", content.getTitle());
            map.put("width", 670);
            map.put("src", content.getPic());
            map.put("widthB", 550);
            map.put("href", content.getUrl());
            map.put("heightB", 240);

            lists.add(map);

        }

        try {

            jedisClient.hset(INDEX_AD_BIG, INDEX_AD_BIG_ID,JsonUtils.objectToJson(lists));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lists;

    }
}
