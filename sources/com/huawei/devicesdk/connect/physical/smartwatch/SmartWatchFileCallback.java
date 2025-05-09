package com.huawei.devicesdk.connect.physical.smartwatch;

import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;

/* loaded from: classes3.dex */
public interface SmartWatchFileCallback {
    void sendBtFilePath(String str);

    void setFileCallback(BtDeviceResponseCallback btDeviceResponseCallback);

    void setPathExtendNum(int i);

    void setSmartWatchAssetCallback(BtDeviceResponseCallback btDeviceResponseCallback);
}
