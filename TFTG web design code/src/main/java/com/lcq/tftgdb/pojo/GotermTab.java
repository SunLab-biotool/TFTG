package com.lcq.tftgdb.pojo;

public class GotermTab {
    private String tf;
    private String go_term;

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getGo_term() {
        return go_term;
    }

    public void setGo_term(String go_term) {
        this.go_term = go_term;
    }

    @Override
    public String toString() {
        return "GotermTab{" +
                "tf='" + tf + '\'' +
                ", go_term='" + go_term + '\'' +
                '}';
    }
}
