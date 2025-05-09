package com.huawei.health.plan.model.model;

/* loaded from: classes8.dex */
public class CreatePlanBean {
    private long mBeginDate;
    private int mDifficulty;
    private String mExcludedDate;
    private int mGoal;
    private int mMovementTimes;
    private float mWeight;

    public CreatePlanBean() {
    }

    public CreatePlanBean(float f, long j, int i, int i2, int i3, String str) {
        this.mWeight = f;
        this.mBeginDate = j;
        this.mGoal = i;
        this.mDifficulty = i2;
        this.mMovementTimes = i3;
        this.mExcludedDate = str;
    }

    public float getWeight() {
        return this.mWeight;
    }

    public void setWeight(float f) {
        this.mWeight = f;
    }

    public long getBeginDate() {
        return this.mBeginDate;
    }

    public void setBeginDate(long j) {
        this.mBeginDate = j;
    }

    public int getGoal() {
        return this.mGoal;
    }

    public void setGoal(int i) {
        this.mGoal = i;
    }

    public int getDifficulty() {
        return this.mDifficulty;
    }

    public void setDifficulty(int i) {
        this.mDifficulty = i;
    }

    public int getMovementTimes() {
        return this.mMovementTimes;
    }

    public void setMovementTimes(int i) {
        this.mMovementTimes = i;
    }

    public String getExcludedDate() {
        return this.mExcludedDate;
    }

    public void setExcludedDate(String str) {
        this.mExcludedDate = str;
    }
}
