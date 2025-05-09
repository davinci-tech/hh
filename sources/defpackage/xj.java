package defpackage;

import com.huawei.android.appbundle.remote.RemoteTask;

/* loaded from: classes8.dex */
final class xj extends RemoteTask {
    private final xb c;

    xj(xb xbVar) {
        this.c = xbVar;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        this.c.a();
    }
}
