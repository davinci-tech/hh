package com.huawei.pluginachievement.report.bean;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;

/* loaded from: classes6.dex */
public class ReportTrailRunData extends ReportDataBean {
    private int mMaxDistance;
    private int mSumDistance;

    public ReportTrailRunData(int i) {
        super(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE, i, 2010);
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
