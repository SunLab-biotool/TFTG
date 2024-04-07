package com.lcq.tftgdb.pojo;

public class Analysis1Result {
    private String tf;
    private String sampleid;
    private String method;
    private String biosample_name;
    private String core_enrichment;
    private String genenumber;
    private String pvalue;
    private String fdr;

    public String getSampleid() {
        return sampleid;
    }

    public void setSampleid(String sampleid) {
        this.sampleid = sampleid;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBiosample_name() {
        return biosample_name;
    }

    public void setBiosample_name(String biosample_name) {
        this.biosample_name = biosample_name;
    }

    public String getCore_enrichment() {
        return core_enrichment;
    }

    public void setCore_enrichment(String core_enrichment) {
        this.core_enrichment = core_enrichment;
    }

    public String getGenenumber() {
        return genenumber;
    }

    public void setGenenumber(String genenumber) {
        this.genenumber = genenumber;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public String getFdr() {
        return fdr;
    }

    public void setFdr(String fdr) {
        this.fdr = fdr;
    }

    @Override
    public String toString() {
        return "Analysis1Result{" +
                "tf='" + tf + '\'' +
                ", sampleid='" + sampleid + '\'' +
                ", method='" + method + '\'' +
                ", biosample_name='" + biosample_name + '\'' +
                ", core_enrichment='" + core_enrichment + '\'' +
                ", genenumber='" + genenumber + '\'' +
                ", pvalue='" + pvalue + '\'' +
                ", fdr='" + fdr + '\'' +
                '}';
    }
}
