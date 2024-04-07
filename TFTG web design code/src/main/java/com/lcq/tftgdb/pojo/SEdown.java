package com.lcq.tftgdb.pojo;

public class SEdown {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String se_chr;
    private String se_start;
    private String se_end;
    private String se_source;
    private String rol;
    private String overlap_gene;
    private String proximal_gene;
    private String closest_gene;
    private String cell_line;

    public SEdown(String sampleID, String tf_chr, String tf_start, String tf_end, String se_chr, String se_start, String se_end, String se_source, String rol, String overlap_gene, String proximal_gene, String closest_gene, String cell_line) {
        this.sampleID = sampleID;
        this.tf_chr = tf_chr;
        this.tf_start = tf_start;
        this.tf_end = tf_end;
        this.se_chr = se_chr;
        this.se_start = se_start;
        this.se_end = se_end;
        this.se_source = se_source;
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
                se_chr + '\t' +
                se_start + '\t' +
                se_end + '\t' +
                se_source + '\t' +
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

    public String getSe_chr() {
        return se_chr;
    }

    public void setSe_chr(String se_chr) {
        this.se_chr = se_chr;
    }

    public String getSe_start() {
        return se_start;
    }

    public void setSe_start(String se_start) {
        this.se_start = se_start;
    }

    public String getSe_end() {
        return se_end;
    }

    public void setSe_end(String se_end) {
        this.se_end = se_end;
    }

    public String getSe_source() {
        return se_source;
    }

    public void setSe_source(String se_source) {
        this.se_source = se_source;
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
