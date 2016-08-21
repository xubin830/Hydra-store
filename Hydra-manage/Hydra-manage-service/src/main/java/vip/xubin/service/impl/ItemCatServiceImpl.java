package vip.xubin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xubin.common.pojo.EUITreeNode;
import vip.xubin.mapper.TbItemCatMapper;
import vip.xubin.pojo.TbItemCat;
import vip.xubin.pojo.TbItemCatExample;
import vip.xubin.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 *
 * @author 許彬.
 * @creater 2016-08-18 8:57
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUITreeNode> getItemCatList(Long parentId) {

        TbItemCatExample example = new TbItemCatExample();

        TbItemCatExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);

        ArrayList<EUITreeNode> treeNodes = new ArrayList<>();

        for (TbItemCat itemCat : itemCats) {
            EUITreeNode treeNode = new EUITreeNode();

            treeNode.setId(itemCat.getId());

            treeNode.setText(itemCat.getName());

            treeNode.setState(itemCat.getIsParent() ? "closed" : "open");

            treeNodes.add(treeNode);
        }

        return treeNodes;

    }
}
