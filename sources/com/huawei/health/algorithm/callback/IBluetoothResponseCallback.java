package com.huawei.health.algorithm.callback;

/* loaded from: classes3.dex */
public interface IBluetoothResponseCallback {
    void onFailure(int i, String str);

    void onProgress(int i, String str);

    void onSuccess(int i, String str);
}
