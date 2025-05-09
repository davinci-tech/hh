package com.study.apnea.model.bean.algorithm;

/* loaded from: classes10.dex */
public class OsaSpo2DataBufferS {
    private int[] spo2Data;
    private int spo2DataLen;
    private int[] spo2Sqi;
    private long startTimeStamp;

    public OsaSpo2DataBufferS() {
    }

    public OsaSpo2DataBufferS(int i, int[] iArr, int[] iArr2, long j) {
        this.spo2DataLen = i;
        this.spo2Sqi = iArr;
        this.spo2Data = iArr2;
        this.startTimeStamp = j;
    }

    public int getSpo2DataLen() {
        return this.spo2DataLen;
    }

    public int[] getSpo2Sqi() {
        return this.spo2Sqi;
    }

    public int[] getSpo2Data() {
        return this.spo2Data;
    }

    public long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public void setSpo2DataLen(int i) {
        this.spo2DataLen = i;
    }

    public void setSpo2Sqi(int[] iArr) {
        this.spo2Sqi = iArr;
    }

    public void setSpo2Data(int[] iArr) {
        this.spo2Data = iArr;
    }

    public void setStartTimeStamp(long j) {
        this.startTimeStamp = j;
    }
}
