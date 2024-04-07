package com.lcq.tftgdb.pojo;

public class ItemStyle {
    private Normal normal;

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    @Override
    public String toString() {
        return "ItemStyle{" +
                "normal=" + normal +
                '}';
    }
}
