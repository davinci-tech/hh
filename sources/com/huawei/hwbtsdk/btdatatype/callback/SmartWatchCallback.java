package com.huawei.hwbtsdk.btdatatype.callback;

/* loaded from: classes5.dex */
public interface SmartWatchCallback {
    void onAckReceived(String str, int i, byte[] bArr);

    void onDataReceived(String str, int i, byte[] bArr);

    void onDeviceConnectionStateChanged(String str, int i);
}
