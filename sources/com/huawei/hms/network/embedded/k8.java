package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.j7;
import com.huawei.hms.network.embedded.r1;
import com.huawei.hms.network.embedded.v7;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class k8 {

    /* renamed from: a, reason: collision with root package name */
    @Nullable
    public final t7 f5347a;

    @Nullable
    public final v7 b;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final long f5348a;
        public final t7 b;
        public final v7 c;
        public Date d;
        public String e;
        public Date f;
        public String g;
        public Date h;
        public long i;
        public long j;
        public String k;
        public int l;

        public k8 a() {
            k8 d = d();
            return (d.f5347a == null || !this.b.c().k()) ? d : new k8(null, null);
        }

        private boolean e() {
            return this.c.t().d() == -1 && this.h == null;
        }

        private k8 d() {
            String str;
            if (this.c == null) {
                return new k8(this.b, null);
            }
            if ((!this.b.g() || this.c.x() != null) && k8.a(this.c, this.b)) {
                s6 c = this.b.c();
                if (c.h() || a(this.b)) {
                    return new k8(this.b, null);
                }
                s6 t = this.c.t();
                long b = b();
                long c2 = c();
                if (c.d() != -1) {
                    c2 = Math.min(c2, TimeUnit.SECONDS.toMillis(c.d()));
                }
                long j = 0;
                long millis = c.f() != -1 ? TimeUnit.SECONDS.toMillis(c.f()) : 0L;
                if (!t.g() && c.e() != -1) {
                    j = TimeUnit.SECONDS.toMillis(c.e());
                }
                if (!t.h()) {
                    long j2 = millis + b;
                    if (j2 < j + c2) {
                        v7.a D = this.c.D();
                        if (j2 >= c2) {
                            D.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                        }
                        if (b > 86400000 && e()) {
                            D.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                        }
                        return new k8(null, D.a());
                    }
                }
                String str2 = this.k;
                if (str2 != null) {
                    str = r1.b.n;
                } else {
                    if (this.f != null) {
                        str2 = this.g;
                    } else {
                        if (this.d == null) {
                            return new k8(this.b, null);
                        }
                        str2 = this.e;
                    }
                    str = r1.b.o;
                }
                j7.a c3 = this.b.e().c();
                c8.f5203a.a(c3, str, str2);
                return new k8(this.b.i().a(c3.a()).a(), this.c);
            }
            return new k8(this.b, null);
        }

        private long c() {
            if (this.c.t().d() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.d());
            }
            if (this.h != null) {
                Date date = this.d;
                long time = this.h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f == null || this.c.H().k().o() != null) {
                return 0L;
            }
            Date date2 = this.d;
            long time2 = (date2 != null ? date2.getTime() : this.i) - this.f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        private long b() {
            Date date = this.d;
            long max = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i = this.l;
            if (i != -1) {
                max = Math.max(max, TimeUnit.SECONDS.toMillis(i));
            }
            long j = this.j;
            return max + (j - this.i) + (this.f5348a - j);
        }

        public static boolean a(t7 t7Var) {
            return (t7Var.a(r1.b.o) == null && t7Var.a(r1.b.n) == null) ? false : true;
        }

        public a(long j, t7 t7Var, v7 v7Var) {
            this.l = -1;
            this.f5348a = j;
            this.b = t7Var;
            this.c = v7Var;
            if (v7Var != null) {
                this.i = v7Var.I();
                this.j = v7Var.G();
                j7 y = v7Var.y();
                int d = y.d();
                for (int i = 0; i < d; i++) {
                    String a2 = y.a(i);
                    String b = y.b(i);
                    if ("Date".equalsIgnoreCase(a2)) {
                        this.d = h9.a(b);
                        this.e = b;
                    } else if ("Expires".equalsIgnoreCase(a2)) {
                        this.h = h9.a(b);
                    } else if ("Last-Modified".equalsIgnoreCase(a2)) {
                        this.f = h9.a(b);
                        this.g = b;
                    } else if ("ETag".equalsIgnoreCase(a2)) {
                        this.k = b;
                    } else if ("Age".equalsIgnoreCase(a2)) {
                        this.l = i9.a(b, -1);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        if (r3.t().b() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(com.huawei.hms.network.embedded.v7 r3, com.huawei.hms.network.embedded.t7 r4) {
        /*
            int r0 = r3.w()
            r1 = 200(0xc8, float:2.8E-43)
            r2 = 0
            if (r0 == r1) goto L5a
            r1 = 410(0x19a, float:5.75E-43)
            if (r0 == r1) goto L5a
            r1 = 414(0x19e, float:5.8E-43)
            if (r0 == r1) goto L5a
            r1 = 501(0x1f5, float:7.02E-43)
            if (r0 == r1) goto L5a
            r1 = 203(0xcb, float:2.84E-43)
            if (r0 == r1) goto L5a
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 == r1) goto L5a
            r1 = 307(0x133, float:4.3E-43)
            if (r0 == r1) goto L31
            r1 = 308(0x134, float:4.32E-43)
            if (r0 == r1) goto L5a
            r1 = 404(0x194, float:5.66E-43)
            if (r0 == r1) goto L5a
            r1 = 405(0x195, float:5.68E-43)
            if (r0 == r1) goto L5a
            switch(r0) {
                case 300: goto L5a;
                case 301: goto L5a;
                case 302: goto L31;
                default: goto L30;
            }
        L30:
            goto L59
        L31:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.b(r0)
            if (r0 != 0) goto L5a
            com.huawei.hms.network.embedded.s6 r0 = r3.t()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            com.huawei.hms.network.embedded.s6 r0 = r3.t()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            com.huawei.hms.network.embedded.s6 r0 = r3.t()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            com.huawei.hms.network.embedded.s6 r3 = r3.t()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            com.huawei.hms.network.embedded.s6 r3 = r4.c()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.k8.a(com.huawei.hms.network.embedded.v7, com.huawei.hms.network.embedded.t7):boolean");
    }

    public k8(t7 t7Var, v7 v7Var) {
        this.f5347a = t7Var;
        this.b = v7Var;
    }
}
