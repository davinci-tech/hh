package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class TrackSpeedData implements Serializable {
    private static final long serialVersionUID = -6754616340266058150L;
    private int mRealTimeSpeed;
    private long mTime;

    public TrackSpeedData() {
    }

    public TrackSpeedData(long j, int i) {
        this.mTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
        this.mRealTimeSpeed = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getTime() {
        return ((Long) jdy.d(Long.valueOf(this.mTime))).longValue();
    }

    public void setTime(long j) {
        this.mTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getRealTimeSpeed() {
        return ((Integer) jdy.d(Integer.valueOf(this.mRealTimeSpeed))).intValue();
    }

    public void setRealTimeSpeed(int i) {
        this.mRealTimeSpeed = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("TrackPullFreqData{mTime=");
        sb.append(this.mTime);
        sb.append("mRealTimeSpeed=");
        sb.append(this.mRealTimeSpeed);
        return sb.toString();
    }
}
