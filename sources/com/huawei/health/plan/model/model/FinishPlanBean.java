package com.huawei.health.plan.model.model;

/* loaded from: classes3.dex */
public class FinishPlanBean {
    private float mActualCalorie;
    private float mActualDistance;
    private float mActualDuration;
    private float mCompletionRate;
    private long mFinishTime;
    private String mPlanId;

    public FinishPlanBean(String str, float f, float f2, float f3, float f4, long j) {
        this.mPlanId = str;
        this.mCompletionRate = f;
        this.mActualDistance = f2;
        this.mActualCalorie = f3;
        this.mActualDuration = f4;
        this.mFinishTime = j;
    }

    public String getPlanId() {
        return this.mPlanId;
    }

    public void setPlanId(String str) {
        this.mPlanId = str;
    }

    public float getCompletionRate() {
        return this.mCompletionRate;
    }

    public void setCompletionRate(float f) {
        this.mCompletionRate = f;
    }

    public float getActualDistance() {
        return this.mActualDistance;
    }

    public void setActualDistance(float f) {
        this.mActualDistance = f;
    }

    public float getActualCalorie() {
        return this.mActualCalorie;
    }

    public void setActualCalorie(float f) {
        this.mActualCalorie = f;
    }

    public long getFinishTime() {
        return this.mFinishTime;
    }

    public void setFinishTime(long j) {
        this.mFinishTime = j;
    }

    public float getActualDuration() {
        return this.mActualDuration;
    }

    public void setActualDuration(float f) {
        this.mActualDuration = f;
    }
}
