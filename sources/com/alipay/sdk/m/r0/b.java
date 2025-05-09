package com.alipay.sdk.m.r0;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.alipay.sdk.m.q0.a;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f870a = false;
    public static String c = "OpenDeviceId library";
    public a d;
    public ServiceConnection e;
    public Context b = null;
    public InterfaceC0012b h = null;

    /* renamed from: com.alipay.sdk.m.r0.b$b, reason: collision with other inner class name */
    public interface InterfaceC0012b<T> {
        void a(T t, b bVar);
    }

    public class c implements ServiceConnection {
        public c() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (this) {
                b.this.d = a.AbstractBinderC0011a.a(iBinder);
                if (b.this.h != null) {
                    b.this.h.a("Deviceid Service Connected", b.this);
                }
                b.this.d("Service onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            b.this.d = null;
            b.this.d("Service onServiceDisconnected");
        }
    }

    public String b() {
        if (this.b == null) {
            a("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        try {
            a aVar = this.d;
            if (aVar != null) {
                return aVar.a();
            }
            return null;
        } catch (RemoteException e) {
            a("getOAID error, RemoteException!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean d() {
        try {
            if (this.d == null) {
                return false;
            }
            d("Device support opendeviceid");
            return this.d.c();
        } catch (RemoteException unused) {
            a("isSupport error, RemoteException!");
            return false;
        }
    }

    public int c(Context context, InterfaceC0012b<String> interfaceC0012b) {
        if (context != null) {
            this.b = context;
            this.h = interfaceC0012b;
            this.e = new c();
            Intent intent = new Intent();
            intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
            if (this.b.bindService(intent, this.e, 1)) {
                d("bindService Successful!");
                return 1;
            }
            d("bindService Failed!");
            return -1;
        }
        throw new NullPointerException("Context can not be null.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (f870a) {
            Log.i(c, str);
        }
    }

    private void a(String str) {
        if (f870a) {
            Log.e(c, str);
        }
    }
}
