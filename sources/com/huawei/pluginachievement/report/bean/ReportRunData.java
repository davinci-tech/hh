package com.huawei.pluginachievement.report.bean;

/* loaded from: classes6.dex */
public class ReportRunData extends ReportDataBean {
    private float mBestPace;
    private float mBritishBestPace;
    private double mBritishFiveKmMinTime;
    private int mBritishFullMarathonCount;
    private double mBritishFullMarathonMinTime;
    private int mBritishHalfMarathonCount;
    private double mBritishHalfMarathonMinTime;
    private double mBritishTenKmMinTime;
    private double mBritishThreeKmMinTime;
    private double mFiveKmMinTime;
    private int mFullMarathonCount;
    private double mFullMarathonMinTime;
    private int mHalfMarathonCount;
    private double mHalfMarathonMinTime;
    private int mMaxDistance;
    private int mSumDistance;
    private double mTenKmMinTime;
    private double mThreeKmMinTime;

    public ReportRunData(int i) {
        super(258, i, 2008);
        this.mBestPace = Float.MAX_VALUE;
        this.mBritishBestPace = Float.MAX_VALUE;
        this.mThreeKmMinTime = Double.MAX_VALUE;
        this.mFiveKmMinTime = Double.MAX_VALUE;
        this.mTenKmMinTime = Double.MAX_VALUE;
        this.mHalfMarathonMinTime = Double.MAX_VALUE;
        this.mFullMarathonMinTime = Double.MAX_VALUE;
        this.mBritishThreeKmMinTime = Double.MAX_VALUE;
        this.mBritishFiveKmMinTime = Double.MAX_VALUE;
        this.mBritishTenKmMinTime = Double.MAX_VALUE;
        this.mBritishHalfMarathonMinTime = Double.MAX_VALUE;
        this.mBritishFullMarathonMinTime = Double.MAX_VALUE;
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

    public void setBestPace(float f) {
        this.mBestPace = f;
    }

    public float getBestPace() {
        return this.mBestPace;
    }

    public void setBritishBestPace(float f) {
        this.mBritishBestPace = f;
    }

    public float getBritishBestPace() {
        return this.mBritishBestPace;
    }

    public void setThreeKmMinTime(double d) {
        this.mThreeKmMinTime = d;
    }

    public double getThreeKmMinTime() {
        return this.mThreeKmMinTime;
    }

    public void setFiveKmMinTime(double d) {
        this.mFiveKmMinTime = d;
    }

    public double getFiveKmMinTime() {
        return this.mFiveKmMinTime;
    }

    public void setTenKmMinTime(double d) {
        this.mTenKmMinTime = d;
    }

    public double getTenKmMinTime() {
        return this.mTenKmMinTime;
    }

    public void setHalfMarathonCount(int i) {
        this.mHalfMarathonCount = i;
    }

    public int getHalfMarathonCount() {
        return this.mHalfMarathonCount;
    }

    public void setFullMarathonCount(int i) {
        this.mFullMarathonCount = i;
    }

    public int getFullMarathonCount() {
        return this.mFullMarathonCount;
    }

    public void setHalfMarathonMinTime(double d) {
        this.mHalfMarathonMinTime = d;
    }

    public double getHalfMarathonMinTime() {
        return this.mHalfMarathonMinTime;
    }

    public void setFullMarathonMinTime(double d) {
        this.mFullMarathonMinTime = d;
    }

    public double getFullMarathonMinTime() {
        return this.mFullMarathonMinTime;
    }

    public void setBritishThreeKmMinTime(double d) {
        this.mBritishThreeKmMinTime = d;
    }

    public double getBritishThreeKmMinTime() {
        return this.mBritishThreeKmMinTime;
    }

    public void setBritishFiveKmMinTime(double d) {
        this.mBritishFiveKmMinTime = d;
    }

    public double getBritishFiveKmMinTime() {
        return this.mBritishFiveKmMinTime;
    }

    public void setBritishTenKmMinTime(double d) {
        this.mBritishTenKmMinTime = d;
    }

    public double getBritishTenKmMinTime() {
        return this.mBritishTenKmMinTime;
    }

    public void setBritishHalfMarathonCount(int i) {
        this.mBritishHalfMarathonCount = i;
    }

    public int getBritishHalfMarathonCount() {
        return this.mBritishHalfMarathonCount;
    }

    public void setBritishFullMarathonCount(int i) {
        this.mBritishFullMarathonCount = i;
    }

    public int getBritishFullMarathonCount() {
        return this.mBritishFullMarathonCount;
    }

    public void setBritishHalfMarathonMinTime(double d) {
        this.mBritishHalfMarathonMinTime = d;
    }

    public double getBritishHalfMarathonMinTime() {
        return this.mBritishHalfMarathonMinTime;
    }

    public void setBritishFullMarathonMinTime(double d) {
        this.mBritishFullMarathonMinTime = d;
    }

    public double getBritishFullMarathonMinTime() {
        return this.mBritishFullMarathonMinTime;
    }
}
