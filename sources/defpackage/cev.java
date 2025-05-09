package defpackage;

import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class cev {

    /* renamed from: a, reason: collision with root package name */
    private int f667a;
    private long d;
    private String e;

    private cev(int i, long j, String str) {
        this.f667a = i;
        this.d = j;
        this.e = str;
    }

    public int c() {
        return ((Integer) cpt.d(Integer.valueOf(this.f667a))).intValue();
    }

    public long e() {
        return ((Long) cpt.d(Long.valueOf(this.d))).longValue();
    }

    public String a() {
        return (String) cpt.d(this.e);
    }

    public static final class b {
        private static final int[] e = {1, 2, 4, 8, 16, 32};
        private int c = 1;
        private long b = -1;
        private String d = "no";

        public b a(int i) {
            for (int i2 : e) {
                if (i2 == i) {
                    this.c = i;
                    return this;
                }
            }
            throw new IllegalArgumentException("invalid scan type " + i);
        }

        public b c(long j, TimeUnit timeUnit) {
            if (j < 0 && j != -1) {
                throw new IllegalArgumentException("invalid scan duration " + j);
            }
            this.b = j > 0 ? (int) timeUnit.toMillis(j) : -1L;
            return this;
        }

        public b e(String str) {
            this.d = str;
            return this;
        }

        public cev c() {
            return new cev(this.c, this.b, this.d);
        }
    }
}
