package defpackage;

/* loaded from: classes7.dex */
public class rdp {

    /* renamed from: a, reason: collision with root package name */
    private long f16721a;
    private long b;
    private int c;

    private rdp(e eVar) {
        this.b = eVar.b;
        this.f16721a = eVar.f16722a;
        this.c = eVar.c;
    }

    public long d() {
        return this.b;
    }

    public long b() {
        return this.f16721a;
    }

    public int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof rdp)) {
            return false;
        }
        rdp rdpVar = (rdp) obj;
        return rdpVar.d() == this.b && rdpVar.b() == this.f16721a && rdpVar.e() == this.c;
    }

    public int hashCode() {
        return Long.valueOf(this.b).hashCode() + Long.valueOf(this.f16721a).hashCode() + Integer.valueOf(this.c).hashCode();
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private long f16722a;
        private long b;
        private int c;

        public rdp c() {
            return new rdp(this);
        }

        public e c(long j) {
            this.b = j;
            return this;
        }

        public e d(long j) {
            this.f16722a = j;
            return this;
        }

        public e a(int i) {
            this.c = i;
            return this;
        }
    }

    public String toString() {
        return "startTIme = " + this.b + ", endTime = " + this.f16721a + ", sportType = " + this.c;
    }
}
