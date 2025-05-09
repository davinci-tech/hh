package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.ParamFromServer;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class oy {

    /* renamed from: a, reason: collision with root package name */
    private Context f7425a;
    private a b;
    private AdContentRsp c;
    private dt d = dt.h();
    private fx e;

    public interface a {
        void a(int i);

        void a(Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map);
    }

    public void a(AdContentRsp adContentRsp) {
        a aVar;
        ho.b("InterstitialAdProcessor", "parser");
        if (adContentRsp == null) {
            a aVar2 = this.b;
            if (aVar2 != null) {
                aVar2.a(ErrorCode.ERROR_CODE_OTHER);
            }
            ho.c("InterstitialAdProcessor", "response is null");
            return;
        }
        this.c = adContentRsp;
        List<Ad30> c = adContentRsp.c();
        HashMap hashMap = new HashMap(4);
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(this.f7425a);
        if (com.huawei.openalliance.ad.utils.bg.a(c)) {
            ho.b("InterstitialAdProcessor", "response ads is isEmpty");
            aVar = this.b;
            if (aVar == null) {
                return;
            }
        } else {
            for (Ad30 ad30 : c) {
                String a2 = ad30.a();
                int b2 = ad30.b();
                if (200 != b2) {
                    ho.b("InterstitialAdProcessor", "ad failed, retcode30: %s, slotId: %s.", Integer.valueOf(b2), a2);
                }
                List<com.huawei.openalliance.ad.inter.data.b> a3 = a(ad30, b);
                if (!com.huawei.openalliance.ad.utils.bg.a(a3)) {
                    hashMap.put(a2, a3);
                }
            }
            ho.b("InterstitialAdProcessor", "interstitialAdMap empty: %s", Boolean.valueOf(com.huawei.openalliance.ad.utils.bl.a(hashMap)));
            if (this.b == null) {
                return;
            }
            if (!com.huawei.openalliance.ad.utils.bl.a(hashMap)) {
                this.b.a(hashMap);
                return;
            }
            aVar = this.b;
        }
        aVar.a(204);
    }

    private boolean a(Content content) {
        String m;
        ParamFromServer paramFromServer;
        return (content == null || TextUtils.isEmpty(content.g()) || content.k() <= 0 || content.c() == null || (m = content.m()) == null || (paramFromServer = (ParamFromServer) com.huawei.openalliance.ad.utils.be.b(m, ParamFromServer.class, new Class[0])) == null || (TextUtils.isEmpty(paramFromServer.a()) && TextUtils.isEmpty(paramFromServer.b()))) ? false : true;
    }

    private void a(ContentRecord contentRecord, ImageInfo imageInfo, com.huawei.openalliance.ad.inter.data.d dVar) {
        rt rtVar = new rt();
        rtVar.a(contentRecord);
        rtVar.c(imageInfo.c());
        rtVar.a(52428800L);
        rtVar.b(imageInfo.a());
        rtVar.b(imageInfo.g() == 0);
        rtVar.a(Constants.AD_MATERIAL_SUB_DIR);
        rtVar.c(true);
        rtVar.a(Long.valueOf(System.currentTimeMillis()));
        ru a2 = this.e.a(rtVar);
        if (a2 == null || com.huawei.openalliance.ad.utils.cz.b(a2.a())) {
            ho.c("InterstitialAdProcessor", "download image failed");
        } else {
            dVar.M(a2.a());
            a(imageInfo, a2.a());
        }
    }

    private void a(ContentRecord contentRecord) {
        if (contentRecord == null || contentRecord.h() == null || contentRecord.h().K() == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.e.a(this.f7425a, contentRecord.h().K());
    }

    private void a(final VideoInfo videoInfo, final String str, final String str2, final Integer num) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.oy.1
            @Override // java.lang.Runnable
            public void run() {
                ho.a("InterstitialAdProcessor", "download interstitialad video:%s", com.huawei.openalliance.ad.utils.dl.a(videoInfo.a()));
                dr drVar = new dr(videoInfo.a(), videoInfo.c(), videoInfo.i() == 0, videoInfo.g(), null, videoInfo.l() == 1, 1, str, str2, 12, false, oy.this.c.n());
                drVar.a(num);
                oy.this.d.a(drVar);
            }
        });
    }

    private void a(ImageInfo imageInfo, String str) {
        if (imageInfo.d() <= 0 || imageInfo.e() <= 0) {
            Rect a2 = com.huawei.openalliance.ad.utils.az.a(str);
            int width = a2.width();
            int height = a2.height();
            if (width <= 0 || height <= 0) {
                return;
            }
            imageInfo.a(width);
            imageInfo.b(height);
        }
    }

    private List<com.huawei.openalliance.ad.inter.data.b> a(Ad30 ad30, byte[] bArr) {
        ImageInfo imageInfo;
        ArrayList arrayList = null;
        if (ad30 != null && !TextUtils.isEmpty(ad30.a())) {
            String a2 = ad30.a();
            String g = ad30.g();
            List<Content> c = ad30.c();
            if (com.huawei.openalliance.ad.utils.bg.a(c)) {
                ho.c("InterstitialAdProcessor", "content is null" + a2);
                return null;
            }
            arrayList = new ArrayList(4);
            for (Content content : c) {
                if (content != null) {
                    AdContentRsp adContentRsp = this.c;
                    if (adContentRsp != null) {
                        content.a(adContentRsp.h(), 12);
                    }
                    MetaData c2 = content.c();
                    if (c2 == null || !a(content)) {
                        ho.d("InterstitialAdProcessor", "content is invalid:" + content.g());
                    } else {
                        ContentRecord a3 = pf.a(a2, content, 12, g);
                        a3.a(bArr);
                        a3.y(this.c.k());
                        a3.o(this.c.n());
                        a3.T(this.c.u());
                        com.huawei.openalliance.ad.inter.data.d a4 = ox.a(a2, content, bArr);
                        a4.i(this.c.k());
                        a4.c(this.c.n());
                        a4.n(this.c.p());
                        a4.o(this.c.q());
                        a4.u(this.c.u());
                        BiddingInfo.a lurl = new BiddingInfo.a().a(content.ag()).a(content.ah()).b(content.ai()).setLurl(content.aj());
                        if (!lurl.a().a()) {
                            a4.a(lurl.a());
                        }
                        if (c2.d() != null) {
                            a(c2.d(), content.g(), a2, content.I());
                        }
                        List<ImageInfo> o = c2.o();
                        if (o != null && o.size() > 0 && (imageInfo = o.get(0)) != null) {
                            a(a3, imageInfo, a4);
                        }
                        a(a3);
                        arrayList.add(a4);
                    }
                }
            }
        }
        return arrayList;
    }

    public oy(Context context, a aVar) {
        this.f7425a = context;
        this.b = aVar;
        this.e = fb.a(context);
    }
}
