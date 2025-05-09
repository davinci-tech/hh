package com.huawei.hwcommonmodel.fitnessdatatype;

/* loaded from: classes9.dex */
public class FitnessTotalValue {
    private int mAll;
    private int mClimb;
    private int mDataType;
    private int mRide;
    private int mRun;
    private int mWalk;

    public void setAll(int i) {
        this.mAll = i;
    }

    public int getAll() {
        return this.mAll;
    }

    public void setDataType(int i) {
        this.mDataType = i;
    }

    public void setWalk(int i) {
        this.mWalk = i;
    }

    public int getWalk() {
        return this.mWalk;
    }

    public void setRun(int i) {
        this.mRun = i;
    }

    public int getRun() {
        return this.mRun;
    }

    public void setClimb(int i) {
        this.mClimb = i;
    }

    public int getClimb() {
        return this.mClimb;
    }

    public void setRide(int i) {
        this.mRide = i;
    }

    public int getRide() {
        return this.mRide;
    }

    public String toString() {
        return "FitnessTotalValue{dataType=" + this.mDataType + ", all=" + this.mAll + ", walk=" + this.mWalk + ", run=" + this.mRun + ", climb=" + this.mClimb + ", ride=" + this.mRide + '}';
    }
}
