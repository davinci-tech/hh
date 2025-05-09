package com.huawei.health.plan.model.model;

/* loaded from: classes8.dex */
public class BestRecordFit {
    private String mActionId;
    private String mBestRecordValue;
    private long mCompleteTime;
    private int mType;

    public String acquireActionId() {
        return this.mActionId;
    }

    public void saveActionId(String str) {
        this.mActionId = str;
    }

    public int acquireType() {
        return this.mType;
    }

    public void saveType(int i) {
        this.mType = i;
    }

    public String acquireValue() {
        return this.mBestRecordValue;
    }

    public void saveValue(String str) {
        this.mBestRecordValue = str;
    }

    public long acquireCompleteTime() {
        return this.mCompleteTime;
    }

    public void saveCompleteTime(long j) {
        this.mCompleteTime = j;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("BestRecordFit{mActionId='");
        stringBuffer.append(this.mActionId).append("', mType=");
        stringBuffer.append(this.mType);
        stringBuffer.append(", mBestRecordValue='").append(this.mBestRecordValue).append("', mCompleteTime=");
        stringBuffer.append(this.mCompleteTime);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
