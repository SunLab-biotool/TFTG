package com.lcq.tftgdb.pojo;

public class Analysis2ResultTab {
    private String gene;
    private String degree;
    private String betweenness;
    private String closeness;
    private String tfOrNot;
    private String inputOrNot;

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBetweenness() {
        return betweenness;
    }

    public void setBetweenness(String betweenness) {
        this.betweenness = betweenness;
    }

    public String getCloseness() {
        return closeness;
    }

    public void setCloseness(String closeness) {
        this.closeness = closeness;
    }

    public String getTfOrNot() {
        return tfOrNot;
    }

    public void setTfOrNot(String tfOrNot) {
        this.tfOrNot = tfOrNot;
    }

    public String getInputOrNot() {
        return inputOrNot;
    }

    public void setInputOrNot(String inputOrNot) {
        this.inputOrNot = inputOrNot;
    }

    @Override
    public String toString() {
        return "Analysis2ResultTab{" +
                "gene='" + gene + '\'' +
                ", degree='" + degree + '\'' +
                ", betweenness='" + betweenness + '\'' +
                ", closeness='" + closeness + '\'' +
                ", tfOrNot='" + tfOrNot + '\'' +
                ", inputOrNot='" + inputOrNot + '\'' +
                '}';
    }
}
