package com.huawei.hwcommonmodel.fitnessdatatype;

/* loaded from: classes5.dex */
public class HeartRateDetect {
    private int mHeartRate;
    private long mTimestamp;

    public void setHeartRate(int i) {
        this.mHeartRate = i;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public int getHeartRate() {
        return this.mHeartRate;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public String toString() {
        return "HeartRateDetect{heartRate=" + this.mHeartRate + ", timestamp=" + this.mTimestamp + '}';
    }
}
