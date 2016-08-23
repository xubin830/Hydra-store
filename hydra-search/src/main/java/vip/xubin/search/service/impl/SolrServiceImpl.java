package vip.xubin.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.search.mapper.SolrMapper;
import vip.xubin.search.pojo.SearchResult;
import vip.xubin.search.pojo.SolrItem;
import vip.xubin.search.service.SolrService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 許彬.
 * @creater 2016-08-23 21:08
 */
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SolrMapper solrMapper;


    @Override
    public HydraResult solrImportAll() {

        List<SolrItem> list = solrMapper.getSolrItemList();

        try {

            for (SolrItem item : list) {

                SolrInputDocument fields = new SolrInputDocument();

                fields.addField("id", item.getId());
                fields.addField("item_category_name", item.getCategory_name());
                fields.addField("item_title", item.getTitle());
                fields.addField("item_image", item.getImage());
                fields.addField("item_price", item.getPrice());
                fields.addField("item_sell_point", item.getSell_point());
                fields.addField("item_desc", item.getItem_desc());

                solrServer.add(fields);
            }

            solrServer.commit();

        } catch (Exception e) {
            e.printStackTrace();
            return HydraResult.build(500, "商品索引构建失败");
        }

        return HydraResult.ok();
    }

    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws SolrServerException {

        SearchResult searchResult = new SearchResult();

        SolrQuery query = new SolrQuery();

        //设置查询条件
        query.setQuery(queryString);

        //设置分页
        query.setStart((page - 1) * rows);

        query.setRows(rows);

        //设置默认搜素域
        query.set("df", "item_keywords");

        query.setHighlight(true);

        query.setHighlightSimplePre("<em style=\"color:red\">");

        query.setHighlightSimplePost("</em>");

        QueryResponse response = solrServer.query(query);

        SolrDocumentList results = response.getResults();

        searchResult.setRecordCount(results.getNumFound());

        List<SolrItem> solrItems = new ArrayList<>();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument result : results) {

            SolrItem solrItem = new SolrItem();

            solrItem.setId((String) result.get("id"));

            List<String> strings = highlighting.get(result.get("id")).get("item_title");
            if (strings != null && strings.size() > 0) {
                solrItem.setTitle(strings.get(0));
            } else {
                solrItem.setTitle((String) result.get("item_title"));
            }
            solrItem.setCategory_name((String) result.get("item_category_name"));
            solrItem.setImage((String) result.get("item_image"));
            solrItem.setSell_point((String) result.get("item_sell_point"));
            solrItem.setItem_desc((String) result.get("item_desc"));
            solrItem.setPrice((Long) result.get("item_price"));

            solrItems.add(solrItem);

        }

        searchResult.setItemList(solrItems);
        searchResult.setCurPage(page);

        long recordCount = searchResult.getRecordCount();
        long pageCount = recordCount / rows;

        if (recordCount % rows > 0) {
            pageCount++;
        }

        searchResult.setPageCount(pageCount);

        return searchResult;
    }
}
