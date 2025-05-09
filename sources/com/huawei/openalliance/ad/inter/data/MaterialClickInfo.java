package com.huawei.openalliance.ad.inter.data;

/* loaded from: classes5.dex */
public class MaterialClickInfo {
    private Long clickDTime;
    private Long clickUTime;
    private Integer clickX;
    private Integer clickY;
    private String creativeSize;
    private Float density;
    private Integer mark;
    private String shakeAngle;
    private Integer sld;
    private Integer upX;
    private Integer upY;

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private Integer f7046a;
        private Integer b;
        private String c;
        private Integer d;
        private Integer e;
        private Integer f;
        private Float g;
        private Long h;
        private Long i;
        private String j;

        public a e(Integer num) {
            this.f = num;
            return this;
        }

        public a d(Integer num) {
            this.e = num;
            return this;
        }

        public a c(Integer num) {
            this.d = num;
            return this;
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public a b(Long l) {
            this.i = l;
            return this;
        }

        public a b(Integer num) {
            this.b = num;
            return this;
        }

        public MaterialClickInfo a() {
            return new MaterialClickInfo(this);
        }

        public a a(String str) {
            this.j = str;
            return this;
        }

        public a a(Long l) {
            this.h = l;
            return this;
        }

        public a a(Integer num) {
            this.f7046a = num;
            return this;
        }

        public a a(Float f) {
            this.g = f;
            return this;
        }
    }

    public String toString() {
        return "MaterialClickInfo{clickX=" + this.clickX + ", clickY=" + this.clickY + ", creativeSize='" + this.creativeSize + "', sld=" + this.sld + ", density=" + this.density + ", upX=" + this.upX + ", upY=" + this.upY + '}';
    }

    public String k() {
        return this.shakeAngle;
    }

    public Integer j() {
        return this.mark;
    }

    public Long i() {
        return this.clickDTime;
    }

    public Long h() {
        return this.clickUTime;
    }

    public Float g() {
        return this.density;
    }

    public void f(Integer num) {
        this.mark = num;
    }

    public Integer f() {
        return this.upY;
    }

    public void e(Integer num) {
        this.upY = num;
    }

    public Integer e() {
        return this.upX;
    }

    public void d(Integer num) {
        this.upX = num;
    }

    public Integer d() {
        return this.sld;
    }

    public void c(Integer num) {
        this.sld = num;
    }

    public String c() {
        return this.creativeSize;
    }

    public void b(Integer num) {
        this.clickY = num;
    }

    public Integer b() {
        return this.clickY;
    }

    public void a(String str) {
        this.creativeSize = str;
    }

    public void a(Long l) {
        this.clickUTime = l;
    }

    public void a(Integer num) {
        this.clickX = num;
    }

    public void a(Float f) {
        this.density = f;
    }

    public Integer a() {
        return this.clickX;
    }

    public MaterialClickInfo(a aVar) {
        this.clickX = aVar.f7046a;
        this.clickY = aVar.b;
        this.creativeSize = aVar.c;
        this.sld = aVar.d;
        this.density = aVar.g;
        this.upX = aVar.e;
        this.upY = aVar.f;
        this.clickDTime = aVar.i;
        this.clickUTime = aVar.h;
        this.shakeAngle = aVar.j;
    }

    public MaterialClickInfo() {
    }
}
