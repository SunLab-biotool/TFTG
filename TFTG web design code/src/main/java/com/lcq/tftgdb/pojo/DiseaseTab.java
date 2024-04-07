package com.lcq.tftgdb.pojo;

public class DiseaseTab {
    private String tf;
    private String diseasename;
    private String diseasetype;
    private String diseaseclass;
    private String diseasesemantictype;
    private String source;

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename;
    }

    public String getDiseasetype() {
        return diseasetype;
    }

    public void setDiseasetype(String diseasetype) {
        this.diseasetype = diseasetype;
    }

    public String getDiseaseclass() {
        return diseaseclass;
    }

    public void setDiseaseclass(String diseaseclass) {
        this.diseaseclass = diseaseclass;
    }

    public String getDiseasesemantictype() {
        return diseasesemantictype;
    }

    public void setDiseasesemantictype(String diseasesemantictype) {
        this.diseasesemantictype = diseasesemantictype;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "DiseaseTab{" +
                "tf='" + tf + '\'' +
                ", diseasename='" + diseasename + '\'' +
                ", diseasetype='" + diseasetype + '\'' +
                ", diseaseclass='" + diseaseclass + '\'' +
                ", diseasesemantictype='" + diseasesemantictype + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
