package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.appbundle.remote.RemoteTask;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.List;

/* loaded from: classes8.dex */
final class xo extends RemoteTask {

    /* renamed from: a, reason: collision with root package name */
    private final xz f17747a;
    private final List<String> b;
    private final TaskCompletionSource<Void> d;

    xo(xz xzVar, TaskCompletionSource taskCompletionSource, List<String> list, TaskCompletionSource<Void> taskCompletionSource2) {
        super(taskCompletionSource);
        this.f17747a = xzVar;
        this.b = list;
        this.d = taskCompletionSource2;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        try {
            this.f17747a.b.ee_().deferredUninstall(this.f17747a.f17752a, xz.b(this.b), xz.en_(), new b(this.f17747a, this.d));
        } catch (RemoteException e) {
            xz.e.e(e, "deferredUninstall(%s)", this.b);
            this.d.setException(new RuntimeException(e));
        }
    }

    static class b extends xv<Void> {
        b(xz xzVar, TaskCompletionSource<Void> taskCompletionSource) {
            super(xzVar, taskCompletionSource);
        }

        @Override // defpackage.xv, com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
        public void onDeferredUninstall(Bundle bundle) {
            super.onDeferredUninstall(bundle);
            this.b.setResult(null);
        }
    }
}
