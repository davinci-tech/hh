package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.views.PPSDestView;
import com.huawei.openalliance.ad.views.PPSLinkedView;

/* loaded from: classes9.dex */
public class ub {

    /* renamed from: a, reason: collision with root package name */
    protected lz f7549a = new lo();

    public void i() {
        this.f7549a.k();
    }

    public void h() {
        this.f7549a.g();
    }

    public void g() {
        this.f7549a.h();
    }

    public void f() {
        this.f7549a.i();
    }

    public void e() {
        this.f7549a.l();
    }

    public void d() {
        this.f7549a.e();
    }

    public void c() {
        this.f7549a.j();
    }

    public void b(int i) {
        this.f7549a.b(i);
    }

    public void b() {
        this.f7549a.b();
    }

    public void a(PPSLinkedView pPSLinkedView, ContentRecord contentRecord, PPSDestView pPSDestView, Context context, com.huawei.openalliance.ad.views.ah ahVar) {
        this.f7549a.b();
        lo loVar = new lo();
        this.f7549a = loVar;
        loVar.a(context, contentRecord, pPSDestView, true);
        mi a2 = this.f7549a.a();
        if (a2 != null) {
            a2.a(ahVar, mh.VIDEO_CONTROLS, null);
            a2.a(pPSLinkedView, mh.OTHER, null);
            this.f7549a.a(false);
            this.f7549a.b(true);
            this.f7549a.c();
            this.f7549a.a(mq.a(0.0f, true, mp.STANDALONE));
        }
    }

    public void a(VideoInfo videoInfo) {
        if (this.f7549a == null || videoInfo == null) {
            return;
        }
        ho.b("LinkedSplashOmProxy", "om start");
        this.f7549a.a(videoInfo.getVideoDuration(), !"y".equals(videoInfo.getSoundSwitch()));
    }

    public void a(int i) {
        this.f7549a.a(i);
    }

    public void a() {
        this.f7549a.a(mo.CLICK);
    }
}
