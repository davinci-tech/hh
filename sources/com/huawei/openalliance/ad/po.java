package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.ParamFromServer;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class po implements qu {

    /* renamed from: a, reason: collision with root package name */
    private Context f7442a;
    private a b;
    private AdContentRsp c;
    private dt d = dt.h();

    public interface a {
        void a(int i);

        void a(Map<String, List<IRewardAd>> map);
    }

    @Override // com.huawei.openalliance.ad.qu
    public void a(AdContentRsp adContentRsp) {
        ho.b("RewardAdProcessor", "parser");
        if (adContentRsp == null) {
            this.b.a(ErrorCode.ERROR_CODE_OTHER);
            ho.c("RewardAdProcessor", "response is null");
            return;
        }
        this.c = adContentRsp;
        List<Ad30> c = adContentRsp.c();
        HashMap hashMap = new HashMap(4);
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(this.f7442a);
        if (!com.huawei.openalliance.ad.utils.bg.a(c)) {
            for (Ad30 ad30 : c) {
                String a2 = ad30.a();
                int b2 = ad30.b();
                if (200 != b2) {
                    ho.b("RewardAdProcessor", "ad failed, retcode30: %s, slotId: %s.", Integer.valueOf(b2), a2);
                }
                List<IRewardAd> a3 = a(ad30, b);
                if (!com.huawei.openalliance.ad.utils.bg.a(a3)) {
                    hashMap.put(a2, a3);
                }
            }
        }
        ho.b("RewardAdProcessor", "rewardAdMap empty: %s", Boolean.valueOf(hashMap.isEmpty()));
        if (this.b != null) {
            if (hashMap.isEmpty()) {
                ho.b("RewardAdProcessor", "onAdFailed: %s", 204);
                this.b.a(900);
            } else {
                ho.b("RewardAdProcessor", "onAdsLoaded");
                this.b.a(hashMap);
            }
        }
    }

    private boolean a(Integer num) {
        return num != null && 3 == num.intValue();
    }

    private boolean a(Content content) {
        MetaData c;
        String m;
        ParamFromServer paramFromServer;
        if (content == null || TextUtils.isEmpty(content.g()) || content.k() <= 0 || (c = content.c()) == null || (m = content.m()) == null || (paramFromServer = (ParamFromServer) com.huawei.openalliance.ad.utils.be.b(m, ParamFromServer.class, new Class[0])) == null) {
            return false;
        }
        if (TextUtils.isEmpty(paramFromServer.a()) && TextUtils.isEmpty(paramFromServer.b())) {
            return false;
        }
        if (a(content.U())) {
            ho.b("RewardAdProcessor", "v3 data");
            return true;
        }
        VideoInfo d = c.d();
        String J = c.J();
        if (d == null && !TextUtils.isEmpty(J)) {
            ho.b("RewardAdProcessor", "use vastInfo");
            ps.a(c, ps.a(c, 7, content.f()), 7, false);
            content.b(com.huawei.openalliance.ad.utils.be.b(c));
            d = c.d();
        }
        return d != null && d.b() > 0 && ((long) d.c()) < 209715200;
    }

    private void a(final VideoInfo videoInfo, final String str, final String str2, final Integer num) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.po.1
            @Override // java.lang.Runnable
            public void run() {
                ho.a("RewardAdProcessor", "download reward video:%s", com.huawei.openalliance.ad.utils.dl.a(videoInfo.a()));
                dr drVar = new dr(videoInfo.a(), videoInfo.c(), videoInfo.i() == 0, videoInfo.g(), null, 1 == videoInfo.l(), 1, str, str2, 7, false, po.this.c.n());
                drVar.a(num);
                po.this.d.a(drVar);
            }
        });
    }

    private void a(MetaData metaData) {
        if (metaData == null || metaData.K() == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.e.a(this.f7442a, metaData.K());
    }

    private List<IRewardAd> a(Ad30 ad30, byte[] bArr) {
        ArrayList arrayList = null;
        if (ad30 != null && !TextUtils.isEmpty(ad30.a())) {
            String a2 = ad30.a();
            List<Content> c = ad30.c();
            String g = ad30.g();
            if (com.huawei.openalliance.ad.utils.bg.a(c)) {
                ho.c("RewardAdProcessor", "content is null" + a2);
                return null;
            }
            arrayList = new ArrayList(4);
            for (Content content : c) {
                if (content != null) {
                    AdContentRsp adContentRsp = this.c;
                    if (adContentRsp != null) {
                        content.a(adContentRsp.h(), 7);
                    }
                    MetaData c2 = content.c();
                    if (c2 == null || !a(content)) {
                        ho.d("RewardAdProcessor", "content is invalid:%s", content.g());
                    } else {
                        com.huawei.openalliance.ad.inter.data.h a3 = pn.a(a2, content, bArr, g);
                        a3.i(this.c.k());
                        a3.c(this.c.n());
                        a3.n(this.c.p());
                        a3.o(this.c.q());
                        a3.u(this.c.u());
                        arrayList.add(a3);
                        if (!a(content.U())) {
                            a(c2.d(), content.g(), a2, content.I());
                        }
                        a(c2);
                    }
                }
            }
        }
        return arrayList;
    }

    public po(Context context, a aVar) {
        this.f7442a = context.getApplicationContext();
        this.b = aVar;
    }
}
