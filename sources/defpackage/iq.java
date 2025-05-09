package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.sdk.m.k0.a;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class iq implements com.alipay.sdk.m.b.b {

    public static final class d implements ServiceConnection {
        public final LinkedBlockingQueue<IBinder> d;
        public boolean e;

        public d() {
            this.e = false;
            this.d = new LinkedBlockingQueue<>();
        }

        public IBinder a() throws InterruptedException {
            if (this.e) {
                throw new IllegalStateException();
            }
            this.e = true;
            return this.d.poll(5L, TimeUnit.SECONDS);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.d.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    @Override // com.alipay.sdk.m.b.b
    public String a(Context context) {
        d dVar = new d();
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        if (context.bindService(intent, dVar, 1)) {
            try {
                return a.AbstractBinderC0009a.a(dVar.a()).a();
            } catch (Exception unused) {
            } finally {
                context.unbindService(dVar);
            }
        }
        return null;
    }
}
