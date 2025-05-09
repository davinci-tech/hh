package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.android.bundlecore.install.remote.DefaultTask;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import health.compact.a.LogUtil;

/* loaded from: classes8.dex */
public final class yy extends DefaultTask {

    /* renamed from: a, reason: collision with root package name */
    private final int f17769a;

    public yy(ISplitInstallServiceCallback iSplitInstallServiceCallback, int i) {
        super("OnCancelInstallTask", iSplitInstallServiceCallback);
        this.f17769a = i;
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask
    public void execute(ModuleInstallSupervisor moduleInstallSupervisor) throws RemoteException {
        moduleInstallSupervisor.cancelInstall(this.f17769a, this);
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask, com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onCancelInstall(int i, Bundle bundle) {
        super.onCancelInstall(i, bundle);
        if (this.mCallback != null) {
            try {
                this.mCallback.onCancelInstall(i, bundle);
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "onCancelInstall RemoteException");
            }
        }
    }
}
