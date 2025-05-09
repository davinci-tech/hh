package defpackage;

/* loaded from: classes8.dex */
public class bzl {

    /* renamed from: a, reason: collision with root package name */
    private long f567a;
    private long b;
    private long e;

    public bzl(c cVar) {
        this.b = cVar.e;
        this.e = cVar.f568a;
        this.f567a = cVar.b;
    }

    public long b() {
        return this.b;
    }

    public long d() {
        return this.e;
    }

    public long a() {
        return this.f567a;
    }

    public String toString() {
        return "SleepRegularParam{mSleepBeginTime=" + this.b + ", mSleepEndTime=" + this.e + ", mSleepValidTime=" + this.f567a + '}';
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private long f568a;
        private long b;
        private long e;

        public c d(long j) {
            this.e = j;
            return this;
        }

        public c b(long j) {
            this.f568a = j;
            return this;
        }

        public c a(long j) {
            this.b = j;
            return this;
        }

        public bzl d() {
            return new bzl(this);
        }
    }
}
