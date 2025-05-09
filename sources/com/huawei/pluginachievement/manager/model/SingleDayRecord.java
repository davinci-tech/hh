package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class SingleDayRecord extends mcz {
    private String bestCycleDistance;
    private String bestCycleSpeed;
    private String bestRun10KMPace;
    private String bestRun3KMPace;
    private String bestRun5KMPace;
    private String bestRunDistance;
    private String bestRunFMPace;
    private String bestRunHMPace;
    private String bestRunPace;
    private String bestStepDay;
    private String bestWalkDistance;
    private double distance;
    private long distanceDate;
    private boolean isDelete;
    private String mBestRopeContinuousCount;
    private String mBestRopeDuration;
    private String mBestRopeSingCount;
    private String mBestRopeSpeed;
    private int matchSpeed;
    private long matchSpeedDate;
    private String npeBestResult;
    private int steps;
    private long stepsDate;

    public SingleDayRecord() {
        super(2);
        this.isDelete = false;
    }

    public int getSteps() {
        return this.steps;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public double acquireDistance() {
        return this.distance;
    }

    public void saveDistance(double d) {
        this.distance = d;
    }

    public int acquireMatchSpeed() {
        return this.matchSpeed;
    }

    public void saveMatchSpeed(int i) {
        this.matchSpeed = i;
    }

    public long getStepsDate() {
        return this.stepsDate;
    }

    public void setStepsDate(long j) {
        this.stepsDate = j;
    }

    public long getDistanceDate() {
        return this.distanceDate;
    }

    public void setDistanceDate(long j) {
        this.distanceDate = j;
    }

    public long getMatchSpeedDate() {
        return this.matchSpeedDate;
    }

    public void setMatchSpeedDate(long j) {
        this.matchSpeedDate = j;
    }

    public String acquireBestWalkDistance() {
        return this.bestWalkDistance;
    }

    public void saveBestWalkDistance(String str) {
        this.bestWalkDistance = str;
    }

    public String acquireBestCycleDistance() {
        return this.bestCycleDistance;
    }

    public void saveBestCycleDistance(String str) {
        this.bestCycleDistance = str;
    }

    public String acquireBestCycleSpeed() {
        return this.bestCycleSpeed;
    }

    public void saveBestCycleSpeed(String str) {
        this.bestCycleSpeed = str;
    }

    public String acquireBestRun3KMPace() {
        return this.bestRun3KMPace;
    }

    public void saveBestRun3KMPace(String str) {
        this.bestRun3KMPace = str;
    }

    public String acquireBestRun5KMPace() {
        return this.bestRun5KMPace;
    }

    public void saveBestRun5KMPace(String str) {
        this.bestRun5KMPace = str;
    }

    public String acquireBestRun10KMPace() {
        return this.bestRun10KMPace;
    }

    public void saveBestRun10KMPace(String str) {
        this.bestRun10KMPace = str;
    }

    public String acquireBestRunHMPace() {
        return this.bestRunHMPace;
    }

    public void saveBestRunHMPace(String str) {
        this.bestRunHMPace = str;
    }

    public String acquireBestRunFMPace() {
        return this.bestRunFMPace;
    }

    public void saveBestRunFMPace(String str) {
        this.bestRunFMPace = str;
    }

    public String acquireBestStepDay() {
        return this.bestStepDay;
    }

    public void saveBestStepDay(String str) {
        this.bestStepDay = str;
    }

    public String acquireBestRunDistance() {
        return this.bestRunDistance;
    }

    public void saveBestRunDistance(String str) {
        this.bestRunDistance = str;
    }

    public String acquireBestRunPace() {
        return this.bestRunPace;
    }

    public void saveBestRunPace(String str) {
        this.bestRunPace = str;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public void setDelete(boolean z) {
        this.isDelete = z;
    }

    public String acquireBestRopeSingCount() {
        return this.mBestRopeSingCount;
    }

    public void saveBestRopeSingCount(String str) {
        this.mBestRopeSingCount = str;
    }

    public String acquireBestRopeContinuousCount() {
        return this.mBestRopeContinuousCount;
    }

    public void saveBestRopeContinuousCount(String str) {
        this.mBestRopeContinuousCount = str;
    }

    public String acquireBestRopeSpeed() {
        return this.mBestRopeSpeed;
    }

    public void saveBestRopeSpeed(String str) {
        this.mBestRopeSpeed = str;
    }

    public String acquireBestRopeDuration() {
        return this.mBestRopeDuration;
    }

    public void saveBestRopeDuration(String str) {
        this.mBestRopeDuration = str;
    }

    public String getNpeBestResult() {
        return this.npeBestResult;
    }

    public void setNpeBestResult(String str) {
        this.npeBestResult = str;
    }

    public String toString() {
        return "SingleDayRecord{steps=" + this.steps + ", distance=" + this.distance + ", matchSpeed=" + this.matchSpeed + ", stepsDate=" + this.stepsDate + ", distanceDate=" + this.distanceDate + ", matchSpeedDate=" + this.matchSpeedDate + ", bestStepDay='" + this.bestStepDay + "', mBestRopeSingCount='" + this.mBestRopeSingCount + "', mBestRopeContinuousCount='" + this.mBestRopeContinuousCount + "', mBestRopeSpeed='" + this.mBestRopeSpeed + "', mBestRopeDuration='" + this.mBestRopeDuration + "', bestRunDistance='" + this.bestRunDistance + "', bestRunPace='" + this.bestRunPace + "', bestWalkDistance='" + this.bestWalkDistance + "', bestCycleDistance='" + this.bestCycleDistance + "', bestCycleSpeed='" + this.bestCycleSpeed + "', bestRun3KMPace='" + this.bestRun3KMPace + "', bestRun5KMPace='" + this.bestRun5KMPace + "', bestRun10KMPace='" + this.bestRun10KMPace + "', bestRunHMPace='" + this.bestRunHMPace + "', bestRunFMPace='" + this.bestRunFMPace + "', isDelete=" + this.isDelete + '}';
    }
}
