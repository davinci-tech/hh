package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportRun extends AnnualData {
    private int bestPace;
    private int bestPaceCompare;
    private int description;
    private int maxDistance;
    private long maxDistanceDay;
    private int maxRunContinuousDays;
    private int numberOfTimes;
    private int runNumberOfDays;
    private int timeOfDay;
    private int totalDistance;
    private String yearSumDistances;

    public AnnualReportRun() {
        super(EnumAnnualType.REPORT_RUN.value());
    }

    public String acquireYearSumDistances() {
        return this.yearSumDistances;
    }

    public void saveYearSumDistances(String str) {
        this.yearSumDistances = str;
    }

    public int acquireTotalDistance() {
        return this.totalDistance;
    }

    public void saveTotalDistance(int i) {
        this.totalDistance = i;
    }

    public int acquireNumberOfTimes() {
        return this.numberOfTimes;
    }

    public void saveNumberOfTimes(int i) {
        this.numberOfTimes = i;
    }

    public int acquireMaxDistance() {
        return this.maxDistance;
    }

    public void saveMaxDistance(int i) {
        this.maxDistance = i;
    }

    public long acquireMaxDistanceDay() {
        return this.maxDistanceDay;
    }

    public void saveMaxDistanceDay(long j) {
        this.maxDistanceDay = j;
    }

    public int acquireTimeOfDay() {
        return this.timeOfDay;
    }

    public void saveTimeOfDay(int i) {
        this.timeOfDay = i;
    }

    public int acquireDescription() {
        return this.description;
    }

    public void saveDescription(int i) {
        this.description = i;
    }

    public int getRunNumberOfDays() {
        return this.runNumberOfDays;
    }

    public void setRunNumberOfDays(int i) {
        this.runNumberOfDays = i;
    }

    public int getMaxRunContinuousDays() {
        return this.maxRunContinuousDays;
    }

    public void setMaxRunContinuousDays(int i) {
        this.maxRunContinuousDays = i;
    }

    public int getBestPace() {
        return this.bestPace;
    }

    public void setBestPace(int i) {
        this.bestPace = i;
    }

    public int getBestPaceCompare() {
        return this.bestPaceCompare;
    }

    public void setBestPaceCompare(int i) {
        this.bestPaceCompare = i;
    }

    public String toString() {
        return "AnnualReportRun{totalDistance=" + this.totalDistance + ", numberOfTimes=" + this.numberOfTimes + ", maxDistance=" + this.maxDistance + ", maxDistanceDay=" + this.maxDistanceDay + ", timeOfDay=" + this.timeOfDay + ", description=" + this.description + '}';
    }
}
