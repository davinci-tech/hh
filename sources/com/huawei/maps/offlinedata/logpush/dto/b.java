package com.huawei.maps.offlinedata.logpush.dto;

/* loaded from: classes5.dex */
public class b extends c {

    /* renamed from: a, reason: collision with root package name */
    private String f6453a;
    private String b;
    private Throwable c;

    public String a() {
        return this.f6453a;
    }

    public String b() {
        return this.b;
    }

    public Throwable c() {
        return this.c;
    }

    public String toString() {
        return "ErrorTraceLogDTO{ scenario='" + this.f6453a + "', message='" + this.b + "'}";
    }

    private b(a aVar) {
        this.f6453a = aVar.f6454a;
        this.b = aVar.b;
        this.c = aVar.c;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f6454a;
        private String b;
        private Throwable c;

        public a a(String str) {
            this.f6454a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }
}
