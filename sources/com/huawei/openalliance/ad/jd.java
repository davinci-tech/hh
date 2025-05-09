package com.huawei.openalliance.ad;

import android.view.View;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.data.VideoInfo;

/* loaded from: classes9.dex */
public class jd extends jh {

    /* renamed from: a, reason: collision with root package name */
    boolean f7117a;
    boolean b;
    protected LinkedSplashAd c;
    private a e;
    private long f;
    private int g;
    private boolean h;
    private long i;
    private int j;
    private int k;
    private int l;

    public interface a {
        void a();

        void a(long j, int i);

        void b();

        void b(long j, int i);

        void c();

        void d();

        void e();
    }

    public boolean g() {
        return k() >= b();
    }

    public void f() {
        a aVar;
        int k = k();
        ho.b("PPSLinkedViewMonitor", "checkAutoPlay, visibleArea: %s", Integer.valueOf(k));
        if (k < b() || (aVar = this.e) == null) {
            return;
        }
        aVar.c();
    }

    public long e() {
        return this.i;
    }

    public int d() {
        return this.j;
    }

    protected int c() {
        return this.l;
    }

    public void b(long j, int i) {
        this.g = i;
        this.f = j;
    }

    void b(int i) {
        a aVar;
        if (i >= b()) {
            this.b = false;
            if (this.f7117a) {
                return;
            }
            this.f7117a = true;
            a aVar2 = this.e;
            if (aVar2 != null) {
                aVar2.c();
                return;
            }
            return;
        }
        this.f7117a = false;
        if (i > 100 - c()) {
            if (this.b && (aVar = this.e) != null) {
                aVar.e();
            }
            this.b = false;
            return;
        }
        if (this.b) {
            return;
        }
        this.b = true;
        a aVar3 = this.e;
        if (aVar3 != null) {
            aVar3.d();
        }
    }

    protected int b() {
        return this.k;
    }

    public boolean a(long j) {
        return j >= this.f && this.j >= this.g;
    }

    public void a(LinkedSplashAd linkedSplashAd) {
        this.c = linkedSplashAd;
        if (linkedSplashAd == null || linkedSplashAd.getVideoInfo() == null) {
            return;
        }
        VideoInfo videoInfo = linkedSplashAd.getVideoInfo();
        this.k = videoInfo.getAutoPlayAreaRatio();
        this.l = Math.max(100 - videoInfo.getAutoStopPlayAreaRatio(), 0);
    }

    @Override // com.huawei.openalliance.ad.jh
    protected void a(long j, int i) {
        p();
        a aVar = this.e;
        if (aVar != null) {
            aVar.b(j, i);
        }
        b(0);
    }

    @Override // com.huawei.openalliance.ad.jh
    protected void a(int i) {
        ho.b("PPSLinkedViewMonitor", "onUpdateViewShowArea, percentage: %s", Integer.valueOf(i));
        if (i > this.j) {
            this.j = i;
        }
        if (i >= this.g) {
            o();
        } else {
            p();
        }
        b(i);
    }

    @Override // com.huawei.openalliance.ad.jh
    protected void a() {
        a aVar = this.e;
        if (aVar != null) {
            aVar.b();
        }
    }

    private void p() {
        if (this.h) {
            ho.b("PPSLinkedViewMonitor", "viewShowEndRecord");
            this.h = false;
            long currentTimeMillis = System.currentTimeMillis() - this.i;
            if (ho.a()) {
                ho.a("PPSLinkedViewMonitor", "max visible area percentage: %d duration: %d", Integer.valueOf(this.j), Long.valueOf(currentTimeMillis));
            }
            a aVar = this.e;
            if (aVar != null) {
                aVar.a(currentTimeMillis, this.j);
            }
            this.j = 0;
        }
    }

    private void o() {
        if (this.h) {
            return;
        }
        ho.b("PPSLinkedViewMonitor", "viewShowStartRecord");
        this.h = true;
        this.i = System.currentTimeMillis();
        a aVar = this.e;
        if (aVar != null) {
            aVar.a();
        }
    }

    public jd(View view, a aVar) {
        super(view);
        this.f = 500L;
        this.g = 50;
        this.h = false;
        this.k = 100;
        this.l = 10;
        this.f7117a = false;
        this.b = false;
        this.e = aVar;
        this.i = com.huawei.openalliance.ad.utils.ao.c();
    }
}
