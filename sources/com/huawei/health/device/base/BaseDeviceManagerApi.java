package com.huawei.health.device.base;

import com.huawei.btsportdevice.callback.MessageOrStateCallback;

/* loaded from: classes3.dex */
public interface BaseDeviceManagerApi {
    String getCurrentMacAddress();

    void init();

    void releaseResource();

    void removeMessageOrStateCallback(String str, boolean z);

    void setFitnessMachineControl(int i, int i2, int[] iArr);

    void setFitnessMachineControl(int i, int[] iArr);

    void setMessageOrStateCallback(String str, MessageOrStateCallback messageOrStateCallback, boolean z);
}
