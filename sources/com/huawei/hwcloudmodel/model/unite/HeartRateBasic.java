package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class HeartRateBasic {
    private int avgRestingHeartRate;
    private int lastHeartRate;
    private int lastRestHeartRate;
    private int maxHeartRate;
    private int minHeartRate;

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public int getMinHeartRate() {
        return this.minHeartRate;
    }

    public void setMinHeartRate(int i) {
        this.minHeartRate = i;
    }

    public int getAvgRestingHeartRate() {
        return this.avgRestingHeartRate;
    }

    public void setAvgRestingHeartRate(int i) {
        this.avgRestingHeartRate = i;
    }

    public int getLastHeartRate() {
        return this.lastHeartRate;
    }

    public void setLastHeartRate(int i) {
        this.lastHeartRate = i;
    }

    public int getLastRestHeartRate() {
        return this.lastRestHeartRate;
    }

    public void setLastRestHeartRate(int i) {
        this.lastRestHeartRate = i;
    }

    public String toString() {
        return "HeartRateBasic{maxHeartRate=" + this.maxHeartRate + ", minHeartRate=" + this.minHeartRate + ", avgRestingHeartRate=" + this.avgRestingHeartRate + ", lastHeartRate=" + this.lastHeartRate + ", lastRestHeartRate=" + this.lastRestHeartRate + "'}";
    }
}
