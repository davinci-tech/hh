package com.amap.api.col.p0003sl;

import android.os.SystemClock;
import com.amap.api.col.p0003sl.li;
import java.util.List;

/* loaded from: classes2.dex */
public final class lj {
    private static volatile lj g;
    private static Object h = new Object();
    private long c;
    private mo d;
    private mo f = new mo();

    /* renamed from: a, reason: collision with root package name */
    private li f1325a = new li();
    private lk b = new lk();
    private lf e = new lf();

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public mo f1326a;
        public List<mp> b;
        public long c;
        public long d;
        public boolean e;
        public long f;
        public byte g;
        public String h;
        public List<mi> i;
        public boolean j;
    }

    public static lj a() {
        if (g == null) {
            synchronized (h) {
                if (g == null) {
                    g = new lj();
                }
            }
        }
        return g;
    }

    private lj() {
    }

    public final ll a(a aVar) {
        ll llVar = null;
        if (aVar == null) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.d == null || aVar.f1326a.a(this.d) >= 10.0d) {
            li.a a2 = this.f1325a.a(aVar.f1326a, aVar.j, aVar.g, aVar.h, aVar.i);
            List<mp> a3 = this.b.a(aVar.f1326a, aVar.b, aVar.e, aVar.d, currentTimeMillis);
            if (a2 != null || a3 != null) {
                mg.a(this.f, aVar.f1326a, aVar.f, currentTimeMillis);
                llVar = new ll(0, this.e.a(this.f, a2, aVar.c, a3));
            }
            this.d = aVar.f1326a;
            this.c = elapsedRealtime;
        }
        return llVar;
    }
}
