package com.study.apnea.model.bean.algorithm;

/* loaded from: classes9.dex */
public class DsStateT {
    private int endTime;
    private int endType;
    private int index;
    private int[] reserved;
    private int startTime;

    public DsStateT() {
    }

    public DsStateT(int i, int[] iArr, int i2, int i3, int i4) {
        this.endType = i;
        this.reserved = iArr;
        this.index = i2;
        this.startTime = i3;
        this.endTime = i4;
    }

    public int getEndType() {
        return this.endType;
    }

    public int[] getReserved() {
        return this.reserved;
    }

    public int getIndex() {
        return this.index;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public void setEndType(int i) {
        this.endType = i;
    }

    public void setReserved(int[] iArr) {
        this.reserved = iArr;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public void setEndTime(int i) {
        this.endTime = i;
    }
}
