package com.huawei.operation.adapter;

/* loaded from: classes5.dex */
public interface OnCaptureCallback {
    void onCapture(String str);

    void onCustomUserPrefSet(String str, String str2, String str3);

    void onNoGranted(String str);

    void onParseFunction(String str);

    void onShare(String str);

    void onShareMultiple(String str, int i);
}
