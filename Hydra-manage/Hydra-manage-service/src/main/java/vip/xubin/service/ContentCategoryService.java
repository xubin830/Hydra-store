package vip.xubin.service;

import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.pojo.EUITreeNode;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbContent;

import java.util.List;

/**
 * Created by cynic on 2016/8/21.
 */

public interface ContentCategoryService {

    List<EUITreeNode> getCategoryList(long parentId);


    EUIDataGridResult getContentListById(long categoryId, Integer page, Integer rows);

    HydraResult createByParentId(long parentId, String name);

    HydraResult updateCategoryById(long id, String name);

    HydraResult deleteCategoryById(long id);

    HydraResult saveContent(TbContent content);
}
