package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.android.appbundle.remote.RemoteTask;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
final class xm extends RemoteTask {
    private final TaskCompletionSource<List<InstallSessionState>> b;
    private final xz c;

    xm(xz xzVar, TaskCompletionSource taskCompletionSource, TaskCompletionSource<List<InstallSessionState>> taskCompletionSource2) {
        super(taskCompletionSource);
        this.c = xzVar;
        this.b = taskCompletionSource2;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        try {
            this.c.b.ee_().getSessionStates(this.c.f17752a, new d(this.c, this.b));
        } catch (RemoteException e) {
            xz.e.e(e, "getSessionStates", new Object[0]);
            this.b.setException(new RuntimeException(e));
        }
    }

    static class d extends xv<List<InstallSessionState>> {
        d(xz xzVar, TaskCompletionSource<List<InstallSessionState>> taskCompletionSource) {
            super(xzVar, taskCompletionSource);
        }

        @Override // defpackage.xv, com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
        public void onGetSessionStates(List<Bundle> list) {
            super.onGetSessionStates(list);
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<Bundle> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(xx.eo_(it.next()));
            }
            this.b.setResult(arrayList);
        }
    }
}
