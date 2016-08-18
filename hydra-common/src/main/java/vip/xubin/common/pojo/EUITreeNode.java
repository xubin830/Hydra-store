package vip.xubin.common.pojo;

/**
 * EUI树形控件的节点
 *
 * @author 許彬.
 * @creater 2016-08-18 8:50
 */

public class EUITreeNode {
    private Long id;
    private String text;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
