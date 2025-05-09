package com.huawei.android.appbundle.splitinstall.protocol;

import android.os.IBinder;
import android.os.IInterface;
import com.huawei.android.appbundle.binder.BinderWrapper;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallService;
import defpackage.ye;

/* loaded from: classes8.dex */
public abstract class SplitInstallServiceHolder extends BinderWrapper implements SplitInstallServiceProxy {
    protected SplitInstallServiceHolder(String str) {
        super(str);
    }

    public static SplitInstallServiceProxy queryLocalInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(ISplitInstallService.DESCRIPTOR);
        return queryLocalInterface instanceof SplitInstallServiceProxy ? (SplitInstallServiceProxy) queryLocalInterface : new ye(iBinder);
    }
}
