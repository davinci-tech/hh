package com.study.apnea.model.bean.algorithm;

/* loaded from: classes10.dex */
public class OsaTagS {
    private int algFlag;
    private int osaTag;
    private int sqiResult;
    private long startTimeStamp;

    public OsaTagS() {
    }

    public OsaTagS(int i, int i2, int i3, long j) {
        setFields(i, i2, i3, j);
    }

    public void setFields(int i, int i2, int i3, long j) {
        this.osaTag = i;
        this.algFlag = i2;
        this.sqiResult = i3;
        this.startTimeStamp = j;
    }

    public int getOsaTag() {
        return this.osaTag;
    }

    public int getAlgFlag() {
        return this.algFlag;
    }

    public int getSqiResult() {
        return this.sqiResult;
    }

    public long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public void setOsaTag(int i) {
        this.osaTag = i;
    }

    public void setAlgFlag(int i) {
        this.algFlag = i;
    }

    public void setSqiResult(int i) {
        this.sqiResult = i;
    }

    public void setStartTimeStamp(long j) {
        this.startTimeStamp = j;
    }

    public String getOutputString() {
        StringBuffer stringBuffer = new StringBuffer("osaTag=");
        stringBuffer.append(this.osaTag).append(";\nalgFlag=");
        stringBuffer.append(this.algFlag).append(";\nsqiResult=");
        stringBuffer.append(this.sqiResult).append(";\nstartTimeStamp=");
        stringBuffer.append(this.startTimeStamp).append(";\n");
        return stringBuffer.toString();
    }
}
