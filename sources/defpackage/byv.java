package defpackage;

/* loaded from: classes3.dex */
public class byv {

    /* renamed from: a, reason: collision with root package name */
    private boolean f548a;
    private final boolean c;
    private int d;
    private final boolean e;

    public byv(d dVar) {
        this.d = dVar.b;
        this.f548a = dVar.f549a;
        this.c = dVar.c;
        this.e = dVar.e;
    }

    public int a() {
        return this.d;
    }

    public boolean b() {
        return this.f548a;
    }

    public boolean d() {
        return this.c;
    }

    public boolean c() {
        return this.e;
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private boolean f549a;
        private int b = -1;
        private boolean c;
        private boolean e;

        public d e(int i) {
            this.b = i;
            return this;
        }

        public d e(boolean z) {
            this.f549a = z;
            return this;
        }

        public d d(boolean z) {
            this.c = z;
            return this;
        }

        public d a(boolean z) {
            this.e = z;
            return this;
        }

        public byv c() {
            return new byv(this);
        }
    }
}
