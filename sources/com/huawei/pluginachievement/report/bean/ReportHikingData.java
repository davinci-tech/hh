package com.huawei.pluginachievement.report.bean;

/* loaded from: classes6.dex */
public class ReportHikingData extends ReportDataBean {
    private int mMaxDistance;
    private int mSumDistance;

    public ReportHikingData(int i) {
        super(282, i, 2004);
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
}
