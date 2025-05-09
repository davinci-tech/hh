package com.huawei.hwcommonmodel.fitnessdatatype;

/* loaded from: classes9.dex */
public class HeartRateSummary {
    private int maxHR = 0;
    private int minHR = 0;
    private int lastHR = 0;
    private int avgRestHR = 0;

    public int getMaxHeartRate() {
        return this.maxHR;
    }

    public void setMaxHeartRate(int i) {
        this.maxHR = i;
    }

    public int getMinHeartRate() {
        return this.minHR;
    }

    public void setMinHeartRate(int i) {
        this.minHR = i;
    }

    public int getAvgRestHeartRate() {
        return this.avgRestHR;
    }

    public void setAvgRestHeartRate(int i) {
        this.avgRestHR = i;
    }

    public String toString() {
        return "HeartRateSummary{maxHR=" + this.maxHR + ", minHR=" + this.minHR + ", lastHR=" + this.lastHR + ", avgRestHR=" + this.avgRestHR + '}';
    }
}
