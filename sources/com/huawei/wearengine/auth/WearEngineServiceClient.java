package com.huawei.wearengine.auth;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.wearengine.auth.AuthListenerManager;
import defpackage.tos;
import defpackage.tot;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes9.dex */
public class WearEngineServiceClient {
    private static final Object b = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f11229a;
    private AuthListenerManager c = null;
    private AtomicBoolean h = new AtomicBoolean(false);
    private boolean d = false;
    private ServiceConnection i = new ServiceConnection() { // from class: com.huawei.wearengine.auth.WearEngineServiceClient.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            tos.a("WearEngineServiceClient", "onServiceConnected success!");
            WearEngineServiceClient.this.c = AuthListenerManager.Stub.asInterface(iBinder);
            WearEngineServiceClient.this.h.getAndSet(true);
            synchronized (WearEngineServiceClient.e) {
                WearEngineServiceClient.this.d = true;
                WearEngineServiceClient.e.notifyAll();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            tos.a("WearEngineServiceClient", "onServiceDisconnected success!");
            WearEngineServiceClient.this.c = null;
            WearEngineServiceClient.this.h.getAndSet(false);
            synchronized (WearEngineServiceClient.e) {
                WearEngineServiceClient.this.d = true;
                WearEngineServiceClient.e.notifyAll();
            }
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            tos.b("WearEngineServiceClient", "onBindingDied!");
            WearEngineServiceClient.this.c = null;
            WearEngineServiceClient.this.h.getAndSet(false);
            synchronized (WearEngineServiceClient.e) {
                WearEngineServiceClient.this.d = true;
                WearEngineServiceClient.e.notifyAll();
            }
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
            tos.b("WearEngineServiceClient", "onNullBinding!");
            WearEngineServiceClient.this.c = null;
            WearEngineServiceClient.this.h.getAndSet(false);
            synchronized (WearEngineServiceClient.e) {
                WearEngineServiceClient.this.d = true;
                WearEngineServiceClient.e.notifyAll();
            }
        }
    };

    public WearEngineServiceClient(Context context) {
        this.f11229a = context;
    }

    public void c() {
        synchronized (b) {
            if (this.c != null) {
                tos.b("WearEngineServiceClient", "Already binder the WearEngineService.");
            } else {
                tos.a("WearEngineServiceClient", "Start to bind service.");
                d();
            }
        }
    }

    public AuthListenerManager b() {
        return this.c;
    }

    private void d() {
        synchronized (b) {
            Intent intent = new Intent();
            intent.setPackage("com.huawei.health");
            intent.setClassName("com.huawei.health", "com.huawei.wearengine.service.WearEngineExtendService");
            intent.setAction("com.huawei.wearengine.action.AUTH_LISTENER_MANAGER");
            Intent fcY_ = tot.fcY_(intent);
            if (fcY_ == null) {
                return;
            }
            tos.a("WearEngineServiceClient", "explicitIntent != null, bindService");
            this.f11229a.bindService(fcY_, this.i, 1);
            h();
        }
    }

    private void h() {
        synchronized (e) {
            tos.b("WearEngineServiceClient", "binder Lock enter!");
            while (!this.d) {
                try {
                    tos.b("WearEngineServiceClient", "start Wait");
                    e.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
                    this.d = true;
                    tos.b("WearEngineServiceClient", "wait end");
                } catch (InterruptedException unused) {
                    tos.e("WearEngineServiceClient", "QUERY_LOCK wait error");
                }
            }
        }
    }

    public void a() {
        if (this.h.get()) {
            this.h.getAndSet(false);
            synchronized (b) {
                try {
                    this.f11229a.unbindService(this.i);
                } catch (IllegalArgumentException unused) {
                    tos.e("WearEngineServiceClient", "unBindService IllegalArgumentException");
                }
            }
        }
    }
}
