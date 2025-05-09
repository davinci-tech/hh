package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.appbundle.remote.RemoteTask;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.List;

/* loaded from: classes8.dex */
final class ya extends RemoteTask {
    private final xz b;
    private final TaskCompletionSource<Integer> c;
    private final List<String> e;

    ya(xz xzVar, TaskCompletionSource taskCompletionSource, List<String> list, TaskCompletionSource<Integer> taskCompletionSource2) {
        super(taskCompletionSource);
        this.b = xzVar;
        this.e = list;
        this.c = taskCompletionSource2;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        try {
            this.b.b.ee_().startInstall(this.b.f17752a, xz.b(this.e), xz.en_(), new a(this.b, this.c));
        } catch (RemoteException e) {
            xz.e.e(e, "startInstall(%s)", this.e);
            this.c.setException(new RuntimeException(e));
        }
    }

    static class a extends xv<Integer> {
        a(xz xzVar, TaskCompletionSource<Integer> taskCompletionSource) {
            super(xzVar, taskCompletionSource);
        }

        @Override // defpackage.xv, com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
        public void onStartInstall(int i, Bundle bundle) {
            super.onStartInstall(i, bundle);
            this.b.setResult(Integer.valueOf(i));
        }
    }
}
