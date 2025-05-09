package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class jg {

    /* renamed from: a, reason: collision with root package name */
    private boolean f7121a = false;
    private boolean b = false;
    private long c = 0;
    private long d = 0;
    private String e;

    public long e() {
        return this.d;
    }

    public long d() {
        return this.c;
    }

    public void c() {
        if (ho.a()) {
            ho.a(this.e, "onVideoEnd");
        }
        this.b = false;
        this.f7121a = false;
        this.c = 0L;
        this.d = 0L;
    }

    public void b() {
        if (ho.a()) {
            ho.a(this.e, "onBufferStart");
        }
        if (this.f7121a) {
            return;
        }
        this.f7121a = true;
        this.c = System.currentTimeMillis();
    }

    public void a() {
        if (ho.a()) {
            ho.a(this.e, "onPlayStart");
        }
        if (this.b) {
            return;
        }
        this.b = true;
        this.d = System.currentTimeMillis();
    }

    public jg(String str) {
        this.e = "VideoMonitor_" + str;
    }
}
