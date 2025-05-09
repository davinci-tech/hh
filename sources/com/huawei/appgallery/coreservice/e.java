package com.huawei.appgallery.coreservice;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.appgallery.coreservice.api.ApiClient;
import com.huawei.appgallery.coreservice.api.ConnectConfig;
import com.huawei.appgallery.coreservice.api.IConnectionResult;
import com.huawei.appgallery.coreservice.internal.service.installhiapp.GuideInstallAppGallery;
import com.huawei.appgallery.serviceverifykit.api.ServiceVerifyKit;
import com.huawei.appmarket.framework.coreservice.DataHolder;
import com.huawei.appmarket.framework.coreservice.Status;
import com.huawei.appmarket.framework.coreservice.a;
import com.huawei.appmarket.framework.coreservice.b;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.aew;
import defpackage.aex;
import defpackage.aft;
import defpackage.afu;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class e implements com.huawei.appgallery.coreservice.c, ServiceConnection {
    private static AtomicInteger c = new AtomicInteger(0);

    /* renamed from: a, reason: collision with root package name */
    private b f1868a;
    private String b;
    private final Context e;
    private ConnectConfig g;
    private final Set<ApiClient.ConnectionCallback> d = new HashSet();
    private boolean j = false;
    private final AtomicInteger f = new AtomicInteger();
    private final ApiClient.ConnectionCallback h = new C0041e();

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        afu.e("InnerApiClientImpl", "Enter onServiceDisconnected.");
        this.f1868a = null;
        this.j = false;
        this.h.onConnectionSuspended(1);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        afu.e("InnerApiClientImpl", "Enter onServiceConnected.");
        this.f1868a = b.a.a(iBinder);
        this.h.onConnected();
        this.j = false;
        if (this.f.get() <= 0) {
            afu.e("InnerApiClientImpl", "service expect to unbind");
            f();
        }
    }

    public boolean e() {
        return this.j;
    }

    public boolean b() {
        b bVar = this.f1868a;
        return bVar != null && bVar.asBinder().isBinderAlive();
    }

    public void c(Set<ApiClient.ConnectionCallback> set) {
        afu.e("InnerApiClientImpl", "connect()");
        this.f.incrementAndGet();
        this.j = true;
        this.d.addAll(set);
        if (h()) {
            try {
                if (c()) {
                    afu.e("InnerApiClientImpl", "bind success!");
                    return;
                }
            } catch (SecurityException e) {
                afu.e("InnerApiClientImpl", "bind Execption", e);
            }
            aft.b(new c(this, 2), 200L);
        }
    }

    @Override // com.huawei.appgallery.coreservice.c
    public void a(DataHolder dataHolder, a.AbstractBinderC0043a abstractBinderC0043a) {
        try {
            b bVar = this.f1868a;
            if (bVar != null) {
                bVar.a(dataHolder, abstractBinderC0043a);
            } else if (abstractBinderC0043a != null) {
                b(abstractBinderC0043a, "mTransportService is null");
            }
        } catch (RemoteException unused) {
            b(abstractBinderC0043a, "asyncCall RemoteExecption");
        }
    }

    public void a(ConnectConfig connectConfig) {
        this.g = connectConfig;
    }

    public void a() {
        afu.e("InnerApiClientImpl", "disconnect()");
        this.f.decrementAndGet();
        f();
    }

    public String c(Context context) {
        String str;
        try {
            return new ServiceVerifyKit.Builder().b(context).hN_(new Intent(this.g.getConnectServiceAction()), ServiceVerifyKit.Builder.ComponentType.SERVICE).a(this.g.getAppSignCertchain()).e(this.g.getAppFingerprintSignature()).d();
        } catch (RuntimeException unused) {
            str = "get market pkg RuntimeException!";
            afu.a("InnerApiClientImpl", str);
            return "";
        } catch (Exception unused2) {
            str = "get market pkg Exception!";
            afu.a("InnerApiClientImpl", str);
            return "";
        }
    }

    private boolean h() {
        String c2 = this.g != null ? c(this.e) : a(this.e);
        this.b = c2;
        if (TextUtils.isEmpty(c2)) {
            afu.a("InnerApiClientImpl", "can not found AppGallery or invalid sign");
            this.j = false;
            ConnectConfig connectConfig = this.g;
            this.h.onConnectionFailed(new aew(4, connectConfig != null ? GuideInstallAppGallery.gM_(this.e, connectConfig, this.b) : GuideInstallAppGallery.gL_(this.e)));
            return false;
        }
        try {
            if (m.a(this.e.getPackageManager().getPackageInfo(this.b, 128))) {
                return true;
            }
            afu.a("InnerApiClientImpl", "unsupport agd");
            this.j = false;
            ConnectConfig connectConfig2 = this.g;
            this.h.onConnectionFailed(new aew(7, connectConfig2 != null ? GuideInstallAppGallery.gM_(this.e, connectConfig2, this.b) : GuideInstallAppGallery.gL_(this.e)));
            return false;
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            afu.a("InnerApiClientImpl", "can not found AppGallery");
            this.j = false;
            ConnectConfig connectConfig3 = this.g;
            this.h.onConnectionFailed(new aew(4, connectConfig3 != null ? GuideInstallAppGallery.gM_(this.e, connectConfig3, this.b) : GuideInstallAppGallery.gL_(this.e)));
            return false;
        }
    }

    private void f() {
        this.j = false;
        try {
            if (b()) {
                this.e.unbindService(this);
            } else {
                afu.e("InnerApiClientImpl", "service does not connected");
            }
            this.f1868a = null;
        } catch (IllegalArgumentException e) {
            afu.a("InnerApiClientImpl", e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            afu.e("InnerApiClientImpl", "start transparent activity");
            Intent intent = new Intent();
            intent.setAction("com.huawei.appmarket.intent.coreservice.LAUNCH_APP");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setPackage(this.b);
            intent.setFlags(268435456);
            this.e.startActivity(intent);
            aft.b(new c(this, 1), 200L);
        } catch (ActivityNotFoundException unused) {
            afu.a("InnerApiClientImpl", "transparent activity not found!");
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.j = false;
        this.h.onConnectionFailed(new aew(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        if (TextUtils.isEmpty(this.b)) {
            return false;
        }
        Intent gK_ = aex.gK_(this.b, this.g);
        gK_.putExtra("mediaPkg", this.e.getPackageName());
        gK_.putExtra("sdkVersion", 9);
        if (Build.VERSION.SDK_INT >= 29) {
            gK_.setIdentifier(this.e.getPackageName() + Constants.LINK + c.getAndIncrement());
        }
        return this.e.bindService(gK_, this, 1);
    }

    public static aew b(Context context) {
        return new aew(4, GuideInstallAppGallery.gL_(context));
    }

    /* renamed from: com.huawei.appgallery.coreservice.e$e, reason: collision with other inner class name */
    class C0041e implements ApiClient.ConnectionCallback {
        @Override // com.huawei.appgallery.coreservice.api.ApiClient.ConnectionCallback
        public void onConnectionSuspended(int i) {
            afu.b("InnerApiClientImpl", "ConnectionCallback : onConnectionSuspended()");
            Iterator it = e.this.d.iterator();
            while (it.hasNext()) {
                ((ApiClient.ConnectionCallback) it.next()).onConnectionSuspended(i);
            }
        }

        @Override // com.huawei.appgallery.coreservice.api.ApiClient.ConnectionCallback
        public void onConnectionFailed(IConnectionResult iConnectionResult) {
            afu.b("InnerApiClientImpl", "OnConnectionFailedListener : onConnectionFailed()");
            Iterator it = e.this.d.iterator();
            while (it.hasNext()) {
                ((ApiClient.ConnectionCallback) it.next()).onConnectionFailed(iConnectionResult);
            }
        }

        @Override // com.huawei.appgallery.coreservice.api.ApiClient.ConnectionCallback
        public void onConnected() {
            afu.b("InnerApiClientImpl", "ConnectionCallback : onConnected()");
            Iterator it = e.this.d.iterator();
            while (it.hasNext()) {
                ((ApiClient.ConnectionCallback) it.next()).onConnected();
            }
        }

        C0041e() {
        }
    }

    public static String a(Context context) {
        String str;
        try {
            return new ServiceVerifyKit.Builder().b(context).hN_(new Intent("com.huawei.appmarket.service.intent.ACTION_CORE_SERVICE"), ServiceVerifyKit.Builder.ComponentType.SERVICE).a("com.huawei.appmarket", "FFE391E0EA186D0734ED601E4E70E3224B7309D48E2075BAC46D8C667EAE7212").a("com.huawei.appmarket", "3BAF59A2E5331C30675FAB35FF5FFF0D116142D3D4664F1C3CB804068B40614F").d();
        } catch (RuntimeException unused) {
            str = "get market pkg RuntimeException!";
            afu.a("InnerApiClientImpl", str);
            return "";
        } catch (Exception unused2) {
            str = "get market pkg Exception!";
            afu.a("InnerApiClientImpl", str);
            return "";
        }
    }

    private void b(a.AbstractBinderC0043a abstractBinderC0043a, String str) {
        afu.a("InnerApiClientImpl", "call Failed:" + str);
        try {
            abstractBinderC0043a.call(new Status(4));
        } catch (RemoteException unused) {
            afu.a("InnerApiClientImpl", str);
        }
    }

    static class c implements Runnable {
        public int b;
        public WeakReference<e> e;

        @Override // java.lang.Runnable
        public void run() {
            e eVar;
            WeakReference<e> weakReference = this.e;
            if (weakReference == null || (eVar = weakReference.get()) == null) {
                afu.e("InnerApiClientImpl", "innerApiClient already null");
                return;
            }
            int i = this.b;
            if (i != 1) {
                if (i == 2) {
                    eVar.g();
                }
            } else {
                afu.e("InnerApiClientImpl", "delay bind core service");
                try {
                    if (eVar.c()) {
                        return;
                    }
                } catch (SecurityException e) {
                    afu.e("InnerApiClientImpl", "bindCoreService Execption", e);
                }
                eVar.d();
            }
        }

        public c(e eVar, int i) {
            this.b = i;
            this.e = new WeakReference<>(eVar);
        }
    }

    public e(Context context) {
        this.e = context;
    }
}
