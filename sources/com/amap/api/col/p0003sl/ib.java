package com.amap.api.col.p0003sl;

import android.content.Context;

/* loaded from: classes2.dex */
public class ib {

    /* renamed from: a, reason: collision with root package name */
    private static ib f1170a;
    private final Context b;
    private final String c = ii.a(ia.c("RYW1hcF9kZXZpY2VfYWRpdQ"));

    private ib(Context context) {
        this.b = context.getApplicationContext();
    }

    public static ib a(Context context) {
        if (f1170a == null) {
            synchronized (ib.class) {
                if (f1170a == null) {
                    f1170a = new ib(context);
                }
            }
        }
        return f1170a;
    }

    public final void a() {
        synchronized (this) {
            try {
                if (hr.c() == null) {
                    hr.a(Cif.a());
                }
            } catch (Throwable unused) {
            }
        }
    }

    public final void a(String str) {
        ic.a(this.b).a(this.c);
        ic.a(this.b).b(str);
    }
}
