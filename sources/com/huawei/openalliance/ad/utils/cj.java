package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.ads.adsrec.IEventReportCallback;
import com.huawei.ads.adsrec.IUtilCallback;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;
import com.huawei.openalliance.ad.constant.SpDefaultValues;
import com.huawei.openalliance.ad.constant.TradeMode;
import com.huawei.openalliance.ad.fg;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import defpackage.uw;
import defpackage.vf;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class cj {

    /* renamed from: a, reason: collision with root package name */
    private static long f7657a = 0;
    private static boolean b = false;

    public static String[] c(Context context) {
        List<String> m = fh.b(context).m();
        return bg.a(m) ? new String[0] : (String[]) m.toArray(new String[m.size()]);
    }

    public static boolean b(final Context context) {
        if (ao.c() - f7657a > AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.cj.1
                @Override // java.lang.Runnable
                public void run() {
                    ho.b("RecommendEngineUtil", "do check ds version");
                    int a2 = cz.a(fh.b(context).b(ConfigMapKeys.SUPPORT_DS_ENGINE_VER, String.valueOf(SpDefaultValues.DEFAULT_DS_VER)), SpDefaultValues.DEFAULT_DS_VER);
                    int f = i.f(context, AiCoreSdkConstant.DECISION_PKG_NAME);
                    ho.a("RecommendEngineUtil", "check ds, local ver: %s, cfg ver: %s", Integer.valueOf(f), Integer.valueOf(a2));
                    boolean unused = cj.b = f >= a2;
                    long unused2 = cj.f7657a = ao.c();
                }
            });
        }
        return b;
    }

    public static int b(String str) {
        int a2;
        if (!cz.b(str) && (a2 = cz.a(str, 3)) > 0) {
            return a2;
        }
        return 3;
    }

    static class b implements IUtilCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Context f7660a;

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public String getConfig(String str) {
            return null;
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public List<String> getDailyIntentId(int i, int i2) {
            return null;
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public int getMediaType(String str) {
            return 0;
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public Map<String, Double> getUserCost(int i) {
            return null;
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public boolean isInHmsProcess() {
            return false;
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public byte[] getDeviceAiParamKey() {
            return new byte[0];
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public Long getCostEndTime(int i) {
            return 0L;
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public String[] getAllowCachedTradeModeList(String str, String str2) {
            return cj.a(this.f7660a, str2);
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public String[] getAllowCachedTradeModeList(String str) {
            return cj.c(this.f7660a);
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public String aes128Encrypt(String str) {
            return f.a(str, cp.b(this.f7660a));
        }

        @Override // com.huawei.ads.adsrec.IUtilCallback
        public String aes128Decrypt(String str) {
            return f.b(str, cp.b(this.f7660a));
        }

        b(Context context) {
            this.f7660a = context;
        }
    }

    public static String[] a(Context context, String str) {
        Map<String, String> a2 = be.a(fh.b(context).b(ConfigMapKeys.TRADE_MODE_IN_AD_TYPE, null));
        if (a2 == null || !a2.containsKey(str)) {
            return c(context);
        }
        String str2 = a2.get(str);
        return str2 == null ? new String[0] : str2.split(",");
    }

    public static boolean a(Context context, List<String> list, AdContentRsp adContentRsp) {
        if (adContentRsp == null) {
            return false;
        }
        if (bg.a(adContentRsp.f())) {
            return a(context, 1, list);
        }
        ho.a("RecommendEngineUtil", "preload request not use rec engine");
        return false;
    }

    private static boolean a(Context context, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (fg.a(context).a(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Context context, int i, List<String> list) {
        if (a(context, list)) {
            ho.a("RecommendEngineUtil", "rec disabled");
            return false;
        }
        if (x.j(context) || i == 2) {
            return false;
        }
        int D = fh.b(context).D();
        if (x.v(context, D)) {
            ho.b("RecommendEngineUtil", "child mode, do not req from rec");
            return false;
        }
        if (!x.u(context, D) || fh.b(context).l()) {
            return true;
        }
        ho.a("RecommendEngineUtil", "hw user disabled");
        return false;
    }

    public static void a(Context context, AdContentRsp adContentRsp) {
        String str;
        if (adContentRsp == null) {
            return;
        }
        List<Ad30> c = adContentRsp.c();
        if (bg.a(c)) {
            return;
        }
        for (Ad30 ad30 : c) {
            if (ad30 != null) {
                String a2 = ad30.a();
                if (cz.b(a2)) {
                    str = "empty slot id";
                } else {
                    String g = ad30.g();
                    if (cz.b(g)) {
                        str = "empty config map";
                    } else {
                        Map<String, String> map = (Map) be.b(g, Map.class, new Class[0]);
                        if (!bl.a(map)) {
                            fg.a(context).a(a2, map);
                        }
                    }
                }
                ho.a("RecommendEngineUtil", str);
            }
        }
    }

    public static void a(Context context) {
        uw.e(new b(context));
        uw.a(new a(context));
    }

    static class a implements IEventReportCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Context f7659a;

        @Override // com.huawei.ads.adsrec.IEventReportCallback
        public void eventProcess(vf vfVar) {
            new com.huawei.openalliance.ad.analysis.c(this.f7659a).a(vfVar);
        }

        a(Context context) {
            this.f7659a = context;
        }
    }

    public static List<String> a(String str) {
        if (cz.b(str)) {
            return TradeMode.DEFAULT_VALUE;
        }
        String[] split = str.split(",");
        if (bg.a(split)) {
            return TradeMode.DEFAULT_VALUE;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            if (TradeMode.OPTIONAL_VALUE.contains(str2)) {
                arrayList.add(str2);
            }
        }
        return bg.a(arrayList) ? TradeMode.DEFAULT_VALUE : arrayList;
    }
}
