package com.huawei.maps.offlinedata.logpush.dto;

/* loaded from: classes5.dex */
public class a extends c {

    /* renamed from: a, reason: collision with root package name */
    private String f6451a;
    private String b;
    private String c;
    private long d;
    private long e;

    public void a(String str) {
        this.f6451a = str;
    }

    public String a() {
        return this.f6451a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public String toString() {
        return "AccessTraceLogDTO{ requestId='" + this.f6451a + "', apiName='" + this.b + "', errorCode='" + this.c + "', startTime=" + this.d + ", endTime=" + this.e + '}';
    }

    private a(C0167a c0167a) {
        this.f6451a = "";
        this.b = "";
        this.c = "";
        this.f6451a = c0167a.f6452a;
        this.b = c0167a.b;
        this.d = System.currentTimeMillis();
        c(c0167a.c);
    }

    /* renamed from: com.huawei.maps.offlinedata.logpush.dto.a$a, reason: collision with other inner class name */
    public static final class C0167a {

        /* renamed from: a, reason: collision with root package name */
        private String f6452a = "";
        private String b;
        private String c;

        private C0167a() {
        }

        public C0167a a(String str) {
            this.b = str;
            return this;
        }

        public static C0167a a() {
            C0167a c0167a = new C0167a();
            c0167a.c = "sdk";
            return c0167a;
        }

        public static C0167a b() {
            C0167a c0167a = new C0167a();
            c0167a.c = "cloud";
            return c0167a;
        }

        public a c() {
            return new a(this);
        }
    }
}
