package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.appbundle.remote.RemoteTask;
import com.huawei.hmf.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class xn extends RemoteTask {

    /* renamed from: a, reason: collision with root package name */
    private final xz f17746a;
    private final int b;
    private final TaskCompletionSource<Void> e;

    xn(xz xzVar, TaskCompletionSource<Void> taskCompletionSource, int i, TaskCompletionSource<Void> taskCompletionSource2) {
        super(taskCompletionSource);
        this.f17746a = xzVar;
        this.b = i;
        this.e = taskCompletionSource2;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        try {
            this.f17746a.b.ee_().cancelInstall(this.f17746a.f17752a, this.b, xz.en_(), new b(this.f17746a, this.e));
        } catch (RemoteException e) {
            xz.e.e(e, "cancelInstall(%d)", Integer.valueOf(this.b));
            this.e.setException(new RuntimeException(e));
        }
    }

    static class b extends xv<Void> {
        b(xz xzVar, TaskCompletionSource<Void> taskCompletionSource) {
            super(xzVar, taskCompletionSource);
        }

        @Override // defpackage.xv, com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
        public void onCancelInstall(int i, Bundle bundle) {
            super.onCancelInstall(i, bundle);
            this.b.setResult(null);
        }
    }
}
