package com.huawei.android.bundlecore.install.remote;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import defpackage.yk;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class DefaultTask implements Runnable, ModuleInstallSupervisor.Callback {
    private static final int INTERNAL_ERROR = -100;
    protected final ISplitInstallServiceCallback mCallback;
    protected final String mTag;

    protected abstract void execute(ModuleInstallSupervisor moduleInstallSupervisor) throws RemoteException;

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onCancelInstall(int i, Bundle bundle) {
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onDeferredInstall(Bundle bundle) {
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onDeferredUninstall(Bundle bundle) {
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onGetSession(int i, Bundle bundle) {
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onGetSessionStates(List<Bundle> list) {
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onStartInstall(int i, Bundle bundle) {
    }

    public DefaultTask(String str, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
        this.mTag = str;
        this.mCallback = iSplitInstallServiceCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        ModuleInstallSupervisor a2 = yk.a();
        if (a2 != null) {
            try {
                execute(a2);
                return;
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "run execute RemoteException");
                return;
            }
        }
        onError(ModuleInstallSupervisor.bundleErrorCode(-100));
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onError(Bundle bundle) {
        ISplitInstallServiceCallback iSplitInstallServiceCallback = this.mCallback;
        if (iSplitInstallServiceCallback != null) {
            try {
                iSplitInstallServiceCallback.onError(bundle);
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "onError RemoteException");
            }
        }
    }
}
