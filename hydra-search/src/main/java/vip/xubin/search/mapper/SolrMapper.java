package vip.xubin.search.mapper;

import vip.xubin.search.pojo.SolrItem;

import java.util.List;

/**
 * Solr操作接口
 *
 * @author 許彬.
 * @creater 2016-08-23 20:57
 */

public interface SolrMapper {

    List<SolrItem> getSolrItemList();


}
