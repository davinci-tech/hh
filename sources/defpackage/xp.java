package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.appbundle.remote.RemoteTask;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.List;

/* loaded from: classes8.dex */
final class xp extends RemoteTask {
    private final TaskCompletionSource<Void> c;
    private final List<String> d;
    private final xz e;

    xp(xz xzVar, TaskCompletionSource taskCompletionSource, List<String> list, TaskCompletionSource<Void> taskCompletionSource2) {
        super(taskCompletionSource);
        this.e = xzVar;
        this.d = list;
        this.c = taskCompletionSource2;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        try {
            this.e.b.ee_().deferredInstall(this.e.f17752a, xz.b(this.d), xz.en_(), new d(this.e, this.c));
        } catch (RemoteException e) {
            xz.e.e(e, "deferredInstall(%s)", this.d);
            this.c.setException(new RuntimeException(e));
        }
    }

    static class d extends xv<Void> {
        d(xz xzVar, TaskCompletionSource<Void> taskCompletionSource) {
            super(xzVar, taskCompletionSource);
        }

        @Override // defpackage.xv, com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
        public void onDeferredInstall(Bundle bundle) {
            super.onDeferredInstall(bundle);
            this.b.setResult(null);
        }
    }
}
