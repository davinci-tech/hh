package com.huawei.health.device.api;

import com.huawei.health.device.open.MeasureKit;

/* loaded from: classes3.dex */
public interface MeasureKitManagerApi {
    MeasureKit getHealthDeviceKit(String str);

    com.huawei.hihealth.device.open.MeasureKit getHealthDeviceKitUniversal(String str);

    void registerExternalKit(String str, String str2);
}
