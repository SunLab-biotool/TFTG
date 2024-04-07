package com.lcq.tftgdb.pojo;

public class Multselect {
    private String label;
    private String value;
    private Boolean selected;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Multselect{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", selected=" + selected +
                '}';
    }
}