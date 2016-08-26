package vip.xubin.search.service;

import org.apache.solr.client.solrj.SolrServerException;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.search.pojo.SearchResult;

/**
 * Solr操作service
 *
 * @author 許彬.
 * @creater 2016-08-23 21:06
 */

public interface SolrService {

    HydraResult solrImportAll();

    HydraResult solrImportByItemId(Long itemId);


    SearchResult search(String queryString, Integer page, Integer rows) throws SolrServerException;
}
