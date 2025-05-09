package com.huawei.health.pluginlocation;

/* loaded from: classes3.dex */
public class MagData {
    private double magX;
    private double magY;
    private double magZ;
    private double time;

    public MagData() {
    }

    public MagData(double d, double d2, double d3, double d4) {
        this.time = d;
        this.magX = d2;
        this.magY = d3;
        this.magZ = d4;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double d) {
        this.time = d;
    }

    public double getMagX() {
        return this.magX;
    }

    public void setMagX(double d) {
        this.magX = d;
    }

    public double getMagY() {
        return this.magY;
    }

    public void setMagY(double d) {
        this.magY = d;
    }

    public double getMagZ() {
        return this.magZ;
    }

    public void setMagZ(double d) {
        this.magZ = d;
    }

    public String toString() {
        return this.time + "," + this.magX + "," + this.magY + "," + this.magZ;
    }
}
