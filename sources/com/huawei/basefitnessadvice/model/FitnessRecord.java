package com.huawei.basefitnessadvice.model;

/* loaded from: classes8.dex */
public class FitnessRecord {
    private float mActualCalorie;
    private int mCategory;
    private long mExerciseDuring;
    private long mExerciseEndTime;
    private long mExerciseStartTime;
    private boolean mIsFitnessRecordFromDevice;
    private String mPlanId;
    private int mRecordId;
    private int mType;
    private int mWearType;
    private String mWorkoutName;

    public int acquireRecordId() {
        return this.mRecordId;
    }

    public void saveRecordId(int i) {
        this.mRecordId = i;
    }

    public int acquireRecordType() {
        return this.mType;
    }

    public void saveRecordType(int i) {
        this.mType = i;
    }

    public String acquireWorkoutName() {
        return this.mWorkoutName;
    }

    public void saveWorkoutName(String str) {
        this.mWorkoutName = str;
    }

    public String acquirePlanId() {
        return this.mPlanId;
    }

    public void savePlanId(String str) {
        this.mPlanId = str;
    }

    public long acquireExerciseEndTime() {
        return this.mExerciseEndTime;
    }

    public void saveExerciseEndTime(long j) {
        this.mExerciseEndTime = j;
    }

    public long acquireExerciseStartTime() {
        return this.mExerciseStartTime;
    }

    public void saveExerciseStartTime(long j) {
        this.mExerciseStartTime = j;
    }

    public float acquireActualCalorie() {
        return this.mActualCalorie;
    }

    public void saveActualCalorie(float f) {
        this.mActualCalorie = f;
    }

    public long acquireExerciseDuring() {
        return this.mExerciseDuring;
    }

    public void saveExerciseDuring(long j) {
        this.mExerciseDuring = j;
    }

    public int acquireWearType() {
        return this.mWearType;
    }

    public void saveWearType(int i) {
        this.mWearType = i;
    }

    public boolean isFitnessRecordFromDevice() {
        return this.mIsFitnessRecordFromDevice;
    }

    public void saveFitnessRecordFromDevice(boolean z) {
        this.mIsFitnessRecordFromDevice = z;
    }

    public int getCategory() {
        return this.mCategory;
    }

    public void setCategory(int i) {
        this.mCategory = i;
    }

    public String toString() {
        return "[[mRecordId, " + this.mRecordId + "], [mType, " + this.mType + "], [mPlanId, " + this.mPlanId + "], [mWorkoutName, " + this.mWorkoutName + "], [mExerciseEndTime, " + this.mExerciseEndTime + "], [mExerciseStartTime, " + this.mExerciseStartTime + "], [mActualCalorie, " + this.mActualCalorie + "], [mCategory, " + this.mCategory + "]]";
    }
}
