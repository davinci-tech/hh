package com.huawei.basefitnessadvice.api;

import com.huawei.basefitnessadvice.callback.DeviceCallback;

/* loaded from: classes3.dex */
public interface BaseSportModelApi {
    void sendCommandToDevice(String str, DeviceCallback deviceCallback);

    void sendCommandToDevice(byte[] bArr, int i, DeviceCallback deviceCallback);
}
