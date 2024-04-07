package com.lcq.tftgdb.pojo;

public class Node {
    private String id;
    private String name;
    private String symbolSize;
    private Double x;
    private Double y;
    private String value;
    private Integer category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(String symbolSize) {
        this.symbolSize = symbolSize;
    }

    public double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "node{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbolSize='" + symbolSize + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", value='" + value + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
