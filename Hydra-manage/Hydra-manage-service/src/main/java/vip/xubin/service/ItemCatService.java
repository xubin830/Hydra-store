package vip.xubin.service;

import vip.xubin.common.pojo.EUITreeNode;

import java.util.List;

/**
 * Created by cynic on 2016/8/18.
 */
public interface ItemCatService {

    List<EUITreeNode> getItemCatList(Long parentId);

}
