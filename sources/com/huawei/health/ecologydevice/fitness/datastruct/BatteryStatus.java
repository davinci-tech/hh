package com.huawei.health.ecologydevice.fitness.datastruct;

/* loaded from: classes3.dex */
public class BatteryStatus extends BaseRopeData {
    public BatteryStatus() {
        super(21);
    }

    public void setButteryStatus(int i) {
        this.mFitnessData.put(20017, Integer.valueOf(i));
    }
}
