package defpackage;

/* loaded from: classes5.dex */
public class iyk {

    /* renamed from: a, reason: collision with root package name */
    private final String f13662a;
    private final int b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;

    private iyk(e eVar) {
        this.b = eVar.c;
        this.f13662a = eVar.e;
        this.f = eVar.j;
        this.c = eVar.b;
        this.e = eVar.d;
        this.d = eVar.f13663a;
    }

    public int d() {
        return this.b;
    }

    public String b() {
        return this.f13662a;
    }

    public String f() {
        return this.f;
    }

    public String e() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String a() {
        return this.d;
    }

    public String toString() {
        return "PushOpenAppBiInfo{biType=" + this.b + ", name='" + this.f13662a + "', url='" + this.f + "', pushId='" + this.c + "', serviceId='" + this.e + "', content='" + this.d + "'}";
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private String f13663a;
        private String b;
        private int c;
        private String d;
        private String e;
        private String j;

        public e a(int i) {
            this.c = i;
            return this;
        }

        public e d(String str) {
            this.e = str;
            return this;
        }

        public e a(String str) {
            this.j = str;
            return this;
        }

        public e e(String str) {
            this.b = str;
            return this;
        }

        public e c(String str) {
            this.d = str;
            return this;
        }

        public e b(String str) {
            this.f13663a = str;
            return this;
        }

        public iyk b() {
            return new iyk(this);
        }
    }
}
