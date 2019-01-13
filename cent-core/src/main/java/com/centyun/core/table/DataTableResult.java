package com.centyun.core.table;

import java.util.List;

public class DataTableResult<T> {
    private int draw;

    private int recordsTotal;

    private int recordsFiltered;

    private List<T> data;

    public DataTableResult() {
    }

    public DataTableResult(List<T> data, Long total, int draw) {
        this.draw = draw;
        this.data = data;
        recordsTotal = total == null ? 0 : total.intValue();
        recordsFiltered = recordsTotal;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
