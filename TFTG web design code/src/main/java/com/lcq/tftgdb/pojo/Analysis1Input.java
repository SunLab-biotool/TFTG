package com.lcq.tftgdb.pojo;

public class Analysis1Input {
    private String tf;
    private String sampleID;
    private String gene;
    private String gene_number;
    private String method;
    private String biosample_name;
    private String sample_name;

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getGene_number() {
        return gene_number;
    }

    public void setGene_number(String gene_number) {
        this.gene_number = gene_number;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBiosample_name() {
        return biosample_name;
    }

    public void setBiosample_name(String biosample_name) {
        this.biosample_name = biosample_name;
    }

    public String getSample_name() {
        return sample_name;
    }

    public void setSample_name(String sample_name) {
        this.sample_name = sample_name;
    }

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    @Override
    public String toString() {
        return tf + '\t' + gene + '\t' + gene_number + '\t' + method + '\t' + biosample_name + '\t' + sample_name + '\t' + sampleID;
    }
}
