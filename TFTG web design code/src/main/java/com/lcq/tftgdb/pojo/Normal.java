package com.lcq.tftgdb.pojo;

import javax.swing.*;

public class Normal {
    private String borderColor;
    private String borderWidth;
    private String shadowBlur;
    private String shadowColor;

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(String borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getShadowBlur() {
        return shadowBlur;
    }

    public void setShadowBlur(String shadowBlur) {
        this.shadowBlur = shadowBlur;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
    }

    @Override
    public String toString() {
        return "Normal{" +
                "borderColor='" + borderColor + '\'' +
                ", borderWidth='" + borderWidth + '\'' +
                ", shadowBlur='" + shadowBlur + '\'' +
                ", shadowColor='" + shadowColor + '\'' +
                '}';
    }
}
