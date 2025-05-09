package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class TotalRecord extends mcz {
    private String accumulatedCycleDistance;
    private String accumulatedHealthTime;
    private String accumulatedRunDistance;
    private String accumulatedWalkDistance;
    private double calorie;
    private int days;
    private double distance;
    private long endDate;
    private long startDate;
    private double steps;
    private double stepsRanking;

    public TotalRecord() {
        super(1);
    }

    public long getStartDate() {
        return this.startDate;
    }

    public void setStartDate(long j) {
        this.startDate = j;
    }

    public long getEndDate() {
        return this.endDate;
    }

    public void setEndDate(long j) {
        this.endDate = j;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int i) {
        this.days = i;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getSteps() {
        return this.steps;
    }

    public void setSteps(double d) {
        this.steps = d;
    }

    public double acquireCalorie() {
        return this.calorie;
    }

    public void saveCalorie(double d) {
        this.calorie = d;
    }

    public double getStepsRanking() {
        return this.stepsRanking;
    }

    public void setStepsRanking(double d) {
        this.stepsRanking = d;
    }

    public String acquireAccumulatedHealthTime() {
        return this.accumulatedHealthTime;
    }

    public void saveAccumulatedHealthTime(String str) {
        this.accumulatedHealthTime = str;
    }

    public String acquireAccumulatedRunDistance() {
        return this.accumulatedRunDistance;
    }

    public void saveAccumulatedRunDistance(String str) {
        this.accumulatedRunDistance = str;
    }

    public String acquireAccumulatedCycleDistance() {
        return this.accumulatedCycleDistance;
    }

    public void saveAccumulatedCycleDistance(String str) {
        this.accumulatedCycleDistance = str;
    }

    public String acquireAccumulatedWalkDistance() {
        return this.accumulatedWalkDistance;
    }

    public void saveAccumulatedWalkDistance(String str) {
        this.accumulatedWalkDistance = str;
    }

    public String toString() {
        return "TotalRecord{huid='" + super.getHuid() + "', startDate=" + this.startDate + ", endDate=" + this.endDate + ", days=" + this.days + ", distance=" + this.distance + ", steps=" + this.steps + ", calorie=" + this.calorie + ", stepsRanking=" + this.stepsRanking + '}';
    }
}
