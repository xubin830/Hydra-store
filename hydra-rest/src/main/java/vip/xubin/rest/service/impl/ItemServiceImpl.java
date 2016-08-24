package vip.xubin.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xubin.mapper.TbItemDescMapper;
import vip.xubin.mapper.TbItemMapper;
import vip.xubin.mapper.TbItemParamItemMapper;
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

    @Override
    public TbItem getItemById(long itemId) {

        TbItemExample example = new TbItemExample();

        TbItemExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(itemId);

        List<TbItem> items = itemMapper.selectByExample(example);

        if (items != null && items.size() > 0) {

            return items.get(0);
        } else {

            return null;
        }

    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {

        TbItemDescExample example = new TbItemDescExample();

        TbItemDescExample.Criteria criteria = example.createCriteria();

        criteria.andItemIdEqualTo(itemId);

        List<TbItemDesc> itemDescs = itemDescMapper.selectByExampleWithBLOBs(example);

        if (itemDescs != null && itemDescs.size() > 0) {

            return itemDescs.get(0);

        } else {

            return null;
        }


    }

    @Override
    public TbItemParamItem getItemParamItemById(long itemId) {

        TbItemParamItemExample example = new TbItemParamItemExample();

        TbItemParamItemExample.Criteria criteria = example.createCriteria();

        criteria.andItemIdEqualTo(itemId);

        List<TbItemParamItem> itemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);

        if (itemParamItems != null && itemParamItems.size() > 0) {

            return itemParamItems.get(0);

        } else {

            return null;
        }

    }


}
