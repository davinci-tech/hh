package com.huawei.hihealth.data.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class HiHeartRateData implements Serializable {
    private int heartRate;
    private long timestamp;

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }
}
