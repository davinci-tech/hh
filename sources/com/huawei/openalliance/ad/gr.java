package com.huawei.openalliance.ad;

import android.app.Activity;
import android.view.View;
import com.huawei.openalliance.ad.activity.PPSActivity;
import com.huawei.openalliance.ad.views.PPSWebView;

/* loaded from: classes9.dex */
public class gr {

    /* renamed from: a, reason: collision with root package name */
    private PPSActivity.b f6888a;
    private gt b;
    private PPSWebView c;
    private jk d;

    public jk d() {
        return this.d;
    }

    public void c() {
        jk jkVar = this.d;
        if (jkVar instanceof com.huawei.openalliance.ad.linked.view.c) {
            ((com.huawei.openalliance.ad.linked.view.c) jkVar).c();
        }
    }

    public void b() {
        jk jkVar = this.d;
        if (jkVar instanceof com.huawei.openalliance.ad.linked.view.c) {
            ((com.huawei.openalliance.ad.linked.view.c) jkVar).b();
        }
    }

    public void a(jk jkVar) {
        this.d = jkVar;
    }

    public void a(PPSActivity.b bVar) {
        this.f6888a = bVar;
    }

    public void a() {
        ho.a("LinkedLandVideoViewAdapter", "destroy adapter");
        jk jkVar = this.d;
        if (jkVar instanceof com.huawei.openalliance.ad.linked.view.c) {
            ((com.huawei.openalliance.ad.linked.view.c) jkVar).a();
        }
    }

    public View a(Activity activity) {
        gt gtVar = this.b;
        if (gtVar == null) {
            return this.c;
        }
        if (!gtVar.k() || !this.b.l() || com.huawei.openalliance.ad.utils.dd.a(activity)) {
            return this.c;
        }
        gt gtVar2 = this.b;
        if (gtVar2 instanceof gs) {
            jk jkVar = this.d;
            if ((jkVar instanceof com.huawei.openalliance.ad.linked.view.c) && this.c != null) {
                com.huawei.openalliance.ad.linked.view.c cVar = (com.huawei.openalliance.ad.linked.view.c) jkVar;
                cVar.a(gtVar2);
                cVar.a(this.c);
                e();
                return cVar;
            }
        }
        return this.c;
    }

    private void e() {
        jk jkVar = this.d;
        if (jkVar instanceof com.huawei.openalliance.ad.linked.view.c) {
            ((com.huawei.openalliance.ad.linked.view.c) jkVar).setPlayModeChangeListener(this.f6888a);
        }
    }

    public gr(gt gtVar, jk jkVar, PPSWebView pPSWebView) {
        this.b = gtVar;
        this.d = jkVar;
        this.c = pPSWebView;
    }
}
