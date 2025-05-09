package com.huawei.health.icommon;

import android.hardware.SensorEventListener;

/* loaded from: classes.dex */
public interface HwHealthSensorEventListener extends SensorEventListener {
    void dataReport(int i, boolean z);

    void notifyStepCounterError(boolean z);
}
