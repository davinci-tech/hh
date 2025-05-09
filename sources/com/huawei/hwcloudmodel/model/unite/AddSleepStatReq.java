package com.huawei.hwcloudmodel.model.unite;

import java.util.List;

/* loaded from: classes5.dex */
public class AddSleepStatReq {
    private List<SleepTotal> sleepTotal;
    private String timeZone;

    public void setSleepTotal(List<SleepTotal> list) {
        this.sleepTotal = list;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddSleepStatReq{sleepTotal=");
        stringBuffer.append(this.sleepTotal);
        stringBuffer.append(", timeZone='").append(this.timeZone).append("'}");
        return stringBuffer.toString();
    }
}
