package defpackage;

import health.compact.a.LogUtil;

/* loaded from: classes6.dex */
public class njp {

    /* renamed from: a, reason: collision with root package name */
    private String f15335a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String g;

    public njp(b bVar) {
        if (bVar == null) {
            LogUtil.c("ThemeProductQueryParam", "builder is null");
            return;
        }
        this.b = bVar.b;
        this.e = bVar.c;
        this.f15335a = bVar.f15336a;
        this.c = bVar.d;
        this.g = bVar.h;
        this.d = bVar.e;
    }

    public String a() {
        return this.b;
    }

    public String e() {
        return this.e;
    }

    public String d() {
        return this.f15335a;
    }

    public String c() {
        return this.c;
    }

    public String f() {
        return this.g;
    }

    public String b() {
        return this.d;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f15336a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String h;

        public njp d() {
            return new njp(this);
        }

        public b e(String str) {
            this.b = str;
            return this;
        }

        public b b(String str) {
            this.c = str;
            return this;
        }

        public b c(String str) {
            this.f15336a = str;
            return this;
        }

        public b a(String str) {
            this.d = str;
            return this;
        }

        public b g(String str) {
            this.h = str;
            return this;
        }

        public b d(String str) {
            this.e = str;
            return this;
        }
    }
}
