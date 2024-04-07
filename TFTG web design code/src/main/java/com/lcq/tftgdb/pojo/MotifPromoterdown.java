package com.lcq.tftgdb.pojo;

public class MotifPromoterdown {
    private String tf_motif_chr;
    private String tf_name;
    private String tf_motif_start;
    private String tf_motif_end;
    private String pro_chr;
    private String pro_start;
    private String pro_end;
    private String motif_id;
    private String q_value;
    private String p_value;
    private String sequence;
    private String strand;
    private String gene;

    public MotifPromoterdown(String tf_motif_chr, String tf_name, String tf_motif_start, String tf_motif_end, String pro_chr, String pro_start, String pro_end, String motif_id, String q_value, String p_value, String sequence, String strand, String gene) {
        this.tf_motif_chr = tf_motif_chr;
        this.tf_name = tf_name;
        this.tf_motif_start = tf_motif_start;
        this.tf_motif_end = tf_motif_end;
        this.pro_chr = pro_chr;
        this.pro_start = pro_start;
        this.pro_end = pro_end;
        this.motif_id = motif_id;
        this.q_value = q_value;
        this.p_value = p_value;
        this.sequence = sequence;
        this.strand = strand;
        this.gene = gene;
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

    public String getPro_chr() {
        return pro_chr;
    }

    public void setPro_chr(String pro_chr) {
        this.pro_chr = pro_chr;
    }

    public String getPro_start() {
        return pro_start;
    }

    public void setPro_start(String pro_start) {
        this.pro_start = pro_start;
    }

    public String getPro_end() {
        return pro_end;
    }

    public void setPro_end(String pro_end) {
        this.pro_end = pro_end;
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

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    @Override
    public String toString() {
        return tf_motif_chr + '\t' +
                tf_name + '\t' +
                tf_motif_start + '\t' +
                tf_motif_end + '\t' +
                pro_chr + '\t' +
                pro_start + '\t' +
                pro_end + '\t' +
                motif_id + '\t' +
                q_value + '\t' +
                p_value + '\t' +
                sequence + '\t' +
                strand + '\t' +
                gene;
    }
}
