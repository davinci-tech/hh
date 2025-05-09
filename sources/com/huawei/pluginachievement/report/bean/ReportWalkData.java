package com.huawei.pluginachievement.report.bean;

/* loaded from: classes6.dex */
public class ReportWalkData extends ReportDataBean {
    private int mMaxDistance;
    private int mMaxStepRate;
    private int mSumDistance;

    public ReportWalkData(int i) {
        super(257, i, 2011);
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

    public void setMaxStepRate(int i) {
        this.mMaxStepRate = i;
    }

    public int getMaxStepRate() {
        return this.mMaxStepRate;
    }
}
