package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.ox;
import com.huawei.openalliance.ad.pn;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class aa {
    /* JADX INFO: Access modifiers changed from: private */
    public static List<AdContentData> f(Context context, Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
        if (bl.a(map) || context == null) {
            ho.b("EngineCacheUtil", "getTptAds, adsMap or context is empty.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (List<com.huawei.openalliance.ad.inter.data.b> list : map.values()) {
            if (!bg.a(list)) {
                for (com.huawei.openalliance.ad.inter.data.b bVar : list) {
                    if ((bVar instanceof com.huawei.openalliance.ad.inter.data.d) && bVar.d_()) {
                        arrayList.add(AdContentData.a(context, ox.a((com.huawei.openalliance.ad.inter.data.d) bVar)));
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<AdContentData> e(Context context, Map<String, List<IRewardAd>> map) {
        if (context == null || bl.a(map)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<IRewardAd>> entry : map.entrySet()) {
            if (entry != null && !bg.a(entry.getValue())) {
                for (IRewardAd iRewardAd : entry.getValue()) {
                    if (iRewardAd instanceof com.huawei.openalliance.ad.inter.data.h) {
                        com.huawei.openalliance.ad.inter.data.h hVar = (com.huawei.openalliance.ad.inter.data.h) iRewardAd;
                        if (hVar.aj() != null && hVar.aj().intValue() == 3) {
                            ho.b("EngineCacheUtil", "add v3 rewardAd: %s", hVar.getContentId());
                            arrayList.add(AdContentData.a(context, pn.a(hVar)));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b() {
        return c.a() && com.huawei.openalliance.ad.e.b() != null;
    }

    public static void b(final Context context, final Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
        if (bl.a(map) || context == null) {
            ho.b("EngineCacheUtil", "adsMap or context is empty.");
        } else {
            m.d(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aa.2
                @Override // java.lang.Runnable
                public void run() {
                    String str;
                    if (aa.b()) {
                        List f = aa.f(context, map);
                        if (!bg.a(f)) {
                            try {
                                com.huawei.openalliance.ad.e.b().a(be.b(f), (Bundle) null);
                                return;
                            } catch (RemoteException unused) {
                                ho.c("EngineCacheUtil", "triggerEngineDown RemoteException.");
                                return;
                            } catch (Throwable th) {
                                ho.c("EngineCacheUtil", "triggerEngineDown err: %s.", th.getClass().getSimpleName());
                                return;
                            }
                        }
                        str = "template ads is empty.";
                    } else {
                        str = "uiengine is not loaded or can't support download.";
                    }
                    ho.b("EngineCacheUtil", str);
                }
            });
        }
    }

    public static void a(final Context context, final Map<String, List<IRewardAd>> map) {
        if (context == null || bl.a(map)) {
            return;
        }
        m.d(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aa.1
            @Override // java.lang.Runnable
            public void run() {
                List e = aa.e(context, map);
                if (bg.a(e)) {
                    ho.b("EngineCacheUtil", "3.0 null");
                    return;
                }
                com.huawei.hms.ads.uiengine.e b = com.huawei.openalliance.ad.e.b();
                if (!c.a() || b == null) {
                    ho.c("EngineCacheUtil", "not support engine down or engineUtil is null");
                }
                ho.b("EngineCacheUtil", "trigger ui engine download.");
                try {
                    b.a(be.b(e), (Bundle) null);
                } catch (Throwable th) {
                    ho.c("EngineCacheUtil", "trigger engine download ex: %s", th.getClass().getSimpleName());
                }
            }
        });
    }
}
