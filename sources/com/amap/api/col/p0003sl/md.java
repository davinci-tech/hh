package com.amap.api.col.p0003sl;

import android.os.SystemClock;
import android.util.LongSparseArray;
import java.util.List;

/* loaded from: classes2.dex */
public final class md {
    private static volatile md g;
    private static Object h = new Object();
    private Object e = new Object();
    private Object f = new Object();

    /* renamed from: a, reason: collision with root package name */
    private LongSparseArray<a> f1331a = new LongSparseArray<>();
    private LongSparseArray<a> b = new LongSparseArray<>();
    private LongSparseArray<a> c = new LongSparseArray<>();
    private LongSparseArray<a> d = new LongSparseArray<>();

    public static md a() {
        if (g == null) {
            synchronized (h) {
                if (g == null) {
                    g = new md();
                }
            }
        }
        return g;
    }

    private md() {
    }

    final void a(List<mc> list) {
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.e) {
            a(list, this.f1331a, this.b);
            LongSparseArray<a> longSparseArray = this.f1331a;
            this.f1331a = this.b;
            this.b = longSparseArray;
            longSparseArray.clear();
        }
    }

    final short a(long j) {
        return a(this.f1331a, j);
    }

    final void b(List<mc> list) {
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.f) {
            a(list, this.c, this.d);
            LongSparseArray<a> longSparseArray = this.c;
            this.c = this.d;
            this.d = longSparseArray;
            longSparseArray.clear();
        }
    }

    final short b(long j) {
        return a(this.c, j);
    }

    private static void a(List<mc> list, LongSparseArray<a> longSparseArray, LongSparseArray<a> longSparseArray2) {
        long b = b();
        byte b2 = 0;
        if (longSparseArray.size() == 0) {
            for (mc mcVar : list) {
                a aVar = new a(b2);
                aVar.f1332a = mcVar.b();
                aVar.b = b;
                aVar.c = false;
                longSparseArray2.put(mcVar.a(), aVar);
            }
            return;
        }
        for (mc mcVar2 : list) {
            long a2 = mcVar2.a();
            a aVar2 = longSparseArray.get(a2);
            if (aVar2 == null) {
                aVar2 = new a(b2);
                aVar2.f1332a = mcVar2.b();
                aVar2.b = b;
                aVar2.c = true;
            } else if (aVar2.f1332a != mcVar2.b()) {
                aVar2.f1332a = mcVar2.b();
                aVar2.b = b;
                aVar2.c = true;
            }
            longSparseArray2.put(a2, aVar2);
        }
    }

    private static short a(LongSparseArray<a> longSparseArray, long j) {
        synchronized (longSparseArray) {
            a aVar = longSparseArray.get(j);
            if (aVar == null) {
                return (short) 0;
            }
            short max = (short) Math.max(1L, Math.min(32767L, (b() - aVar.b) / 1000));
            if (!aVar.c) {
                max = (short) (-max);
            }
            return max;
        }
    }

    private static long b() {
        return SystemClock.elapsedRealtime();
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        int f1332a;
        long b;
        boolean c;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }
}
