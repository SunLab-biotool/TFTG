package com.lcq.tftgdb.pojo;

public class BedtoolsResult {
    private String chr;

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    @Override
    public String toString() {
        return "BedtoolsResult{" +
                "chr='" + chr + '\'' +
                '}';
    }
}
