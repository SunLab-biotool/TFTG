package com.lcq.tftgdb.pojo;

public class Links {
    private String source;
    private String target;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "links{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
