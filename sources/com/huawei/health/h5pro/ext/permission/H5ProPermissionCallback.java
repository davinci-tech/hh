package com.huawei.health.h5pro.ext.permission;

/* loaded from: classes3.dex */
public interface H5ProPermissionCallback {
    void onDenied(String str);

    void onForeverDenied(String[] strArr);

    void onGranted();
}
