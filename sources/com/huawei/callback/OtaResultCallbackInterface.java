package com.huawei.callback;

/* loaded from: classes.dex */
public interface OtaResultCallbackInterface {
    void onFileRespond(String str, int i);

    void onFileTransferReport(String str);

    void onFileTransferState(String str, int i);

    void onUpgradeFailed(String str, int i, String str2);
}
