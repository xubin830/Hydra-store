package vip.xubin.portal.service;

import vip.xubin.portal.pojo.SearchResult;

/**
 * 商品搜索Service
 *
 * @author 許彬.
 * @creater 2016-08-24 19:33
 */

public interface SearchService {

    SearchResult search(String searchString, Integer page);

}
