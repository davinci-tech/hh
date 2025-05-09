package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.android.bundlecore.install.remote.DefaultTask;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import health.compact.a.LogUtil;

/* loaded from: classes8.dex */
public class yz extends DefaultTask {
    private final int d;

    public yz(ISplitInstallServiceCallback iSplitInstallServiceCallback, int i) {
        super("OnGetSessionStateTask", iSplitInstallServiceCallback);
        this.d = i;
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask
    public void execute(ModuleInstallSupervisor moduleInstallSupervisor) throws RemoteException {
        moduleInstallSupervisor.getSessionState(this.d, this);
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask, com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onGetSession(int i, Bundle bundle) {
        super.onGetSession(i, bundle);
        if (this.mCallback != null) {
            try {
                this.mCallback.onGetSession(i, bundle);
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "onGetSession RemoteException");
            }
        }
    }
}
