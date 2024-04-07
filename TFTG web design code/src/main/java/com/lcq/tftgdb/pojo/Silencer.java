package com.lcq.tftgdb.pojo;

public class Silencer {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String silencer_chr;
    private String silencer_start;
    private String silencer_end;
    private String silencer_source;
    private String rol;
    private String tf_name;
    private String biosample_name_specific;
    private String overlap_gene;
    private String proximal_gene;
    private String closest_gene;
    private String cell_line;

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

    public String getSilencer_chr() {
        return silencer_chr;
    }

    public void setSilencer_chr(String silencer_chr) {
        this.silencer_chr = silencer_chr;
    }

    public String getSilencer_start() {
        return silencer_start;
    }

    public void setSilencer_start(String silencer_start) {
        this.silencer_start = silencer_start;
    }

    public String getSilencer_end() {
        return silencer_end;
    }

    public void setSilencer_end(String silencer_end) {
        this.silencer_end = silencer_end;
    }

    public String getSilencer_source() {
        return silencer_source;
    }

    public void setSilencer_source(String silencer_source) {
        this.silencer_source = silencer_source;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    @Override
    public String toString() {
        return "Silencer{" +
                "sampleID='" + sampleID + '\'' +
                ", tf_chr='" + tf_chr + '\'' +
                ", tf_start='" + tf_start + '\'' +
                ", tf_end='" + tf_end + '\'' +
                ", silencer_chr='" + silencer_chr + '\'' +
                ", silencer_start='" + silencer_start + '\'' +
                ", silencer_end='" + silencer_end + '\'' +
                ", silencer_source='" + silencer_source + '\'' +
                ", rol='" + rol + '\'' +
                ", tf_name='" + tf_name + '\'' +
                ", biosample_name_specific='" + biosample_name_specific + '\'' +
                ", overlap_gene='" + overlap_gene + '\'' +
                ", proximal_gene='" + proximal_gene + '\'' +
                ", closest_gene='" + closest_gene + '\'' +
                ", cell_line='" + cell_line + '\'' +
                '}';
    }
}
