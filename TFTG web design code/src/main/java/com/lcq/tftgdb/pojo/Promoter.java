package com.lcq.tftgdb.pojo;

public class Promoter {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String promoter_chr;
    private String promoter_start;
    private String promoter_end;
    private String rol;
    private String tf_name;
    private String biosample_name_specific;
    private String gene;

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public String getTf_chr() {
        return tf_chr;
    }

    public void setTf_chr(String tf_chr) {
        this.tf_chr = tf_chr;
    }

    public String getTf_start() {
        return tf_start;
    }

    public void setTf_start(String tf_start) {
        this.tf_start = tf_start;
    }

    public String getTf_end() {
        return tf_end;
    }

    public void setTf_end(String tf_end) {
        this.tf_end = tf_end;
    }

    public String getPromoter_chr() {
        return promoter_chr;
    }

    public void setPromoter_chr(String promoter_chr) {
        this.promoter_chr = promoter_chr;
    }

    public String getPromoter_start() {
        return promoter_start;
    }

    public void setPromoter_start(String promoter_start) {
        this.promoter_start = promoter_start;
    }

    public String getPromoter_end() {
        return promoter_end;
    }

    public void setPromoter_end(String promoter_end) {
        this.promoter_end = promoter_end;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTf_name() {
        return tf_name;
    }

    public void setTf_name(String tf_name) {
        this.tf_name = tf_name;
    }

    public String getBiosample_name_specific() {
        return biosample_name_specific;
    }

    public void setBiosample_name_specific(String biosample_name_specific) {
        this.biosample_name_specific = biosample_name_specific;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    @Override
    public String toString() {
        return "Promoter{" +
                "sampleID='" + sampleID + '\'' +
                ", tf_chr='" + tf_chr + '\'' +
                ", tf_start='" + tf_start + '\'' +
                ", tf_end='" + tf_end + '\'' +
                ", promoter_chr='" + promoter_chr + '\'' +
                ", promoter_start='" + promoter_start + '\'' +
                ", promoter_end='" + promoter_end + '\'' +
                ", rol='" + rol + '\'' +
                ", tf_name='" + tf_name + '\'' +
                ", biosample_name_specific='" + biosample_name_specific + '\'' +
                ", gene='" + gene + '\'' +
                '}';
    }
}
