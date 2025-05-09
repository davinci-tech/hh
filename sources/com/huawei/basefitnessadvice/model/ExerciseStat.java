package com.huawei.basefitnessadvice.model;

/* loaded from: classes8.dex */
public class ExerciseStat {
    private float mCalorie;
    private int mDays;
    private int mPlanCount;

    public ExerciseStat() {
    }

    public ExerciseStat(int i, float f, int i2) {
        this.mPlanCount = i;
        this.mCalorie = f;
        this.mDays = i2;
    }

    public float popCalorie() {
        return this.mCalorie;
    }

    public void putCalorie(float f) {
        this.mCalorie = f;
    }

    public int popDays() {
        return this.mDays;
    }

    public void putDays(int i) {
        this.mDays = i;
    }

    public int popPlanCount() {
        return this.mPlanCount;
    }

    public void putPlanCount(int i) {
        this.mPlanCount = i;
    }
}
