package vip.xubin.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xubin.mapper.TbContentMapper;
import vip.xubin.pojo.TbContent;
import vip.xubin.pojo.TbContentExample;
import vip.xubin.rest.service.ADService;

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


    @Override
    public List getBigAd() {

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

        return lists;

    }
}
