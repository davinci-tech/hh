package com.huawei.hms.mlsdk.asr.engine.cloud.vo;

import java.io.Serializable;

/* loaded from: classes9.dex */
public class RttSegment implements Serializable {
    private static final long serialVersionUID = 8352771809015182681L;
    private String endTime;
    private String startTime;
    private String text;

    public RttSegment() {
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getText() {
        return this.text;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String toString() {
        StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("MLSpeechRealTimeTranscriptionResult[startTime= ");
        a2.append(this.startTime);
        a2.append(", endTime= ");
        a2.append(this.endTime);
        a2.append(", text= ");
        a2.append(this.text);
        a2.append("]");
        return a2.toString();
    }

    public RttSegment(String str, String str2, String str3) {
        this.startTime = str;
        this.endTime = str2;
        this.text = str3;
    }
}
