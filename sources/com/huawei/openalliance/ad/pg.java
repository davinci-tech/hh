package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.Precontent;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateV3;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class pg {
    public static List<ContentRecord> b(AdContentRsp adContentRsp, int i) {
        ArrayList arrayList = new ArrayList();
        if (adContentRsp == null) {
            return arrayList;
        }
        List<Precontent> f = adContentRsp.f();
        String k = adContentRsp.k();
        if (com.huawei.openalliance.ad.utils.bg.a(f)) {
            return arrayList;
        }
        for (Precontent precontent : f) {
            precontent.a(adContentRsp.h(), i);
            ContentRecord a2 = pf.a(precontent.a(), precontent, i);
            if (a2 != null) {
                a2.y(k);
                a2.D(adContentRsp.p());
                a2.E(adContentRsp.q());
                List<TemplateV3> m = precontent.m();
                if (com.huawei.openalliance.ad.utils.bg.a(m)) {
                    arrayList.add(a2);
                } else {
                    for (TemplateV3 templateV3 : m) {
                        a2.L(templateV3.a());
                        a2.a(templateV3.b());
                        a2.P(templateV3.c());
                        a2.Y(templateV3.d());
                        a2.Z(templateV3.e());
                        arrayList.add(a2);
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<ContentRecord> a(Map<String, List<IPlacementAd>> map) {
        ContentRecord a2;
        ArrayList arrayList = new ArrayList();
        if (com.huawei.openalliance.ad.utils.bl.a(map)) {
            return arrayList;
        }
        Iterator<List<IPlacementAd>> it = map.values().iterator();
        while (it.hasNext()) {
            for (IPlacementAd iPlacementAd : it.next()) {
                if ((iPlacementAd instanceof com.huawei.openalliance.ad.inter.data.g) && (a2 = pi.a((com.huawei.openalliance.ad.inter.data.g) iPlacementAd)) != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    public static List<ContentRecord> a(AdContentRsp adContentRsp, int i) {
        List<Ad30> c = adContentRsp.c();
        ArrayList arrayList = new ArrayList();
        if (com.huawei.openalliance.ad.utils.bg.a(c)) {
            return arrayList;
        }
        for (Ad30 ad30 : c) {
            String a2 = ad30.a();
            int b = ad30.b();
            String g = ad30.g();
            if (200 != b) {
                ho.b("NetElementGetter", "ad failed, retcode30: %s, slotId: %s.", Integer.valueOf(b), a2);
            }
            ArrayList<Content> arrayList2 = new ArrayList();
            if (ad30.c() != null) {
                arrayList2.addAll(ad30.c());
            }
            if (!com.huawei.openalliance.ad.utils.bg.a(arrayList2)) {
                for (Content content : arrayList2) {
                    content.a(adContentRsp.h(), i);
                    ContentRecord a3 = pf.a(a2, content, i, g);
                    if (a3 != null) {
                        a3.o(adContentRsp.n());
                        a3.y(adContentRsp.k());
                        a3.D(adContentRsp.p());
                        a3.E(adContentRsp.q());
                        a3.T(adContentRsp.u());
                        arrayList.add(a3);
                    }
                }
            }
        }
        return arrayList;
    }
}
