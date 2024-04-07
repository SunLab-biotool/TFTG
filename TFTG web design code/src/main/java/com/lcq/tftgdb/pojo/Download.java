package com.lcq.tftgdb.pojo;

public class Download {
    private String id;
    private String source;
    private String bioname;
    private String method;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBioname() {
        return bioname;
    }

    public void setBioname(String bioname) {
        this.bioname = bioname;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Download{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", bioname='" + bioname + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
