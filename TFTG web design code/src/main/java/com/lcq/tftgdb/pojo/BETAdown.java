package com.lcq.tftgdb.pojo;

public class BETAdown {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String refseqID;
    private String distance;
    private String score;
    private String gene;

    public BETAdown(String sampleID, String tf_chr, String tf_start, String tf_end, String refseqID, String distance, String score, String gene) {
        this.sampleID = sampleID;
        this.tf_chr = tf_chr;
        this.tf_start = tf_start;
        this.tf_end = tf_end;
        this.refseqID = refseqID;
        this.distance = distance;
        this.score = score;
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
                refseqID + '\t' +
                distance + '\t' +
                score + '\t' +
                gene;
    }
}
