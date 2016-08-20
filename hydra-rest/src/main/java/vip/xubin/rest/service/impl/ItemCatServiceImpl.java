package vip.xubin.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xubin.mapper.TbItemCatMapper;
import vip.xubin.pojo.TbItemCat;
import vip.xubin.pojo.TbItemCatExample;
import vip.xubin.rest.pojo.CatNode;
import vip.xubin.rest.pojo.CatResult;
import vip.xubin.rest.service.ItemCatService;

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

    @Override
    public CatResult getItemCatList() {

        CatResult result = new CatResult();

        result.setData(getCatList(0l));

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
