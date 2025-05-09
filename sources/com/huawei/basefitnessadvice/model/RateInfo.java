package com.huawei.basefitnessadvice.model;

/* loaded from: classes3.dex */
public class RateInfo {
    private int mAerobic;
    private int mAnaerobic;
    private int mFatBurning;
    private int mLimit;
    private int mMaxRate;
    private int mMinRate;
    private int mWarmUp;

    public int acquireMinRate() {
        return this.mMinRate;
    }

    public void saveMinRate(int i) {
        this.mMinRate = i;
    }

    public int acquireMaxRate() {
        return this.mMaxRate;
    }

    public void saveMaxRate(int i) {
        this.mMaxRate = i;
    }

    public int acquireLimit() {
        return this.mLimit;
    }

    public void saveLimit(int i) {
        this.mLimit = i;
    }

    public int getAnaerobic() {
        return this.mAnaerobic;
    }

    public void setAnaerobic(int i) {
        this.mAnaerobic = i;
    }

    public int getAerobic() {
        return this.mAerobic;
    }

    public void setAerobic(int i) {
        this.mAerobic = i;
    }

    public int acquireFatBurning() {
        return this.mFatBurning;
    }

    public void saveFatBurning(int i) {
        this.mFatBurning = i;
    }

    public int acquireWarmUp() {
        return this.mWarmUp;
    }

    public void saveWarmUp(int i) {
        this.mWarmUp = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("RateInfo{mMinRate=");
        stringBuffer.append(this.mMinRate);
        stringBuffer.append(", mMaxRate=").append(this.mMaxRate);
        stringBuffer.append(", mLimit=").append(this.mLimit);
        stringBuffer.append(", mAnaerobic=").append(this.mAnaerobic);
        stringBuffer.append(", mAerobic=").append(this.mAerobic);
        stringBuffer.append(", mFatBurning=").append(this.mFatBurning);
        stringBuffer.append(", mWarmUp=").append(this.mWarmUp);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
