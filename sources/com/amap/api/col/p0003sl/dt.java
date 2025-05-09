package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public class dt {
    private static volatile dt b;

    /* renamed from: a, reason: collision with root package name */
    private la f977a;

    public static dt a() {
        if (b == null) {
            synchronized (dt.class) {
                if (b == null) {
                    b = new dt();
                }
            }
        }
        return b;
    }

    public static void b() {
        if (b != null) {
            try {
                if (b.f977a != null) {
                    b.f977a.e();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            b.f977a = null;
            b = null;
        }
    }

    private dt() {
        this.f977a = null;
        this.f977a = du.a("AMapThreadUtil");
    }

    public final void a(lb lbVar) {
        try {
            this.f977a.a(lbVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(lb lbVar) {
        if (lbVar != null) {
            try {
                lbVar.cancelTask();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
