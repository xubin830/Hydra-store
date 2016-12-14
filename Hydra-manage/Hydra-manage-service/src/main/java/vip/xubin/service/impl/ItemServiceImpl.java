package vip.xubin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.common.utils.IDUtils;
import vip.xubin.mapper.TbItemDescMapper;
import vip.xubin.mapper.TbItemMapper;
import vip.xubin.mapper.TbItemParamItemMapper;
import vip.xubin.pojo.TbItem;
import vip.xubin.pojo.TbItemDesc;
import vip.xubin.pojo.TbItemExample;
import vip.xubin.pojo.TbItemParamItem;
import vip.xubin.service.ItemService;

import java.util.Date;
import java.util.List;

/**
 * @author 許彬.
 * @creater 2016-08-17 13:41
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Value("${SOLR_SYNC_BASE_URL}")
    private String SOLR_SYNC_BASE_URL;

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

    @Override
    public EUIDataGridResult getItemList(Integer page, Integer rows) {

        TbItemExample example = new TbItemExample();

        PageHelper.startPage(page,rows);

        List<TbItem> items = itemMapper.selectByExample(example);

        PageInfo<TbItem> pageInfo = new PageInfo<>(items);

        EUIDataGridResult gridResult = new EUIDataGridResult();
        gridResult.setRows(items);
        gridResult.setTotal((int) pageInfo.getTotal());

        return gridResult;
    }


    @Override
    public HydraResult createItem(TbItem item, String desc,String itemParams) throws Exception {
        Long itemId = IDUtils.genItemId();

        item.setId(itemId);

        item.setStatus((byte) 1);

        item.setCreated(new Date());
        item.setUpdated(new Date());

        itemMapper.insert(item);

        HydraResult result = insertItemDesc(item.getId(), desc);

        if (result.getStatus() != 200) throw new Exception();

        result = insertItemParamItem(itemId, itemParams);

        if (result.getStatus() != 200) throw new Exception();

        //缓存同步
        //HttpClientUtil.doPost(SOLR_SYNC_BASE_URL + itemId);

        return HydraResult.ok();
    }

    /**
     *  保存商品描述
     * @param id 商品id
     * @param desc 商品描述
     * @return  ok
     */
    public HydraResult insertItemDesc(Long id, String desc) {

        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());

        itemDescMapper.insert(itemDesc);

        return HydraResult.ok();
    }

    /**
     *  保存商品规格参数
     *
     * @param cid  商品id
     * @param itemParams 商品规格描述
     * @return
     */
    public HydraResult insertItemParamItem(Long cid, String itemParams) {

        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(cid);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItem.setParamData(itemParams);

        itemParamItemMapper.insert(itemParamItem);

        return HydraResult.ok();

    }

}
