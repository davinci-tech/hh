package com.huawei.basefitnessadvice.model;

/* loaded from: classes3.dex */
public class FitnessTrackRecord {
    private int mCategory;
    private String mMonthDate;
    private long mMonthZeroTime;
    private float mSumCalorie;
    private int mSumExerciseCount;
    private long mSumExerciseTime;
    private int mType;

    public int acquireRecordType() {
        return this.mType;
    }

    public void saveRecordType(int i) {
        this.mType = i;
    }

    public String acquireMonthDate() {
        return this.mMonthDate;
    }

    public void saveMonthDate(String str) {
        this.mMonthDate = str;
    }

    public int acquireSumExerciseTimes() {
        return this.mSumExerciseCount;
    }

    public void saveSumExerciseTimes(int i) {
        this.mSumExerciseCount = i;
    }

    public float acquireSumCalorie() {
        return this.mSumCalorie;
    }

    public void saveSumCalorie(float f) {
        this.mSumCalorie = f;
    }

    public long acquireSumExerciseTime() {
        return this.mSumExerciseTime;
    }

    public void saveSumExerciseTime(long j) {
        this.mSumExerciseTime = j;
    }

    public long acquireMonthZeroTime() {
        return this.mMonthZeroTime;
    }

    public void saveMonthZeroTime(long j) {
        this.mMonthZeroTime = j;
    }

    public int acquireCategory() {
        return this.mCategory;
    }

    public void saveCategory(int i) {
        this.mCategory = i;
    }

    public String toString() {
        return "[[mType, " + this.mType + "],[mSumExerciseCount, " + this.mSumExerciseCount + "], [mSumExerciseTime, " + this.mSumExerciseTime + "], [mSumCalorie, " + this.mSumCalorie + "], [mMonthDate, " + this.mMonthDate + "], [mCategory, " + this.mCategory + "]]";
    }
}
