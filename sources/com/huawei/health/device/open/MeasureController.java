package com.huawei.health.device.open;

import android.os.Bundle;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public abstract class MeasureController {
    private static final String TAG = "MeasureController";

    public abstract void cleanup();

    public abstract void ending();

    public abstract boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle);

    public abstract boolean start();

    public void updateState(int i) {
        LogUtil.a(TAG, " update state to ", Integer.valueOf(i));
    }
}
