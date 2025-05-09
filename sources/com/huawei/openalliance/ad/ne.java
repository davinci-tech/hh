package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.views.interfaces.IPPSNativeView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class ne extends jj<IPPSNativeView> {
    private static final String d = "ne";
    private Context e;
    private com.huawei.openalliance.ad.inter.data.e f;

    public void c(List<FeedbackInfo> list) {
        this.b.a(list);
    }

    public void c(String str) {
        if (this.f7126a == null) {
            return;
        }
        this.f7126a.b(str);
        this.b.a(this.f7126a);
    }

    public void b(List<FeedbackInfo> list) {
        this.b.b(list);
    }

    public void b(String str) {
        if (this.f7126a == null) {
            return;
        }
        this.f7126a.C(str);
        this.b.a(this.f7126a);
    }

    public void b() {
        this.b.p();
    }

    public boolean a(MaterialClickInfo materialClickInfo, Integer num, String str, boolean z, HashMap<String, String> hashMap) {
        com.huawei.openalliance.ad.inter.data.e eVar = this.f;
        if (eVar == null) {
            return false;
        }
        eVar.f(true);
        ho.a(d, "begin to deal click");
        Map<String, String> ah = this.f.ah();
        a(hashMap);
        ta a2 = sz.a(this.e, this.f7126a, ah);
        if (!z) {
            a(a2, materialClickInfo, num, str);
            return true;
        }
        boolean a3 = a2.a();
        if (a3) {
            a(a2, materialClickInfo, num, str);
        }
        return a3;
    }

    public void a(List<String> list) {
        this.b.a(0, 0, list);
    }

    public void a(Long l, Integer num, Integer num2, String str) {
        String f = f();
        com.huawei.openalliance.ad.inter.data.e eVar = this.f;
        if (eVar != null) {
            ho.a(d, "slotId: %s, contentId: %s, slot pos: %s", eVar.getSlotId(), this.f.getContentId(), f);
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(l).a(num).b(num2).a(com.huawei.openalliance.ad.utils.b.a(d())).d(f).c(str).e(g());
        this.b.a(c0207a.a());
    }

    public void a(com.huawei.openalliance.ad.inter.data.e eVar) {
        this.f = eVar;
        this.f7126a = pd.a(eVar);
        Context context = this.e;
        this.b = new ou(context, new sf(context, eVar == null ? 3 : eVar.e()), this.f7126a);
        if (eVar == null || eVar.aa() == null || eVar.aa().intValue() != 3) {
            return;
        }
        db.a().a(eVar.getUniqueId(), this.f7126a);
    }

    public void a(VideoInfo videoInfo) {
        com.huawei.openalliance.ad.inter.data.e eVar = this.f;
        if (eVar == null) {
            return;
        }
        eVar.a(videoInfo);
    }

    public void a(long j, int i) {
        this.b.a(j, i);
    }

    public void a() {
        this.b.b();
    }

    private void a(HashMap<String, String> hashMap) {
        MetaData h;
        if (hashMap == null || (h = this.f7126a.h()) == null) {
            return;
        }
        ApkInfo r = h.r();
        if (r != null) {
            String d2 = com.huawei.openalliance.ad.utils.cz.d(hashMap.get(Constants.BF_DOWNLOAD_TXT));
            if (!TextUtils.isEmpty(d2)) {
                r.r(d2);
            }
            String d3 = com.huawei.openalliance.ad.utils.cz.d(hashMap.get(Constants.AF_DOWNLOAD_TXT));
            if (!TextUtils.isEmpty(d3)) {
                r.s(d3);
            }
        }
        this.f7126a.b(com.huawei.openalliance.ad.utils.be.b(h));
    }

    private void a(ta taVar, MaterialClickInfo materialClickInfo, Integer num, String str) {
        b.a aVar = new b.a();
        aVar.b(taVar.c()).a(Integer.valueOf(num != null ? num.intValue() : 7)).a(materialClickInfo).d(com.huawei.openalliance.ad.utils.b.a(d())).e(str);
        this.b.a(aVar.a());
        if (this.c != null) {
            this.c.a(aVar.a());
        }
    }

    public ne(Context context, IPPSNativeView iPPSNativeView) {
        this.e = context;
        a((ne) iPPSNativeView);
        this.b = new ou(context, new sf(context, 3));
    }
}
