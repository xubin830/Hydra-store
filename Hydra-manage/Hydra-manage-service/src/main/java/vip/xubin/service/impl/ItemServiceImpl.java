package vip.xubin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xubin.mapper.TbItemMapper;
import vip.xubin.pojo.TbItem;
import vip.xubin.pojo.TbItemExample;
import vip.xubin.service.ItemService;

import java.util.List;

/**
 * @author 許彬.
 * @creater 2016-08-17 13:41
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        //TbItem item = itemMapper.selectByPrimaryKey(itemId);

        TbItemExample example = new TbItemExample();

        TbItemExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(itemId);

        List<TbItem> items = itemMapper.selectByExample(example);

        if (items != null && items.size()>0) {
            return items.get(0);
        }
            return null;

    }
}
