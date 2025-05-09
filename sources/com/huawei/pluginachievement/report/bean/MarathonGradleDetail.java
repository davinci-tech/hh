package com.huawei.pluginachievement.report.bean;

/* loaded from: classes6.dex */
public class MarathonGradleDetail {
    private double bestRecord;
    private long bestTime;
    private double firstRecord;
    private long firstTime;
    private int isRefresh;
    private int times;

    public MarathonGradleDetail(double d, long j, int i, int i2) {
        this.bestRecord = d;
        this.firstTime = j;
        this.times = i;
        this.isRefresh = i2;
    }

    public long getBestTime() {
        return this.bestTime;
    }

    public void setBestTime(long j) {
        this.bestTime = j;
    }

    public double getBestRecord() {
        return this.bestRecord;
    }

    public void setBestRecord(double d) {
        this.bestRecord = d;
    }

    public long getFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(long j) {
        this.firstTime = j;
    }

    public void setFirstRecord(double d) {
        this.firstRecord = d;
    }

    public int getTimes() {
        return this.times;
    }

    public void setTimes(int i) {
        this.times = i;
    }

    public String toString() {
        return "MarathonGradleDetail{bestTime=" + this.bestTime + ", bestRecord=" + this.bestRecord + ", firstTime=" + this.firstTime + ", firstRecord=" + this.firstRecord + ", times=" + this.times + ", isRefresh=" + this.isRefresh + '}';
    }
}
