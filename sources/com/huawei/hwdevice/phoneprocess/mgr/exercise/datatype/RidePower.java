package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class RidePower implements TimeSequence, Serializable {
    private static final long serialVersionUID = -494849147409643364L;
    private int mPower;
    private long mTime;

    public int getPower() {
        return this.mPower;
    }

    public void setPower(int i) {
        this.mPower = i;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.mTime;
    }

    public String toString() {
        return "RidePower [time=" + this.mTime + ", powerValue=" + this.mPower + "]";
    }
}
