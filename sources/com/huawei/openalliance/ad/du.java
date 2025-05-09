package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.download.DownloadTask;

/* loaded from: classes5.dex */
public class du extends DownloadTask {
    private String c;
    private String d;
    private String e;
    private Long f;
    private Long g;
    private String i;
    private int b = -1;
    private boolean h = false;

    /* renamed from: a, reason: collision with root package name */
    int f6829a = 0;

    @Override // com.huawei.openalliance.ad.download.DownloadTask
    public String n() {
        return this.c;
    }

    public void j(String str) {
        this.i = str;
    }

    public void i(String str) {
        this.e = str;
    }

    @Override // com.huawei.openalliance.ad.download.DownloadTask
    public int hashCode() {
        return super.hashCode();
    }

    public void h(String str) {
        this.d = str;
    }

    public void g(String str) {
        this.c = str;
    }

    @Override // com.huawei.openalliance.ad.download.DownloadTask
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void e(boolean z) {
        this.h = z;
    }

    public void e(int i) {
        this.f6829a = i;
    }

    public void d(int i) {
        this.b = i;
    }

    public void b(Long l) {
        this.g = l;
    }

    public void a(Long l) {
        this.f = l;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f6830a = false;
        private String b;
        private int c;
        private String d;
        private String e;
        private String f;

        public a d(String str) {
            this.f = str;
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }

        public a b(String str) {
            this.d = str;
            return this;
        }

        public du a() {
            du duVar = new du();
            duVar.a(this.f6830a);
            String a2 = com.huawei.openalliance.ad.utils.cu.a(this.b);
            duVar.g(a2);
            duVar.e(dt.h().b(a2));
            duVar.d(Scheme.DISKCACHE.toString() + a2);
            duVar.a(this.b);
            duVar.c(this.d);
            duVar.a((long) this.c);
            duVar.a(0);
            duVar.i(this.f);
            duVar.h(this.e);
            return duVar;
        }

        public a a(boolean z) {
            this.f6830a = z;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }
    }

    public int I() {
        return this.f6829a;
    }

    public String H() {
        return this.i;
    }

    public int G() {
        return this.b;
    }

    public Long F() {
        return this.g;
    }

    public Long E() {
        return this.f;
    }

    public boolean D() {
        return this.h;
    }

    public String C() {
        return this.e;
    }

    public String B() {
        return this.d;
    }
}
