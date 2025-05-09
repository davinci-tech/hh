package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class BloodOxygenSaturationBasic {
    private double avgSaturation;
    private double lastSaturation;
    private double maxSaturation;
    private double minSaturation;

    public String toString() {
        return "BloodOxygenSaturationBasic{maxSaturation=" + this.maxSaturation + ", minSaturation=" + this.minSaturation + ", avgSaturation=" + this.avgSaturation + ", lastSaturation=" + this.lastSaturation + '}';
    }

    public double getMaxSaturation() {
        return this.maxSaturation;
    }

    public void setMaxSaturation(double d) {
        this.maxSaturation = d;
    }

    public double getMinSaturation() {
        return this.minSaturation;
    }

    public void setMinSaturation(double d) {
        this.minSaturation = d;
    }

    public double getAvgSaturation() {
        return this.avgSaturation;
    }

    public void setAvgSaturation(double d) {
        this.avgSaturation = d;
    }

    public double getLastSaturation() {
        return this.lastSaturation;
    }

    public void setLastSaturation(double d) {
        this.lastSaturation = d;
    }
}
