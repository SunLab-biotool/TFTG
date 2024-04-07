package com.lcq.tftgdb.pojo;

import java.util.List;

public class DataTabPageCurate {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<Curate> data;

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

    public List<Curate> getData() {
        return data;
    }

    public void setData(List<Curate> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataTabPage{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                '}';
    }
}
