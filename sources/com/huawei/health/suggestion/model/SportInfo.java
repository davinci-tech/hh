package com.huawei.health.suggestion.model;

/* loaded from: classes8.dex */
public class SportInfo {
    private float mCalorie;
    private float mDistance;
    private int mHeartRate;
    private int mPace;
    private String mSportId;
    private int mTime;

    public float acquireDistance() {
        return this.mDistance;
    }

    public void saveDistance(float f) {
        this.mDistance = f;
    }

    public int acquireTime() {
        return this.mTime;
    }

    public void saveTime(int i) {
        this.mTime = i;
    }

    public int getPace() {
        return this.mPace;
    }

    public void savePace(int i) {
        this.mPace = i;
    }

    public int getHeartRate() {
        return this.mHeartRate;
    }

    public void saveHeartRate(int i) {
        this.mHeartRate = i;
    }

    public float getCalorie() {
        return this.mCalorie;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public String getSportId() {
        return this.mSportId;
    }

    public void saveSportId(String str) {
        this.mSportId = str;
    }
}
