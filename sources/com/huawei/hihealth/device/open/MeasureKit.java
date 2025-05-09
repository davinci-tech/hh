package com.huawei.hihealth.device.open;

import android.content.Context;
import com.huawei.hihealth.device.open.HealthDevice;

/* loaded from: classes.dex */
public abstract class MeasureKit {
    public MeasureController getBackgroundController() {
        return null;
    }

    public DeviceProvider getDeviceProvider() {
        return null;
    }

    public abstract HealthDevice.HEALTH_DEVICE_KIND getHealthDataKind();

    public MeasureController getMeasureController() {
        return null;
    }

    public abstract String getUuid();

    protected MeasureKit(Context context) {
    }
}
