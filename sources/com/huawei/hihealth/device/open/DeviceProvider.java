package com.huawei.hihealth.device.open;

/* loaded from: classes.dex */
public interface DeviceProvider {
    boolean isDeviceAvailable(String str);

    boolean scanDevice(IDeviceEventHandler iDeviceEventHandler);

    boolean stopScanDevice();
}
