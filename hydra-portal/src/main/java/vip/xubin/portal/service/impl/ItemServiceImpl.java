package vip.xubin.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.HttpClientUtil;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.pojo.TbItemDesc;
import vip.xubin.pojo.TbItemParamItem;
import vip.xubin.portal.pojo.Item;
import vip.xubin.portal.service.ItemService;

import java.util.List;
import java.util.Map;

/**
 * @author 許彬.
 * @creater 2016-08-24 21:32
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${ITEM_INFO_DESC}")
    private String ITEM_INFO_DESC;
    @Value("${ITEM_INFO_PARAM}")
    private String ITEM_INFO_PARAM;

    @Override
    public Item getItemById(long itemId) {

        String itemString = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO + itemId);

        if (!StringUtils.isBlank(itemString)) {

            HydraResult hydraResult = HydraResult.formatToPojo(itemString, Item.class);

            if (hydraResult.getStatus() == 200 && hydraResult.getData() != null) {

                return (Item) hydraResult.getData();
            }
        }

        return null;


    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {

        String descString = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_DESC + itemId);

        if (!StringUtils.isBlank(descString)) {

            HydraResult hydraResult = HydraResult.formatToPojo(descString, TbItemDesc.class);

            if (hydraResult.getStatus() == 200 && hydraResult.getData() != null) {

                return (TbItemDesc) hydraResult.getData();

            }
        }
        return null;
    }

    @Override
    public String getItemParamById(long itemId) {

        String itemParam = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_PARAM + itemId);

        if (!StringUtils.isBlank(itemParam)) {

            HydraResult hydraResult = HydraResult.formatToPojo(itemParam, TbItemParamItem.class);

            if (hydraResult.getStatus() == 200 && hydraResult.getData() != null) {

                TbItemParamItem data = (TbItemParamItem) hydraResult.getData();

                List<Map> maps = JsonUtils.jsonToList(data.getParamData(), Map.class);

                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n<tbody>");

                for (Map m1 : maps) {
                    sb.append("<tr><th class=\"tdTitle\" colspan=\"2\">");
                    sb.append(m1.get("group"));
                    sb.append("</th></tr>");
                    List<Map> params = (List<Map>) m1.get("params");
                    for (Map m2 : params) {
                        sb.append("<tr><td class=\"tdTitle\">");
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
        return null;
    }
}
