package com.lcq.tftgdb.pojo;

public class EncodeData {
    private String sample_name;
    private String data_sources;
    private String tF_name;
    private String biosample_type;
    private String sample_type;
    private String biosample_name;
    private String sample_source;
    private String bed_name;
    private String dataset_ID;

    @Override
    public String toString() {
        return "EncodeData{" +
                "sample_name='" + sample_name + '\'' +
                ", data_sources='" + data_sources + '\'' +
                ", tF_name='" + tF_name + '\'' +
                ", biosample_type='" + biosample_type + '\'' +
                ", sample_type='" + sample_type + '\'' +
                ", biosample_name='" + biosample_name + '\'' +
                ", sample_source='" + sample_source + '\'' +
                ", bed_name='" + bed_name + '\'' +
                ", dataset_ID='" + dataset_ID + '\'' +
                '}';
    }

    public String getSample_name() {
        return sample_name;
    }

    public void setSample_name(String sample_name) {
        this.sample_name = sample_name;
    }

    public String getData_sources() {
        return data_sources;
    }

    public void setData_sources(String data_sources) {
        this.data_sources = data_sources;
    }

    public String gettF_name() {
        return tF_name;
    }

    public void settF_name(String tF_name) {
        this.tF_name = tF_name;
    }

    public String getBiosample_type() {
        return biosample_type;
    }

    public void setBiosample_type(String biosample_type) {
        this.biosample_type = biosample_type;
    }

    public String getSample_type() {
        return sample_type;
    }

    public void setSample_type(String sample_type) {
        this.sample_type = sample_type;
    }

    public String getBiosample_name() {
        return biosample_name;
    }

    public void setBiosample_name(String biosample_name) {
        this.biosample_name = biosample_name;
    }

    public String getSample_source() {
        return sample_source;
    }

    public void setSample_source(String sample_source) {
        this.sample_source = sample_source;
    }

    public String getBed_name() {
        return bed_name;
    }

    public void setBed_name(String bed_name) {
        this.bed_name = bed_name;
    }

    public String getDataset_ID() {
        return dataset_ID;
    }

    public void setDataset_ID(String dataset_ID) {
        this.dataset_ID = dataset_ID;
    }
}
