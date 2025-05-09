package com.huawei.wearengine.auth;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.tos;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes9.dex */
public class AuthListenerManagerProxy implements AuthListenerManager {
    private Context e;
    private WearEngineServiceClient h;
    private ExecutorService d = Executors.newSingleThreadExecutor();

    /* renamed from: a, reason: collision with root package name */
    private final Object f11227a = new Object();
    private IBinder.DeathRecipient b = new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.auth.AuthListenerManagerProxy.1
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tos.a("AuthListenerManagerProxy", "binderDied enter");
            if (AuthListenerManagerProxy.this.c != null) {
                AuthListenerManagerProxy.this.c.asBinder().unlinkToDeath(AuthListenerManagerProxy.this.b, 0);
            }
            AuthListenerManagerProxy.this.c = null;
        }
    };
    private AuthListenerManager c = null;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    public AuthListenerManagerProxy(Context context) {
        this.e = context;
        this.h = new WearEngineServiceClient(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() throws RemoteException {
        synchronized (this.f11227a) {
            if (this.c == null) {
                this.h.c();
                AuthListenerManager b = this.h.b();
                this.c = b;
                b.asBinder().linkToDeath(this.b, 0);
            }
        }
    }

    @Override // com.huawei.wearengine.auth.AuthListenerManager
    public void authListenerOnOk(final String str, final Permission[] permissionArr) throws RemoteException {
        this.d.execute(new Runnable() { // from class: com.huawei.wearengine.auth.AuthListenerManagerProxy.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AuthListenerManagerProxy.this.c();
                    if (AuthListenerManagerProxy.this.c != null) {
                        AuthListenerManagerProxy.this.c.authListenerOnOk(str, permissionArr);
                    }
                    AuthListenerManagerProxy.this.e();
                } catch (Exception unused) {
                    tos.e("AuthListenerManagerProxy", "authListenerOnOk Exception");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        WearEngineServiceClient wearEngineServiceClient = this.h;
        if (wearEngineServiceClient != null) {
            wearEngineServiceClient.a();
        }
    }

    @Override // com.huawei.wearengine.auth.AuthListenerManager
    public void authListenerOnCancel(final String str) throws RemoteException {
        this.d.execute(new Runnable() { // from class: com.huawei.wearengine.auth.AuthListenerManagerProxy.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AuthListenerManagerProxy.this.c();
                    if (AuthListenerManagerProxy.this.c != null) {
                        AuthListenerManagerProxy.this.c.authListenerOnCancel(str);
                    }
                    AuthListenerManagerProxy.this.e();
                } catch (Exception unused) {
                    tos.e("AuthListenerManagerProxy", "authListenerOnOk Exception");
                }
            }
        });
    }

    @Override // com.huawei.wearengine.auth.AuthListenerManager
    public List<String> getAllPackageName() throws RemoteException {
        Future submit = this.d.submit(new Callable<List<String>>() { // from class: com.huawei.wearengine.auth.AuthListenerManagerProxy.2
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public List<String> call() throws Exception {
                List<String> arrayList = new ArrayList<>();
                try {
                    AuthListenerManagerProxy.this.c();
                    if (AuthListenerManagerProxy.this.c != null) {
                        arrayList = AuthListenerManagerProxy.this.c.getAllPackageName();
                    }
                    AuthListenerManagerProxy.this.e();
                } catch (RemoteException unused) {
                    tos.b("AuthListenerManagerProxy", "getAllPackageName RemoteException");
                }
                return arrayList;
            }
        });
        ArrayList arrayList = new ArrayList();
        try {
            return (List) submit.get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            tos.e("AuthListenerManagerProxy", "getAllPackageName InterruptedException");
            return arrayList;
        } catch (ExecutionException unused2) {
            tos.e("AuthListenerManagerProxy", "getAllPackageName ExecutionException");
            return arrayList;
        } catch (TimeoutException unused3) {
            tos.e("AuthListenerManagerProxy", "getAllPackageName TimeoutException");
            return arrayList;
        }
    }
}
