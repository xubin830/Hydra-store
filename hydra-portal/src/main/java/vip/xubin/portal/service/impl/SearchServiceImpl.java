package vip.xubin.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.HttpClientUtil;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.portal.pojo.SearchResult;
import vip.xubin.portal.service.SearchService;

/**
 * @author 許彬.
 * @creater 2016-08-24 19:34
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_URL}")
    private String SEARCH_URL;

    @Override
    public SearchResult search(String searchString, Integer page) {

        String searchResult = HttpClientUtil.doGet(SEARCH_URL + "?q=" + searchString + "&page=" + page);

        HydraResult hydraResult = HydraResult.formatToPojo(searchResult, SearchResult.class);

        if (hydraResult.getStatus() == 400) {
            return null;
        }

        return (SearchResult) hydraResult.getData();
    }
}
