package com.huawei.devicesdk.callback;

import com.huawei.devicesdk.entity.DeviceInfo;
import defpackage.biu;

/* loaded from: classes3.dex */
public interface MessageReceiveCallback {
    void onChannelEnable(DeviceInfo deviceInfo, String str, int i);

    void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i);
}
