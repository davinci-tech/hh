package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.android.bundlecore.install.remote.DefaultTask;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public final class zb extends DefaultTask {
    public zb(ISplitInstallServiceCallback iSplitInstallServiceCallback) {
        super("OnGetSessionStatesTask", iSplitInstallServiceCallback);
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask
    public void execute(ModuleInstallSupervisor moduleInstallSupervisor) throws RemoteException {
        moduleInstallSupervisor.getSessionStates(this);
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask, com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onGetSessionStates(List<Bundle> list) {
        super.onGetSessionStates(list);
        if (this.mCallback != null) {
            try {
                this.mCallback.onGetSessionStates(list);
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "onGetSessionStates RemoteException");
            }
        }
    }
}
