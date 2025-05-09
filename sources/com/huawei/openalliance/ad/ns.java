package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public abstract class ns {

    /* renamed from: a, reason: collision with root package name */
    protected gc f7335a;
    private hl b;
    private final String c = "min_show_time_task" + hashCode();
    private final String d = "max_show_time_task" + hashCode();

    public void a() {
    }

    protected void d() {
        com.huawei.openalliance.ad.utils.dj.a(this.c);
    }

    protected void c() {
        hl hlVar = this.b;
        if (hlVar != null) {
            hlVar.b();
        }
    }

    protected void b(long j) {
        ho.b(getClass().getSimpleName(), "start min show time task duration: %d", Long.valueOf(j));
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ns.2
            @Override // java.lang.Runnable
            public void run() {
                ns.this.b();
            }
        }, this.c, j);
    }

    protected void b() {
        hl hlVar = this.b;
        if (hlVar != null) {
            hlVar.a();
        }
    }

    protected void a(long j) {
        ho.b(getClass().getSimpleName(), "start max show time task duration: %d", Long.valueOf(j));
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ns.1
            @Override // java.lang.Runnable
            public void run() {
                ho.a(getClass().getSimpleName(), "cancelMinShowTimeCountTask and cancelMinShowTimeCountTask");
                ns.this.d();
                ns.this.c();
            }
        }, this.d, j);
    }

    public ns(gc gcVar, hl hlVar) {
        this.f7335a = gcVar;
        this.b = hlVar;
    }
}
