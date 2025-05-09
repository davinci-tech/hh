package com.huawei.wearengine.sensor;

import com.huawei.wearengine.sensor.AsyncReadCallback;
import defpackage.tqs;

/* loaded from: classes9.dex */
public class SensorClient$4 extends AsyncReadCallback.Stub {
    final /* synthetic */ tqs this$0;
    final /* synthetic */ SensorReadCallback val$sensorReadCallback;

    SensorClient$4(tqs tqsVar, SensorReadCallback sensorReadCallback) {
        this.this$0 = tqsVar;
        this.val$sensorReadCallback = sensorReadCallback;
    }

    @Override // com.huawei.wearengine.sensor.AsyncReadCallback
    public void onReadResult(int i, DataResult dataResult) {
        SensorReadCallback sensorReadCallback = this.val$sensorReadCallback;
        if (sensorReadCallback != null) {
            sensorReadCallback.onReadResult(i, dataResult);
        }
    }
}
