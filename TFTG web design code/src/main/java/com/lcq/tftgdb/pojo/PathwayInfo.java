package com.lcq.tftgdb.pojo;

public class PathwayInfo {
    private String pathway_ID;
    private String pathway_name;
    private String pathway_source;
    private String gene_number;
    private String edge_number;
    private String geneset;

    public String getPathway_ID() {
        return pathway_ID;
    }

    public void setPathway_ID(String pathway_ID) {
        this.pathway_ID = pathway_ID;
    }

    public String getPathway_name() {
        return pathway_name;
    }

    public void setPathway_name(String pathway_name) {
        this.pathway_name = pathway_name;
    }

    public String getPathway_source() {
        return pathway_source;
    }

    public void setPathway_source(String pathway_source) {
        this.pathway_source = pathway_source;
    }

    public String getGene_number() {
        return gene_number;
    }

    public void setGene_number(String gene_number) {
        this.gene_number = gene_number;
    }

    public String getEdge_number() {
        return edge_number;
    }

    public void setEdge_number(String edge_number) {
        this.edge_number = edge_number;
    }

    public String getGeneset() {
        return geneset;
    }

    public void setGeneset(String geneset) {
        this.geneset = geneset;
    }

    @Override
    public String toString() {
        return pathway_ID + '\t' + pathway_name + '\t' + pathway_source + '\t' + gene_number + '\t' + edge_number + '\t' + geneset;
    }
}
