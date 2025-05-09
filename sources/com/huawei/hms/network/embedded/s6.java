package com.huawei.hms.network.embedded;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class s6 {
    public static final s6 n = new a().c().a();
    public static final s6 o = new a().f().b(Integer.MAX_VALUE, TimeUnit.SECONDS).a();

    /* renamed from: a, reason: collision with root package name */
    public final boolean f5474a;
    public final boolean b;
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final int h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;

    @Nullable
    public String m;

    public String toString() {
        String str = this.m;
        if (str != null) {
            return str;
        }
        String m = m();
        this.m = m;
        return m;
    }

    public int l() {
        return this.d;
    }

    public boolean k() {
        return this.j;
    }

    public boolean j() {
        return this.k;
    }

    public boolean i() {
        return this.b;
    }

    public boolean h() {
        return this.f5474a;
    }

    public boolean g() {
        return this.g;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public boolean f5475a;
        public boolean b;
        public int c = -1;
        public int d = -1;
        public int e = -1;
        public boolean f;
        public boolean g;
        public boolean h;

        public a f() {
            this.f = true;
            return this;
        }

        public a e() {
            this.g = true;
            return this;
        }

        public a d() {
            this.b = true;
            return this;
        }

        public a c(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds(i);
                this.e = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("minFresh < 0: " + i);
        }

        public a c() {
            this.f5475a = true;
            return this;
        }

        public a b(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds(i);
                this.d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i);
        }

        public a b() {
            this.h = true;
            return this;
        }

        public s6 a() {
            return new s6(this);
        }

        public a a(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds(i);
                this.c = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxAge < 0: " + i);
        }
    }

    public int f() {
        return this.i;
    }

    public int e() {
        return this.h;
    }

    public int d() {
        return this.c;
    }

    public boolean c() {
        return this.f;
    }

    public boolean b() {
        return this.e;
    }

    public boolean a() {
        return this.l;
    }

    private String m() {
        StringBuilder sb = new StringBuilder();
        if (this.f5474a) {
            sb.append("no-cache, ");
        }
        if (this.b) {
            sb.append("no-store, ");
        }
        if (this.c != -1) {
            sb.append("max-age=");
            sb.append(this.c);
            sb.append(", ");
        }
        if (this.d != -1) {
            sb.append("s-maxage=");
            sb.append(this.d);
            sb.append(", ");
        }
        if (this.e) {
            sb.append("private, ");
        }
        if (this.f) {
            sb.append("public, ");
        }
        if (this.g) {
            sb.append("must-revalidate, ");
        }
        if (this.h != -1) {
            sb.append("max-stale=");
            sb.append(this.h);
            sb.append(", ");
        }
        if (this.i != -1) {
            sb.append("min-fresh=");
            sb.append(this.i);
            sb.append(", ");
        }
        if (this.j) {
            sb.append("only-if-cached, ");
        }
        if (this.k) {
            sb.append("no-transform, ");
        }
        if (this.l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.embedded.s6 a(com.huawei.hms.network.embedded.j7 r24) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.s6.a(com.huawei.hms.network.embedded.j7):com.huawei.hms.network.embedded.s6");
    }

    public s6(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.f5474a = z;
        this.b = z2;
        this.c = i;
        this.d = i2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = i3;
        this.i = i4;
        this.j = z6;
        this.k = z7;
        this.l = z8;
        this.m = str;
    }

    public s6(a aVar) {
        this.f5474a = aVar.f5475a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = -1;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = aVar.d;
        this.i = aVar.e;
        this.j = aVar.f;
        this.k = aVar.g;
        this.l = aVar.h;
    }
}
