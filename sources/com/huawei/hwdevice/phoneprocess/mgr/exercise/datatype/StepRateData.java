package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class StepRateData implements Serializable {
    private static final long serialVersionUID = -1186336294220451546L;
    private int setpRate;
    private long time;

    public StepRateData() {
    }

    public StepRateData(long j, int i) {
        this.time = j;
        this.setpRate = i;
    }

    public long getTime() {
        return ((Long) jdy.d(Long.valueOf(this.time))).longValue();
    }

    public void setTime(long j) {
        this.time = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getStepRate() {
        return ((Integer) jdy.d(Integer.valueOf(this.setpRate))).intValue();
    }

    public void setStepRate(int i) {
        this.setpRate = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        return "StepRateData [time=" + this.time + ", stepRate=" + this.setpRate + "]";
    }
}
