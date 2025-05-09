package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.magazine.inter.MagLockAd;
import com.huawei.openalliance.ad.magazine.inter.MagLockAdContent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class pb {
    private static void a(ContentRecord contentRecord, Content content) {
        contentRecord.c(content.j());
        contentRecord.b(content.d());
        contentRecord.l(content.m());
        contentRecord.i(content.l());
    }

    private static List<MagLockAdContent> a(List<Content> list, boolean z, Context context) {
        ArrayList arrayList = new ArrayList();
        Iterator<Content> it = list.iterator();
        while (it.hasNext()) {
            MagLockAdContent a2 = a(it.next(), z, context);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private static MagLockAdContent a(Content content, boolean z, Context context) {
        if (content == null) {
            return null;
        }
        ho.c("MagLockConverter", "content in response :" + content.g());
        ContentRecord a2 = es.a(context).a(content.g());
        if (a2 != null && !TextUtils.isEmpty(a2.y())) {
            ho.a("MagLockConverter", "content is in db: %s,and path: %s.", content.g(), a2.y());
            if (!z) {
                a(a2, content);
            }
            return a(context, a2);
        }
        if (a2 == null) {
            ho.c("MagLockConverter", "content is null!" + content.g());
        }
        return null;
    }

    private static MagLockAd a(Ad30 ad30, boolean z, Context context) {
        if (ad30 == null) {
            return null;
        }
        hx hxVar = new hx();
        hxVar.setRetCode(ad30.b());
        hxVar.setSlotId(ad30.a());
        List<Content> c = ad30.c();
        if (200 == ad30.b() && !com.huawei.openalliance.ad.utils.bg.a(c)) {
            hxVar.setAdList(a(c, z, context));
        }
        return hxVar;
    }

    public static hy a(AdContentRsp adContentRsp, Context context, boolean z) {
        if (adContentRsp == null || com.huawei.openalliance.ad.utils.bg.a(adContentRsp.c())) {
            return null;
        }
        hy hyVar = new hy();
        hyVar.setRetCode(adContentRsp.a());
        ArrayList arrayList = new ArrayList();
        Iterator<Ad30> it = adContentRsp.c().iterator();
        while (it.hasNext()) {
            MagLockAd a2 = a(it.next(), z, context);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        hyVar.setMultiAds(arrayList);
        return hyVar;
    }

    private static hw a(Context context, ContentRecord contentRecord) {
        hw hwVar = new hw();
        hwVar.setContentId(contentRecord.m());
        hwVar.b(contentRecord.B());
        String g = contentRecord.g();
        MetaData metaData = (MetaData) com.huawei.openalliance.ad.utils.be.b(g, MetaData.class, new Class[0]);
        if (metaData != null) {
            List<ImageInfo> o = metaData.o();
            if (o != null && !o.isEmpty()) {
                ImageInfo imageInfo = o.get(0);
                hwVar.setUrl(imageInfo.c());
                hwVar.setSha256(imageInfo.a());
                hwVar.setFileSize(imageInfo.f());
                hwVar.a(contentRecord.y());
                hwVar.setTitle(metaData.e());
                hwVar.setDescription(metaData.f());
            }
            ImageInfo p = metaData.p();
            if (p != null) {
                hwVar.setPreviewUrl(p.c());
                hwVar.setPreviewSha256(p.a());
            }
            hwVar.d(metaData.n());
            hwVar.c(metaData.h());
            hwVar.b(metaData.k());
            hwVar.g(metaData.t());
            if ("2".equals(metaData.t())) {
                hwVar.h(context.getResources().getString(R.string._2130850988_res_0x7f0234ac));
            }
            if (metaData.r() != null) {
                hwVar.f(metaData.r().j());
                hwVar.e(metaData.r().a());
            }
            if (metaData.s() != null && metaData.s().getName() != null) {
                hwVar.f(metaData.s().getName());
                hwVar.a(metaData.s().getType());
            }
            hwVar.setMetaData(g);
        }
        hwVar.setEndTime(contentRecord.q());
        return hwVar;
    }
}
