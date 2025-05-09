package com.huawei.hwcommonmodel.fitnessdatatype;

/* loaded from: classes5.dex */
public class Vo2maxDetail {
    private long mTimestamp;
    private int mVo2maxValue;

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public int getVo2maxValue() {
        return this.mVo2maxValue;
    }

    public void setVo2maxValue(int i) {
        this.mVo2maxValue = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Vo2maxDetail{mTimestamp=");
        stringBuffer.append(this.mTimestamp);
        stringBuffer.append(", mVo2maxValue=").append(this.mVo2maxValue);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
