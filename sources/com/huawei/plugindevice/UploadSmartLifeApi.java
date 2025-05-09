package com.huawei.plugindevice;

import com.huawei.health.device.connectivity.comm.MeasurableDevice;

/* loaded from: classes6.dex */
public interface UploadSmartLifeApi {
    void delete(String str, String str2);

    void upload(String str, String str2, MeasurableDevice measurableDevice);

    void upload(String str, String str2, String str3, MeasurableDevice measurableDevice);
}
