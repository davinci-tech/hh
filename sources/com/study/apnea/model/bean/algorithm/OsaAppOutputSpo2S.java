package com.study.apnea.model.bean.algorithm;

/* loaded from: classes10.dex */
public class OsaAppOutputSpo2S {
    private int spo2AllNightLen;
    private float spo2Avg;
    private int[] spo2MaxAllNight;
    private float[] spo2MeanAllNight;
    private float spo2Min;
    private int[] spo2MinAllNight;
    private float spo2ODIPerHour;
    private long spo2StartMin;

    public OsaAppOutputSpo2S() {
    }

    public OsaAppOutputSpo2S(int i, long j, int[] iArr, int[] iArr2, float[] fArr, float f, float f2, float f3) {
        setFields(i, j, iArr, iArr2, fArr, f, f2, f3);
    }

    public void setFields(int i, long j, int[] iArr, int[] iArr2, float[] fArr, float f, float f2, float f3) {
        this.spo2AllNightLen = i;
        this.spo2StartMin = j;
        this.spo2MinAllNight = iArr;
        this.spo2MaxAllNight = iArr2;
        this.spo2MeanAllNight = fArr;
        this.spo2Avg = f;
        this.spo2Min = f2;
        this.spo2ODIPerHour = f3;
    }

    public void setSpo2AllNightLen(int i) {
        this.spo2AllNightLen = i;
    }

    public void setSpo2StartMin(long j) {
        this.spo2StartMin = j;
    }

    public void setSpo2MinAllNight(int[] iArr) {
        this.spo2MinAllNight = iArr;
    }

    public void setSpo2MaxAllNight(int[] iArr) {
        this.spo2MaxAllNight = iArr;
    }

    public void setSpo2MeanAllNight(float[] fArr) {
        this.spo2MeanAllNight = fArr;
    }

    public int getSpo2AllNightLen() {
        return this.spo2AllNightLen;
    }

    public long getSpo2StartMin() {
        return this.spo2StartMin;
    }

    public int[] getSpo2MinAllNight() {
        return this.spo2MinAllNight;
    }

    public int[] getSpo2MaxAllNight() {
        return this.spo2MaxAllNight;
    }

    public float[] getSpo2MeanAllNight() {
        return this.spo2MeanAllNight;
    }

    public float getSpo2Avg() {
        return this.spo2Avg;
    }

    public float getSpo2Min() {
        return this.spo2Min;
    }

    public float getSpo2ODIPerHour() {
        return this.spo2ODIPerHour;
    }

    public void setSpo2Avg(float f) {
        this.spo2Avg = f;
    }

    public void setSpo2Min(float f) {
        this.spo2Min = f;
    }

    public void setSpo2ODIPerHour(float f) {
        this.spo2ODIPerHour = f;
    }

    public String getOutputString() {
        StringBuffer stringBuffer = new StringBuffer("spo2AllNightLen=");
        stringBuffer.append(this.spo2AllNightLen).append(";\nspo2StartMin=");
        stringBuffer.append(this.spo2StartMin).append(";\nspo2Avg=");
        stringBuffer.append(this.spo2Avg).append(";\nspo2Min=");
        stringBuffer.append(this.spo2Min).append(";\nspo2ODIPerHour=");
        stringBuffer.append(this.spo2ODIPerHour).append(";\n");
        if (this.spo2MinAllNight != null) {
            stringBuffer.append("spo2MinAllNight=");
            for (int i : this.spo2MinAllNight) {
                stringBuffer.append(i).append(",");
            }
            stringBuffer.append(";\n");
        }
        if (this.spo2MaxAllNight != null) {
            stringBuffer.append("spo2MaxAllNight=");
            for (int i2 : this.spo2MaxAllNight) {
                stringBuffer.append(i2).append(",");
            }
            stringBuffer.append(";\n");
        }
        if (this.spo2MeanAllNight != null) {
            stringBuffer.append("spo2MeanAllNight=");
            for (float f : this.spo2MeanAllNight) {
                stringBuffer.append(f).append(",");
            }
            stringBuffer.append(";\n");
        }
        return stringBuffer.toString();
    }
}
