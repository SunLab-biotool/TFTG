package com.lcq.tftgdb.pojo;

import java.io.Serializable;

public class TfMethodWeigdown implements Serializable {
    private String gene;
    private String genemapper;
    private String beta;
    private String se;
    private String te;
    private String atac;
    private String silencer;
    private String promoter;
    private String knock;
    private String m_se;
    private String m_te;
    private String m_atac;
    private String m_silencer;
    private String m_promoter;
    private String curate;
    private String weight;

    public TfMethodWeigdown() {
        super();
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getGenemapper() {
        return genemapper;
    }

    public void setGenemapper(String genemapper) {
        this.genemapper = genemapper;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    public String getAtac() {
        return atac;
    }

    public void setAtac(String atac) {
        this.atac = atac;
    }

    public String getSilencer() {
        return silencer;
    }

    public void setSilencer(String silencer) {
        this.silencer = silencer;
    }

    public String getPromoter() {
        return promoter;
    }

    public void setPromoter(String promoter) {
        this.promoter = promoter;
    }

    public String getKnock() {
        return knock;
    }

    public void setKnock(String knock) {
        this.knock = knock;
    }

    public String getM_se() {
        return m_se;
    }

    public void setM_se(String m_se) {
        this.m_se = m_se;
    }

    public String getM_te() {
        return m_te;
    }

    public void setM_te(String m_te) {
        this.m_te = m_te;
    }

    public String getM_atac() {
        return m_atac;
    }

    public void setM_atac(String m_atac) {
        this.m_atac = m_atac;
    }

    public String getM_silencer() {
        return m_silencer;
    }

    public void setM_silencer(String m_silencer) {
        this.m_silencer = m_silencer;
    }

    public String getM_promoter() {
        return m_promoter;
    }

    public void setM_promoter(String m_promoter) {
        this.m_promoter = m_promoter;
    }

    public String getCurate() {
        return curate;
    }

    public void setCurate(String curate) {
        this.curate = curate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public TfMethodWeigdown(String gene, String genemapper, String beta, String se, String te, String atac, String silencer, String promoter, String knock, String m_se, String m_te, String m_atac, String m_silencer, String m_promoter, String curate, String weight) {
        this.gene = gene;
        this.genemapper = genemapper;
        this.beta = beta;
        this.se = se;
        this.te = te;
        this.atac = atac;
        this.silencer = silencer;
        this.promoter = promoter;
        this.knock = knock;
        this.m_se = m_se;
        this.m_te = m_te;
        this.m_atac = m_atac;
        this.m_silencer = m_silencer;
        this.m_promoter = m_promoter;
        this.curate = curate;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return gene + '\t' +
                genemapper + '\t' +
                beta + '\t' +
                se + '\t' +
                te + '\t' +
                atac + '\t' +
                silencer + '\t' +
                promoter + '\t' +
                knock + '\t' +
                m_se + '\t' +
                m_te + '\t' +
                m_atac + '\t' +
                m_silencer + '\t' +
                m_promoter + '\t' +
                curate + '\t' +
                weight;
    }
}
