package defpackage;

import android.os.IBinder;

/* loaded from: classes8.dex */
final class xe implements IBinder.DeathRecipient {
    private final xb d;

    xe(xb xbVar) {
        this.d = xbVar;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        this.d.c();
    }
}
