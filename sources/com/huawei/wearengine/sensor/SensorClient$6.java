package com.huawei.wearengine.sensor;

import com.huawei.wearengine.sensor.AsyncStopCallback;
import defpackage.tqs;

/* loaded from: classes9.dex */
public class SensorClient$6 extends AsyncStopCallback.Stub {
    final /* synthetic */ tqs this$0;
    final /* synthetic */ SensorStopCallback val$sensorStopCallback;

    SensorClient$6(tqs tqsVar, SensorStopCallback sensorStopCallback) {
        this.this$0 = tqsVar;
        this.val$sensorStopCallback = sensorStopCallback;
    }

    @Override // com.huawei.wearengine.sensor.AsyncStopCallback
    public void onStopResult(int i) {
        SensorStopCallback sensorStopCallback = this.val$sensorStopCallback;
        if (sensorStopCallback != null) {
            sensorStopCallback.onStopResult(i);
        }
    }
}
