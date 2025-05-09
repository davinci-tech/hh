package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.android.bundlecore.install.remote.DefaultTask;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public final class zd extends DefaultTask {

    /* renamed from: a, reason: collision with root package name */
    private final List<Bundle> f17770a;

    public zd(ISplitInstallServiceCallback iSplitInstallServiceCallback, List<Bundle> list) {
        super("OnDeferredUninstallTask", iSplitInstallServiceCallback);
        this.f17770a = list;
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask
    public void execute(ModuleInstallSupervisor moduleInstallSupervisor) throws RemoteException {
        moduleInstallSupervisor.deferredUninstall(this.f17770a, this);
    }

    @Override // com.huawei.android.bundlecore.install.remote.DefaultTask, com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor.Callback
    public void onDeferredUninstall(Bundle bundle) {
        super.onDeferredUninstall(bundle);
        if (this.mCallback != null) {
            try {
                this.mCallback.onDeferredUninstall(bundle);
            } catch (RemoteException unused) {
                LogUtil.e(this.mTag, "onDeferredUninstall RemoteException");
            }
        }
    }
}
