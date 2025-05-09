package com.huawei.health.h5pro.jsbridge.system.permission;

import android.content.Context;
import com.huawei.health.h5pro.jsbridge.system.permission.AndroidPermission;

/* loaded from: classes3.dex */
public interface IPermission {
    void destroy();

    void onMount(Context context);

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    void requestPermissions(String[] strArr, AndroidPermission.PermissionCallback permissionCallback);
}
