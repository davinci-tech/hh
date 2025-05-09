package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class BaseMetaData {

    @SerializedName("deviceCode")
    private final long mDeviceCode;

    @SerializedName("endTime")
    private final long mEndTime;

    @SerializedName("startTime")
    private final long mStartTime;

    public BaseMetaData(long j, long j2, long j3) {
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mDeviceCode = j3;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public long getDeviceCode() {
        return this.mDeviceCode;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("BaseMetaData { mStartTime = ");
        stringBuffer.append(this.mStartTime);
        stringBuffer.append(", mEndTime = ").append(this.mEndTime);
        stringBuffer.append(", mDeviceCode = ").append(this.mDeviceCode);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }
}
