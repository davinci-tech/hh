package com.huawei.ui.homehealth.runcard.operation.recommendalgo.model;

/* loaded from: classes9.dex */
public class SportLevel {
    private int mLevel;
    private int mReason;
    private double mValue;

    public SportLevel() {
        this.mLevel = -1;
        this.mReason = 0;
        this.mValue = 0.0d;
    }

    public SportLevel(int i, int i2) {
        this(i, i2, 0.0d);
    }

    public SportLevel(int i, int i2, double d) {
        this.mLevel = i;
        this.mReason = i2;
        this.mValue = d;
    }

    public int acquireLevel() {
        return this.mLevel;
    }

    public void saveLevel(int i) {
        this.mLevel = i;
    }

    public int acquireReason() {
        return this.mReason;
    }

    public void saveReason(int i) {
        this.mReason = i;
    }

    public double acquireValue() {
        return this.mValue;
    }

    public void saveValue(double d) {
        this.mValue = d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SportLevel)) {
            return false;
        }
        SportLevel sportLevel = (SportLevel) obj;
        return this.mLevel == sportLevel.acquireLevel() && this.mValue == sportLevel.acquireValue() && this.mReason == sportLevel.acquireReason();
    }

    public int hashCode() {
        int i = this.mLevel;
        int i2 = this.mReason;
        long doubleToLongBits = Double.doubleToLongBits(this.mValue);
        return (((i * 31) + i2) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    public String toString() {
        return "level:" + this.mLevel + " reason:" + this.mReason + " value:" + this.mValue;
    }
}
