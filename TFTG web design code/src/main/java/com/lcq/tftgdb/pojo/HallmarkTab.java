package com.lcq.tftgdb.pojo;

public class HallmarkTab {
    private String gene;
    private String cancer_hallmark;
    private String go_term_id;
    private String go_term_name;

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getCancer_hallmark() {
        return cancer_hallmark;
    }

    public void setCancer_hallmark(String cancer_hallmark) {
        this.cancer_hallmark = cancer_hallmark;
    }

    public String getGo_term_id() {
        return go_term_id;
    }

    public void setGo_term_id(String go_term_id) {
        this.go_term_id = go_term_id;
    }

    public String getGo_term_name() {
        return go_term_name;
    }

    public void setGo_term_name(String go_term_name) {
        this.go_term_name = go_term_name;
    }

    @Override
    public String toString() {
        return "HallmarkTab{" +
                "gene='" + gene + '\'' +
                ", cancer_hallmark='" + cancer_hallmark + '\'' +
                ", go_term_id='" + go_term_id + '\'' +
                ", go_term_name='" + go_term_name + '\'' +
                '}';
    }
}
