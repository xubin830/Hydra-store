package vip.xubin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;
import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.mapper.TbItemParamItemMapper;
import vip.xubin.mapper.TbItemParamMapper;
import vip.xubin.pojo.TbItemParam;
import vip.xubin.pojo.TbItemParamExample;
import vip.xubin.pojo.TbItemParamExample.Criteria;
import vip.xubin.pojo.TbItemParamItem;
import vip.xubin.pojo.TbItemParamItemExample;
import vip.xubin.service.ItemParamService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 許彬.
 * @creater 2016-08-19 12:24
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public HydraResult getItemParamByCid(Long cid) {
        TbItemParamExample example = new TbItemParamExample();

        Criteria criteria = example.createCriteria();

        criteria.andItemCatIdEqualTo(cid);

        List<TbItemParam> itemParams = itemParamMapper.selectByExampleWithBLOBs(example);

        if (itemParams != null && itemParams.size() > 0) {
            return HydraResult.ok(itemParams.get(0));
        }

        return HydraResult.ok();

    }

    @Override
    public EUIDataGridResult getItemList(Integer page, Integer rows) {
        TbItemParamExample example = new TbItemParamExample();

        EUIDataGridResult result = new EUIDataGridResult();

        PageHelper.startPage(page,rows);

        List<TbItemParam> params = itemParamMapper.selectByExampleWithBLOBs(example);

        result.setRows(params);

        PageInfo<TbItemParam> pageInfo = new PageInfo<>(params);

        result.setTotal((int) pageInfo.getTotal());

        return result;

    }

    @Override
    public HydraResult saveItemParam(Long cid, String paramData) {

        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setUpdated(new Date());
        itemParam.setCreated(new Date());

        itemParamMapper.insert(itemParam);

        return HydraResult.ok();
    }

    @Override
    public String showItemParam(Long itemId) {

        TbItemParamItemExample example = new TbItemParamItemExample();

        example.createCriteria().andItemIdEqualTo(itemId);

        List<TbItemParamItem> itemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);

        List<Map> maps = JsonUtils.jsonToList(itemParamItems.get(0).getParamData(), Map.class);

        StringBuffer sb = new StringBuffer();
        sb.append("<table><tbody>");

        for (Map m1 : maps) {
            sb.append("<tr><th>");
            sb.append(m1.get("group"));
            sb.append("</th></tr>");
            List<Map> params = (List<Map>) m1.get("params");
            for (Map m2 : params) {
                sb.append("<tr><td>");
                sb.append(m2.get("k"));
                sb.append("</td><td>");
                sb.append(m2.get("v"));
                sb.append("</td></tr>");
            }
        }

        sb.append("</tbody></table>");

        return sb.toString();
    }


}
