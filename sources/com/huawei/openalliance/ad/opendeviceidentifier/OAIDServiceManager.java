package com.huawei.openalliance.ad.opendeviceidentifier;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.analysis.c;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.cr;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bx;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.i;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes5.dex */
public class OAIDServiceManager {

    /* renamed from: a, reason: collision with root package name */
    private static OAIDServiceManager f7384a;
    private static final byte[] b = new byte[0];
    private static final byte[] c = new byte[0];
    private cr d;
    private Context e;
    private c k;
    private int l;
    private Set<OaidResultCallback> f = new HashSet();
    private boolean g = false;
    private final byte[] h = new byte[0];
    private final String i = "oaid_timeout_task" + hashCode();
    private long j = -1;
    private ServiceConnection m = new ServiceConnection() { // from class: com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.5
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ho.b("OAIDServiceManager", "OAID service disconnected");
            OAIDServiceManager.this.a((cr) null);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                OAIDServiceManager.this.a((String) null, (String) null);
                dj.a(OAIDServiceManager.this.i);
                ho.b("OAIDServiceManager", "OAID service connected " + System.currentTimeMillis());
                OAIDServiceManager.this.a(cr.a.a(iBinder));
                if (!OAIDServiceManager.this.b()) {
                    final cr c2 = OAIDServiceManager.this.c();
                    m.f(new Runnable() { // from class: com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            OAIDServiceManager oAIDServiceManager;
                            if (c2 != null) {
                                synchronized (OAIDServiceManager.c) {
                                    try {
                                        String a2 = c2.a();
                                        boolean b2 = c2.b();
                                        Iterator it = OAIDServiceManager.this.f.iterator();
                                        while (it.hasNext()) {
                                            ((OaidResultCallback) it.next()).a(a2, b2);
                                        }
                                        oAIDServiceManager = OAIDServiceManager.this;
                                    } catch (Throwable th) {
                                        try {
                                            ho.c("OAIDServiceManager", "get oaid Exception: " + th.getClass().getSimpleName());
                                            OAIDServiceManager.this.e();
                                            oAIDServiceManager = OAIDServiceManager.this;
                                        } catch (Throwable th2) {
                                            OAIDServiceManager.this.f.clear();
                                            throw th2;
                                        }
                                    }
                                    oAIDServiceManager.f.clear();
                                }
                                return;
                            }
                            OAIDServiceManager.this.e();
                        }
                    });
                } else {
                    ho.c("OAIDServiceManager", "oaid require is already timeout");
                }
            } catch (Throwable th) {
                ho.c("OAIDServiceManager", "Oaid Service, service error: %s", th.getClass().getSimpleName());
            }
        }
    };

    public static abstract class OaidResultCallback {
        public abstract void a();

        public abstract void a(String str, boolean z);

        public int b() {
            return -1;
        }
    }

    public void requireOaid(final OaidResultCallback oaidResultCallback, final long j) {
        if (oaidResultCallback == null) {
            return;
        }
        if (fh.b(this.e).ai() == 0 && bx.b(this.e)) {
            ho.b("OAIDServiceManager", "requireOaid via provider");
            m.a(new Runnable() { // from class: com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.1
                @Override // java.lang.Runnable
                public void run() {
                    OAIDServiceManager.this.a(oaidResultCallback, j);
                }
            });
        } else {
            ho.b("OAIDServiceManager", "requireOaid via aidl");
            b(oaidResultCallback, j);
        }
    }

    public void requireOaid(OaidResultCallback oaidResultCallback) {
        requireOaid(oaidResultCallback, 400L);
    }

    public static OAIDServiceManager getInstance(Context context) {
        OAIDServiceManager oAIDServiceManager;
        synchronized (b) {
            if (f7384a == null) {
                f7384a = new OAIDServiceManager(context);
            }
            oAIDServiceManager = f7384a;
        }
        return oAIDServiceManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        synchronized (c) {
            try {
                try {
                    try {
                        for (final OaidResultCallback oaidResultCallback : this.f) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    oaidResultCallback.a();
                                }
                            });
                        }
                    } catch (RuntimeException e) {
                        ho.c("OAIDServiceManager", "notifyOaidAcquireFail RuntimeException " + e.getClass().getSimpleName());
                    }
                } catch (Exception e2) {
                    ho.c("OAIDServiceManager", "notifyOaidAcquireFail " + e2.getClass().getSimpleName());
                }
                this.f.clear();
            } catch (Throwable th) {
                this.f.clear();
                throw th;
            }
        }
    }

    private boolean d() {
        String simpleName;
        String message;
        try {
            ho.b("OAIDServiceManager", "bindService " + System.currentTimeMillis());
            Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            String e = i.e(this.e);
            intent.setPackage(e);
            if (!bz.b(this.e)) {
                String e2 = i.e(this.e, e);
                boolean isEmpty = TextUtils.isEmpty(e2);
                ho.b("OAIDServiceManager", "is sign empty: %s", Boolean.valueOf(isEmpty));
                if (!isEmpty && !WhiteListPkgList.isTrustApp(this.e, e, e2)) {
                    return false;
                }
            }
            boolean bindService = this.e.bindService(intent, this.m, 1);
            ho.b("OAIDServiceManager", "bind service result: %s", Boolean.valueOf(bindService));
            if (!bindService) {
                e();
                a((String) null, "bind result false");
            }
            return bindService;
        } catch (SecurityException e3) {
            ho.c("OAIDServiceManager", "bindService SecurityException");
            e();
            String simpleName2 = e3.getClass().getSimpleName();
            message = e3.getMessage();
            simpleName = simpleName2;
            a(simpleName, message);
            return false;
        } catch (Exception e4) {
            ho.c("OAIDServiceManager", "bindService " + e4.getClass().getSimpleName());
            e();
            simpleName = e4.getClass().getSimpleName();
            message = e4.getMessage();
            a(simpleName, message);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cr c() {
        cr crVar;
        synchronized (this) {
            crVar = this.d;
        }
        return crVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        boolean z;
        synchronized (this.h) {
            z = this.g;
        }
        return z;
    }

    private void b(OaidResultCallback oaidResultCallback, long j) {
        cr c2 = c();
        if (c2 != null) {
            m.a(new a(oaidResultCallback, c2), m.a.CALCULATION, false);
            return;
        }
        if (this.j < 0) {
            this.j = ao.c();
            this.l = oaidResultCallback.b();
        }
        synchronized (c) {
            this.f.add(oaidResultCallback);
        }
        if (d()) {
            b(j);
        }
    }

    private void b(long j) {
        dj.a(this.i);
        a(false);
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.2
            @Override // java.lang.Runnable
            public void run() {
                m.f(new Runnable() { // from class: com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ho.b("OAIDServiceManager", "bind timeout " + System.currentTimeMillis());
                        OAIDServiceManager.this.a(true);
                        OAIDServiceManager.this.e();
                    }
                });
            }
        }, this.i, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        synchronized (this.h) {
            this.g = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final String str2) {
        final long c2 = ao.c() - this.j;
        if (c2 > 100000) {
            return;
        }
        final int i = this.l;
        ho.a("OAIDServiceManager", "aidl bind duration: %d msg: %s", Long.valueOf(c2), str2);
        m.h(new Runnable() { // from class: com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.4
            @Override // java.lang.Runnable
            public void run() {
                OAIDServiceManager.this.k.a(HealthZonePushReceiver.ECG_MEASUREMENT_ABNORMAL, c2, str, str2, i);
            }
        });
        this.j = -1L;
        this.l = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(OaidResultCallback oaidResultCallback, long j) {
        a(j);
        synchronized (c) {
            this.f.add(oaidResultCallback);
        }
        Pair<String, Boolean> a2 = bx.a(this.e);
        dj.a(this.i);
        if (b()) {
            ho.c("OAIDServiceManager", "provider timeout");
        } else {
            oaidResultCallback.a((String) a2.first, ((Boolean) a2.second).booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(cr crVar) {
        synchronized (this) {
            this.d = crVar;
        }
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private OaidResultCallback f7392a;
        private cr b;

        @Override // java.lang.Runnable
        public void run() {
            String str;
            try {
                this.f7392a.a(this.b.a(), this.b.b());
            } catch (RemoteException unused) {
                str = "requireOaid RemoteException";
                ho.c("OAIDServiceManager", str);
                this.f7392a.a();
            } catch (Exception unused2) {
                str = "requireOaid exception";
                ho.c("OAIDServiceManager", str);
                this.f7392a.a();
            }
        }

        a(OaidResultCallback oaidResultCallback, cr crVar) {
            this.f7392a = oaidResultCallback;
            this.b = crVar;
        }
    }

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<OAIDServiceManager> f7393a;

        @Override // java.lang.Runnable
        public void run() {
            OAIDServiceManager oAIDServiceManager = this.f7393a.get();
            ho.a("OAIDServiceManager", "provider timeout");
            if (oAIDServiceManager != null) {
                oAIDServiceManager.e();
                oAIDServiceManager.a(true);
            }
        }

        public b(OAIDServiceManager oAIDServiceManager) {
            this.f7393a = new WeakReference<>(oAIDServiceManager);
        }
    }

    private void a(long j) {
        dj.a(this.i);
        a(false);
        dj.a(new b(this), this.i, j);
    }

    private OAIDServiceManager(Context context) {
        this.e = context.getApplicationContext();
        this.k = new c(context);
    }
}
