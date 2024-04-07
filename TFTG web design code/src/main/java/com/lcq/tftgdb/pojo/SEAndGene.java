package com.lcq.tftgdb.pojo;

public class SEAndGene {
    private String se;
    private String gene;

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    @Override
    public String toString() {
        return "SEAndGene{" +
                "se='" + se + '\'' +
                ", gene='" + gene + '\'' +
                '}';
    }
}
