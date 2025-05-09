package defpackage;

import android.os.IBinder;
import com.huawei.android.appbundle.remote.RemoteTask;

/* loaded from: classes8.dex */
final class xi extends RemoteTask {

    /* renamed from: a, reason: collision with root package name */
    private final IBinder f17744a;
    private final xc e;

    xi(xc xcVar, IBinder iBinder) {
        this.e = xcVar;
        this.f17744a = iBinder;
    }

    @Override // com.huawei.android.appbundle.remote.RemoteTask
    public void execute() {
        this.e.c.ed_(this.f17744a);
    }
}
