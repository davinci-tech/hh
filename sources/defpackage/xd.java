package defpackage;

import com.huawei.android.appbundle.remote.RemoteTask;

/* loaded from: classes8.dex */
final class xd extends RemoteTask {

    /* renamed from: a, reason: collision with root package name */
    private final RemoteTask f17742a;
    private final xb d;

    xd(xb xbVar, RemoteTask remoteTask) {
        this.d = xbVar;
        this.f17742a = remoteTask;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        this.d.e(this.f17742a);
    }
}
