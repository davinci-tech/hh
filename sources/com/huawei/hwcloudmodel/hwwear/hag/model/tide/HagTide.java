package com.huawei.hwcloudmodel.hwwear.hag.model.tide;

/* loaded from: classes5.dex */
public class HagTide {
    private int mTideHeight;
    private long mTideTime;
    private int mTideType;

    public long getTideTime() {
        return this.mTideTime;
    }

    public void setTideTime(long j) {
        this.mTideTime = j;
    }

    public int getTideType() {
        return this.mTideType;
    }

    public void setTideType(int i) {
        this.mTideType = i;
    }

    public int getTideHeight() {
        return this.mTideHeight;
    }

    public void setTideHeight(int i) {
        this.mTideHeight = i;
    }

    public String toString() {
        return "HagTide{mTideTime='" + this.mTideTime + "', mTideType=" + this.mTideType + ", mTideHeight=" + this.mTideHeight + '}';
    }
}
