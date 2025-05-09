package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportFitness extends AnnualData {
    private int description;
    private int maxDuration;
    private long maxDurationDay;
    private int numberOfTimes;
    private int totalDuration;

    public AnnualReportFitness() {
        super(EnumAnnualType.REPORT_FITNESS.value());
    }

    public int acquireTotalDuration() {
        return this.totalDuration;
    }

    public void saveTotalDuration(int i) {
        this.totalDuration = i;
    }

    public int acquireNumberOfTimes() {
        return this.numberOfTimes;
    }

    public void saveNumberOfTimes(int i) {
        this.numberOfTimes = i;
    }

    public int acquireMaxDuration() {
        return this.maxDuration;
    }

    public void saveMaxDuration(int i) {
        this.maxDuration = i;
    }

    public long acquireMaxDurationDay() {
        return this.maxDurationDay;
    }

    public void saveMaxDurationDay(long j) {
        this.maxDurationDay = j;
    }

    public int acquireDescription() {
        return this.description;
    }

    public void saveDescription(int i) {
        this.description = i;
    }

    public String toString() {
        return "AnnualReportFitness{totalDuration=" + this.totalDuration + ", numberOfTimes=" + this.numberOfTimes + ", maxDuration=" + this.maxDuration + ", maxDurationDay=" + this.maxDurationDay + ", description=" + this.description + '}';
    }
}
