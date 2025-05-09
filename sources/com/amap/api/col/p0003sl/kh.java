package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class kh {

    /* renamed from: a, reason: collision with root package name */
    static int f1255a = 1000;
    static boolean b = false;
    static int c = 20;
    static int d = 0;
    private static WeakReference<kd> e = null;
    private static int f = 10;

    @Deprecated
    public static void a(int i, boolean z) {
        synchronized (kh.class) {
            f1255a = i;
            b = z;
        }
    }

    public static void a(int i, boolean z, int i2, int i3) {
        synchronized (kh.class) {
            f1255a = i;
            b = z;
            if (i2 < 10 || i2 > 100) {
                i2 = 20;
            }
            c = i2;
            if (i2 / 5 > f) {
                f = i2 / 5;
            }
            d = i3;
        }
    }

    static final class a extends lb {

        /* renamed from: a, reason: collision with root package name */
        private int f1256a;
        private Context b;
        private kg c;

        a(Context context, int i) {
            this.b = context;
            this.f1256a = i;
        }

        a(Context context, kg kgVar) {
            this(context, 1);
            this.c = kgVar;
        }

        @Override // com.amap.api.col.p0003sl.lb
        public final void runTask() {
            int i = this.f1256a;
            if (i == 1) {
                try {
                    synchronized (kh.class) {
                        String l = Long.toString(System.currentTimeMillis());
                        kd a2 = kk.a(kh.e);
                        kk.a(this.b, a2, it.i, kh.f1255a, 2097152, "6");
                        if (a2.e == null) {
                            a2.e = new jl(new jn(new jo(new jn())));
                        }
                        ke.a(l, this.c.a(), a2);
                    }
                    return;
                } catch (Throwable th) {
                    iv.c(th, "ofm", "aple");
                    return;
                }
            }
            if (i == 2) {
                try {
                    kd a3 = kk.a(kh.e);
                    kk.a(this.b, a3, it.i, kh.f1255a, 2097152, "6");
                    a3.h = 14400000;
                    if (a3.g == null) {
                        a3.g = new ko(new kn(this.b, new ks(), new jl(new jn(new jo())), new String(io.a(10)), hn.f(this.b), hr.v(this.b), hr.k(this.b), hr.h(this.b), hr.a(), Build.MANUFACTURER, Build.DEVICE, hr.y(this.b), hn.c(this.b), Build.MODEL, hn.d(this.b), hn.b(this.b), hr.g(this.b), hr.a(this.b), String.valueOf(Build.VERSION.SDK_INT)));
                    }
                    if (TextUtils.isEmpty(a3.i)) {
                        a3.i = "fKey";
                    }
                    a3.f = new kw(this.b, a3.h, a3.i, new ku(this.b, kh.b, kh.f * 1024, kh.c * 1024, "offLocKey", kh.d * 1024));
                    ke.a(a3);
                } catch (Throwable th2) {
                    iv.c(th2, "ofm", "uold");
                }
            }
        }
    }

    public static void a(kg kgVar, Context context) {
        synchronized (kh.class) {
            la.a().a(new a(context, kgVar));
        }
    }

    public static void a(Context context) {
        la.a().a(new a(context, 2));
    }
}
