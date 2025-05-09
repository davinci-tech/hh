package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class TrackPullFreqData implements Serializable {
    private static final long serialVersionUID = 3173921036839865638L;
    private int mPullFreq;
    private long mTime;

    public TrackPullFreqData() {
    }

    public TrackPullFreqData(long j, int i) {
        this.mTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
        this.mPullFreq = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getTime() {
        return ((Long) jdy.d(Long.valueOf(this.mTime))).longValue();
    }

    public void setTime(long j) {
        this.mTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getPullFreq() {
        return ((Integer) jdy.d(Integer.valueOf(this.mPullFreq))).intValue();
    }

    public void setPullFreq(int i) {
        this.mPullFreq = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        return "TrackPullFreqData{mTime=" + this.mTime + ", mPullFreq=" + this.mPullFreq + '}';
    }
}
