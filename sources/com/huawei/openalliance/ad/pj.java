package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.ParamFromServer;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class pj extends op {
    private AdContentRsp d;
    private dk e;

    @Override // com.huawei.openalliance.ad.op
    public void b(AdContentRsp adContentRsp) {
        this.d = adContentRsp;
        a();
    }

    private boolean b(com.huawei.openalliance.ad.inter.data.g gVar) {
        PlacementMediaFile mediaFile = gVar.getMediaFile();
        if (mediaFile == null) {
            return false;
        }
        if (2 == mediaFile.getPlayMode()) {
            return true;
        }
        return !TextUtils.isEmpty(this.e.e(mediaFile.getUrl()));
    }

    private boolean a(com.huawei.openalliance.ad.inter.data.g gVar) {
        if (gVar.getMediaFile() != null) {
            return !TextUtils.isEmpty(this.e.e(r2.getUrl()));
        }
        return false;
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
        MediaFile u = c.u();
        String J = c.J();
        if (u == null && !TextUtils.isEmpty(J)) {
            ho.b("PlacementAdProcessor", "use vastInfo");
            return true;
        }
        if (u == null) {
            return false;
        }
        if (u.k() || u.j()) {
            return u.d() < (u.k() ? 209715200L : fh.b(this.b).f(u.l()) * 1024);
        }
        return false;
    }

    private void a() {
        String str;
        ho.b("PlacementAdProcessor", "parser");
        AdContentRsp adContentRsp = this.d;
        if (adContentRsp == null) {
            this.c.a(ErrorCode.ERROR_CODE_OTHER);
            str = "response is null";
        } else {
            Map<String, List<IPlacementAd>> b = b(adContentRsp.f());
            List<Ad30> c = this.d.c();
            if (!com.huawei.openalliance.ad.utils.bg.a(c)) {
                HashMap hashMap = new HashMap(4);
                byte[] b2 = com.huawei.openalliance.ad.utils.cp.b(this.b);
                for (Ad30 ad30 : c) {
                    String a2 = ad30.a();
                    int b3 = ad30.b();
                    if (200 != b3) {
                        ho.b("PlacementAdProcessor", "ad failed, retcode30: %s, slotId: %s.", Integer.valueOf(b3), a2);
                    }
                    List<IPlacementAd> a3 = a(ad30, b2, b);
                    if (!com.huawei.openalliance.ad.utils.bg.a(a3)) {
                        List list = (List) hashMap.get(a2);
                        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
                            hashMap.put(a2, a3);
                        } else {
                            list.addAll(a3);
                        }
                    }
                }
                if (this.c != null) {
                    this.c.a(hashMap, b);
                    return;
                }
                return;
            }
            this.c.a(null, b);
            str = "multi ad is null";
        }
        ho.c("PlacementAdProcessor", str);
    }

    private List<IPlacementAd> a(Ad30 ad30, byte[] bArr, Map<String, List<IPlacementAd>> map) {
        if (ad30 == null || TextUtils.isEmpty(ad30.a())) {
            return null;
        }
        List<Content> c = ad30.c();
        if (com.huawei.openalliance.ad.utils.bg.a(c)) {
            ho.c("PlacementAdProcessor", "content is null" + ad30.a());
            return null;
        }
        ArrayList<Content> arrayList = new ArrayList(c);
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList(4);
        String a2 = ad30.a();
        for (Content content : arrayList) {
            if (content != null) {
                AdContentRsp adContentRsp = this.d;
                if (adContentRsp != null) {
                    content.a(adContentRsp.h(), 60);
                }
                MetaData c2 = content.c();
                if (c2 == null || c2.x() <= 0 || !a(content)) {
                    ho.d("PlacementAdProcessor", "content is invalid:" + content.g());
                } else {
                    com.huawei.openalliance.ad.inter.data.g a3 = a(a2, content, bArr);
                    a3.i(this.d.k());
                    a3.c(this.d.n());
                    a3.n(this.d.p());
                    a3.o(this.d.q());
                    a3.u(this.d.u());
                    if (b(a3)) {
                        arrayList2.add(a3);
                    }
                    if (!a(a3) && map != null) {
                        List<IPlacementAd> list = map.get(a2);
                        if (list == null) {
                            list = new ArrayList<>();
                            map.put(a2, list);
                        }
                        PlacementMediaFile mediaFile = a3.getMediaFile();
                        if (mediaFile != null) {
                            mediaFile.a(1);
                            a3.B().add(mediaFile);
                        }
                        list.add(a3);
                    }
                }
            }
        }
        return arrayList2;
    }

    public pj(Context context, qx qxVar) {
        super(context, qxVar);
        this.e = dh.a(context, "normal");
    }
}
