package com.lcq.tftgdb.pojo;

import java.util.Arrays;

public class Datadistance {
    private String name;
    private float[] data;
    private String type;
    private String stack;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Datadistance{" +
                "name='" + name + '\'' +
                ", data=" + Arrays.toString(data) +
                ", type='" + type + '\'' +
                ", stack='" + stack + '\'' +
                '}';
    }
}
