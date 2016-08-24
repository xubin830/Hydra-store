package vip.xubin.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.xubin.portal.pojo.SearchResult;
import vip.xubin.portal.service.SearchService;

import java.io.UnsupportedEncodingException;

/**
 * 搜索Controller
 *
 * @author 許彬.
 * @creater 2016-08-24 19:23
 */

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String searchString, @RequestParam(defaultValue = "1") Integer page, Model model) {

        if (searchString != null) {

            String string = null;
            try {
                string = new String(searchString.getBytes("iso8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            SearchResult search = searchService.search(string, page);

            model.addAttribute("query", string);
            model.addAttribute("totalPages", search.getPageCount());
            model.addAttribute("itemList", search.getItemList());
            model.addAttribute("page", search.getCurPage());

            return "search";
        }

        return "index";

    }

}
