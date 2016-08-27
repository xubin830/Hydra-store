package vip.xubin.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.mapper.TbItemDescMapper;
import vip.xubin.mapper.TbItemMapper;
import vip.xubin.mapper.TbItemParamItemMapper;
import vip.xubin.rest.service.JedisClient;
import vip.xubin.pojo.*;
import vip.xubin.rest.service.ItemService;

import java.util.List;

/**
 * @author 許彬.
 * @creater 2016-08-24 21:41
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ITEM}")
    private String ITEM;
    @Value("${ITEM_DESC}")
    private String ITEM_DESC;
    @Value("${ITEM_PARAM_ITEM}")
    private String ITEM_PARAM_ITEM;
    @Value("${REDIS_EXPIRE_TIME}")
    private Integer REDIS_EXPIRE_TIME;


    @Override
    public TbItem getItemById(long itemId) {

        try {
            String hget = jedisClient.get(ITEM + ":" + itemId);

            if (!StringUtils.isBlank(hget)) {

                return JsonUtils.jsonToPojo(hget, TbItem.class);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemExample example = new TbItemExample();

        TbItemExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(itemId);

        List<TbItem> items = itemMapper.selectByExample(example);

        if (items != null && items.size() > 0) {

            TbItem tbItem = items.get(0);

            try {
                String key = ITEM + ":" + itemId;

                jedisClient.set(key, JsonUtils.objectToJson(tbItem));

                jedisClient.expire(key, REDIS_EXPIRE_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return tbItem;
        } else {

            return null;
        }

    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {

        try {
            String hget = jedisClient.get(ITEM_DESC + ":" + itemId);

            if (!StringUtils.isBlank(hget)) {

                return JsonUtils.jsonToPojo(hget, TbItemDesc.class);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDescExample example = new TbItemDescExample();

        TbItemDescExample.Criteria criteria = example.createCriteria();

        criteria.andItemIdEqualTo(itemId);

        List<TbItemDesc> itemDescs = itemDescMapper.selectByExampleWithBLOBs(example);

        if (itemDescs != null && itemDescs.size() > 0) {

            TbItemDesc itemDesc = itemDescs.get(0);
            try {

                String key = ITEM_DESC + ":" + itemId;

                jedisClient.set(key, JsonUtils.objectToJson(itemDesc));

                jedisClient.expire(key, REDIS_EXPIRE_TIME);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return itemDesc;

        } else {

            return null;
        }


    }

    @Override
    public TbItemParamItem getItemParamItemById(long itemId) {
        try {
            String hget = jedisClient.get(ITEM_PARAM_ITEM + ":" + itemId);

            if (!StringUtils.isBlank(hget)) {

                return JsonUtils.jsonToPojo(hget, TbItemParamItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemParamItemExample example = new TbItemParamItemExample();

        TbItemParamItemExample.Criteria criteria = example.createCriteria();

        criteria.andItemIdEqualTo(itemId);

        List<TbItemParamItem> itemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);

        if (itemParamItems != null && itemParamItems.size() > 0) {

            TbItemParamItem tbItemParamItem = itemParamItems.get(0);
            try {

                String key = ITEM_PARAM_ITEM + ":" + itemId;

                jedisClient.set(key, JsonUtils.objectToJson(tbItemParamItem));

                jedisClient.expire(key, REDIS_EXPIRE_TIME);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return tbItemParamItem;

        } else {

            return null;
        }

    }


}
