package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportReward extends AnnualData {
    private String medalIdList;

    public AnnualReportReward() {
        super(EnumAnnualType.REPORT_REWARD.value());
    }

    public void saveMedalIdList(String str) {
        this.medalIdList = str;
    }

    public String toString() {
        return "AnnualReportReward{medalIdList='" + this.medalIdList + "'}";
    }
}
