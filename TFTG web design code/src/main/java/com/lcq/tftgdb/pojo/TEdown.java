package com.lcq.tftgdb.pojo;

public class TEdown {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String te_chr;
    private String te_start;
    private String te_end;
    private String te_source;
    private String rol;
    private String overlap_gene;
    private String proximal_gene;
    private String closest_gene;
    private String cell_line;

    public TEdown(String sampleID, String tf_chr, String tf_start, String tf_end, String te_chr, String te_start, String te_end, String te_source, String rol, String overlap_gene, String proximal_gene, String closest_gene, String cell_line) {
        this.sampleID = sampleID;
        this.tf_chr = tf_chr;
        this.tf_start = tf_start;
        this.tf_end = tf_end;
        this.te_chr = te_chr;
        this.te_start = te_start;
        this.te_end = te_end;
        this.te_source = te_source;
        this.rol = rol;
        this.overlap_gene = overlap_gene;
        this.proximal_gene = proximal_gene;
        this.closest_gene = closest_gene;
        this.cell_line = cell_line;
    }

    @Override
    public String toString() {
        return sampleID + '\t' +
                tf_chr + '\t' +
                tf_start + '\t' +
                tf_end + '\t' +
                te_chr + '\t' +
                te_start + '\t' +
                te_end + '\t' +
                te_source + '\t' +
                rol + '\t' +
                overlap_gene + '\t' +
                proximal_gene + '\t' +
                closest_gene + '\t' +
                cell_line;
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

    public String getTe_chr() {
        return te_chr;
    }

    public void setTe_chr(String te_chr) {
        this.te_chr = te_chr;
    }

    public String getTe_start() {
        return te_start;
    }

    public void setTe_start(String te_start) {
        this.te_start = te_start;
    }

    public String getTe_end() {
        return te_end;
    }

    public void setTe_end(String te_end) {
        this.te_end = te_end;
    }

    public String getTe_source() {
        return te_source;
    }

    public void setTe_source(String te_source) {
        this.te_source = te_source;
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

    public String getCell_line() {
        return cell_line;
    }

    public void setCell_line(String cell_line) {
        this.cell_line = cell_line;
    }
}
