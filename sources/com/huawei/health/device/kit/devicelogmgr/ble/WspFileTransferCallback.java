package com.huawei.health.device.kit.devicelogmgr.ble;

/* loaded from: classes3.dex */
public interface WspFileTransferCallback {
    String getFilePath();

    void onFailure(int i, String str);

    void onProgress(int i, int i2, String str);

    void onStart(int i);

    void onSuccess(int i, int i2, String str);
}
