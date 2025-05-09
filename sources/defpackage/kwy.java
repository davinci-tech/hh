package defpackage;

import java.util.Arrays;

/* loaded from: classes5.dex */
public class kwy {

    /* renamed from: a, reason: collision with root package name */
    private int[] f14671a;
    private long b;
    private int c;
    private int d;
    private long e;
    private int f;
    private int h;
    private int j;

    private kwy(a aVar) {
        this.f = aVar.i;
        this.h = aVar.g;
        this.e = aVar.d;
        this.b = aVar.f14672a;
        this.d = aVar.c;
        this.j = aVar.j;
        this.c = aVar.e;
        this.f14671a = aVar.b;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private long f14672a;
        private int[] b;
        private int c;
        private long d;
        private int e;
        private int g;
        private int i;
        private int j;

        public kwy d() {
            return new kwy(this);
        }

        public a d(int i) {
            this.i = i;
            return this;
        }

        public a a(int i) {
            this.g = i;
            return this;
        }

        public a a(long j) {
            this.d = j;
            return this;
        }

        public a e(long j) {
            this.f14672a = j;
            return this;
        }

        public a b(int i) {
            this.c = i;
            return this;
        }

        public a c(int i) {
            this.j = i;
            return this;
        }

        public a c(int[] iArr) {
            if (iArr != null) {
                this.b = (int[]) iArr.clone();
            }
            return this;
        }
    }

    public int f() {
        return this.f;
    }

    public int i() {
        return this.h;
    }

    public long a() {
        return this.e;
    }

    public long c() {
        return this.b;
    }

    public int e() {
        return this.d;
    }

    public int g() {
        return this.j;
    }

    public int b() {
        return this.c;
    }

    public int[] d() {
        int[] iArr = this.f14671a;
        if (iArr != null) {
            return (int[]) iArr.clone();
        }
        return null;
    }

    public String toString() {
        return "SportRecordQueryParam{mType=" + this.f + ", mSubType=" + this.h + ", mStartTime=" + this.e + ", mEndTime=" + this.b + ", mCount=" + this.d + ", mTimeUnit=" + this.j + ", mSortOrderType=" + this.c + ", mMultiType=" + Arrays.toString(this.f14671a) + '}';
    }
}
