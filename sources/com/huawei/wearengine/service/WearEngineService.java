package com.huawei.wearengine.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.wearengine.BinderService;
import com.huawei.wearengine.ClientToken;
import com.huawei.wearengine.IdentityStoreCallback;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import com.huawei.wearengine.connect.ServiceConnectCallbackManager;
import com.huawei.wearengine.core.callback.ServiceConnectStateCallback;
import defpackage.tos;
import defpackage.tot;
import defpackage.tpf;
import defpackage.trf;
import defpackage.trv;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class WearEngineService extends Service {
    private boolean b;
    private final Map<ClientToken, a> d = new ConcurrentHashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private final List<c> f11237a = new CopyOnWriteArrayList();
    private final List<e> e = new CopyOnWriteArrayList();
    private final ExecutorService h = new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadPoolExecutor.DiscardPolicy());
    private final IBinder c = new BinderServiceProxy();
    private final trv g = new trv("com.huawei.health", "com.huawei.wearengine.service.WearEngineExtendService");
    private final IBinder.DeathRecipient i = new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.service.WearEngineService.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tos.a("WearEngineService", "thirdApp binderDied enter");
            Iterator it = WearEngineService.this.d.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                ClientToken clientToken = (ClientToken) entry.getKey();
                if (!clientToken.asBinder().pingBinder()) {
                    a aVar = (a) entry.getValue();
                    a(aVar);
                    b(aVar);
                    clientToken.asBinder().unlinkToDeath(WearEngineService.this.i, 0);
                    it.remove();
                    tos.b("WearEngineService", "mCachedRegisterTokenMaps.size = " + WearEngineService.this.d.size());
                }
            }
        }

        private void b(a aVar) {
            for (e eVar : WearEngineService.this.e) {
                if (eVar != null && eVar.b() == aVar.b() && eVar.d() == aVar.d()) {
                    WearEngineService.this.e.remove(eVar);
                }
            }
            tos.b("WearEngineService", "mCachedPermissionIdentityList.size = " + WearEngineService.this.e.size());
        }

        private void a(a aVar) {
            for (c cVar : WearEngineService.this.f11237a) {
                if (cVar != null && cVar.b() == aVar.b() && cVar.d() == aVar.d()) {
                    WearEngineService.this.f11237a.remove(cVar);
                }
            }
            tos.b("WearEngineService", "mCachedSdkApiList.size = " + WearEngineService.this.f11237a.size());
        }
    };
    private final IdentityStoreCallback j = new IdentityStoreCallback.Stub() { // from class: com.huawei.wearengine.service.WearEngineService.1
        @Override // com.huawei.wearengine.IdentityStoreCallback
        public void storePermissionIdentity(String str) {
        }
    };
    private final ServiceConnectStateCallback o = new ServiceConnectStateCallback() { // from class: com.huawei.wearengine.service.WearEngineService.4
        @Override // com.huawei.wearengine.core.callback.ServiceConnectStateCallback
        public void onConnectChanged(ComponentName componentName, IBinder iBinder, boolean z) {
            if (!z) {
                tos.a("WearEngineService", "WearEngineExtendServiceDied");
                WearEngineService.this.b = true;
                tpf b = tpf.b();
                b.d();
                b.c();
                return;
            }
            tos.a("WearEngineService", "onServiceConnectedCallback enter");
            if (!WearEngineService.this.b) {
                tos.b("WearEngineService", "onServiceConnectedCallback isPhoneServiceDied false");
                return;
            }
            BinderService asInterface = BinderService.Stub.asInterface(iBinder);
            if (asInterface != null) {
                for (e eVar : WearEngineService.this.e) {
                    try {
                        asInterface.checkPermissionIdentityProxy(eVar.b(), eVar.d(), eVar.c(), eVar.b, WearEngineService.this.j);
                    } catch (RemoteException unused) {
                        tos.e("WearEngineService", "onServiceConnectedCallback checkPermissionIdentityProxy RemoteException");
                        return;
                    }
                }
                for (Map.Entry entry : WearEngineService.this.d.entrySet()) {
                    try {
                        ClientToken clientToken = (ClientToken) entry.getKey();
                        a aVar = (a) entry.getValue();
                        asInterface.registerTokenProxy(aVar.b(), aVar.d(), aVar.c(), clientToken);
                    } catch (RemoteException unused2) {
                        tos.e("WearEngineService", "onServiceConnectedCallback registerTokenProxy RemoteException");
                        return;
                    }
                }
                for (c cVar : WearEngineService.this.f11237a) {
                    try {
                        asInterface.exchangeApiLevelProxy(cVar.b(), cVar.d(), cVar.e);
                    } catch (RemoteException unused3) {
                        tos.e("WearEngineService", "onServiceConnectedCallback exchangeApiLevelProxy RemoteException");
                        return;
                    }
                }
                WearEngineService.this.b = false;
                return;
            }
            tos.d("WearEngineService", "onServiceConnectedCallback binderService instance is null");
        }
    };
    private final IBinder f = new ServiceConnectCallbackManager.Stub() { // from class: com.huawei.wearengine.service.WearEngineService.3
        @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
        public int registerConnectCallback(ServiceConnectCallback serviceConnectCallback) {
            if (!trf.c()) {
                tos.e("WearEngineService", "registerConnectCallback is not InnerInvoke");
                return 12;
            }
            return tpf.b().b(serviceConnectCallback);
        }

        @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
        public int unregisterConnectCallback(ServiceConnectCallback serviceConnectCallback) {
            if (!trf.c()) {
                tos.e("WearEngineService", "unregisterConnectCallback is not InnerInvoke");
                return 12;
            }
            return tpf.b().d(serviceConnectCallback);
        }

        @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
        public int registerOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) {
            if (!trf.c()) {
                tos.e("WearEngineService", "registerOtaServiceConnectCallback is not InnerInvoke");
                return 12;
            }
            return tpf.b().c(serviceConnectCallback);
        }

        @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
        public int unregisterOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) {
            if (!trf.c()) {
                tos.e("WearEngineService", "unregisterOtaServiceConnectCallback is not InnerInvoke");
                return 12;
            }
            return tpf.b().e(serviceConnectCallback);
        }
    };

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service
    public void onCreate() {
        tos.a("WearEngineService", "onCreate!");
        tot.a(getApplicationContext());
        ExecutorService executorService = this.h;
        if (executorService instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) executorService).allowCoreThreadTimeOut(true);
        }
        this.g.c(this.o);
        super.onCreate();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        tos.a("WearEngineService", "onBind!");
        if (intent != null && "com.huawei.wearengine.action.REGISTER_CALLBACK_MANAGER".equals(intent.getAction())) {
            tos.b("WearEngineService", "onBind return ServiceConnectionCallbackManager");
            return this.f;
        }
        return this.c;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        tos.a("WearEngineService", "onUnbind!");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        tos.a("WearEngineService", "onDestroy!");
        d();
        super.onDestroy();
    }

    private void d() {
        this.b = false;
        this.f11237a.clear();
        this.d.clear();
        this.e.clear();
        b();
    }

    private void b() {
        this.h.execute(new Runnable() { // from class: com.huawei.wearengine.service.WearEngineService.5
            @Override // java.lang.Runnable
            public void run() {
                WearEngineService.this.g.c();
            }
        });
    }

    class BinderServiceProxy extends BinderService.Stub {
        @Override // com.huawei.wearengine.BinderService
        public void checkPermissionIdentityProxy(int i, int i2, String str, String str2, IdentityStoreCallback identityStoreCallback) {
        }

        @Override // com.huawei.wearengine.BinderService
        public int exchangeApiLevelProxy(int i, int i2, int i3) {
            return 0;
        }

        @Override // com.huawei.wearengine.BinderService
        public IBinder getBinderProxy(int i, int i2, int i3) {
            return null;
        }

        @Override // com.huawei.wearengine.BinderService
        public void registerTokenProxy(int i, int i2, String str, ClientToken clientToken) {
        }

        private BinderServiceProxy() {
        }

        @Override // com.huawei.wearengine.BinderService
        public IBinder getBinder(int i) throws RemoteException {
            tos.b("WearEngineService", "getBinder enter");
            try {
                return WearEngineService.this.c().getBinderProxy(tot.d(), tot.e(), i);
            } catch (DeadObjectException unused) {
                tos.e("WearEngineService", "getBinder has DeadObjectException");
                throw new IllegalStateException(String.valueOf(12));
            }
        }

        @Override // com.huawei.wearengine.BinderService
        public void registerToken(String str, ClientToken clientToken) throws RemoteException {
            tos.b("WearEngineService", "registerToken enter");
            if (clientToken != null) {
                for (Map.Entry entry : WearEngineService.this.d.entrySet()) {
                    ClientToken clientToken2 = (ClientToken) entry.getKey();
                    a aVar = (a) entry.getValue();
                    if (aVar.b() == tot.d() && aVar.d() == tot.e()) {
                        WearEngineService.this.d.remove(clientToken2);
                    }
                }
                WearEngineService.this.d.put(clientToken, new a(tot.d(), tot.e(), str));
                tos.b("WearEngineService", "mCachedRegisterTokenMaps.size = " + WearEngineService.this.d.size());
                clientToken.asBinder().linkToDeath(WearEngineService.this.i, 0);
                try {
                    WearEngineService.this.c().registerTokenProxy(tot.d(), tot.e(), str, clientToken);
                    return;
                } catch (DeadObjectException unused) {
                    tos.e("WearEngineService", str + ",registerToken has DeadObjectException");
                    return;
                } catch (IllegalStateException unused2) {
                    tos.e("WearEngineService", str + ",registerToken has an IllegalStateException");
                    return;
                }
            }
            tos.e("WearEngineService", "BinderService registerToken token is null");
        }

        @Override // com.huawei.wearengine.BinderService
        public void checkPermissionIdentity(String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
            tos.b("WearEngineService", "checkPermissionIdentity enter");
            if (checkPermission()) {
                e eVar = new e(tot.d(), tot.e(), str, str2);
                WearEngineService.this.e.remove(eVar);
                WearEngineService.this.e.add(eVar);
                tos.b("WearEngineService", "mCachedPermissionIdentityList.size = " + WearEngineService.this.e.size());
                try {
                    WearEngineService.this.c().checkPermissionIdentityProxy(tot.d(), tot.e(), str, str2, identityStoreCallback);
                } catch (DeadObjectException unused) {
                    tos.e("WearEngineService", str + ",checkPermissionIdentity has DeadObjectException");
                } catch (IllegalStateException unused2) {
                    tos.e("WearEngineService", str + ",checkPermissionIdentity has an IllegalStateException");
                }
            }
        }

        @Override // com.huawei.wearengine.BinderService
        public int exchangeApiLevel(int i) throws RemoteException {
            tos.b("WearEngineService", "exchangeApiLevel enter");
            if (!checkPermission()) {
                return 0;
            }
            c cVar = new c(tot.d(), tot.e(), null, i);
            WearEngineService.this.f11237a.remove(cVar);
            WearEngineService.this.f11237a.add(cVar);
            tos.b("WearEngineService", "mCachedSdkApiList.size = " + WearEngineService.this.f11237a.size());
            try {
                return WearEngineService.this.c().exchangeApiLevelProxy(tot.d(), tot.e(), i);
            } catch (DeadObjectException unused) {
                tos.e("WearEngineService", "exchangeApiLevel has DeadObjectException");
                return 0;
            } catch (IllegalStateException unused2) {
                tos.e("WearEngineService", "pid: " + tot.e() + ",exchangeApiLevel has an IllegalStateException");
                return 0;
            }
        }

        private boolean checkPermission() {
            return Binder.getCallingUid() == getCallingUid();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BinderService c() {
        IBinder ffl_ = this.g.ffl_();
        if (ffl_ == null) {
            tos.e("WearEngineService", " getServiceBinder serviceBinder is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        return BinderService.Stub.asInterface(ffl_);
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private final int f11240a;
        private final int d;
        private final String e;

        private a(int i, int i2, String str) {
            this.f11240a = i;
            this.d = i2;
            this.e = str;
        }

        public int b() {
            return this.f11240a;
        }

        public int d() {
            return this.d;
        }

        public String c() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return this.f11240a == aVar.f11240a && this.d == aVar.d;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.f11240a), Integer.valueOf(this.d));
        }
    }

    static class e extends a {
        private final String b;

        private e(int i, int i2, String str, String str2) {
            super(i, i2, str);
            this.b = str2;
        }

        @Override // com.huawei.wearengine.service.WearEngineService.a
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override // com.huawei.wearengine.service.WearEngineService.a
        public int hashCode() {
            return super.hashCode();
        }
    }

    static class c extends a {
        private final int e;

        private c(int i, int i2, String str, int i3) {
            super(i, i2, str);
            this.e = i3;
        }

        @Override // com.huawei.wearengine.service.WearEngineService.a
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override // com.huawei.wearengine.service.WearEngineService.a
        public int hashCode() {
            return super.hashCode();
        }
    }
}
