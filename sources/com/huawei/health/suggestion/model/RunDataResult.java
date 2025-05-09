package com.huawei.health.suggestion.model;

/* loaded from: classes8.dex */
public class RunDataResult {
    private float mDuration;
    private float mMaxHeartRate;
    private int mMinHeartRate;

    public RunDataResult(float f, int i, float f2) {
        this.mDuration = f;
        this.mMinHeartRate = i;
        this.mMaxHeartRate = f2;
    }

    public float getDuration() {
        return this.mDuration;
    }

    public void saveDuration(int i) {
        this.mDuration = i;
    }

    public int getHeartRateMin() {
        return this.mMinHeartRate;
    }

    public void setHeartRateMin(int i) {
        this.mMinHeartRate = i;
    }

    public float getHeartRateMax() {
        return this.mMaxHeartRate;
    }

    public void saveHeartRateMax(float f) {
        this.mMaxHeartRate = f;
    }
}
