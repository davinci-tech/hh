package com.huawei.wearengine;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.wearengine.BinderService;
import com.huawei.wearengine.ClientToken;
import com.huawei.wearengine.IdentityStoreCallback;
import com.huawei.wearengine.client.ServiceConnectionListener;
import defpackage.tnx;
import defpackage.tov;
import defpackage.tra;
import defpackage.trr;
import defpackage.trx;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public class WearEngineClientInner {
    private static volatile WearEngineClientInner c;
    private static final Object d = new Object();
    private static final Object e = new Object();
    private volatile ServiceConnectionListener h;
    private volatile ServiceConnectionListener n;
    private BinderService b = null;
    private List<ReleaseConnectionCallback> f = new CopyOnWriteArrayList();
    private AtomicBoolean i = new AtomicBoolean(false);
    private ExecutorService j = Executors.newSingleThreadExecutor();

    /* renamed from: a, reason: collision with root package name */
    private boolean f11224a = false;
    private ServiceConnection g = new ServiceConnection() { // from class: com.huawei.wearengine.WearEngineClientInner.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            tov.b("WearEngineClientInner", "onServiceConnected success!");
            WearEngineClientInner.this.b = BinderService.Stub.asInterface(iBinder);
            WearEngineClientInner.this.i.getAndSet(true);
            WearEngineClientInner.this.k();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            tov.b("WearEngineClientInner", "onServiceDisconnected success!");
            WearEngineClientInner.this.b = null;
            WearEngineClientInner.this.i.getAndSet(false);
            synchronized (WearEngineClientInner.e) {
                WearEngineClientInner.this.f11224a = true;
                tov.b("WearEngineClientInner", "onServiceDisconnected BINDER_LOCK notifyAll");
                WearEngineClientInner.e.notifyAll();
            }
            WearEngineClientInner.this.l();
        }
    };

    public interface ReleaseConnectionCallback {
        void onReleaseConnection();
    }

    private WearEngineClientInner() {
    }

    public static WearEngineClientInner c() {
        if (c == null) {
            synchronized (WearEngineClientInner.class) {
                if (c == null) {
                    c = new WearEngineClientInner();
                }
            }
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.j.submit(new Runnable() { // from class: com.huawei.wearengine.WearEngineClientInner.5
            @Override // java.lang.Runnable
            public void run() {
                WearEngineClientInner.this.i();
                WearEngineClientInner.this.p();
                WearEngineClientInner.this.h();
                synchronized (WearEngineClientInner.e) {
                    WearEngineClientInner.this.f11224a = true;
                    tov.b("WearEngineClientInner", "onServiceConnected BINDER_LOCK notifyAll");
                    WearEngineClientInner.e.notifyAll();
                }
                WearEngineClientInner.this.o();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.j.submit(new Runnable() { // from class: com.huawei.wearengine.WearEngineClientInner.2
            @Override // java.lang.Runnable
            public void run() {
                WearEngineClientInner.this.n();
            }
        });
    }

    public void d() {
        synchronized (d) {
            if (g()) {
                return;
            }
            tov.b("WearEngineClientInner", "Start to bind service.");
            j();
        }
    }

    public void e(String str) {
        synchronized (d) {
            if (g()) {
                return;
            }
            if (c(str)) {
                tov.b("WearEngineClientInner", "Start to bind service.");
                j();
            }
        }
    }

    private boolean g() {
        if (this.b == null) {
            return false;
        }
        tov.b("WearEngineClientInner", "Already binder the Wear Engine Service.");
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
    
        if (r3 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0088, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0085, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0083, code lost:
    
        if (r3 != null) goto L31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "WearEngineClientInner"
            r1 = 1
            r2 = 0
            r10.f()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L78
            java.lang.String r3 = "content://com.huawei.healthcloud.health.provider/wear_device_state/wearEngine"
            android.net.Uri r5 = android.net.Uri.parse(r3)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L78
            r10.fcQ_(r5)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L78
            android.content.Context r3 = defpackage.trr.c()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L78
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L78
            android.content.ContentProviderClient r3 = r3.acquireUnstableContentProviderClient(r5)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L78
            if (r3 != 0) goto L29
            java.lang.String r11 = "isAllowBindService contentProviderClient is null"
            defpackage.tov.d(r0, r11)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L89
            if (r3 == 0) goto L28
            r3.close()
        L28:
            return r1
        L29:
            r6 = 0
            r8 = 0
            r9 = 0
            r4 = r3
            r7 = r11
            android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            if (r2 == 0) goto L68
            boolean r4 = r2.moveToNext()     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            if (r4 == 0) goto L68
            java.lang.String r4 = "isAllowBindService"
            int r4 = r2.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            r5 = -1
            if (r4 != r5) goto L4f
            java.lang.String r4 = "isAllowBindService columnIndex = -1"
            defpackage.tov.c(r0, r4)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            goto L53
        L4f:
            java.lang.String r4 = r2.getString(r4)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
        L53:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            java.lang.String r6 = "isAllowBindService = "
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            r5.append(r4)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            defpackage.tov.a(r0, r5)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            r10.b(r11, r4)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
            goto L6d
        L68:
            java.lang.String r11 = "isAllowedBindService cursor is null or no result"
            defpackage.tov.d(r0, r11)     // Catch: java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L79 java.lang.Throwable -> L89
        L6d:
            if (r2 == 0) goto L72
            r2.close()
        L72:
            if (r3 == 0) goto L88
            goto L85
        L75:
            r11 = move-exception
            r3 = r2
            goto L8a
        L78:
            r3 = r2
        L79:
            java.lang.String r11 = "isAllowedBindService query exception"
            defpackage.tov.d(r0, r11)     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L83
            r2.close()
        L83:
            if (r3 == 0) goto L88
        L85:
            r3.close()
        L88:
            return r1
        L89:
            r11 = move-exception
        L8a:
            if (r2 == 0) goto L8f
            r2.close()
        L8f:
            if (r3 == 0) goto L94
            r3.close()
        L94:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.wearengine.WearEngineClientInner.c(java.lang.String):boolean");
    }

    private void fcQ_(Uri uri) {
        PackageManager packageManager = trr.c().getPackageManager();
        if (packageManager == null) {
            tov.d("WearEngineClientInner", "verifyAppIdentity packageManager is null");
            throw new tnx(12);
        }
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(uri.getAuthority(), 0);
        if (resolveContentProvider == null) {
            tov.d("WearEngineClientInner", "verifyAppIdentity providerInfo is null");
            return;
        }
        ApplicationInfo applicationInfo = resolveContentProvider.applicationInfo;
        if (applicationInfo == null) {
            tov.d("WearEngineClientInner", "verifyAppIdentity packageManager is null");
            throw new tnx(12);
        }
        String str = applicationInfo.packageName;
        tov.a("WearEngineClientInner", "verifyAppIdentity provider service's package name is : " + str);
        if (str == null) {
            throw new tnx(12);
        }
        if (!trr.a(str)) {
            throw new tnx(2);
        }
    }

    private void b(String str, String str2) {
        if (Boolean.TRUE.toString().equals(str2)) {
            return;
        }
        if (str.equals("getBondedDevices") || str.equals("getAllBondedDevices") || str.equals("getCommonDevice")) {
            throw new tnx(4);
        }
        if (str.equals("hasAvailableDevices")) {
            throw new tnx(16);
        }
    }

    private void f() {
        if (trr.c() != null) {
            return;
        }
        tov.d("WearEngineClientInner", "context is null");
        throw new tnx(12);
    }

    public IBinder fcR_(int i) {
        if (this.b != null) {
            tov.b("WearEngineClientInner", "queryBinder " + i);
            try {
                return this.b.getBinder(i);
            } catch (RemoteException unused) {
                tov.c("WearEngineClientInner", "queryBinder query failed");
                this.i.getAndSet(false);
            }
        }
        tov.c("WearEngineClientInner", "queryBinder failed something happened");
        return null;
    }

    public void d(ServiceConnectionListener serviceConnectionListener) {
        this.n = serviceConnectionListener;
    }

    public void e() {
        this.n = null;
    }

    public void a(ReleaseConnectionCallback releaseConnectionCallback) {
        this.f.add(releaseConnectionCallback);
    }

    public int a() {
        return q();
    }

    private int q() {
        synchronized (d) {
            if (this.i.get()) {
                tov.a("WearEngineClientInner", "begin unbind WearEngineService");
                try {
                    trr.c().unbindService(this.g);
                    this.i.getAndSet(false);
                    this.b = null;
                    m();
                } catch (IllegalArgumentException unused) {
                    tov.d("WearEngineClientInner", "unBindService catch IllegalArgumentException");
                    return 12;
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        tov.b("WearEngineClientInner", "setClientToken enter");
        if (this.b != null) {
            ClientToken.Stub stub = new ClientToken.Stub() { // from class: com.huawei.wearengine.WearEngineClientInner.1
            };
            try {
                this.b.registerToken(trr.c().getPackageName(), stub);
            } catch (RemoteException unused) {
                tov.c("WearEngineClientInner", "setClientToken failed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        tov.b("WearEngineClientInner", "checkPermissionIdentity enter");
        if (this.b == null) {
            tov.c("WearEngineClientInner", "checkPermissionIdentity mBinderService is null.");
            return;
        }
        IdentityStoreCallback.Stub stub = new IdentityStoreCallback.Stub() { // from class: com.huawei.wearengine.WearEngineClientInner.3
            @Override // com.huawei.wearengine.IdentityStoreCallback
            public void storePermissionIdentity(String str) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    tov.c("WearEngineClientInner", "storePermissionIdentity permissionIdentity isEmpty");
                } else {
                    trx.c(trr.c(), str);
                }
            }
        };
        String c2 = trx.c(trr.c());
        try {
            this.b.checkPermissionIdentity(trr.c().getPackageName(), c2, stub);
        } catch (RemoteException unused) {
            tov.d("WearEngineClientInner", "clearPermissionData failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        tov.a("WearEngineClientInner", "exchangeApiLevel enter");
        if (this.b != null) {
            try {
                int exchangeApiLevel = this.b.exchangeApiLevel(tra.a());
                tov.a("WearEngineClientInner", "exchangeApiLevel serviceApiLevel:" + exchangeApiLevel);
                tra.d(exchangeApiLevel);
                return;
            } catch (RemoteException unused) {
                tov.c("WearEngineClientInner", "exchangeApiLevel failed");
                return;
            }
        }
        tov.c("WearEngineClientInner", "exchangeApiLevel mBinderService is null");
    }

    private void j() {
        synchronized (d) {
            tov.b("WearEngineClientInner", "begin bindToService");
            Intent fcP_ = fcP_("com.huawei.health", "com.huawei.wearengine.service.WearEngineExtendService");
            if (fcP_ == null) {
                tov.b("WearEngineClientInner", "bindService WearEngineService");
                fcP_ = fcP_("com.huawei.health", "com.huawei.wearengine.service.WearEngineService");
                if (fcP_ == null) {
                    throw new tnx(2);
                }
            }
            synchronized (e) {
                tov.b("WearEngineClientInner", "bindService locked");
                this.f11224a = false;
                if (!trr.c().bindService(fcP_, this.g, Build.VERSION.SDK_INT >= 34 ? 549 : 37)) {
                    tov.d("WearEngineClientInner", "bindToService do not has permission");
                    throw new tnx(15);
                }
                s();
            }
        }
    }

    private void s() {
        while (!this.f11224a) {
            try {
                tov.b("WearEngineClientInner", "bindService BINDER_LOCK wait");
                e.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
                this.f11224a = true;
            } catch (InterruptedException unused) {
                tov.d("WearEngineClientInner", "bindToService wait error");
                return;
            }
        }
    }

    private Intent fcP_(String str, String str2) {
        PackageManager packageManager = trr.c().getPackageManager();
        if (packageManager == null) {
            tov.d("WearEngineClientInner", "createExplicitIntent getPackageManager is null");
            throw new tnx(12);
        }
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setClassName(str, str2);
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() != 1) {
            tov.c("WearEngineClientInner", "createExplicitIntent implicitIntent List are null");
            return null;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        String str3 = resolveInfo.serviceInfo.packageName;
        String str4 = resolveInfo.serviceInfo.name;
        if (!trr.a(str3)) {
            tov.d("WearEngineClientInner", "createExplicitIntent verifyAppIdentity failed");
            return null;
        }
        ComponentName componentName = new ComponentName(str3, str4);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.n != null) {
            tov.b("WearEngineClientInner", "mServiceConnectionListener begin onServiceConnect");
            this.n.onServiceConnect();
        }
        if (this.h != null) {
            tov.b("WearEngineClientInner", "mOtaServiceConnectionListener begin onServiceConnect");
            this.h.onServiceConnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.n != null) {
            tov.b("WearEngineClientInner", "mServiceConnectionListener begin onServiceDisconnect");
            this.n.onServiceDisconnect();
        }
        if (this.h != null) {
            tov.b("WearEngineClientInner", "mOtaServiceConnectionListener begin onServiceDisconnect");
            this.h.onServiceDisconnect();
        }
    }

    private void m() {
        tov.b("WearEngineClientInner", "begin executeReleaseConnectionCallback");
        Iterator<ReleaseConnectionCallback> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onReleaseConnection();
        }
    }
}
