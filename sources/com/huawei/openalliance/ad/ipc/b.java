package com.huawei.openalliance.ad.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.text.TextUtils;
import com.huawei.openalliance.ad.analysis.c;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.a;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.i;
import com.huawei.openalliance.ad.utils.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes5.dex */
public abstract class b<SERVICE extends IInterface> implements a.InterfaceC0194a {

    /* renamed from: a, reason: collision with root package name */
    protected Context f7074a;
    protected c b;
    protected com.huawei.openalliance.ad.ipc.a c;
    private SERVICE e;
    private final String d = "install_service_timeout_task" + hashCode();
    private boolean f = false;
    private final byte[] g = new byte[0];
    private Set<a> h = new CopyOnWriteArraySet();
    private long i = -1;
    private ServiceConnection j = new ServiceConnection() { // from class: com.huawei.openalliance.ad.ipc.b.3
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ho.b(b.this.b(), "PPS remote service disconnected");
            b.this.a((b) null);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                if (b.this.h().equalsIgnoreCase(componentName.getClassName())) {
                    b.this.a((String) null, (String) null);
                    if (b.this.e()) {
                        dj.a(b.this.d);
                    }
                    ho.b(b.this.b(), "PPS remote service connected " + System.currentTimeMillis());
                    final IInterface a2 = b.this.a(iBinder);
                    b.this.a((b) a2);
                    if (b.this.i() && b.this.e()) {
                        ho.c(b.this.b(), "install request is already timeout");
                        return;
                    } else {
                        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ipc.b.3.1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.lang.Runnable
                            public void run() {
                                if (a2 != null) {
                                    ArrayList arrayList = new ArrayList(b.this.h);
                                    b.this.h.clear();
                                    Iterator it = arrayList.iterator();
                                    while (it.hasNext()) {
                                        ((a) it.next()).a((a) a2);
                                    }
                                }
                            }
                        });
                        return;
                    }
                }
                b.this.a("pps remote service name not match, disconnect service.");
                b.this.a((b) null);
            } catch (Throwable th) {
                ho.c(b.this.b(), "BaseASM Service, service error: %s", th.getClass().getSimpleName());
            }
        }
    };

    protected abstract SERVICE a(IBinder iBinder);

    protected abstract String c();

    protected abstract String d();

    protected boolean e() {
        return true;
    }

    protected abstract boolean f();

    protected abstract String g();

    protected abstract String h();

    protected String b() {
        return "";
    }

    protected void a(a aVar, long j) {
        ho.a(b(), "handleTask");
        aVar.a(this.c);
        this.c.a();
        SERVICE k = k();
        if (k != null) {
            aVar.a((a) k);
            return;
        }
        if (this.i < 0) {
            this.i = ao.c();
        }
        this.h.add(aVar);
        if (j() && e()) {
            a(j);
        }
    }

    @Override // com.huawei.openalliance.ad.ipc.a.InterfaceC0194a
    public void a() {
        synchronized (this) {
            this.f7074a.unbindService(this.j);
            this.e = null;
        }
    }

    private SERVICE k() {
        SERVICE service;
        synchronized (this) {
            service = this.e;
        }
        return service;
    }

    private boolean j() {
        try {
            ho.b(b(), "bindService " + System.currentTimeMillis());
            Intent intent = new Intent(c());
            String d = d();
            intent.setPackage(d);
            if (!bz.b(this.f7074a) && i.a(d)) {
                String e = i.e(this.f7074a, d);
                boolean isEmpty = TextUtils.isEmpty(e);
                ho.b(b(), "is sign empty: %s", Boolean.valueOf(isEmpty));
                if (!isEmpty && !WhiteListPkgList.isTrustApp(this.f7074a, d, e)) {
                    return false;
                }
            }
            boolean bindService = this.f7074a.bindService(intent, this.j, 1);
            ho.b(b(), "bind service result: %s", Boolean.valueOf(bindService));
            if (!bindService) {
                a("bind service failed");
                a((String) null, "bind result false");
            }
            return bindService;
        } catch (SecurityException e2) {
            ho.c(b(), "bindService SecurityException");
            a("bindService SecurityException");
            a(e2.getClass().getSimpleName(), e2.getMessage());
            return false;
        } catch (Exception e3) {
            ho.c(b(), "bindService " + e3.getClass().getSimpleName());
            a("bindService " + e3.getClass().getSimpleName());
            a(e3.getClass().getSimpleName(), e3.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        boolean z;
        synchronized (this.g) {
            z = this.f;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        synchronized (this.g) {
            this.f = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final String str2) {
        if (f()) {
            final long c = ao.c() - this.i;
            ho.a(b(), "aidl bind duration: %d msg: %s", Long.valueOf(c), str2);
            m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ipc.b.2
                @Override // java.lang.Runnable
                public void run() {
                    b.this.b.a(b.this.g(), c, str, str2, -1);
                }
            });
            this.i = -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            ArrayList arrayList = new ArrayList(this.h);
            this.h.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(str);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public static abstract class a<SERVICE extends IInterface> {

        /* renamed from: a, reason: collision with root package name */
        private com.huawei.openalliance.ad.ipc.a f7079a;

        public abstract void a(SERVICE service);

        public void a(String str) {
        }

        protected void finalize() {
            try {
                super.finalize();
                m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ipc.b.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (a.this.f7079a != null) {
                            a.this.f7079a.b();
                        }
                    }
                });
            } catch (Throwable th) {
                ho.b("BaseAidlSer", "finalize err: %s", th.getClass().getSimpleName());
            }
        }

        public void a(com.huawei.openalliance.ad.ipc.a aVar) {
            this.f7079a = aVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SERVICE service) {
        synchronized (this) {
            this.e = service;
        }
    }

    private void a(long j) {
        dj.a(this.d);
        a(false);
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ipc.b.1
            @Override // java.lang.Runnable
            public void run() {
                ho.b(b.this.b(), "bind timeout " + System.currentTimeMillis());
                b.this.a(true);
                b.this.a("service bind timeout");
            }
        }, this.d, j);
    }

    public b(Context context) {
        this.f7074a = context.getApplicationContext();
        this.b = new c(context);
    }
}
