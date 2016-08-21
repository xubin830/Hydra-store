package vip.xubin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.pojo.EUITreeNode;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.mapper.TbContentCategoryMapper;
import vip.xubin.mapper.TbContentMapper;
import vip.xubin.pojo.TbContent;
import vip.xubin.pojo.TbContentCategory;
import vip.xubin.pojo.TbContentCategoryExample;
import vip.xubin.pojo.TbContentExample;
import vip.xubin.pojo.TbContentExample.Criteria;
import vip.xubin.service.ContentCategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 許彬.
 * @creater 2016-08-21 20:03
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper categoryMapper;

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public List<EUITreeNode> getCategoryList(long parentId) {

        TbContentCategoryExample example = new TbContentCategoryExample();

        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        List<TbContentCategory> list = categoryMapper.selectByExample(example);

        ArrayList<EUITreeNode> treeNodes = new ArrayList<>();

        for (TbContentCategory contentCategory : list) {

            EUITreeNode treeNode = new EUITreeNode();

            treeNode.setId(contentCategory.getId());

            treeNode.setText(contentCategory.getName());

            treeNode.setState(contentCategory.getIsParent() ? "closed" : "open");

            treeNodes.add(treeNode);
        }

        return treeNodes;

    }

    @Override
    public EUIDataGridResult getContentListById(long categoryId, Integer page, Integer rows) {

        TbContentExample example = new TbContentExample();

        Criteria criteria = example.createCriteria();

        criteria.andCategoryIdEqualTo(categoryId);

        PageHelper.startPage(page,rows);

        List<TbContent> list = contentMapper.selectByExample(example);

        PageInfo<TbContent> pageInfo = new PageInfo<>(list);

        EUIDataGridResult result = new EUIDataGridResult();

        result.setTotal((int) pageInfo.getTotal());

        result.setRows(list);

        return result;
    }

    @Override
    public HydraResult createByParentId(long parentId, String name) {

        TbContentCategoryExample example = new TbContentCategoryExample();

        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(parentId);

        TbContentCategory parentCategory = categoryMapper.selectByExample(example).get(0);

        if (!parentCategory.getIsParent()) {

            parentCategory.setIsParent(true);

            categoryMapper.updateByPrimaryKey(parentCategory);

        }
        TbContentCategory category = new TbContentCategory();

        category.setIsParent(false);
        category.setName(name);
        category.setParentId(parentId);
        category.setSortOrder(1);
        category.setCreated(new Date());
        category.setUpdated(new Date());

        categoryMapper.insert(category);

        return HydraResult.ok();

    }

    @Override
    public HydraResult updateCategoryById(long id, String name) {

        TbContentCategory category = new TbContentCategory();

        category.setId(id);

        category.setName(name);

        category.setUpdated(new Date());

        categoryMapper.updateByPrimaryKeySelective(category);

        return HydraResult.ok();

    }

    @Override
    public HydraResult deleteCategoryById(long id) {

        categoryMapper.deleteByPrimaryKey(id);

        return HydraResult.ok();
    }

    @Override
    public HydraResult saveContent(TbContent content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());

        contentMapper.insert(content);

        return HydraResult.ok();



    }
}
