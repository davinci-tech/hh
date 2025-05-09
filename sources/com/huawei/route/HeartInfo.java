package com.huawei.route;

/* loaded from: classes6.dex */
public class HeartInfo {
    private final int mHeartRate;
    private final long mTime;

    public HeartInfo(int i, long j) {
        this.mHeartRate = i;
        this.mTime = j;
    }

    public long getTime() {
        return this.mTime;
    }

    public int getHeartRate() {
        return this.mHeartRate;
    }
}
