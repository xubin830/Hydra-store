package vip.xubin.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 首页商品列表返回POJO
 *
 * @author 許彬.
 * @creater 2016-08-20 15:48
 */

public class CatNode {

    @JsonProperty("u")
    private String url;

    @JsonProperty("n")
    private String name;

    @JsonProperty("i")
    private List<?> items;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
