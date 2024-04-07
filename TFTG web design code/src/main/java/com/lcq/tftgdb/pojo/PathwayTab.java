package com.lcq.tftgdb.pojo;

public class PathwayTab {
    private String tf;
    private String pathway_name;
    private String pathway_source;
    private String gene_number;
    private String edge_number;

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
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

    @Override
    public String toString() {
        return "PathwayTab{" +
                "tf='" + tf + '\'' +
                ", pathway_name='" + pathway_name + '\'' +
                ", pathway_source='" + pathway_source + '\'' +
                ", gene_number='" + gene_number + '\'' +
                ", edge_number='" + edge_number + '\'' +
                '}';
    }
}
