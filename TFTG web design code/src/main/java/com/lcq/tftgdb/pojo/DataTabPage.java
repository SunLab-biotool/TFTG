package com.lcq.tftgdb.pojo;

import java.util.List;

public class DataTabPage {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<TfMethodWeig> data;

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

    public List<TfMethodWeig> getData() {
        return data;
    }

    public void setData(List<TfMethodWeig> data) {
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
