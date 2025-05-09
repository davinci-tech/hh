package com.huawei.health.device.open;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.HealthDataParser;

/* loaded from: classes3.dex */
public abstract class MeasureKit {
    public MeasureController getBackgroundController() {
        return null;
    }

    public abstract HealthDevice.HealthDeviceKind getHealthDataKind();

    public abstract HealthDataParser getHealthDataParser();

    public MeasureController getMeasureController() {
        return null;
    }

    public abstract String getUuid();
}
