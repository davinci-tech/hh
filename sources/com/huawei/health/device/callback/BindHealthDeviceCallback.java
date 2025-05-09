package com.huawei.health.device.callback;

import android.os.Bundle;
import com.huawei.health.device.model.HealthDevice;

/* loaded from: classes3.dex */
public interface BindHealthDeviceCallback extends IHealthDeviceCallback {
    void onStatusChanged(HealthDevice healthDevice, int i, Bundle bundle);
}
