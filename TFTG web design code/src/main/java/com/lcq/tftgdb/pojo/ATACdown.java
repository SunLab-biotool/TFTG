package com.lcq.tftgdb.pojo;

public class ATACdown {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String atac_chr;
    private String atac_start;
    private String atac_end;
    private String atac_source;
    private String rol;
    private String overlap_gene;
    private String proximal_gene;
    private String closest_gene;
    private String cell_line;

    public ATACdown(String sampleID, String tf_chr, String tf_start, String tf_end, String atac_chr, String atac_start, String atac_end, String atac_source, String rol, String overlap_gene, String proximal_gene, String closest_gene, String cell_line) {
        this.sampleID = sampleID;
        this.tf_chr = tf_chr;
        this.tf_start = tf_start;
        this.tf_end = tf_end;
        this.atac_chr = atac_chr;
        this.atac_start = atac_start;
        this.atac_end = atac_end;
        this.atac_source = atac_source;
        this.rol = rol;
        this.overlap_gene = overlap_gene;
        this.proximal_gene = proximal_gene;
        this.closest_gene = closest_gene;
        this.cell_line = cell_line;
    }

    public String getCell_line() {
        return cell_line;
    }

    public void setCell_line(String cell_line) {
        this.cell_line = cell_line;
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

    public String getAtac_chr() {
        return atac_chr;
    }

    public void setAtac_chr(String atac_chr) {
        this.atac_chr = atac_chr;
    }

    public String getAtac_start() {
        return atac_start;
    }

    public void setAtac_start(String atac_start) {
        this.atac_start = atac_start;
    }

    public String getAtac_end() {
        return atac_end;
    }

    public void setAtac_end(String atac_end) {
        this.atac_end = atac_end;
    }

    public String getAtac_source() {
        return atac_source;
    }

    public void setAtac_source(String atac_source) {
        this.atac_source = atac_source;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getOverlap_gene() {
        return overlap_gene;
    }

    public void setOverlap_gene(String overlap_gene) {
        this.overlap_gene = overlap_gene;
    }

    public String getProximal_gene() {
        return proximal_gene;
    }

    public void setProximal_gene(String proximal_gene) {
        this.proximal_gene = proximal_gene;
    }

    public String getClosest_gene() {
        return closest_gene;
    }

    public void setClosest_gene(String closest_gene) {
        this.closest_gene = closest_gene;
    }

    @Override
    public String toString() {
        return sampleID + '\t' +
                tf_chr + '\t' +
                tf_start + '\t' +
                tf_end + '\t' +
                atac_chr + '\t' +
                atac_start + '\t' +
                atac_end + '\t' +
                atac_source + '\t' +
                rol + '\t' +
                overlap_gene + '\t' +
                proximal_gene + '\t' +
                closest_gene;
    }
}
