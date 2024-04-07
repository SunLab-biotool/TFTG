package com.lcq.tftgdb.pojo;

public class GeneInfo {
    private String geneName;
    private String geneID;
    private String geneBiotype;
    private String geneSource;
    private String chr;
    private String start;
    private String end;
    private String strand;

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getGeneID() {
        return geneID;
    }

    public void setGeneID(String geneID) {
        this.geneID = geneID;
    }

    public String getGeneBiotype() {
        return geneBiotype;
    }

    public void setGeneBiotype(String geneBiotype) {
        this.geneBiotype = geneBiotype;
    }

    public String getGeneSource() {
        return geneSource;
    }

    public void setGeneSource(String geneSource) {
        this.geneSource = geneSource;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    @Override
    public String toString() {
        return "GeneInfo{" +
                "geneName='" + geneName + '\'' +
                ", geneID='" + geneID + '\'' +
                ", geneBiotype='" + geneBiotype + '\'' +
                ", geneSource='" + geneSource + '\'' +
                ", chr='" + chr + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", strand='" + strand + '\'' +
                '}';
    }
}
