package defpackage;

/* loaded from: classes6.dex */
public class plr {
    protected String b(String str, int i) {
        return str + "&from=" + i;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f16175a;
        private String b;
        private String c;
        private String d;

        public String c() {
            return this.c;
        }

        public void d(String str) {
            this.c = str;
        }

        public String e() {
            return this.d;
        }

        public void a(String str) {
            this.d = str;
        }

        public String b() {
            return this.f16175a;
        }

        public void e(String str) {
            this.f16175a = str;
        }

        public String a() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public String toString() {
            return "title: " + this.c + " content: " + this.d + " watchDeeplink: " + this.b + " phoneDeeplink: " + this.b;
        }
    }
}
