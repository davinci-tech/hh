package com.huawei.aifitness.mobilesensor;

import android.hardware.SensorEvent;

/* loaded from: classes2.dex */
public interface AccGyroListener {
    void getAcc(SensorEvent sensorEvent, int i);

    void getGyro(SensorEvent sensorEvent);
}
