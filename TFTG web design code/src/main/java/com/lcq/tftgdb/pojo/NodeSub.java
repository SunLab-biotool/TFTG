package com.lcq.tftgdb.pojo;

public class NodeSub {
    private String id;
    private String name;
    private String value;
    private String category;
    private ItemStyle itemStyle;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    @Override
    public String toString() {
        return "NodeSub{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", category='" + category + '\'' +
                ", itemStyle=" + itemStyle +
                '}';
    }
}
