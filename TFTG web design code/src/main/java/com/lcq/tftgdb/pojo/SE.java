package com.lcq.tftgdb.pojo;

public class SE {
    private String sampleID;
    private String tf_chr;
    private String tf_start;
    private String tf_end;
    private String se_chr;
    private String se_start;
    private String se_end;
    private String se_source;
    private String rol;
    private String tf_name;
    private String biosample_name_specific;
    private String overlap_gene;
    private String proximal_gene;
    private String closest_gene;
    private String cell_line;

    @Override
    public String toString() {
        return "SE{" +
                "sampleID='" + sampleID + '\'' +
                ", tf_chr='" + tf_chr + '\'' +
                ", tf_start='" + tf_start + '\'' +
                ", tf_end='" + tf_end + '\'' +
                ", se_chr='" + se_chr + '\'' +
                ", se_start='" + se_start + '\'' +
                ", se_end='" + se_end + '\'' +
                ", se_source='" + se_source + '\'' +
                ", rol='" + rol + '\'' +
                ", tf_name='" + tf_name + '\'' +
                ", biosample_name_specific='" + biosample_name_specific + '\'' +
                ", overlap_gene='" + overlap_gene + '\'' +
                ", proximal_gene='" + proximal_gene + '\'' +
                ", closest_gene='" + closest_gene + '\'' +
                ", cell_line='" + cell_line + '\'' +
                '}';
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
}
