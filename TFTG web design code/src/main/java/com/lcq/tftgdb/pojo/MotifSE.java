package com.lcq.tftgdb.pojo;

public class MotifSE {

    private String tf_name;
    private String tf_motif_chr;
    private String tf_motif_start;
    private String tf_motif_end;
    private String se_chr;
    private String se_start;
    private String se_end;
    private String cell_line;
    private String se_source;
    private String motif_id;
    private String q_value;
    private String p_value;
    private String sequence;
    private String overlap_gene;
    private String proximal_gene;
    private String closest_gene;

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

    public String getTf_motif_chr() {
        return tf_motif_chr;
    }

    public void setTf_motif_chr(String tf_motif_chr) {
        this.tf_motif_chr = tf_motif_chr;
    }

    public String getTf_name() {
        return tf_name;
    }

    public void setTf_name(String tf_name) {
        this.tf_name = tf_name;
    }

    public String getTf_motif_start() {
        return tf_motif_start;
    }

    public void setTf_motif_start(String tf_motif_start) {
        this.tf_motif_start = tf_motif_start;
    }

    public String getTf_motif_end() {
        return tf_motif_end;
    }

    public void setTf_motif_end(String tf_motif_end) {
        this.tf_motif_end = tf_motif_end;
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

    public String getCell_line() {
        return cell_line;
    }

    public void setCell_line(String cell_line) {
        this.cell_line = cell_line;
    }

    public String getSe_source() {
        return se_source;
    }

    public void setSe_source(String se_source) {
        this.se_source = se_source;
    }

    public String getMotif_id() {
        return motif_id;
    }

    public void setMotif_id(String motif_id) {
        this.motif_id = motif_id;
    }

    public String getQ_value() {
        return q_value;
    }

    public void setQ_value(String q_value) {
        this.q_value = q_value;
    }

    public String getP_value() {
        return p_value;
    }

    public void setP_value(String p_value) {
        this.p_value = p_value;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "MotifSE{" +
                "tf_name='" + tf_name + '\'' +
                ", tf_motif_chr='" + tf_motif_chr + '\'' +
                ", tf_motif_start='" + tf_motif_start + '\'' +
                ", tf_motif_end='" + tf_motif_end + '\'' +
                ", se_chr='" + se_chr + '\'' +
                ", se_start='" + se_start + '\'' +
                ", se_end='" + se_end + '\'' +
                ", cell_line='" + cell_line + '\'' +
                ", se_source='" + se_source + '\'' +
                ", motif_id='" + motif_id + '\'' +
                ", q_value='" + q_value + '\'' +
                ", p_value='" + p_value + '\'' +
                ", sequence='" + sequence + '\'' +
                ", overlap_gene='" + overlap_gene + '\'' +
                ", proximal_gene='" + proximal_gene + '\'' +
                ", closest_gene='" + closest_gene + '\'' +
                '}';
    }
}
