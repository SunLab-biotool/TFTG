package com.lcq.tftgdb.pojo;

public class Gene {
    private String overlap;
    private String proximal;
    private String closest;

    public String getOverlap() {
        return overlap;
    }

    public void setOverlap(String overlap) {
        this.overlap = overlap;
    }

    public String getProximal() {
        return proximal;
    }

    public void setProximal(String proximal) {
        this.proximal = proximal;
    }

    public String getClosest() {
        return closest;
    }

    public void setClosest(String closest) {
        this.closest = closest;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "overlap='" + overlap + '\'' +
                ", proximal='" + proximal + '\'' +
                ", closest='" + closest + '\'' +
                '}';
    }
}
