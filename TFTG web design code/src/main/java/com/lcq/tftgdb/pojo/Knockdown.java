package com.lcq.tftgdb.pojo;

public class Knockdown {
    private String tf;
    private String gene;
    private String fc;
    private String log2fc;
    private String rank;
    private String p_value;
    private String up_down;
    private String sample_source;
    private String sample_experiment_accession;
    private String sample_method;
    private String sample_biosample_name;

    public Knockdown(String tf, String gene, String fc, String log2fc, String rank, String p_value, String up_down, String sample_source, String sample_experiment_accession, String sample_method, String sample_biosample_name) {
        this.tf = tf;
        this.gene = gene;
        this.fc = fc;
        this.log2fc = log2fc;
        this.rank = rank;
        this.p_value = p_value;
        this.up_down = up_down;
        this.sample_source = sample_source;
        this.sample_experiment_accession = sample_experiment_accession;
        this.sample_method = sample_method;
        this.sample_biosample_name = sample_biosample_name;
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

    public String getSample_method() {
        return sample_method;
    }

    public void setSample_method(String sample_method) {
        this.sample_method = sample_method;
    }

    public String getSample_biosample_name() {
        return sample_biosample_name;
    }

    public void setSample_biosample_name(String sample_biosample_name) {
        this.sample_biosample_name = sample_biosample_name;
    }

    @Override
    public String toString() {
        return tf + '\t' +
                gene + '\t' +
                fc + '\t' +
                log2fc + '\t' +
                rank + '\t' +
                p_value + '\t' +
                up_down + '\t' +
                sample_source + '\t' +
                sample_experiment_accession + '\t' +
                sample_method + '\t' +
                sample_biosample_name;
    }
}
