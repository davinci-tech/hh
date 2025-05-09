package com.huawei.health.h5pro.ext.permission;

import android.content.Context;

/* loaded from: classes.dex */
public interface H5ProPermissionExtApi {
    void checkAndRequestPermissions(Context context, String[] strArr, boolean z, H5ProPermissionRequestCallback h5ProPermissionRequestCallback);

    boolean isSupport(String[] strArr);

    void notifyPermissionsChange(String[] strArr, int[] iArr);
}
