package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.android.bundlecore.install.remote.DefaultTask;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public final class za extends DefaultTask {
    private final List<Bundle> b;

    public za(ISplitInstallServiceCallback iSplitInstallServiceCallback, List<Bundle> list) {
        super("OnDeferredInstallTask", iSplitInstallServiceCallback);
        this.b = list;
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask
    public void execute(ModuleInstallSupervisor moduleInstallSupervisor) throws RemoteException {
        moduleInstallSupervisor.deferredInstall(this.b, this);
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask, com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onDeferredInstall(Bundle bundle) {
        super.onDeferredInstall(bundle);
        if (this.mCallback != null) {
            try {
                this.mCallback.onDeferredInstall(bundle);
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "onDeferredInstall RemoteException");
            }
        }
    }
}
