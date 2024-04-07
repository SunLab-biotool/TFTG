package com.lcq.tftgdb.pojo;

public class PathwayChlidTf {
    private String tf;
    private String rank;
    private String annotated_gene;
    private String annotated_gene_number;
    private String p_value;
    private String fdr;
    private String num1;
    private String num2;
    private String num3;

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAnnotated_gene() {
        return annotated_gene;
    }

    public void setAnnotated_gene(String annotated_gene) {
        this.annotated_gene = annotated_gene;
    }

    public String getAnnotated_gene_number() {
        return annotated_gene_number;
    }

    public void setAnnotated_gene_number(String annotated_gene_number) {
        this.annotated_gene_number = annotated_gene_number;
    }

    public String getP_value() {
        return p_value;
    }

    public void setP_value(String p_value) {
        this.p_value = p_value;
    }

    public String getFdr() {
        return fdr;
    }

    public void setFdr(String fdr) {
        this.fdr = fdr;
    }

    @Override
    public String toString() {
        return "PathwayChlidTf{" +
                "tf='" + tf + '\'' +
                ", rank='" + rank + '\'' +
                ", annotated_gene='" + annotated_gene + '\'' +
                ", annotated_gene_number='" + annotated_gene_number + '\'' +
                ", p_value='" + p_value + '\'' +
                ", fdr='" + fdr + '\'' +
                ", num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", num3='" + num3 + '\'' +
                '}';
    }
}
