package com.lcq.tftgdb.pojo;

public class Knock {
    private String sample_ID;
    private String dataSet_ID;
    private String tf;
    private String gene;
    private String fc;
    private String log2fc;
    private String rank;
    private String p_value;
    private String up_down;
    private String sample_source;
    private String sample_experiment_accession;
    private String sample_platform;
    private String sample_method;
    private String sample_target_sequence;
    private String sample_class;
    private String sample_superclass;
    private String sample_biosample_name;
    private String sample_tissue_type;
    private String sample_biosample_type;
    private String sample_case;
    private String sample_control;
    private String sample_pubmed;
    private String sample_up;
    private String sample_down;
    private String sample_other;

    public String getSample_ID() {
        return sample_ID;
    }

    public void setSample_ID(String sample_ID) {
        this.sample_ID = sample_ID;
    }

    public String getDataSet_ID() {
        return dataSet_ID;
    }

    public void setDataSet_ID(String dataSet_ID) {
        this.dataSet_ID = dataSet_ID;
    }

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

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getLog2fc() {
        return log2fc;
    }

    public void setLog2fc(String log2fc) {
        this.log2fc = log2fc;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getP_value() {
        return p_value;
    }

    public void setP_value(String p_value) {
        this.p_value = p_value;
    }

    public String getUp_down() {
        return up_down;
    }

    public void setUp_down(String up_down) {
        this.up_down = up_down;
    }

    public String getSample_source() {
        return sample_source;
    }

    public void setSample_source(String sample_source) {
        this.sample_source = sample_source;
    }

    public String getSample_experiment_accession() {
        return sample_experiment_accession;
    }

    public void setSample_experiment_accession(String sample_experiment_accession) {
        this.sample_experiment_accession = sample_experiment_accession;
    }

    public String getSample_platform() {
        return sample_platform;
    }

    public void setSample_platform(String sample_platform) {
        this.sample_platform = sample_platform;
    }

    public String getSample_method() {
        return sample_method;
    }

    public void setSample_method(String sample_method) {
        this.sample_method = sample_method;
    }

    public String getSample_target_sequence() {
        return sample_target_sequence;
    }

    public void setSample_target_sequence(String sample_target_sequence) {
        this.sample_target_sequence = sample_target_sequence;
    }

    public String getSample_class() {
        return sample_class;
    }

    public void setSample_class(String sample_class) {
        this.sample_class = sample_class;
    }

    public String getSample_superclass() {
        return sample_superclass;
    }

    public void setSample_superclass(String sample_superclass) {
        this.sample_superclass = sample_superclass;
    }

    public String getSample_biosample_name() {
        return sample_biosample_name;
    }

    public void setSample_biosample_name(String sample_biosample_name) {
        this.sample_biosample_name = sample_biosample_name;
    }

    public String getSample_tissue_type() {
        return sample_tissue_type;
    }

    public void setSample_tissue_type(String sample_tissue_type) {
        this.sample_tissue_type = sample_tissue_type;
    }

    public String getSample_biosample_type() {
        return sample_biosample_type;
    }

    public void setSample_biosample_type(String sample_biosample_type) {
        this.sample_biosample_type = sample_biosample_type;
    }

    public String getSample_case() {
        return sample_case;
    }

    public void setSample_case(String sample_case) {
        this.sample_case = sample_case;
    }

    public String getSample_control() {
        return sample_control;
    }

    public void setSample_control(String sample_control) {
        this.sample_control = sample_control;
    }

    public String getSample_pubmed() {
        return sample_pubmed;
    }

    public void setSample_pubmed(String sample_pubmed) {
        this.sample_pubmed = sample_pubmed;
    }

    public String getSample_up() {
        return sample_up;
    }

    public void setSample_up(String sample_up) {
        this.sample_up = sample_up;
    }

    public String getSample_down() {
        return sample_down;
    }

    public void setSample_down(String sample_down) {
        this.sample_down = sample_down;
    }

    public String getSample_other() {
        return sample_other;
    }

    public void setSample_other(String sample_other) {
        this.sample_other = sample_other;
    }

    @Override
    public String toString() {
        return "Knock{" +
                "sample_ID='" + sample_ID + '\'' +
                ", dataSet_ID='" + dataSet_ID + '\'' +
                ", tf='" + tf + '\'' +
                ", gene='" + gene + '\'' +
                ", fc='" + fc + '\'' +
                ", log2fc='" + log2fc + '\'' +
                ", rank='" + rank + '\'' +
                ", p_value='" + p_value + '\'' +
                ", up_down='" + up_down + '\'' +
                ", sample_source='" + sample_source + '\'' +
                ", sample_experiment_accession='" + sample_experiment_accession + '\'' +
                ", sample_platform='" + sample_platform + '\'' +
                ", sample_method='" + sample_method + '\'' +
                ", sample_target_sequence='" + sample_target_sequence + '\'' +
                ", sample_class='" + sample_class + '\'' +
                ", sample_superclass='" + sample_superclass + '\'' +
                ", sample_biosample_name='" + sample_biosample_name + '\'' +
                ", sample_tissue_type='" + sample_tissue_type + '\'' +
                ", sample_biosample_type='" + sample_biosample_type + '\'' +
                ", sample_case='" + sample_case + '\'' +
                ", sample_control='" + sample_control + '\'' +
                ", sample_pubmed='" + sample_pubmed + '\'' +
                ", sample_up='" + sample_up + '\'' +
                ", sample_down='" + sample_down + '\'' +
                ", sample_other='" + sample_other + '\'' +
                '}';
    }
}
