package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.appbundle.remote.RemoteTask;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.hmf.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class xl extends RemoteTask {

    /* renamed from: a, reason: collision with root package name */
    private final int f17745a;
    private final TaskCompletionSource<InstallSessionState> b;
    private final xz c;

    xl(xz xzVar, TaskCompletionSource taskCompletionSource, int i, TaskCompletionSource<InstallSessionState> taskCompletionSource2) {
        super(taskCompletionSource);
        this.c = xzVar;
        this.f17745a = i;
        this.b = taskCompletionSource2;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        try {
            this.c.b.ee_().getSessionState(this.c.f17752a, this.f17745a, new d(this.c, this.b));
        } catch (RemoteException e) {
            xz.e.e(e, "getSessionState(%d)", Integer.valueOf(this.f17745a));
            this.b.setException(new RuntimeException(e));
        }
    }

    static class d extends xv<InstallSessionState> {
        d(xz xzVar, TaskCompletionSource<InstallSessionState> taskCompletionSource) {
            super(xzVar, taskCompletionSource);
        }

        @Override // defpackage.xv, com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
        public void onGetSession(int i, Bundle bundle) {
            super.onGetSession(i, bundle);
            this.b.setResult(xx.eo_(bundle));
        }
    }
}
