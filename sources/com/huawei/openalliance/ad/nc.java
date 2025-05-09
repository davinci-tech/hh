package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import com.huawei.hms.ads.uiengine.common.IInterstitialView;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;

/* loaded from: classes5.dex */
public class nc extends jj<IInterstitialView> {
    private IInterstitialView d;
    private Context e;
    private com.huawei.openalliance.ad.inter.data.d f;

    public void b() {
    }

    public void c() {
        this.b.b();
    }

    public void a(boolean z) {
        this.b.b(z);
    }

    public void a(ta taVar, Integer num, MaterialClickInfo materialClickInfo) {
        b.a aVar = new b.a();
        if (num == null) {
            num = 7;
        }
        aVar.b(taVar.c()).a(num).a(materialClickInfo);
        this.b.a(aVar.a());
    }

    public void a(com.huawei.openalliance.ad.inter.data.d dVar) {
        this.f = dVar;
        this.f7126a = ox.a(dVar);
        this.b.a(this.f7126a);
    }

    @Override // com.huawei.openalliance.ad.jj
    public final void a(IInterstitialView iInterstitialView) {
        this.d = iInterstitialView;
    }

    public void a(long j, int i, Integer num) {
        String f = f();
        com.huawei.openalliance.ad.inter.data.d dVar = this.f;
        if (dVar != null) {
            ho.a("InterstitialAdPresenter", "slotId: %s, contentId: %s, slot pos: %s", dVar.O(), this.f.getContentId(), f);
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(j)).a(Integer.valueOf(i)).d(f).e(vd.b((View) this.d)).b(num);
        this.b.a(c0207a.a());
    }

    public void a(long j, int i) {
        this.b.a(j, i);
    }

    @Override // com.huawei.openalliance.ad.jj
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public IInterstitialView d() {
        return this.d;
    }

    public nc(Context context, IInterstitialView iInterstitialView) {
        a(iInterstitialView);
        this.e = context;
        this.b = new ou(context, new sd(context));
    }
}
