package com.lcq.tftgdb.pojo;

public class EncodeCate {
    private int sum;
    private String name;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "encodeCate{" +
                "sum=" + sum +
                ", name='" + name + '\'' +
                '}';
    }
}
