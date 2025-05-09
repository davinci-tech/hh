package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.utils.m;

/* loaded from: classes5.dex */
public class rs {
    public static void a(final Context context, String str, dk dkVar, final rt rtVar, final String str2) {
        if (dkVar == null || rtVar == null) {
            return;
        }
        dkVar.h(str);
        ContentRecord j = rtVar.j();
        if (j == null) {
            ho.b("SourceFetcherUtil", "updateOnCacheUri, contentRecord is null");
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.rs.1
            @Override // java.lang.Runnable
            public void run() {
                eu.a(context).a(rs.a(rtVar), str2);
            }
        }, m.a.DISK_CACHE, false);
        Integer h = com.huawei.openalliance.ad.utils.cz.h(j.an());
        if (h == null) {
            h = Integer.valueOf(dj.a(j.e()));
        }
        dkVar.a(str, h.intValue());
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        String a2 = com.huawei.openalliance.ad.utils.cu.a(str);
        return TextUtils.isEmpty(a2) ? String.valueOf(str.hashCode()) : a2;
    }

    public static ContentResource a(rt rtVar) {
        String a2;
        Integer h;
        if (ho.a()) {
            ho.a("SourceFetcherUtil", "generateContentResource: %s", rtVar.g());
        }
        try {
            ContentRecord j = rtVar.j();
            if (j == null || TextUtils.isEmpty(j.m())) {
                return null;
            }
            ContentResource contentResource = new ContentResource(j);
            if (rtVar.q()) {
                a2 = a(rtVar.g()) + com.huawei.openalliance.ad.utils.ae.e(rtVar.g());
            } else {
                a2 = a(rtVar.g());
            }
            contentResource.a(a2);
            Integer h2 = com.huawei.openalliance.ad.utils.cz.h(j.an());
            if (h2 == null) {
                h2 = Integer.valueOf(dj.a(j.e()));
            }
            contentResource.b(h2.intValue());
            contentResource.c(j.i());
            String T = j.T();
            if (rtVar.s() == null) {
                if (!com.huawei.openalliance.ad.utils.cz.b(T) && (h = com.huawei.openalliance.ad.utils.cz.h(T)) != null) {
                }
                contentResource.d(rtVar.o());
                contentResource.f(0);
                contentResource.e(rtVar.p());
                contentResource.a(System.currentTimeMillis());
                return contentResource;
            }
            h = rtVar.s();
            contentResource.d(h.intValue());
            contentResource.d(rtVar.o());
            contentResource.f(0);
            contentResource.e(rtVar.p());
            contentResource.a(System.currentTimeMillis());
            return contentResource;
        } catch (Throwable th) {
            ho.c("SourceFetcherUtil", "generateContentResource " + th.getClass().getSimpleName());
            return null;
        }
    }
}
