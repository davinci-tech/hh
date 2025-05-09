package com.study.apnea.model.bean.algorithm;

/* loaded from: classes10.dex */
public class OsaAppOutputHrS {
    private int[] hrAllNight;
    private int hrAllNightLen;
    private int hrMax;
    private float hrMean;
    private int hrMin;
    private long hrStartMin;

    public OsaAppOutputHrS() {
    }

    public OsaAppOutputHrS(int i, int i2, long j, float f, int[] iArr, int i3) {
        setFields(i, i2, j, f, iArr, i3);
    }

    public void setFields(int i, int i2, long j, float f, int[] iArr, int i3) {
        this.hrAllNightLen = i;
        this.hrMin = i2;
        this.hrStartMin = j;
        this.hrMean = f;
        this.hrAllNight = iArr;
        this.hrMax = i3;
    }

    public int getHrAllNightLen() {
        return this.hrAllNightLen;
    }

    public int getHrMin() {
        return this.hrMin;
    }

    public float getHrMean() {
        return this.hrMean;
    }

    public int[] getHrAllNight() {
        return this.hrAllNight;
    }

    public void setHrAllNightLen(int i) {
        this.hrAllNightLen = i;
    }

    public void setHrMin(int i) {
        this.hrMin = i;
    }

    public void setHrMean(float f) {
        this.hrMean = f;
    }

    public void setHrAllNight(int[] iArr) {
        this.hrAllNight = iArr;
    }

    public int getHrMax() {
        return this.hrMax;
    }

    public void setHrMax(int i) {
        this.hrMax = i;
    }

    public long getHrStartMin() {
        return this.hrStartMin;
    }

    public void setHrStartMin(long j) {
        this.hrStartMin = j;
    }

    public String getOutputString() {
        StringBuffer stringBuffer = new StringBuffer("hrAllNightLen=");
        stringBuffer.append(this.hrAllNightLen).append(";\nhrMin=");
        stringBuffer.append(this.hrMin).append(";\nhrMean=");
        stringBuffer.append(this.hrMean).append(";\nhrMax=");
        stringBuffer.append(this.hrMax).append(";\nhrStartMin=");
        stringBuffer.append(this.hrStartMin).append(";\n");
        if (this.hrAllNight != null) {
            stringBuffer.append("hrAllNight=");
            for (int i : this.hrAllNight) {
                stringBuffer.append(i).append(",");
            }
            stringBuffer.append(";\n");
        }
        return stringBuffer.toString();
    }
}
