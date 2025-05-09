package defpackage;

/* loaded from: classes8.dex */
public class bzd {

    /* renamed from: a, reason: collision with root package name */
    private int f552a;
    private String b;
    private String c;
    private int d;
    private int e;

    public bzd(d dVar) {
        this.b = dVar.b;
        this.d = dVar.e;
        this.e = dVar.f553a;
        this.f552a = dVar.d;
        this.c = dVar.c;
    }

    public String e() {
        return this.b;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    public int a() {
        return this.f552a;
    }

    public String d() {
        return this.c;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private int f553a;
        private String b;
        private String c;
        private int d;
        private int e;

        public d(String str, String str2) {
            this.b = str;
            this.c = str2;
        }

        public d e(int i) {
            this.f553a = i;
            return this;
        }

        public d b(int i) {
            this.e = i;
            return this;
        }

        public d a(int i) {
            this.d = i;
            return this;
        }

        public bzd e() {
            return new bzd(this);
        }
    }

    public String toString() {
        return "SleepMonitorParam{mId='" + this.b + "', mGender=" + this.e + ", mAge=" + this.d + ", mMaxSleepDuration=" + this.f552a + ", mActivityName='" + this.c + "'}";
    }
}
