package defpackage;

/* loaded from: classes6.dex */
public class ntp {

    /* renamed from: a, reason: collision with root package name */
    private final int[] f15486a;
    private final int[] c;
    private final int d;

    public ntp(b bVar) {
        this.f15486a = bVar.c;
        this.c = bVar.e;
        this.d = bVar.f15487a;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private int f15487a;
        private int[] c;
        private int[] e;

        public b a(int i) {
            this.f15487a = i;
            return this;
        }

        public b b(int[] iArr) {
            this.c = iArr;
            return this;
        }

        public b c(int[] iArr) {
            this.e = iArr;
            return this;
        }

        public ntp e() {
            return new ntp(this);
        }
    }

    public int a() {
        return this.d;
    }

    public int[] d() {
        return this.f15486a;
    }

    public int[] e() {
        return this.c;
    }
}
