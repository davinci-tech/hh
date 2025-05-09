package com.huawei.hwdevice.phoneprocess.mgr.notification;

/* loaded from: classes5.dex */
public enum SensitivePermissionStatus {
    RESTART("restart"),
    COMPLETE("complete");

    private final String value;

    SensitivePermissionStatus(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
