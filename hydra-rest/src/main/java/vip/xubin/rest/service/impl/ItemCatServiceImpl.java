package vip.xubin.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.mapper.TbItemCatMapper;
import vip.xubin.pojo.TbItemCat;
import vip.xubin.pojo.TbItemCatExample;
import vip.xubin.rest.pojo.CatNode;
import vip.xubin.rest.pojo.CatResult;
import vip.xubin.rest.service.ItemCatService;
import vip.xubin.rest.service.JedisClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 許彬.
 * @creater 2016-08-20 15:55
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_ITEM_CAT}")
    private String INDEX_ITEM_CAT;
    @Value("${INDEX_ITEM_CAT_ID}")
    private String INDEX_ITEM_CAT_ID;

    @Override
    public CatResult getItemCatList() {

        CatResult result = new CatResult();

        try {
            String hget = jedisClient.hget(INDEX_ITEM_CAT, INDEX_ITEM_CAT_ID);

            if (!StringUtils.isBlank(hget)){
                return JsonUtils.jsonToPojo(hget, CatResult.class);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        result.setData(getCatList(0l));

        try {
            jedisClient.hset(INDEX_ITEM_CAT, INDEX_ITEM_CAT_ID, JsonUtils.objectToJson(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private List getCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();

        TbItemCatExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        ArrayList resultList = new ArrayList();

        int count = 0;
        for (TbItemCat itemCat : list) {

            if (itemCat.getIsParent()) {

                CatNode node = new CatNode();

                node.setUrl("/products/"+itemCat.getId()+".html");

                if (parentId == 0) {

                    node.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");

                }else {
                    node.setName(itemCat.getName());
                }

                node.setItems(getCatList(itemCat.getId()));

                resultList.add(node);
                count ++;
                if (count >=14) {
                    break;
                }
            } else {

                resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName() + "");
            }

        }
        return resultList;
    }
}
