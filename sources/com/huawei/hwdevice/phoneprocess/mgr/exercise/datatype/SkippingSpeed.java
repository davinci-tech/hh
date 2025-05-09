package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class SkippingSpeed implements Serializable, TimeSequence {
    private static final long serialVersionUID = 7816239952862604274L;
    private int mSpeed;
    private long mTime;

    public SkippingSpeed() {
    }

    public SkippingSpeed(long j, int i) {
        this.mSpeed = i;
        this.mTime = j;
    }

    public int getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(int i) {
        this.mSpeed = i;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.mTime;
    }

    public String toString() {
        return "SkippingSpeed [time=" + this.mTime + ", heartRate=" + this.mSpeed + "]";
    }
}
