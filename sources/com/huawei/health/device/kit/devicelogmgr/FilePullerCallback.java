package com.huawei.health.device.kit.devicelogmgr;

/* loaded from: classes3.dex */
public interface FilePullerCallback {
    public static final int STATUS_SYNC_DEVICE_LOG_FAIL = -3;

    void onFail(int i);

    void onProgress(int i, String str);

    void onSuccess(Object obj);
}
