package com.huawei.openalliance.ad.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.openalliance.ad.ho;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class ag {

    static final class b implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        boolean f7575a;
        private final LinkedBlockingQueue<IBinder> b;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ho.a("GaidUtil", "onServiceDisconnected %d", Long.valueOf(System.currentTimeMillis()));
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                ho.a("GaidUtil", "onServiceConnected %d", Long.valueOf(System.currentTimeMillis()));
                if (iBinder != null) {
                    this.b.put(iBinder);
                }
            } catch (InterruptedException unused) {
                ho.c("GaidUtil", "onServiceConnected InterruptedException " + System.currentTimeMillis());
            }
        }

        public IBinder a() {
            if (this.f7575a) {
                throw new IllegalStateException();
            }
            this.f7575a = true;
            return this.b.poll(100L, TimeUnit.MILLISECONDS);
        }

        private b() {
            this.f7575a = false;
            this.b = new LinkedBlockingQueue<>(1);
        }
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final String f7574a;
        private final boolean b;

        public boolean b() {
            return this.b;
        }

        public String a() {
            return this.f7574a;
        }

        a(String str, boolean z) {
            this.f7574a = str;
            this.b = z;
        }
    }

    static final class c implements IInterface {

        /* renamed from: a, reason: collision with root package name */
        private IBinder f7576a;

        public boolean b() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.f7576a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f7576a;
        }

        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f7576a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public c(IBinder iBinder) {
            this.f7576a = iBinder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a d(Context context) {
        String str;
        b bVar = new b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, bVar, 1)) {
            ho.a("GaidUtil", "bind ok");
            try {
                try {
                    IBinder a2 = bVar.a();
                    if (a2 == null) {
                        return null;
                    }
                    c cVar = new c(a2);
                    a aVar = new a(cVar.a(), cVar.b());
                    ho.a("GaidUtil", "gaid: %s", cz.g(aVar.f7574a));
                    return aVar;
                } finally {
                    context.unbindService(bVar);
                }
            } catch (RemoteException unused) {
                str = "bind gms service RemoteException";
                ho.d("GaidUtil", str);
                return null;
            } catch (IllegalStateException unused2) {
                str = "bind gms service IllegalStateException";
                ho.d("GaidUtil", str);
                return null;
            } catch (InterruptedException unused3) {
                str = "bind gms service InterruptedException";
                ho.d("GaidUtil", str);
                return null;
            }
        }
        ho.a("GaidUtil", "bind failed");
        return null;
    }

    public static boolean b(Context context) {
        return !com.huawei.openalliance.ad.bz.a(context).d();
    }

    public static a a(final Context context) {
        final cg a2 = cg.a(context);
        if (a2 == null) {
            return null;
        }
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.ag.1
            @Override // java.lang.Runnable
            public void run() {
                a d = ag.d(context);
                if (d != null) {
                    a2.E(d.a());
                    a2.a(Boolean.valueOf(d.b()));
                }
            }
        });
        String J = a2.J();
        Boolean K = a2.K();
        if (cz.b(J) || K == null) {
            return null;
        }
        return new a(J, K.booleanValue());
    }
}
