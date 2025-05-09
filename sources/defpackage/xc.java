package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* loaded from: classes8.dex */
final class xc implements ServiceConnection {
    final xb c;

    xc(xb xbVar) {
        this.c = xbVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.c.b.e("RemoteServiceConnection.onServiceConnected(%s)", componentName);
        this.c.b(new xi(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.c.b.e("RemoteServiceConnection.onServiceDisconnected(%s)", componentName);
        this.c.b(new xg(this));
    }
}
