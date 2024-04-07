package com.lcq.tftgdb.pojo;

public class Promoterdown {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String promoter_chr;
    private String promoter_start;
    private String promoter_end;
    private String rol;
    private String gene;

    public Promoterdown(String sampleID, String tf_chr, String tf_start, String tf_end, String promoter_chr, String promoter_start, String promoter_end, String rol, String gene) {
        this.sampleID = sampleID;
        this.tf_chr = tf_chr;
        this.tf_start = tf_start;
        this.tf_end = tf_end;
        this.promoter_chr = promoter_chr;
        this.promoter_start = promoter_start;
        this.promoter_end = promoter_end;
        this.rol = rol;
        this.gene = gene;
    }

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

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    @Override
    public String toString() {
        return sampleID + '\t' +
                tf_chr + '\t' +
                tf_start + '\t' +
                tf_end + '\t' +
                promoter_chr + '\t' +
                promoter_start + '\t' +
                promoter_end + '\t' +
                rol + '\t' +
                gene;
    }
}
