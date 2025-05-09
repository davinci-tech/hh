package com.amap.api.col.p0003sl;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class me {
    public static long a(int i, int i2) {
        return (i2 & 4294967295L) | ((i & 4294967295L) << 32);
    }

    public static void a(List<mi> list) {
        synchronized (me.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    ArrayList arrayList = new ArrayList(list.size());
                    for (mi miVar : list) {
                        if (miVar instanceof mk) {
                            mk mkVar = (mk) miVar;
                            arrayList.add(new a(mkVar.j, mkVar.k, mkVar.c));
                        } else if (miVar instanceof ml) {
                            ml mlVar = (ml) miVar;
                            arrayList.add(new a(mlVar.j, mlVar.k, mlVar.c));
                        } else if (miVar instanceof mm) {
                            mm mmVar = (mm) miVar;
                            arrayList.add(new a(mmVar.j, mmVar.k, mmVar.c));
                        } else if (miVar instanceof mj) {
                            mj mjVar = (mj) miVar;
                            arrayList.add(new a(mjVar.k, mjVar.l, mjVar.c));
                        }
                    }
                    md.a().a(arrayList);
                }
            }
        }
    }

    public static void b(List<mp> list) {
        synchronized (me.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    ArrayList arrayList = new ArrayList(list.size());
                    for (mp mpVar : list) {
                        arrayList.add(new b(mpVar.f1338a, mpVar.c));
                    }
                    md.a().b(arrayList);
                }
            }
        }
    }

    public static short a(long j) {
        short a2;
        synchronized (me.class) {
            a2 = md.a().a(j);
        }
        return a2;
    }

    public static short b(long j) {
        short b2;
        synchronized (me.class) {
            b2 = md.a().b(j);
        }
        return b2;
    }

    public static final class a implements mc {

        /* renamed from: a, reason: collision with root package name */
        private int f1333a;
        private int b;
        private int c;

        a(int i, int i2, int i3) {
            this.f1333a = i;
            this.b = i2;
            this.c = i3;
        }

        @Override // com.amap.api.col.p0003sl.mc
        public final long a() {
            return me.a(this.f1333a, this.b);
        }

        @Override // com.amap.api.col.p0003sl.mc
        public final int b() {
            return this.c;
        }
    }

    public static final class b implements mc {

        /* renamed from: a, reason: collision with root package name */
        private long f1334a;
        private int b;

        b(long j, int i) {
            this.f1334a = j;
            this.b = i;
        }

        @Override // com.amap.api.col.p0003sl.mc
        public final long a() {
            return this.f1334a;
        }

        @Override // com.amap.api.col.p0003sl.mc
        public final int b() {
            return this.b;
        }
    }
}
