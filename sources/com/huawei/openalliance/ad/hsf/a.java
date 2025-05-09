package com.huawei.openalliance.ad.hsf;

import android.content.Context;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.hsf.c;
import com.huawei.openalliance.ad.hsf.d;
import com.huawei.openalliance.ad.hsf.e;
import com.huawei.openalliance.ad.utils.m;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class a implements e.a {
    private static a d;
    private static final byte[] e = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private e f6920a;
    private d b;
    private List<C0188a> c = new CopyOnWriteArrayList();

    public interface b {
        void a();

        void b();

        void c();
    }

    @Override // com.huawei.openalliance.ad.hsf.e.a
    public void b(int i) {
        ho.b("HsfPackageInstaller", "onConnectionFailed result: %d", Integer.valueOf(i));
        this.b = null;
        if (i != 5 && i != 1) {
            c();
            return;
        }
        for (C0188a c0188a : this.c) {
            if (c0188a.c != null) {
                c0188a.c.a();
            }
        }
        this.c.clear();
    }

    public void a(String str, String str2, b bVar) {
        if (this.b == null) {
            if (this.f6920a.b()) {
                d b2 = b();
                this.b = b2;
                if (b2 == null) {
                    a(bVar);
                }
            } else {
                this.c.add(new C0188a(str, str2, bVar));
                this.f6920a.a();
            }
        }
        b(str, str2, bVar);
    }

    @Override // com.huawei.openalliance.ad.hsf.e.a
    public void a(int i) {
        ho.b("HsfPackageInstaller", "onConnectionSuspended cause: %d", Integer.valueOf(i));
        this.b = null;
        c();
    }

    @Override // com.huawei.openalliance.ad.hsf.e.a
    public void a() {
        this.b = b();
        for (C0188a c0188a : this.c) {
            if (this.b == null) {
                a(c0188a.c);
            } else {
                b(c0188a.f6923a, c0188a.b, c0188a.c);
            }
        }
        this.c.clear();
    }

    private void c() {
        Iterator<C0188a> it = this.c.iterator();
        while (it.hasNext()) {
            a(it.next().c);
        }
        this.c.clear();
    }

    private void b(final String str, final String str2, final b bVar) {
        final d dVar = this.b;
        if (dVar != null) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.hsf.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        dVar.a(str, str2, new c.a() { // from class: com.huawei.openalliance.ad.hsf.a.1.1
                            @Override // com.huawei.openalliance.ad.hsf.c
                            public void a(String str3, int i) {
                                ho.a("HsfPackageInstaller", "packageInstalled %s code: %d", str3, Integer.valueOf(i));
                                a aVar = a.this;
                                if (i != 1) {
                                    aVar.a(bVar);
                                } else {
                                    aVar.b(bVar);
                                }
                            }
                        }, 2);
                    } catch (Exception e2) {
                        ho.c("HsfPackageInstaller", "installPackage " + e2.getClass().getSimpleName());
                        a.this.a(bVar);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(b bVar) {
        if (bVar != null) {
            bVar.b();
        }
    }

    private d b() {
        PPSHsfService a2 = this.f6920a.a("com.huawei.hsf.pm.service.IPackageManager");
        if (a2 != null) {
            return d.a.a(a2.b());
        }
        ho.c("HsfPackageInstaller", "cannot find package manager, hsf isConnected: %s", Boolean.valueOf(this.f6920a.b()));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(b bVar) {
        if (bVar != null) {
            bVar.c();
        }
    }

    public static a a(Context context) {
        a aVar;
        synchronized (e) {
            if (d == null) {
                d = new a(context);
            }
            aVar = d;
        }
        return aVar;
    }

    /* renamed from: com.huawei.openalliance.ad.hsf.a$a, reason: collision with other inner class name */
    static class C0188a {

        /* renamed from: a, reason: collision with root package name */
        String f6923a;
        String b;
        b c;

        C0188a(String str, String str2, b bVar) {
            this.f6923a = str;
            this.b = str2;
            this.c = bVar;
        }
    }

    private a(Context context) {
        e a2 = e.a(context, this);
        this.f6920a = a2;
        a2.a();
    }
}
