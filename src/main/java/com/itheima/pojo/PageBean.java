package com.itheima.pojo;

import java.util.List;

/**
 * 分页查询的JavaBean
 * 返回给前台页面
 * @param <T> 自定义泛型：可能是Brand..User...
 */
public class PageBean<T> {
    // 总记录数
    private int totalCount;
    // 当前页数据
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
