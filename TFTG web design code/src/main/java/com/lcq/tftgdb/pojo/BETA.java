package com.lcq.tftgdb.pojo;

public class BETA {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String refseqID;
    private String distance;
    private String score;
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

    public String getRefseqID() {
        return refseqID;
    }

    public void setRefseqID(String refseqID) {
        this.refseqID = refseqID;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
        return "BETA{" +
                "sampleID='" + sampleID + '\'' +
                ", tf_chr='" + tf_chr + '\'' +
                ", tf_start='" + tf_start + '\'' +
                ", tf_end='" + tf_end + '\'' +
                ", refseqID='" + refseqID + '\'' +
                ", distance='" + distance + '\'' +
                ", score='" + score + '\'' +
                ", tf_name='" + tf_name + '\'' +
                ", biosample_name_specific='" + biosample_name_specific + '\'' +
                ", gene='" + gene + '\'' +
                '}';
    }
}
