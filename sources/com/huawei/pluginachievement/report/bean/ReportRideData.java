package com.huawei.pluginachievement.report.bean;

/* loaded from: classes6.dex */
public class ReportRideData extends ReportDataBean {
    private double mBestPace;
    private double mMaxBritishSpeed;
    private int mMaxDistance;
    private int mSumCreepingWave;
    private float mSumDescent;
    private int mSumDistance;

    public ReportRideData(int i) {
        super(259, i, 2006);
    }

    public void setSumDistance(int i) {
        this.mSumDistance = i;
    }

    public int getSumDistance() {
        return this.mSumDistance;
    }

    public void setMaxDistance(int i) {
        this.mMaxDistance = i;
    }

    public int getMaxDistance() {
        return this.mMaxDistance;
    }

    public void setSumCreepingWave(int i) {
        this.mSumCreepingWave = i;
    }

    public int getSumCreepingWave() {
        return this.mSumCreepingWave;
    }

    public void setSumDescent(float f) {
        this.mSumDescent = f;
    }

    public float getSumDescent() {
        return this.mSumDescent;
    }

    public void setBestPace(double d) {
        this.mBestPace = d;
    }

    public double getBestPace() {
        return this.mBestPace;
    }

    public void setMaxBritishSpeed(double d) {
        this.mMaxBritishSpeed = d;
    }

    public double getMaxBritishSpeed() {
        return this.mMaxBritishSpeed;
    }
}
