package com.amap.api.col.p0003sl;

import com.huawei.hms.network.embedded.k;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class fz {

    /* renamed from: a, reason: collision with root package name */
    private static volatile fz f1057a;
    private HashMap<String, ga> b = new HashMap<>();

    public static fz a() {
        if (f1057a == null) {
            synchronized (fz.class) {
                if (f1057a == null) {
                    f1057a = new fz();
                }
            }
        }
        return f1057a;
    }

    public final ga a(String str) {
        ga gaVar;
        synchronized (this) {
            gaVar = this.b.get(str);
        }
        return gaVar;
    }

    public final void a(String str, ga gaVar) {
        synchronized (this) {
            this.b.put(str, gaVar);
        }
    }

    public final c a(b bVar) {
        c a2;
        if (bVar == null) {
            return null;
        }
        for (ga gaVar : this.b.values()) {
            if (gaVar != null && (a2 = gaVar.a(bVar)) != null) {
                return a2;
            }
        }
        return null;
    }

    public final void a(b bVar, Object obj) {
        for (ga gaVar : this.b.values()) {
            if (gaVar != null) {
                gaVar.a(bVar, obj);
            }
        }
    }

    public final boolean b(b bVar) {
        if (bVar == null) {
            return false;
        }
        for (ga gaVar : this.b.values()) {
            if (gaVar != null && gaVar.b(bVar)) {
                return true;
            }
        }
        return false;
    }

    public final void a(a aVar) {
        if (aVar == null) {
            return;
        }
        for (ga gaVar : this.b.values()) {
            if (gaVar != null) {
                gaVar.a(aVar);
            }
        }
    }

    public final void a(String str, a aVar) {
        ga gaVar;
        if (str == null || aVar == null || (gaVar = this.b.get(str)) == null) {
            return;
        }
        gaVar.a(aVar);
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        Object f1060a;
        boolean b;

        public c(Object obj, boolean z) {
            this.f1060a = obj;
            this.b = z;
        }
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        String f1059a;
        Object b;

        b() {
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                b bVar = (b) obj;
                String str = this.f1059a;
                if (str == null) {
                    return bVar.f1059a == null && this.b == bVar.b;
                }
                if (str.equals(bVar.f1059a) && this.b == bVar.b) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            String str = this.f1059a;
            int hashCode = str == null ? 0 : str.hashCode();
            Object obj = this.b;
            return ((hashCode + 31) * 31) + (obj != null ? obj.hashCode() : 0);
        }
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f1058a = true;
        private long b = k.b.m;
        private int c = 10;
        private double d = 0.0d;

        a() {
        }

        public final boolean a() {
            return this.f1058a;
        }

        public final void a(boolean z) {
            this.f1058a = z;
        }

        public final long b() {
            return this.b;
        }

        public final void a(long j) {
            this.b = j;
        }

        public final int c() {
            return this.c;
        }

        public final void a(int i) {
            this.c = i;
        }

        public final double d() {
            return this.d;
        }

        public final void a(double d) {
            this.d = d;
        }
    }
}
