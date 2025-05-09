package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.views.interfaces.IPPSPlacementView;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class nm extends jj<IPPSPlacementView> {
    private Context d;
    private com.huawei.openalliance.ad.inter.data.g e;

    public void b() {
        this.b.b();
    }

    public void a(boolean z) {
        if (this.b == null) {
            return;
        }
        this.b.b(z);
    }

    public void a(com.huawei.openalliance.ad.inter.data.g gVar) {
        this.e = gVar;
        this.f7126a = pi.a(gVar);
        Context context = this.d;
        this.b = new ou(context, new sg(context), this.f7126a);
    }

    public void a(MaterialClickInfo materialClickInfo) {
        com.huawei.openalliance.ad.inter.data.g gVar = this.e;
        if (gVar == null) {
            return;
        }
        gVar.e(true);
        ho.a("PlacementAdPresenter", "begin to deal click");
        HashMap hashMap = new HashMap();
        hashMap.put("appId", this.e.getAppMarketAppId());
        hashMap.put("thirdId", this.e.getPromotionChannel());
        ta a2 = sz.a(this.d, this.f7126a, hashMap);
        if (a2.a()) {
            a(a2, materialClickInfo);
        }
    }

    public void a(long j, int i, Integer num) {
        String f = f();
        com.huawei.openalliance.ad.inter.data.g gVar = this.e;
        if (gVar != null) {
            ho.a("PlacementAdPresenter", "slotId: %s, contentId: %s, slot pos: %s", gVar.F(), this.e.getContentId(), f);
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(j)).a(Integer.valueOf(i)).b(num).d(f).e(g()).a(com.huawei.openalliance.ad.utils.b.a(d()));
        this.b.a(c0207a.a());
    }

    public void a() {
        this.b.b(0, 0);
    }

    private void a(ta taVar, MaterialClickInfo materialClickInfo) {
        b.a aVar = new b.a();
        aVar.b(taVar.c()).a((Integer) 7).a(materialClickInfo).d(com.huawei.openalliance.ad.utils.b.a(d()));
        this.b.a(aVar.a());
        if (this.c != null) {
            this.c.a(aVar.a());
        }
    }

    public nm(Context context, IPPSPlacementView iPPSPlacementView) {
        this.d = context;
        a((nm) iPPSPlacementView);
        this.b = new ou(context, new sg(context));
    }
}
