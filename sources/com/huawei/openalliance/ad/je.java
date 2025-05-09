package com.huawei.openalliance.ad;

import android.view.View;

/* loaded from: classes5.dex */
public class je extends jh {

    /* renamed from: a, reason: collision with root package name */
    private a f7118a;
    private long b;
    private int c;
    private boolean e;
    private long f;
    private int g;

    public interface a {
        void a();

        void a(long j, int i);

        void b();

        void b(long j, int i);
    }

    public long d() {
        return this.f;
    }

    public int c() {
        return this.g;
    }

    public void b(long j, int i) {
        this.c = i;
        this.b = j;
    }

    public void b() {
        this.c = 50;
        this.b = 500L;
    }

    public boolean a(long j) {
        return j >= this.b && this.g >= this.c;
    }

    @Override // com.huawei.openalliance.ad.jh
    protected void a(long j, int i) {
        f();
        a aVar = this.f7118a;
        if (aVar != null) {
            aVar.b(j, i);
        }
    }

    @Override // com.huawei.openalliance.ad.jh
    protected void a(int i) {
        if (i > this.g) {
            this.g = i;
        }
        if (i >= this.c) {
            e();
        } else {
            f();
        }
    }

    @Override // com.huawei.openalliance.ad.jh
    protected void a() {
        a aVar = this.f7118a;
        if (aVar != null) {
            aVar.b();
        }
    }

    private void f() {
        if (this.e) {
            ho.b("PPSNativeViewMonitor", "viewShowEndRecord");
            this.e = false;
            long currentTimeMillis = System.currentTimeMillis() - this.f;
            if (ho.a()) {
                ho.a("PPSNativeViewMonitor", "max visible area percentage: %d duration: %d", Integer.valueOf(this.g), Long.valueOf(currentTimeMillis));
            }
            a aVar = this.f7118a;
            if (aVar != null) {
                aVar.a(currentTimeMillis, this.g);
            }
            this.g = 0;
        }
    }

    private void e() {
        if (this.e) {
            return;
        }
        ho.b("PPSNativeViewMonitor", "viewShowStartRecord");
        this.e = true;
        this.f = System.currentTimeMillis();
        a aVar = this.f7118a;
        if (aVar != null) {
            aVar.a();
        }
    }

    public je(View view, a aVar) {
        super(view);
        this.b = 500L;
        this.c = 50;
        this.e = false;
        this.f7118a = aVar;
        this.f = com.huawei.openalliance.ad.utils.ao.c();
    }
}
