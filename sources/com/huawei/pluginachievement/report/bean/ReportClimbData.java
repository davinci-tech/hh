package com.huawei.pluginachievement.report.bean;

/* loaded from: classes6.dex */
public class ReportClimbData extends ReportDataBean {
    private float mMaxHeight;
    private int mSumDistance;
    private float mSumHeight;

    public ReportClimbData(int i) {
        super(260, i, 2001);
    }

    public void setSumDistance(int i) {
        this.mSumDistance = i;
    }

    public int getSumDistance() {
        return this.mSumDistance;
    }

    public void setSumHeight(float f) {
        this.mSumHeight = f;
    }

    public float getSumHeight() {
        return this.mSumHeight;
    }

    public void setMaxHeight(float f) {
        this.mMaxHeight = f;
    }

    public float getMaxHeight() {
        return this.mMaxHeight;
    }
}
