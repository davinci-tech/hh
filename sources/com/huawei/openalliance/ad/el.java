package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.ads.adsrec.AdRecommendEngine;
import com.huawei.ads.adsrec.recall.IAdRequestDelegate;
import com.huawei.ads.fund.util.AsyncExec;
import com.huawei.openalliance.ad.beans.inner.UserIntent;
import com.huawei.openalliance.ad.beans.metadata.AdSlot30;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.beans.metadata.PostBackEvent;
import com.huawei.openalliance.ad.beans.server.AdContentReq;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.AdRecommendEvent;
import com.huawei.openalliance.ad.net.http.Response;
import defpackage.uy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class el {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f6842a = false;
    private static final Map<String, Integer> b = new ConcurrentHashMap();

    private static String c(AdContentReq adContentReq) {
        List<UserIntent> b2 = b(adContentReq);
        if (com.huawei.openalliance.ad.utils.bg.a(b2)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b2.size(); i++) {
            UserIntent userIntent = b2.get(i);
            if (userIntent != null) {
                String b3 = userIntent.b();
                if (!com.huawei.openalliance.ad.utils.cz.b(b3) && b3.length() >= 8) {
                    sb.append(b3.substring(0, 8));
                    sb.append(":");
                    sb.append(userIntent.a());
                    sb.append(",");
                }
            }
        }
        com.huawei.openalliance.ad.utils.cz.a(sb, ',');
        return sb.toString();
    }

    private static void b(Context context) {
        synchronized (el.class) {
            if (!f6842a) {
                com.huawei.openalliance.ad.utils.s.a(context);
                a();
                f6842a = true;
                ho.b("AdRecommendEngineCaller", "common lib inited");
                com.huawei.openalliance.ad.utils.cj.a(context);
            }
        }
    }

    private static List<UserIntent> b(AdContentReq adContentReq) {
        Map<String, String> E = adContentReq.E();
        if (com.huawei.openalliance.ad.utils.bl.a(E)) {
            return null;
        }
        String str = E.get("intents");
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            return null;
        }
        List<UserIntent> list = (List) com.huawei.openalliance.ad.utils.be.b(str, List.class, UserIntent.class);
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return null;
        }
        return list;
    }

    private static defpackage.vt b(Context context, AdContentReq adContentReq, List<String> list, int i, Integer num) {
        AdSlot30 adSlot30;
        String str;
        App h = adContentReq.h();
        if (h == null) {
            str = "compose param - app is null";
        } else {
            String c = h.c();
            if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
                defpackage.vt vtVar = new defpackage.vt(c, adContentReq.r(), list, num, i);
                vtVar.d(Arrays.asList(com.huawei.openalliance.ad.utils.cj.a(context, String.valueOf(i))));
                vtVar.c(Integer.valueOf(fh.b(context).n()));
                vtVar.b(context.getPackageName());
                vtVar.d(a(adContentReq));
                vtVar.e(c(adContentReq));
                vtVar.e(adContentReq.K());
                List<AdSlot30> k = adContentReq.k();
                if (k != null && (adSlot30 = k.get(0)) != null) {
                    vtVar.b(adSlot30.m());
                }
                return vtVar;
            }
            str = "slots is empty";
        }
        ho.c("AdRecommendEngineCaller", str);
        return null;
    }

    public static boolean a(String str) {
        Integer num = b.get(str);
        return num != null && num.intValue() == 1;
    }

    public static boolean a(Context context) {
        try {
            b(context);
            return true;
        } catch (Throwable th) {
            ho.d("AdRecommendEngineCaller", "check ad rec engine error:%s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static void a(List<String> list, int i) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            b.put(it.next(), Integer.valueOf(i));
        }
    }

    public static void a(Context context, PostBackEvent postBackEvent) {
        String g = postBackEvent.g();
        if (!AdRecommendEvent.eventTypeList.contains(g)) {
            ho.a("AdRecommendEngineCaller", "no need report to rec engine");
            return;
        }
        ho.a("AdRecommendEngineCaller", "post ad affair to rec engine: %s", g);
        AdRecommendEngine adRecommendEngine = new AdRecommendEngine(context);
        uy.a aVar = new uy.a();
        String a2 = postBackEvent.a();
        if (TextUtils.isEmpty(a2)) {
            a2 = context.getPackageName();
        }
        aVar.a(a2).f(postBackEvent.b()).e(postBackEvent.c()).c(postBackEvent.d()).c(Long.valueOf(postBackEvent.e())).c(Integer.valueOf(postBackEvent.f())).d(g).a(Long.valueOf(postBackEvent.h())).b(postBackEvent.i());
        adRecommendEngine.d(aVar.b());
    }

    private static void a() {
        AsyncExec.setExecutorFactory(new AsyncExec.c());
    }

    private static JSONObject a(final Context context, final AdContentReq adContentReq, defpackage.vt vtVar, final Response response) {
        return new AdRecommendEngine(context).a(vtVar, new IAdRequestDelegate() { // from class: com.huawei.openalliance.ad.el.1
            @Override // com.huawei.ads.adsrec.recall.IAdRequestDelegate
            public String requestAd() {
                ho.a("AdRecommendEngineCaller", "do ad request");
                Response<String> a2 = fb.a(context).a(adContentReq);
                response.a((Response) a2);
                return a2.b();
            }
        });
    }

    private static String a(AdContentReq adContentReq) {
        List<UserIntent> b2 = b(adContentReq);
        if (com.huawei.openalliance.ad.utils.bg.a(b2)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b2.size(); i++) {
            UserIntent userIntent = b2.get(i);
            if (userIntent != null) {
                String b3 = userIntent.b();
                if (!com.huawei.openalliance.ad.utils.cz.b(b3) && b3.length() >= 4) {
                    sb.append(b3.substring(0, 4));
                    sb.append(",");
                }
            }
        }
        com.huawei.openalliance.ad.utils.cz.a(sb, ',');
        return sb.toString();
    }

    public static Response<AdContentRsp> a(Context context, AdContentReq adContentReq, List<String> list, int i, Integer num) {
        long c = com.huawei.openalliance.ad.utils.ao.c();
        defpackage.vt b2 = b(context, adContentReq, list, i, num);
        if (b2 == null) {
            ho.c("AdRecommendEngineCaller", "request ads param is null");
            return null;
        }
        Response<AdContentRsp> response = new Response<>();
        response.a(200);
        JSONObject a2 = a(context, adContentReq, b2, response);
        ho.a("AdRecommendEngineCaller", "recall result: %s", a2);
        if (a2 == null) {
            response.a(-1);
        }
        AdContentRsp adContentRsp = (AdContentRsp) com.huawei.openalliance.ad.utils.be.a(context, a2, AdContentRsp.class, new Class[0]);
        response.a((Response<AdContentRsp>) adContentRsp);
        if (adContentRsp != null) {
            response.b(adContentRsp.toString());
        } else {
            response.a(-1);
        }
        response.e(com.huawei.openalliance.ad.utils.ao.c() - c);
        return response;
    }
}
