package com.lcq.tftgdb.pojo;

public class TF_All {
    private String symbol;
    private String geneID;
    private String ensembl;
    private String name;
    private String family;
    private String sample_num;
    private String disease;
    private String goterm;
    private String pathway;
    private String tfClass;
    private String hallmark;
    private String protein;
    private String proteinSplit;
    private String tf;
    private String gene;
    private String method;
    private String weight;

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getProteinSplit() {
        return proteinSplit;
    }

    public void setProteinSplit(String proteinSplit) {
        this.proteinSplit = proteinSplit;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "TF_All{" +
                "symbol='" + symbol + '\'' +
                ", geneID='" + geneID + '\'' +
                ", ensembl='" + ensembl + '\'' +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", sample_num='" + sample_num + '\'' +
                ", disease='" + disease + '\'' +
                ", goterm='" + goterm + '\'' +
                ", pathway='" + pathway + '\'' +
                ", tfClass='" + tfClass + '\'' +
                ", hallmark='" + hallmark + '\'' +
                ", protein='" + protein + '\'' +
                ", proteinSplit='" + proteinSplit + '\'' +
                ", tf='" + tf + '\'' +
                ", gene='" + gene + '\'' +
                ", method='" + method + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }

    public String getTfClass() {
        return tfClass;
    }

    public void setTfClass(String tfClass) {
        this.tfClass = tfClass;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getGeneID() {
        return geneID;
    }

    public void setGeneID(String geneID) {
        this.geneID = geneID;
    }

    public String getEnsembl() {
        return ensembl;
    }

    public void setEnsembl(String ensembl) {
        this.ensembl = ensembl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSample_num() {
        return sample_num;
    }

    public void setSample_num(String sample_num) {
        this.sample_num = sample_num;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getGoterm() {
        return goterm;
    }

    public void setGoterm(String goterm) {
        this.goterm = goterm;
    }

    public String getPathway() {
        return pathway;
    }

    public void setPathway(String pathway) {
        this.pathway = pathway;
    }

    public String getHallmark() {
        return hallmark;
    }

    public void setHallmark(String hallmark) {
        this.hallmark = hallmark;
    }
}
