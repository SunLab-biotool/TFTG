package com.lcq.tftgdb.pojo;

public class SampleAndCellline {
    private String sampleID;
    private String cellLine;

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public String getCellLine() {
        return cellLine;
    }

    public void setCellLine(String cellLine) {
        this.cellLine = cellLine;
    }

    @Override
    public String toString() {
        return "SampleAndCellline{" +
                "sampleID='" + sampleID + '\'' +
                ", cellLine='" + cellLine + '\'' +
                '}';
    }
}
