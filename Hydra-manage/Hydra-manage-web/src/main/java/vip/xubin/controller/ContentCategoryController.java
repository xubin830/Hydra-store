package vip.xubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xubin.common.pojo.EUIDataGridResult;
import vip.xubin.common.pojo.EUITreeNode;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.pojo.TbContent;
import vip.xubin.service.ContentCategoryService;

import java.util.List;

/**
 * 网站内容管理Controller
 *
 * @author 許彬.
 * @creater 2016-08-21 19:58
 */
@Controller
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService categoryService;

    @RequestMapping("/category/list")
    @ResponseBody
    public List<EUITreeNode> getCategoryList(@RequestParam(value = "id",defaultValue = "0") long parentId) {

        return categoryService.getCategoryList(parentId);

    }

    @RequestMapping("/query/list")
    @ResponseBody
    public EUIDataGridResult getContentList(long categoryId, Integer page, Integer rows) {
        return categoryService.getContentListById(categoryId, page, rows);
    }

    // /category/create parentId:89 name:新建分类

    @RequestMapping("/category/create")
    @ResponseBody
    public HydraResult createCategory(long parentId, String name) {

        return categoryService.createByParentId(parentId, name);

    }

    //id:89  name:大广
    @RequestMapping("/category/update")
    @ResponseBody
    public HydraResult updateCategory(long id, String name) {

        return categoryService.updateCategoryById(id, name);

    }

    //category/delete/ id
    @RequestMapping("/category/delete/")
    @ResponseBody
    public HydraResult deleteCategory(long id) {
        return categoryService.deleteCategoryById(id);
    }

    @RequestMapping("/save")
    @ResponseBody
    public HydraResult saveContent(TbContent content) {

        return categoryService.saveContent(content);

    }



}
