package com.huawei.health.suggestion.model;

/* loaded from: classes4.dex */
public class FitnessShareRecord {
    private int mActionCount;
    private float mCalorie;
    private int mDeviceType;
    private float mDuration;
    private String mExerciseName;
    private long mExerciseTime;
    private int mExerciseTimes;
    private int mLevelCount;
    private long mTotalTime;

    public void setExerciseName(String str) {
        this.mExerciseName = str;
    }

    public String getExerciseName() {
        return this.mExerciseName;
    }

    public void setExerciseTime(long j) {
        this.mExerciseTime = j;
    }

    public long getExerciseTime() {
        return this.mExerciseTime;
    }

    public void setLevelCount(int i) {
        this.mLevelCount = i;
    }

    public int getLevelCount() {
        return this.mLevelCount;
    }

    public void setDuration(float f) {
        this.mDuration = f;
    }

    public float getDuration() {
        return this.mDuration;
    }

    public void setCalorie(float f) {
        this.mCalorie = f;
    }

    public float getCalorie() {
        return this.mCalorie;
    }

    public void setTotalTime(long j) {
        this.mTotalTime = j;
    }

    public long getTotalTime() {
        return this.mTotalTime;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setActionCount(int i) {
        this.mActionCount = i;
    }

    public int getActionCount() {
        return this.mActionCount;
    }

    public void setExerciseTimes(int i) {
        this.mExerciseTimes = i;
    }

    public int getExerciseTimes() {
        return this.mExerciseTimes;
    }
}
