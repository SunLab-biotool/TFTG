package com.lcq.tftgdb.pojo;

public class BrowserParam {
    private String tfFamilyPara;
    private String tfClassPara;
    private String tfNamePara;

    public String getTfClassPara() {
        return tfClassPara;
    }

    public void setTfClassPara(String tfClassPara) {
        this.tfClassPara = tfClassPara;
    }

    public BrowserParam(String tfFamilyPara, String tfClassPara, String tfNamePara) {
        this.tfNamePara = tfNamePara;
        this.tfFamilyPara = tfFamilyPara;
        this.tfClassPara = tfClassPara;
    }

    public String getTfFamilyPara() {
        return tfFamilyPara;
    }

    public void setTfFamilyPara(String tfFamilyPara) {
        this.tfFamilyPara = tfFamilyPara;
    }

    public String getTfNamePara() {
        return tfNamePara;
    }

    public void setTfNamePara(String tfNamePara) {
        this.tfNamePara = tfNamePara;
    }

    @Override
    public String toString() {
        return "BrowserParam{" +
                "tfFamilyPara='" + tfFamilyPara + '\'' +
                ", tfClassPara='" + tfClassPara + '\'' +
                ", tfNamePara='" + tfNamePara + '\'' +
                '}';
    }
}
