package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

/* loaded from: classes5.dex */
public enum ProgressCode {
    SUCCESS(0),
    DOWNLOAD_NETWORK_FAILED(1),
    DOWNLOAD_OTHER_FAILED(2),
    TRANSFER_DISCONNECT_FAILED(3),
    TRANSFER_OTHER_FAILED(4),
    APPLY_OTHER_FAILED(5),
    CANCELED(6),
    CANCEL_FAIL(7),
    FILE_LIMITED(8),
    DEVICE_NO_SPACE(9);

    private final int mValue;

    ProgressCode(int i) {
        this.mValue = i;
    }

    public int value() {
        return this.mValue;
    }
}
