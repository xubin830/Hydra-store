package vip.xubin.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.search.pojo.SearchResult;
import vip.xubin.search.service.SolrService;

/**
 * Solr操作Controller
 *
 * @author 許彬.
 * @creater 2016-08-23 21:26
 */

@Controller
public class SolrController {

    @Autowired
    private SolrService solrService;

    @RequestMapping("/import/all")
    @ResponseBody
    public HydraResult importAll() {

        return solrService.solrImportAll();

    }

    @RequestMapping("/import/{itemId}")
    @ResponseBody
    public HydraResult importByItemId(@PathVariable Long itemId) {

        return solrService.solrImportByItemId(itemId);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public HydraResult search(@RequestParam("q") String queryString,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "60") Integer rows) {

        if (queryString == null) {
            return HydraResult.build(400, "查询条件不能为空");
        }

        SearchResult search = null;
        try {

            String String = new String(queryString.getBytes("iso8859-1"), "utf-8");
            search = solrService.search(String, page, rows);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return HydraResult.ok(search);


    }


}
