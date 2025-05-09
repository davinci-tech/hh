package com.huawei.openalliance.ad.processor.eventbeans;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private Long f7452a;
    private Integer b;
    private Integer c;
    private String d;
    private String e;
    private boolean f;
    private Integer g;
    private String h;
    private String i;
    private boolean j;
    private String k;
    private boolean l;

    /* renamed from: com.huawei.openalliance.ad.processor.eventbeans.a$a, reason: collision with other inner class name */
    public static class C0207a {

        /* renamed from: a, reason: collision with root package name */
        private Long f7453a;
        private Integer b;
        private Integer c;
        private String h;
        private String i;
        private String k;
        private String d = null;
        private String e = null;
        private boolean f = true;
        private Integer g = null;
        private boolean j = true;
        private boolean l = false;

        public C0207a e(String str) {
            this.k = str;
            return this;
        }

        public C0207a d(String str) {
            this.i = str;
            return this;
        }

        public C0207a c(boolean z) {
            this.l = z;
            return this;
        }

        public C0207a c(String str) {
            this.h = str;
            return this;
        }

        public C0207a c(Integer num) {
            this.g = num;
            return this;
        }

        public C0207a b(boolean z) {
            this.f = z;
            return this;
        }

        public C0207a b(String str) {
            this.e = str;
            return this;
        }

        public C0207a b(Integer num) {
            this.c = num;
            return this;
        }

        public a a() {
            return new a(this);
        }

        public C0207a a(boolean z) {
            this.j = z;
            return this;
        }

        public C0207a a(String str) {
            this.d = str;
            return this;
        }

        public C0207a a(Long l) {
            this.f7453a = l;
            return this;
        }

        public C0207a a(Integer num) {
            this.b = num;
            return this;
        }
    }

    public boolean l() {
        return this.l;
    }

    public String k() {
        return this.i;
    }

    public String j() {
        return this.h;
    }

    public Integer i() {
        return this.g;
    }

    public boolean h() {
        return this.f;
    }

    public String g() {
        return this.e;
    }

    public String f() {
        return this.d;
    }

    public Integer e() {
        return this.c;
    }

    public Integer d() {
        return this.b;
    }

    public Long c() {
        return this.f7452a;
    }

    public boolean b() {
        return this.j;
    }

    public String a() {
        return this.k;
    }

    private a(C0207a c0207a) {
        this.j = true;
        this.l = false;
        this.f7452a = c0207a.f7453a;
        this.b = c0207a.b;
        this.c = c0207a.c;
        this.d = c0207a.d;
        this.e = c0207a.e;
        this.f = c0207a.f;
        this.g = c0207a.g;
        this.h = c0207a.h;
        this.i = c0207a.i;
        this.j = c0207a.j;
        this.k = c0207a.k;
        this.l = c0207a.l;
    }

    private a() {
        this.j = true;
        this.l = false;
    }
}
