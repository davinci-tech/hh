package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportInital extends AnnualData {
    private long firstUseDate;
    private int totalDays;
    private int totalSportDistance;

    public AnnualReportInital() {
        super(EnumAnnualType.REPORT_INITAL.value());
    }

    public long acquireFirstUseDate() {
        return this.firstUseDate;
    }

    public void saveFirstUseDate(long j) {
        this.firstUseDate = j;
    }

    public void saveTotalDays(int i) {
        this.totalDays = i;
    }

    public void saveTotalSportDistance(int i) {
        this.totalSportDistance = i;
    }

    public String toString() {
        return "AnnualReportInital{firstUseDate=" + this.firstUseDate + ", totalDays=" + this.totalDays + ", totalSportDistance=" + this.totalSportDistance + '}';
    }
}
