package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class Crop {

    @SerializedName("lower_left_x")
    @Expose
    private int lowerLeftX;

    @SerializedName("lower_left_y")
    @Expose
    private int lowerLeftY;

    @SerializedName("lower_right_x")
    @Expose
    private int lowerRightX;

    @SerializedName("lower_right_y")
    @Expose
    private int lowerRightY;

    @SerializedName("upper_left_x")
    @Expose
    private int upperLeftX;

    @SerializedName("upper_left_y")
    @Expose
    private int upperLeftY;

    @SerializedName("upper_right_x")
    @Expose
    private int upperRightX;

    @SerializedName("upper_right_y")
    @Expose
    private int upperRightY;

    public void setUpperLeftX(int i) {
        this.upperLeftX = i;
    }

    public int getUpperLeftX() {
        return this.upperLeftX;
    }

    public Crop withUpperLeftX(int i) {
        this.upperLeftX = i;
        return this;
    }

    public void setUpperLeftY(int i) {
        this.upperLeftY = i;
    }

    public int getUpperLeftY() {
        return this.upperLeftY;
    }

    public Crop withUpperLeftY(int i) {
        this.upperLeftY = i;
        return this;
    }

    public void setUpperRightX(int i) {
        this.upperRightX = i;
    }

    public int getUpperRightX() {
        return this.upperRightX;
    }

    public Crop withUpperRightX(int i) {
        this.upperRightX = i;
        return this;
    }

    public void setUpperRightY(int i) {
        this.upperRightY = i;
    }

    public int getUpperRightY() {
        return this.upperRightY;
    }

    public Crop withUpperRightY(int i) {
        this.upperRightY = i;
        return this;
    }

    public void setLowerLeftX(int i) {
        this.lowerLeftX = i;
    }

    public int getLowerLeftX() {
        return this.lowerLeftX;
    }

    public Crop withLowerLeftX(int i) {
        this.lowerLeftX = i;
        return this;
    }

    public void setLowerLeftY(int i) {
        this.lowerLeftY = i;
    }

    public int getLowerLeftY() {
        return this.lowerLeftY;
    }

    public Crop withLowerLeftY(int i) {
        this.lowerLeftY = i;
        return this;
    }

    public void setLowerRightX(int i) {
        this.lowerRightX = i;
    }

    public int getLowerRightX() {
        return this.lowerRightX;
    }

    public Crop withLowerRightX(int i) {
        this.lowerRightX = i;
        return this;
    }

    public void setLowerRightY(int i) {
        this.lowerRightY = i;
    }

    public int getLowerRightY() {
        return this.lowerRightY;
    }

    public Crop withLowerRightY(int i) {
        this.lowerRightY = i;
        return this;
    }
}
