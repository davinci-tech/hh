package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportCycle extends AnnualData {
    private int maxCycleContinuousDays;
    private int maxDistance;
    private long maxDistanceDay;
    private int numberOfTimes;
    private int totalDistance;

    public AnnualReportCycle() {
        super(EnumAnnualType.REPORT_CYCLE.value());
    }

    public int acquireNumberOfTimes() {
        return this.numberOfTimes;
    }

    public void saveNumberOfTimes(int i) {
        this.numberOfTimes = i;
    }

    public int acquireTotalDistance() {
        return this.totalDistance;
    }

    public void saveTotalDistance(int i) {
        this.totalDistance = i;
    }

    public long acquireMaxDistanceDay() {
        return this.maxDistanceDay;
    }

    public void saveMaxDistanceDay(long j) {
        this.maxDistanceDay = j;
    }

    public int acquireMaxDistance() {
        return this.maxDistance;
    }

    public void saveMaxDistance(int i) {
        this.maxDistance = i;
    }

    public int getMaxCycleContinuousDays() {
        return this.maxCycleContinuousDays;
    }

    public void setMaxCycleContinuousDays(int i) {
        this.maxCycleContinuousDays = i;
    }

    public String toString() {
        return "AnnualReportCycle{totalDistance=" + this.totalDistance + ", numberOfTimes=" + this.numberOfTimes + ", maxDistance=" + this.maxDistance + ", maxDistanceDay=" + this.maxDistanceDay + '}';
    }
}
