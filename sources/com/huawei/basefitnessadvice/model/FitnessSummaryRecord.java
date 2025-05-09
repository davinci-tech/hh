package com.huawei.basefitnessadvice.model;

/* loaded from: classes8.dex */
public class FitnessSummaryRecord {
    private long mRecordsCount;
    private long mRecordsEndTime;
    private long mRecordsStartTime;
    private float mRecordsSumCalorie;
    private long mRecordsSumTime;
    private int mType;

    public int acquireRecordType() {
        return this.mType;
    }

    public void saveRecordType(int i) {
        this.mType = i;
    }

    public long acquireRecordsEndTime() {
        return this.mRecordsEndTime;
    }

    public void saveRecordsEndTime(long j) {
        this.mRecordsEndTime = j;
    }

    public long acquireRecordsStartTime() {
        return this.mRecordsStartTime;
    }

    public void saveRecordsStartTime(long j) {
        this.mRecordsStartTime = j;
    }

    public long acquireRecordsCount() {
        return this.mRecordsCount;
    }

    public void saveRecordsCount(long j) {
        this.mRecordsCount = j;
    }

    public float acquireRecordsSumCalorie() {
        return this.mRecordsSumCalorie;
    }

    public void saveRecordsSumCalorie(float f) {
        this.mRecordsSumCalorie = f;
    }

    public long acquireRecordsSumTime() {
        return this.mRecordsSumTime;
    }

    public void saveRecordsSumTime(long j) {
        this.mRecordsSumTime = j;
    }
}
