package com.huawei.operation.h5pro.bridgeimpl;

import android.content.Context;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionExtApi;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionRequestCallback;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import defpackage.jeg;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class PermissionImpl implements H5ProPermissionExtApi {
    private static final String TAG = "H5pro_PermissionImpl";

    @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionExtApi
    public void checkAndRequestPermissions(Context context, String[] strArr, boolean z, H5ProPermissionRequestCallback h5ProPermissionRequestCallback) {
        LogUtil.i(TAG, "checkAndRequestPermissions enter");
        PermissionUtil.PermissionType permissionType = getPermissionType(strArr);
        if (permissionType == PermissionUtil.PermissionType.NONE) {
            LogUtil.w(TAG, "checkAndRequestPermissions onDefault: notMatchPermissionType");
            h5ProPermissionRequestCallback.onDefault();
        } else {
            doActionWithPermissions(context, strArr, permissionType, z, h5ProPermissionRequestCallback);
        }
    }

    @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionExtApi
    public boolean isSupport(String[] strArr) {
        return PermissionUtil.PermissionType.NONE != getPermissionType(strArr);
    }

    @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionExtApi
    public void notifyPermissionsChange(String[] strArr, int[] iArr) {
        jeg.d().d(strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doActionWithPermissions(final Context context, final String[] strArr, final PermissionUtil.PermissionType permissionType, final boolean z, final H5ProPermissionRequestCallback h5ProPermissionRequestCallback) {
        final List asList = Arrays.asList(strArr);
        PermissionUtil.b(context, permissionType, new PermissionsResultAction() { // from class: com.huawei.operation.h5pro.bridgeimpl.PermissionImpl.1
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.i(PermissionImpl.TAG, "checkAndRequestPermissions: onGranted");
                if (asList.contains("android.permission.ACCESS_BACKGROUND_LOCATION") && permissionType != PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) {
                    PermissionImpl.this.doActionWithPermissions(context, strArr, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND, z, h5ProPermissionRequestCallback);
                } else {
                    h5ProPermissionRequestCallback.onGranted();
                }
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.i(PermissionImpl.TAG, "checkAndRequestPermissions: onDenied -> " + str);
                h5ProPermissionRequestCallback.onDenied(str);
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType2) {
                LogUtil.i(PermissionImpl.TAG, "checkAndRequestPermissions: onForeverDenied -> " + permissionType2);
                super.onForeverDenied(permissionType2);
                h5ProPermissionRequestCallback.onForeverDenied(strArr);
                if (z) {
                    nsn.cLK_(context, permissionType2, null, null, null);
                }
            }
        });
    }

    private PermissionUtil.PermissionType getPermissionType(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return PermissionUtil.PermissionType.NONE;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        if (arrayList.contains("android.permission.ACCESS_BACKGROUND_LOCATION")) {
            if (arrayList.size() == 1) {
                return PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND;
            }
            arrayList.remove("android.permission.ACCESS_BACKGROUND_LOCATION");
        }
        for (PermissionUtil.PermissionType permissionType : PermissionUtil.PermissionType.values()) {
            if (isPermissionMatch(arrayList, PermissionUtil.c(permissionType))) {
                return permissionType;
            }
        }
        return PermissionUtil.PermissionType.NONE;
    }

    private boolean isPermissionMatch(List<String> list, String[] strArr) {
        if (list.size() != strArr.length) {
            return false;
        }
        return Arrays.asList(strArr).containsAll(list);
    }
}
