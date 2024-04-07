package com.lcq.tftgdb.pojo;

public class PathwayResult {
    private String id;
    private String tertf;
    private String pathway_ID;
    private String pathway_name;
    private String pathway_source;
    private String annotated_gene;
    private String annotated_gene_number;
    private String total_gene_number;
    private String the_terminal_tf;
    private String the_terminal_tf_short;
    private String tf_number;
    private String p_value;
    private String fdr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTertf() {
        return tertf;
    }

    public void setTertf(String tertf) {
        this.tertf = tertf;
    }

    public String getThe_terminal_tf_short() {
        return the_terminal_tf_short;
    }

    public void setThe_terminal_tf_short(String the_terminal_tf_short) {
        this.the_terminal_tf_short = the_terminal_tf_short;
    }

    public String getPathway_ID() {
        return pathway_ID;
    }

    public void setPathway_ID(String pathway_ID) {
        this.pathway_ID = pathway_ID;
    }

    public String getPathway_name() {
        return pathway_name;
    }

    public void setPathway_name(String pathway_name) {
        this.pathway_name = pathway_name;
    }

    public String getPathway_source() {
        return pathway_source;
    }

    public void setPathway_source(String pathway_source) {
        this.pathway_source = pathway_source;
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

    public String getTotal_gene_number() {
        return total_gene_number;
    }

    public void setTotal_gene_number(String total_gene_number) {
        this.total_gene_number = total_gene_number;
    }

    public String getThe_terminal_tf() {
        return the_terminal_tf;
    }

    public void setThe_terminal_tf(String the_terminal_tf) {
        this.the_terminal_tf = the_terminal_tf;
    }

    public String getTf_number() {
        return tf_number;
    }

    public void setTf_number(String tf_number) {
        this.tf_number = tf_number;
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
        return "PathwayResult{" +
                "id='" + id + '\'' +
                ", tertf='" + tertf + '\'' +
                ", pathway_ID='" + pathway_ID + '\'' +
                ", pathway_name='" + pathway_name + '\'' +
                ", pathway_source='" + pathway_source + '\'' +
                ", annotated_gene='" + annotated_gene + '\'' +
                ", annotated_gene_number='" + annotated_gene_number + '\'' +
                ", total_gene_number='" + total_gene_number + '\'' +
                ", the_terminal_tf='" + the_terminal_tf + '\'' +
                ", the_terminal_tf_short='" + the_terminal_tf_short + '\'' +
                ", tf_number='" + tf_number + '\'' +
                ", p_value='" + p_value + '\'' +
                ", fdr='" + fdr + '\'' +
                '}';
    }
}
