package com.study.apnea.model.bean.algorithm;

/* loaded from: classes10.dex */
public class OsaAppOutputOsaS {
    private float ahi;
    private float ahi2;
    private float osaAvgCntPerHour;
    private int osaCntAllNight;
    private long[] osaCntHalHourTime;
    private int[] osaCntPerHour;
    private int osaCntPerHourLen;
    private int osaDurationAvg;
    private int osaDurationMax;
    private int osaLevel;
    private float osaMeanCntHalfHour;
    private int osaScore;
    private int totalMinsAllNight;

    public OsaAppOutputOsaS() {
    }

    public OsaAppOutputOsaS(int i, int i2, int i3, int i4, int i5, float f, int[] iArr, long[] jArr, float f2, int i6, int i7) {
        setFields(i, i2, i3, i4, i5, f, iArr, jArr, f2, i6, i7);
    }

    public void setFields(int i, int i2, int i3, int i4, int i5, float f, int[] iArr, long[] jArr, float f2, int i6, int i7) {
        this.osaLevel = i;
        this.osaScore = i2;
        this.osaCntPerHourLen = i3;
        this.osaCntAllNight = i4;
        this.totalMinsAllNight = i5;
        this.osaMeanCntHalfHour = f;
        this.osaCntPerHour = iArr;
        this.osaCntHalHourTime = jArr;
        this.osaAvgCntPerHour = f2;
        this.osaDurationMax = i6;
        this.osaDurationAvg = i7;
    }

    public void setOsaLevel(int i) {
        this.osaLevel = i;
    }

    public void setOsaScore(int i) {
        this.osaScore = i;
    }

    public void setOsaCntPerHourLen(int i) {
        this.osaCntPerHourLen = i;
    }

    public void setOsaCntAllNight(int i) {
        this.osaCntAllNight = i;
    }

    public void setTotalMinsAllNight(int i) {
        this.totalMinsAllNight = i;
    }

    public void setOsaMeanCntHalfHour(float f) {
        this.osaMeanCntHalfHour = f;
    }

    public void setOsaCntPerHour(int[] iArr) {
        this.osaCntPerHour = iArr;
    }

    public void setOsaCntHalHourTime(long[] jArr) {
        this.osaCntHalHourTime = jArr;
    }

    public int getOsaLevel() {
        return this.osaLevel;
    }

    public int getOsaScore() {
        return this.osaScore;
    }

    public int getOsaCntPerHourLen() {
        return this.osaCntPerHourLen;
    }

    public int getOsaCntAllNight() {
        return this.osaCntAllNight;
    }

    public int getTotalMinsAllNight() {
        return this.totalMinsAllNight;
    }

    public float getOsaMeanCntHalfHour() {
        return this.osaMeanCntHalfHour;
    }

    public int[] getOsaCntPerHour() {
        return this.osaCntPerHour;
    }

    public long[] getOsaCntHalHourTime() {
        return this.osaCntHalHourTime;
    }

    public float getOsaAvgCntPerHour() {
        return this.osaAvgCntPerHour;
    }

    public int getOsaDurationMax() {
        return this.osaDurationMax;
    }

    public int getOsaDurationAvg() {
        return this.osaDurationAvg;
    }

    public void setOsaAvgCntPerHour(float f) {
        this.osaAvgCntPerHour = f;
    }

    public void setOsaDurationMax(int i) {
        this.osaDurationMax = i;
    }

    public void setOsaDurationAvg(int i) {
        this.osaDurationAvg = i;
    }

    public float getAhi() {
        return this.ahi;
    }

    public void setAhi(float f) {
        this.ahi = f;
    }

    public float getAhi2() {
        return this.ahi2;
    }

    public void setAhi2(float f) {
        this.ahi2 = f;
    }

    public String getOutputString() {
        StringBuffer stringBuffer = new StringBuffer("osaLevel=");
        stringBuffer.append(this.osaLevel).append(";\nosaScore=");
        stringBuffer.append(this.osaScore).append(";\nosaCntPerHourLen=");
        stringBuffer.append(this.osaCntPerHourLen).append(";\nosaCntAllNight=");
        stringBuffer.append(this.osaCntAllNight).append(";\ntotalMinsAllNight=");
        stringBuffer.append(this.totalMinsAllNight).append(";\nosaMeanCntHalfHour=");
        stringBuffer.append(this.osaMeanCntHalfHour).append(";\nosaAvgCntPerHour=");
        stringBuffer.append(this.osaAvgCntPerHour).append(";\nosaDurationMax=");
        stringBuffer.append(this.osaDurationMax).append(";\nosaDurationAvg=");
        stringBuffer.append(this.osaDurationAvg).append(";\nahi=");
        stringBuffer.append(this.ahi).append(";\nahi2=");
        stringBuffer.append(this.ahi2).append(";\n");
        if (this.osaCntPerHour != null) {
            stringBuffer.append("osaCntPerHour=");
            for (int i : this.osaCntPerHour) {
                stringBuffer.append(i).append(",");
            }
            stringBuffer.append(";\n");
        }
        if (this.osaCntHalHourTime != null) {
            stringBuffer.append("osaCntHalHourTime=");
            for (long j : this.osaCntHalHourTime) {
                stringBuffer.append(j).append(",");
            }
            stringBuffer.append(";\n");
        }
        return stringBuffer.toString();
    }
}
