package pojo;

import java.util.List;

/**
 * @author Dunn
 */
public class Pager {
    private int page;//当前页码
    private int pageTotal;//总页码
    private int rowsTotal;//总条数
    private int rows;//每页显示条数
    private List<?> list;//返回的数据集合

    public Pager() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getRowsTotal() {
        return rowsTotal;
    }

    public void setRowsTotal(int rowsTotal) {
        this.rowsTotal = rowsTotal;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Pager(int page,  int rows, int pageTotal, int rowsTotal,List<?> list) {
        this.page = page;
        this.pageTotal = pageTotal;
        this.rowsTotal = rowsTotal;
        this.rows = rows;
        this.list = list;
    }
}
