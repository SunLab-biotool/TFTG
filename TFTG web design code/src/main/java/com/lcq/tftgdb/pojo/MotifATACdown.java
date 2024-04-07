package com.lcq.tftgdb.pojo;

public class MotifATACdown {

    private String tf_name;
    private String tf_motif_chr;
    private String tf_motif_start;
    private String tf_motif_end;
    private String atac_chr;
    private String atac_start;
    private String atac_end;
    private String cell_line;
    private String atac_source;
    private String motif_id;
    private String q_value;
    private String p_value;
    private String sequence;
    private String overlap_gene;
    private String proximal_gene;
    private String closest_gene;

    public MotifATACdown(String tf_name, String tf_motif_chr, String tf_motif_start, String tf_motif_end, String atac_chr, String atac_start, String atac_end, String cell_line, String atac_source, String motif_id, String q_value, String p_value, String sequence, String overlap_gene, String proximal_gene, String closest_gene) {
        this.tf_name = tf_name;
        this.tf_motif_chr = tf_motif_chr;
        this.tf_motif_start = tf_motif_start;
        this.tf_motif_end = tf_motif_end;
        this.atac_chr = atac_chr;
        this.atac_start = atac_start;
        this.atac_end = atac_end;
        this.cell_line = cell_line;
        this.atac_source = atac_source;
        this.motif_id = motif_id;
        this.q_value = q_value;
        this.p_value = p_value;
        this.sequence = sequence;
        this.overlap_gene = overlap_gene;
        this.proximal_gene = proximal_gene;
        this.closest_gene = closest_gene;
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

    public String getCell_line() {
        return cell_line;
    }

    public void setCell_line(String cell_line) {
        this.cell_line = cell_line;
    }

    public String getAtac_source() {
        return atac_source;
    }

    public void setAtac_source(String atac_source) {
        this.atac_source = atac_source;
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
        return tf_name + '\t' +
                tf_motif_chr + '\t' +
                tf_motif_start + '\t' +
                tf_motif_end + '\t' +
                atac_chr + '\t' +
                atac_start + '\t' +
                atac_end + '\t' +
                cell_line + '\t' +
                atac_source + '\t' +
                motif_id + '\t' +
                q_value + '\t' +
                p_value + '\t' +
                sequence + '\t' +
                overlap_gene + '\t' +
                proximal_gene + '\t' +
                closest_gene;
    }
}
