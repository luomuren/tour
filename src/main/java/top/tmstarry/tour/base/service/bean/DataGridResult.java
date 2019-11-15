package top.tmstarry.tour.base.service.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DataGridResult
 * @Author 落幕人
 * <p>
 * 分页封装
 * <p>
 * @Date 2019/10/30 15:22
 * @Version 1.0
 **/
public class DataGridResult implements Serializable {
    private long total;//总记录数
    private List<?> rows;//当前页的结果集

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
