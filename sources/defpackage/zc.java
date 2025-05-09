package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.android.bundlecore.install.remote.DefaultTask;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public final class zc extends DefaultTask {
    private final List<Bundle> e;

    public zc(ISplitInstallServiceCallback iSplitInstallServiceCallback, List<Bundle> list) {
        super("OnStartInstallTask", iSplitInstallServiceCallback);
        this.e = list;
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask
    public void execute(ModuleInstallSupervisor moduleInstallSupervisor) throws RemoteException {
        moduleInstallSupervisor.startInstall(this.e, this);
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask, com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onStartInstall(int i, Bundle bundle) {
        super.onStartInstall(i, bundle);
        if (this.mCallback != null) {
            try {
                this.mCallback.onStartInstall(i, bundle);
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "onStartInstall RemoteException");
            }
        }
    }
}
