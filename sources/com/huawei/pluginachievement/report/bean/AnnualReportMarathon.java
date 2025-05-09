package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportMarathon extends AnnualData {
    private MarathonGradleDetail fullMarathon;
    private MarathonGradleDetail halfMarathon;

    public AnnualReportMarathon() {
        super(EnumAnnualType.REPORT_MARATHON.value());
    }

    public MarathonGradleDetail getHalfMarathon() {
        return this.halfMarathon;
    }

    public void setHalfMarathon(MarathonGradleDetail marathonGradleDetail) {
        this.halfMarathon = marathonGradleDetail;
    }

    public MarathonGradleDetail getFullMarathon() {
        return this.fullMarathon;
    }

    public void setFullMarathon(MarathonGradleDetail marathonGradleDetail) {
        this.fullMarathon = marathonGradleDetail;
    }
}
