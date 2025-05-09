package com.huawei.hwcommonmodel.utils.permission;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import health.compact.a.CommonUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class PermissionsResultAction {
    private static final String TAG = "PermissionsResultAction";
    private Looper mMyLooper;
    private PermissionUtil.PermissionType mPermissionType;
    private final Set<String> mPermissionsSet;

    public abstract void onDenied(String str);

    public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
    }

    public abstract void onGranted();

    public PermissionsResultAction() {
        this.mPermissionsSet = new HashSet();
        this.mMyLooper = Looper.getMainLooper();
    }

    public PermissionsResultAction(Looper looper) {
        this.mPermissionsSet = new HashSet();
        Looper.getMainLooper();
        this.mMyLooper = looper;
    }

    public boolean shouldIgnorePermissionNotFound(String str) {
        synchronized (this) {
            Log.d(TAG, "Permission not found: " + str);
        }
        return true;
    }

    public final boolean onResult(String str, int i) {
        synchronized (this) {
            if (i == 0) {
                return onResult(str, Permissions.GRANTED);
            }
            return onResult(str, Permissions.DENIED);
        }
    }

    public final boolean onResult(final String str, Permissions permissions) {
        synchronized (this) {
            this.mPermissionsSet.remove(str);
            if (permissions == Permissions.GRANTED) {
                if (PermissionUtil.d(str)) {
                    CommonUtil.o(BaseApplication.e(), str);
                }
                if (this.mPermissionsSet.isEmpty()) {
                    new Handler(this.mMyLooper).post(new Runnable() { // from class: com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction.4
                        @Override // java.lang.Runnable
                        public void run() {
                            PermissionsResultAction.this.onGranted();
                        }
                    });
                    return true;
                }
            } else {
                if (permissions == Permissions.DENIED) {
                    new Handler(this.mMyLooper).post(new Runnable() { // from class: com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Activity wa_ = BaseApplication.wa_();
                            if (CommonUtil.av() && PermissionUtil.d(str) && wa_ != null && !wa_.shouldShowRequestPermissionRationale(str)) {
                                PermissionsResultAction permissionsResultAction = PermissionsResultAction.this;
                                permissionsResultAction.onForeverDenied(permissionsResultAction.mPermissionType);
                            } else {
                                PermissionsResultAction.this.onDenied(str);
                            }
                        }
                    });
                    return true;
                }
                if (permissions != Permissions.NOT_FOUND) {
                    return false;
                }
                if (shouldIgnorePermissionNotFound(str)) {
                    if (this.mPermissionsSet.isEmpty()) {
                        new Handler(this.mMyLooper).post(new Runnable() { // from class: com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction.1
                            @Override // java.lang.Runnable
                            public void run() {
                                PermissionsResultAction.this.onGranted();
                            }
                        });
                        return true;
                    }
                } else {
                    new Handler(this.mMyLooper).post(new Runnable() { // from class: com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction.3
                        @Override // java.lang.Runnable
                        public void run() {
                            PermissionsResultAction.this.onDenied(str);
                        }
                    });
                    return true;
                }
            }
            return false;
        }
    }

    public final void registerPermissions(String[] strArr) {
        synchronized (this) {
            Collections.addAll(this.mPermissionsSet, strArr);
        }
    }

    public void setPermissionType(PermissionUtil.PermissionType permissionType) {
        this.mPermissionType = permissionType;
    }
}
