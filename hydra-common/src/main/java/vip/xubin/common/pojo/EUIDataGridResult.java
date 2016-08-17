package vip.xubin.common.pojo;

import java.util.List;

/**
 * Easy-UI 返回POJO
 *
 * @author 許彬.
 * @creater 2016-08-17 19:42
 */

public class EUIDataGridResult {

    private Integer total;
    private List<?> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
