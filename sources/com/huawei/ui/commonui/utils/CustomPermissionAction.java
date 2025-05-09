package com.huawei.ui.commonui.utils;

import android.content.Context;
import android.view.View;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public abstract class CustomPermissionAction extends PermissionsResultAction {
    private static final String TAG = "CustomPermissionAction";
    private final WeakReference<Context> mContextWeakRef;

    public CustomPermissionAction(Context context) {
        this.mContextWeakRef = new WeakReference<>(context);
    }

    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
    public void onDenied(String str) {
        LogUtil.h(TAG, "permission denied by the user");
    }

    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
    public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
        LogUtil.h(TAG, "permission forever denied, show the guide window");
        nsn.e(getContext(), permissionType);
    }

    public void onForeverDenied(PermissionUtil.PermissionType permissionType, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        nsn.cLJ_(getContext(), permissionType, onClickListener, onClickListener2);
    }

    private Context getContext() {
        Context context = this.mContextWeakRef.get();
        return context == null ? BaseApplication.getContext() : context;
    }
}
