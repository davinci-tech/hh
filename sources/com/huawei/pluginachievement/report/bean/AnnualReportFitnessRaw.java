package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginfitnessadvice.Classify;
import java.util.List;

/* loaded from: classes6.dex */
public class AnnualReportFitnessRaw {
    private float mCalorie;
    private int mDuring;
    private long mExerciseTime;
    private List<Classify> mPrimaryClassify;
    private List<Classify> mSecondClassify;
    private String mVersion;
    private String mWorkoutDate;
    private String mWorkoutId;
    private String mWorkoutName;

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void saveWorkoutId(String str) {
        this.mWorkoutId = str;
    }

    public String acquireWorkoutId() {
        return this.mWorkoutId;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public float acquireCalorie() {
        return this.mCalorie;
    }

    public String acquireWorkoutName() {
        return this.mWorkoutName;
    }

    public void saveWorkoutName(String str) {
        this.mWorkoutName = str;
    }

    public void saveWorkoutDate(String str) {
        this.mWorkoutDate = str;
    }

    public long acquireExerciseTime() {
        return this.mExerciseTime;
    }

    public void saveExerciseTime(long j) {
        this.mExerciseTime = j;
    }

    public int acquireDuring() {
        return this.mDuring;
    }

    public void saveDuring(int i) {
        this.mDuring = i;
    }

    public void setPrimaryClassify(List<Classify> list) {
        this.mPrimaryClassify = list;
    }

    public List<Classify> getPrimaryClassify() {
        return this.mPrimaryClassify;
    }

    public List<Classify> getSecondClassify() {
        return this.mSecondClassify;
    }

    public void setSecondClassify(List<Classify> list) {
        this.mSecondClassify = list;
    }

    public String toString() {
        return "workoutName:" + this.mWorkoutName + " workoutDate:" + this.mWorkoutDate + " exerciseTime:" + this.mExerciseTime + " during:" + this.mDuring;
    }
}
