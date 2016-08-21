package vip.xubin.service;

import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.pojo.EUITreeNode;

import java.util.List;

/**
 * Created by cynic on 2016/8/21.
 */

public interface ContentCategoryService {

    List<EUITreeNode> getCategoryList(long parentId);


    EUIDataGridResult getContentListById(long categoryId, Integer page, Integer rows);
}
