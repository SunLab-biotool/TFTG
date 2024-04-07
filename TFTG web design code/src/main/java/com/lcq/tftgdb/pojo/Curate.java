package com.lcq.tftgdb.pojo;

public class Curate {
    private String tf;
    private String gene;
    private String source;
    private String pubmedID;
    private String biosample_name;
    private String repression_Activation;

    @Override
    public String toString() {
        return "Curate{" +
                "tf='" + tf + '\'' +
                ", gene='" + gene + '\'' +
                ", source='" + source + '\'' +
                ", pubmedID='" + pubmedID + '\'' +
                ", biosample_name='" + biosample_name + '\'' +
                ", repression_Activation='" + repression_Activation + '\'' +
                '}';
    }

    public String getBiosample_name() {
        return biosample_name;
    }

    public void setBiosample_name(String biosample_name) {
        this.biosample_name = biosample_name;
    }

    public String getRepression_Activation() {
        return repression_Activation;
    }

    public void setRepression_Activation(String repression_Activation) {
        this.repression_Activation = repression_Activation;
    }

    public String getPubmedID() {
        return pubmedID;
    }

    public void setPubmedID(String pubmedID) {
        this.pubmedID = pubmedID;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
