package com.study.apnea.model.bean.algorithm;

/* loaded from: classes10.dex */
public class OsaRriResultS {
    private int[] rriData;
    private int rriDataLen;
    private int[] sqiResult;
    private long startTimeStamp;

    public OsaRriResultS() {
    }

    public OsaRriResultS(int i, int[] iArr, int[] iArr2, long j) {
        this.rriDataLen = i;
        this.sqiResult = iArr;
        this.rriData = iArr2;
        this.startTimeStamp = j;
    }

    public int getRriDataLen() {
        return this.rriDataLen;
    }

    public int[] getSqiResult() {
        return this.sqiResult;
    }

    public int[] getRriData() {
        return this.rriData;
    }

    public long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public void setRriDataLen(int i) {
        this.rriDataLen = i;
    }

    public void setSqiResult(int[] iArr) {
        this.sqiResult = iArr;
    }

    public void setRriData(int[] iArr) {
        this.rriData = iArr;
    }

    public void setStartTimeStamp(long j) {
        this.startTimeStamp = j;
    }
}
