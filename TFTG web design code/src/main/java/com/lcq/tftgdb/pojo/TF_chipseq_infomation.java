package com.lcq.tftgdb.pojo;

public class TF_chipseq_infomation {
    private String sample_id;
    private String data_sources;
    private String tf_name;
    private String biosample_type;
    private String sample_type;
    private String biosample_name;
    private String biosample_name_specific;
    private String sample_name;

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public String getData_sources() {
        return data_sources;
    }

    public void setData_sources(String data_sources) {
        this.data_sources = data_sources;
    }

    public String getTf_name() {
        return tf_name;
    }

    public void setTf_name(String tf_name) {
        this.tf_name = tf_name;
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

    public String getBiosample_name_specific() {
        return biosample_name_specific;
    }

    public void setBiosample_name_specific(String biosample_name_specific) {
        this.biosample_name_specific = biosample_name_specific;
    }

    public String getSample_name() {
        return sample_name;
    }

    public void setSample_name(String sample_name) {
        this.sample_name = sample_name;
    }

    @Override
    public String toString() {
        return "TF_chipseq_infomation{" +
                "sample_id='" + sample_id + '\'' +
                ", data_sources='" + data_sources + '\'' +
                ", tf_name='" + tf_name + '\'' +
                ", biosample_type='" + biosample_type + '\'' +
                ", sample_type='" + sample_type + '\'' +
                ", biosample_name='" + biosample_name + '\'' +
                ", biosample_name_specific='" + biosample_name_specific + '\'' +
                ", sample_name='" + sample_name + '\'' +
                '}';
    }
}
