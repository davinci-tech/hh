package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.ads.adsrec.AdRecommendEngine;
import com.huawei.hms.ads.consent.inter.Consent;
import com.huawei.hms.ads.data.SearchInfo;
import com.huawei.openalliance.ad.beans.base.ReqBean;
import com.huawei.openalliance.ad.beans.inner.AdRequestParam;
import com.huawei.openalliance.ad.beans.inner.LocationSwitches;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.AdEvent;
import com.huawei.openalliance.ad.beans.metadata.AdEventResult;
import com.huawei.openalliance.ad.beans.metadata.AdEventResultV2;
import com.huawei.openalliance.ad.beans.metadata.AdSlot30;
import com.huawei.openalliance.ad.beans.metadata.AdTimeStatistics;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.beans.metadata.AppCollection;
import com.huawei.openalliance.ad.beans.metadata.AppExt;
import com.huawei.openalliance.ad.beans.metadata.Device;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.metadata.Options;
import com.huawei.openalliance.ad.beans.metadata.v3.CachedContent;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.BiddingParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdContentReq;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.beans.server.AdPreReq;
import com.huawei.openalliance.ad.beans.server.AnalysisReportReq;
import com.huawei.openalliance.ad.beans.server.AppConfigReq;
import com.huawei.openalliance.ad.beans.server.AppConfigRsp;
import com.huawei.openalliance.ad.beans.server.AppDataCollectionReq;
import com.huawei.openalliance.ad.beans.server.AppDataCollectionRsp;
import com.huawei.openalliance.ad.beans.server.ConsentConfigReq;
import com.huawei.openalliance.ad.beans.server.ConsentConfigRsp;
import com.huawei.openalliance.ad.beans.server.EventReportReq;
import com.huawei.openalliance.ad.beans.server.EventReportRsp;
import com.huawei.openalliance.ad.beans.server.PermissionReq;
import com.huawei.openalliance.ad.beans.server.PermissionRsp;
import com.huawei.openalliance.ad.beans.server.ThirdReportRsp;
import com.huawei.openalliance.ad.beans.tags.TagCfgModel;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.constant.TagKey;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.SearchTerm;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.lb;
import com.huawei.openalliance.ad.net.http.Response;
import com.huawei.openalliance.ad.net.http.e;
import com.huawei.openalliance.ad.utils.ag;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class fb implements fx {

    /* renamed from: a, reason: collision with root package name */
    static final Map<Integer, Integer> f6860a;
    private static fx b;
    private static final byte[] c = new byte[0];
    private Context d;
    private gc e;
    private ga f;
    private ju g;
    private jw h;
    private int i;
    private boolean j;
    private int k;
    private final byte[] l = new byte[0];
    private final byte[] m = new byte[0];
    private final byte[] n = new byte[0];
    private final byte[] o = new byte[0];
    private jv p;
    private gh q;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i) {
        return i >= 200 && i < 300 && i != 204;
    }

    @Override // com.huawei.openalliance.ad.fx
    public ThirdReportRsp b(String str) {
        String str2;
        int i;
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            ho.d("NetHandler", "fail to report to thirdParty event, thirdParty url is empty");
            return null;
        }
        try {
            Response<String> a2 = d().a(str, this.e.bi());
            ThirdReportRsp thirdReportRsp = new ThirdReportRsp();
            int a3 = a2.a();
            thirdReportRsp.a(a3);
            if ((a3 < 200 || a3 >= 300) && a3 != 302 && a3 != 301) {
                i = 1;
                thirdReportRsp.responseCode = i;
                thirdReportRsp.errorReason = a2.d();
                return thirdReportRsp;
            }
            i = 0;
            thirdReportRsp.responseCode = i;
            thirdReportRsp.errorReason = a2.d();
            return thirdReportRsp;
        } catch (IllegalArgumentException unused) {
            str2 = "reportThirdPartyEvent IllegalArgumentException";
            ho.c("NetHandler", str2);
            return null;
        } catch (Exception unused2) {
            str2 = "reportThirdPartyEvent exception";
            ho.c("NetHandler", str2);
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.fx
    public AppDataCollectionRsp b(List<AppCollection> list) {
        String str;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return null;
        }
        AppDataCollectionReq appDataCollectionReq = new AppDataCollectionReq();
        appDataCollectionReq.a(list);
        try {
            Response<AppDataCollectionRsp> a2 = g().a(true, appDataCollectionReq, b(appDataCollectionReq));
            if (a2 != null) {
                return a2.b();
            }
        } catch (IllegalArgumentException unused) {
            str = "reportAppDataCollection IllegalArgumentException";
            ho.c("NetHandler", str);
            return null;
        } catch (Exception unused2) {
            str = "reportAppDataCollection Exception";
            ho.c("NetHandler", str);
            return null;
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.fx
    public void a(AdPreReq adPreReq) {
        try {
            ho.b("NetHandler", "preRequestAdContent, result:" + c().a(adPreReq).a());
        } catch (Throwable th) {
            ho.c("NetHandler", "requestAdContent Exception: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.fx
    public Map<Integer, AdContentRsp> a(String str, long j, List<String> list) {
        HashMap hashMap = new HashMap(4);
        Response c2 = c(str);
        c2.b(1);
        AdContentRsp adContentRsp = (AdContentRsp) c2.b();
        long c3 = com.huawei.openalliance.ad.utils.ao.c() - j;
        if (adContentRsp == null) {
            a(-1, "", -1, c2.a(), c2.d(), c3, false, c2, null);
            return hashMap;
        }
        adContentRsp.responseCode = c2.a() == 200 ? 0 : 1;
        String k = adContentRsp.k();
        this.e.i(adContentRsp.g());
        Map<Integer, AdContentRsp> a2 = a(hashMap, adContentRsp, list, k, c2, c3);
        for (Map.Entry<Integer, AdContentRsp> entry : a2.entrySet()) {
            Integer key = entry.getKey();
            if (key != null) {
                AdContentRsp d = d(key.intValue(), list, entry.getValue());
                if (d != null) {
                    a2.put(key, d);
                }
            }
        }
        return a2;
    }

    @Override // com.huawei.openalliance.ad.fx
    public ru a(rt rtVar) {
        return new rr(this.d, rtVar).a();
    }

    @Override // com.huawei.openalliance.ad.fx
    public Response<String> a(AdContentReq adContentReq) {
        return c().b(this.e.bJ(), adContentReq, a((ReqBean) adContentReq));
    }

    @Override // com.huawei.openalliance.ad.fx
    public Response<AdContentRsp> a(AdRequestParam adRequestParam) {
        int a2 = adRequestParam.a();
        AdSlotParam b2 = adRequestParam.b();
        List<String> c2 = adRequestParam.c();
        List<String> d = adRequestParam.d();
        List<String> e = adRequestParam.e();
        AdTimeStatistics i = adRequestParam.i();
        String g = adRequestParam.g();
        long h = adRequestParam.h();
        i.d(h);
        AdContentReq b3 = b(a2, b2, c2, d, e);
        i.c(System.currentTimeMillis());
        b3.b(g);
        b3.b(adRequestParam.f());
        com.huawei.openalliance.ad.utils.dp.a(b2);
        ho.b("NetHandler", "do ad req");
        return a(b3, b2, a2, g, h, i);
    }

    @Override // com.huawei.openalliance.ad.fx
    public PermissionRsp a(String str, String str2, String str3, int i, int i2) {
        String str4;
        try {
            PermissionReq permissionReq = new PermissionReq(str, str2, str3, i, i2);
            Response<PermissionRsp> a2 = c().a(permissionReq, a(permissionReq));
            if (a2 != null) {
                return a2.b();
            }
            return null;
        } catch (IllegalArgumentException unused) {
            str4 = "requestPermission IllegalArgumentException";
            ho.c("NetHandler", str4);
            return null;
        } catch (Exception unused2) {
            str4 = "requestPermission Exception";
            ho.c("NetHandler", str4);
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.fx
    public EventReportRsp a(List<AdEvent> list) {
        EventReportRsp eventReportRsp;
        Exception e;
        IllegalArgumentException e2;
        EventReportRsp eventReportRsp2;
        ho.b("NetHandler", "upload events!");
        EventReportRsp eventReportRsp3 = null;
        if (list == null || list.isEmpty()) {
            ho.c("NetHandler", "fail to upload cache events, events is empty");
            return null;
        }
        List<AdEvent> g = g(list);
        EventReportRsp h = !g.isEmpty() ? h(g) : null;
        if (list.isEmpty()) {
            return h;
        }
        EventReportReq eventReportReq = new EventReportReq(list);
        try {
            Response<EventReportRsp> a2 = c().a(eventReportReq, a(eventReportReq));
            if (a2 == null) {
                return null;
            }
            eventReportRsp = a2.b();
            try {
                String d = a2.d();
                if (d == null) {
                    d = String.valueOf(a2.a());
                }
                if (eventReportRsp != null) {
                    eventReportRsp.responseCode = a2.a() == 200 ? 0 : 1;
                    eventReportRsp.errorReason = d;
                    eventReportRsp3 = eventReportRsp;
                } else {
                    EventReportRsp eventReportRsp4 = new EventReportRsp();
                    try {
                        eventReportRsp4.responseCode = 1;
                        eventReportRsp4.errorReason = d;
                        eventReportRsp3 = eventReportRsp4;
                    } catch (IllegalArgumentException e3) {
                        e2 = e3;
                        eventReportRsp = eventReportRsp4;
                        ho.c("NetHandler", "uploadEvents IllegalArgumentException");
                        if (eventReportRsp == null) {
                            eventReportRsp2 = new EventReportRsp();
                            eventReportRsp2.responseCode = 1;
                            eventReportRsp2.errorReason = e2.getClass().getSimpleName();
                            EventReportRsp eventReportRsp5 = eventReportRsp2;
                            a(h, eventReportRsp5);
                            return eventReportRsp5;
                        }
                        return eventReportRsp;
                    } catch (Exception e4) {
                        e = e4;
                        eventReportRsp = eventReportRsp4;
                        ho.c("NetHandler", "uploadEvents Exception");
                        if (eventReportRsp == null) {
                            eventReportRsp2 = new EventReportRsp();
                            eventReportRsp2.errorReason = e.getClass().getSimpleName();
                            eventReportRsp2.responseCode = 1;
                            EventReportRsp eventReportRsp52 = eventReportRsp2;
                            a(h, eventReportRsp52);
                            return eventReportRsp52;
                        }
                        return eventReportRsp;
                    }
                }
                a(h, eventReportRsp3);
                return eventReportRsp3;
            } catch (IllegalArgumentException e5) {
                e2 = e5;
            } catch (Exception e6) {
                e = e6;
            }
        } catch (IllegalArgumentException e7) {
            eventReportRsp = eventReportRsp3;
            e2 = e7;
        } catch (Exception e8) {
            eventReportRsp = eventReportRsp3;
            e = e8;
        }
    }

    @Override // com.huawei.openalliance.ad.fx
    public ConsentConfigRsp a(String str, ConsentConfigReq consentConfigReq) {
        String str2;
        try {
            if (!"3.4.74.310".equals(consentConfigReq.d())) {
                ho.b("NetHandler", "consent sdk version not match, reset version.");
                consentConfigReq.a("3.4.74.310");
            }
            if (!TextUtils.isEmpty(consentConfigReq.d()) && consentConfigReq.d().startsWith("13.4.")) {
                ho.b("NetHandler", "replace inner consent sdk version.");
                consentConfigReq.a(consentConfigReq.d().replace("13.4.", "3.4."));
            }
            Response<ConsentConfigRsp> a2 = c().a(consentConfigReq, a(consentConfigReq));
            if (a2 == null) {
                return null;
            }
            ConsentConfigRsp b2 = a2.b();
            if (b2 != null) {
                b2.responseCode = a2.a() == 200 ? 0 : 1;
            }
            return b2;
        } catch (IllegalArgumentException unused) {
            str2 = "requestConsentConfig IllegalArgumentException";
            ho.c("NetHandler", str2);
            return null;
        } catch (Exception unused2) {
            str2 = "requestConsentConfig Exception";
            ho.c("NetHandler", str2);
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.fx
    public AppConfigRsp a(String str) {
        String str2;
        try {
            AppConfigReq e = e(str);
            Response<AppConfigRsp> a2 = c().a(this.e.bK(), e, a((ReqBean) e));
            if (a2 == null) {
                return null;
            }
            AppConfigRsp b2 = a2.b();
            if (b2 != null) {
                d(b2.M());
                this.e.h(b2.l());
                b2.responseCode = a2.a() == 200 ? 0 : 1;
                fl.a(this.d).a(b2.ad());
                fl.a(this.d).b(b2.af());
            }
            return b2;
        } catch (IllegalArgumentException unused) {
            str2 = "requestAppConfig IllegalArgumentException";
            ho.c("NetHandler", str2);
            return null;
        } catch (Exception unused2) {
            str2 = "requestAppConfig Exception";
            ho.c("NetHandler", str2);
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.fx
    public AdContentReq a(int i, AdSlotParam adSlotParam, List<String> list, List<String> list2, List<String> list3) {
        AdContentReq b2 = b(i, adSlotParam, list, list2, list3);
        if (b2.k().isEmpty()) {
            b2.a((List<AdSlot30>) null);
        }
        return b2;
    }

    private EventReportRsp h(List<AdEvent> list) {
        String str;
        if (list == null || list.isEmpty()) {
            ho.c("NetHandler", "fail to upload cache events, events is empty");
            return null;
        }
        AnalysisReportReq analysisReportReq = new AnalysisReportReq(list);
        try {
            Map<String, String> a2 = a(analysisReportReq);
            jy.a(a2);
            Response<EventReportRsp> a3 = c().a(analysisReportReq, a2);
            if (a3 != null) {
                EventReportRsp b2 = a3.b();
                if (b2 != null) {
                    b2.responseCode = a3.a() == 200 ? 0 : 1;
                }
                return b2;
            }
        } catch (IllegalArgumentException unused) {
            str = "uploadEvents IllegalArgumentException";
            ho.c("NetHandler", str);
            return null;
        } catch (Exception unused2) {
            str = "uploadEvents Exception";
            ho.c("NetHandler", str);
            return null;
        }
        return null;
    }

    private void g(AdContentReq adContentReq) {
        List<TagCfgModel> ck = this.e.ck();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (TagCfgModel tagCfgModel : ck) {
            int parseInt = Integer.parseInt(tagCfgModel.d());
            if (2 != parseInt) {
                if (1 != parseInt) {
                    String p = adContentReq.i().p();
                    if (!com.huawei.openalliance.ad.utils.cz.b(p) && !Constants.NIL_UUID.equals(p)) {
                    }
                }
                a(adContentReq, tagCfgModel, arrayList, arrayList2);
            }
        }
        b(arrayList, arrayList2);
    }

    private void g(Device device) {
        fw a2 = ey.a(this.d);
        if (a2.d(Constants.INS_APPS_RETURN_CODE) && 200 != a2.c()) {
            f(device);
            return;
        }
        List<String> w = com.huawei.openalliance.ad.utils.d.w(this.d);
        device.a(w);
        if (com.huawei.openalliance.ad.utils.bg.a(w)) {
            return;
        }
        device.c(Integer.valueOf(com.huawei.openalliance.ad.utils.d.x(this.d)));
    }

    private List<AdEvent> g(List<AdEvent> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<AdEvent> it = list.iterator();
        while (it.hasNext()) {
            AdEvent next = it.next();
            if (EventType.ANALYSIS.value().equals(next.a())) {
                it.remove();
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private jv g() {
        jv jvVar;
        synchronized (this.n) {
            if (this.p == null || this.i != this.e.bg()) {
                this.i = this.e.bg();
                f();
            }
            jvVar = this.p;
        }
        return jvVar;
    }

    private void f(Device device) {
        device.a((List<String>) null);
        device.c((Integer) null);
    }

    private void f() {
        ho.b("NetHandler", "createAdServerRequester lib switch: %d", Integer.valueOf(this.i));
        this.p = (jv) new e.a(this.d).c(this.i).a(new js()).b(new jt()).a().a(jv.class);
    }

    private Map<String, String> f(final AdContentReq adContentReq) {
        if (com.huawei.openalliance.ad.utils.x.j(this.d)) {
            return null;
        }
        final com.huawei.openalliance.ad.utils.cg a2 = com.huawei.openalliance.ad.utils.cg.a(this.d);
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.fb.5
            @Override // java.lang.Runnable
            public void run() {
                Long g;
                Long h;
                HashMap hashMap = new HashMap();
                try {
                    Device i = adContentReq.i();
                    int D = fb.this.e.D();
                    hashMap.put(TagKey.HW_D001, com.huawei.openalliance.ad.utils.d.z(fb.this.d));
                    hashMap.put(TagKey.HW_D002, com.huawei.openalliance.ad.utils.d.y(fb.this.d));
                    if (i != null) {
                        g = i.H();
                        h = i.J();
                    } else {
                        g = com.huawei.openalliance.ad.utils.x.g(fb.this.d, D);
                        h = com.huawei.openalliance.ad.utils.x.h(fb.this.d, D);
                    }
                    if (g != null) {
                        hashMap.put(TagKey.HW_D003, Long.toString(g.longValue()));
                    }
                    if (h != null) {
                        hashMap.put(TagKey.HW_D004, Long.toString(h.longValue()));
                    }
                    Integer o = com.huawei.openalliance.ad.utils.x.o(fb.this.d, D);
                    if (o != null) {
                        hashMap.put(TagKey.HW_D005, Integer.toString(o.intValue()));
                    }
                    Integer p = com.huawei.openalliance.ad.utils.x.p(fb.this.d, D);
                    if (p != null) {
                        hashMap.put(TagKey.HW_D006, Integer.toString(p.intValue()));
                    }
                    hashMap.put(TagKey.HW_D007, com.huawei.openalliance.ad.utils.x.l(fb.this.d, D));
                    String T = com.huawei.openalliance.ad.utils.cg.a(fb.this.d).T();
                    if (!com.huawei.openalliance.ad.utils.cz.b(T)) {
                        hashMap.put(TagKey.HW_U001, T);
                    }
                    hashMap.put(TagKey.HW_U002, com.huawei.openalliance.ad.utils.x.f(fb.this.d) ? "1" : "0");
                    hashMap.put(TagKey.HW_M001, Integer.toString(com.huawei.openalliance.ad.utils.bi.a(fb.this.d).c()));
                } catch (Throwable th) {
                    ho.c("NetHandler", "config tag error:%s", th.getClass().getSimpleName());
                }
                a2.a(hashMap);
            }
        });
        return a2.aa();
    }

    private List<String> f(List<CachedContent> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<CachedContent> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    private boolean e() {
        return !bz.b(this.d) && Build.VERSION.SDK_INT < 28 && bz.a(this.d).a(this.d);
    }

    private void e(AdContentReq adContentReq) {
        App h;
        if (adContentReq == null || (h = adContentReq.h()) == null || !com.huawei.openalliance.ad.utils.cz.b(h.d())) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ImpEX(Constants.AUTOCONTENT_CONTENT_AUTO, Constants.LINK));
        HashMap hashMap = new HashMap();
        hashMap.put("contentBundle", arrayList);
        h.c(com.huawei.openalliance.ad.utils.be.b(hashMap));
    }

    private void e(Device device) {
        ag.a a2;
        if (com.huawei.openalliance.ad.utils.ag.b(this.d) && this.e.aL() && (a2 = com.huawei.openalliance.ad.utils.ag.a(this.d)) != null) {
            device.e(a2.a());
            device.f(a2.b() ? "0" : "1");
        }
    }

    private Map<String, List<String>> e(List<CachedContent> list) {
        HashMap hashMap = new HashMap();
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return hashMap;
        }
        for (CachedContent cachedContent : list) {
            if (cachedContent != null) {
                hashMap.put(cachedContent.a(), cachedContent.c());
            }
        }
        return hashMap;
    }

    private AppConfigReq e(String str) {
        AppConfigReq appConfigReq = new AppConfigReq(str);
        Pair<String, Boolean> b2 = com.huawei.openalliance.ad.utils.d.b(this.d, true);
        if (b2 != null) {
            appConfigReq.b((String) b2.first);
        }
        a(appConfigReq);
        appConfigReq.a(this.e.aP());
        appConfigReq.c(com.huawei.openalliance.ad.utils.d.h(this.d));
        appConfigReq.d(bz.a(this.d).o());
        appConfigReq.e(String.valueOf(30474310));
        App app = new App();
        app.b(this.d.getPackageName());
        app.f(com.huawei.openalliance.ad.utils.x.a(this.d, app.c()));
        app.a(HiAd.a(this.d).j());
        appConfigReq.a(app);
        Device device = new Device();
        device.a(this.d);
        appConfigReq.a(device);
        ff a2 = ff.a(this.d);
        appConfigReq.g(a2.a(this.d.getPackageName()));
        ho.a("NetHandler", "sha256 query from databases, sha256 is %s", a2.a(this.d.getPackageName()));
        if (!com.huawei.openalliance.ad.utils.cz.b(e.a())) {
            appConfigReq.a(fl.a(this.d).a());
            appConfigReq.h(e.a());
        }
        appConfigReq.b(fl.a(this.d).b());
        return appConfigReq;
    }

    private void d(String str) {
        if (this.f == null) {
            this.f = fe.a(this.d);
        }
        ga gaVar = this.f;
        if (gaVar != null) {
            gaVar.b(str);
        }
    }

    private void d(AdContentReq adContentReq) {
        int k = this.e.k();
        boolean i = this.e.i();
        boolean j = this.e.j();
        if (2 == k) {
            return;
        }
        if (1 == k) {
            a(adContentReq, i, j);
            return;
        }
        String p = adContentReq.i().p();
        if (com.huawei.openalliance.ad.utils.cz.b(p) || Constants.NIL_UUID.equals(p)) {
            a(adContentReq, i, j);
        }
    }

    private void d(Device device) {
        if (com.huawei.openalliance.ad.utils.cz.b(device.p())) {
            return;
        }
        device.a((String) null);
    }

    private List<String> d(List<AdSlot30> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<AdSlot30> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    private Integer d(RequestOptions requestOptions) {
        if (requestOptions != null) {
            return requestOptions.getIsQueryUseEnabled();
        }
        return null;
    }

    private jw d() {
        jw jwVar;
        synchronized (this.m) {
            if (this.h == null || this.k != this.e.bh()) {
                this.k = this.e.bh();
                a();
            }
            jwVar = this.h;
        }
        return jwVar;
    }

    private AdContentRsp d(int i, List<String> list, AdContentRsp adContentRsp) {
        AdContentRsp a2;
        if (com.huawei.openalliance.ad.utils.bg.a(list) || adContentRsp == null || !com.huawei.openalliance.ad.utils.cj.a(this.d, list, adContentRsp) || (a2 = a(i, list, adContentRsp)) == null) {
            return adContentRsp;
        }
        a2.f(1);
        return a2;
    }

    private boolean c(List<String> list) {
        if (!bz.b(this.d)) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (1 == fg.a(this.d).b(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean c(Device device) {
        return (!TextUtils.equals("1", device.q()) || TextUtils.isEmpty(device.p())) && (!TextUtils.equals("1", device.A()) || TextUtils.isEmpty(device.z()));
    }

    private Integer c(RequestOptions requestOptions) {
        Integer npaAccordingToServerConsent;
        Integer nonPersonalizedAd = requestOptions.getNonPersonalizedAd();
        if ((nonPersonalizedAd != null && !requestOptions.g()) || !com.huawei.openalliance.ad.utils.ao.e() || (npaAccordingToServerConsent = Consent.getInstance(this.d).getNpaAccordingToServerConsent()) == null) {
            return nonPersonalizedAd;
        }
        ho.b("NetHandler", "got npa according to server consent: %s", npaAccordingToServerConsent);
        return npaAccordingToServerConsent;
    }

    private Response c(String str) {
        ByteArrayInputStream byteArrayInputStream;
        BufferedInputStream bufferedInputStream;
        Response response = new Response();
        BufferedInputStream bufferedInputStream2 = null;
        try {
            lb a2 = lb.a.a(AdContentRsp.class);
            byteArrayInputStream = new ByteArrayInputStream((str == null ? "" : str).getBytes(Charset.defaultCharset()));
            try {
                bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
                int i = str == null ? -1 : 200;
                try {
                    int available = bufferedInputStream.available();
                    response.a(i);
                    response.a((Response) a2.a(i, bufferedInputStream, available, new jt()));
                    com.huawei.openalliance.ad.utils.cy.a((Closeable) byteArrayInputStream);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream2 = bufferedInputStream;
                    try {
                        ho.c("NetHandler", "fillResponseData error");
                        response.a(th);
                        com.huawei.openalliance.ad.utils.cy.a((Closeable) byteArrayInputStream);
                        bufferedInputStream = bufferedInputStream2;
                        com.huawei.openalliance.ad.utils.cy.a((Closeable) bufferedInputStream);
                        ho.b("NetHandler", "end request");
                        return response;
                    } catch (Throwable th2) {
                        com.huawei.openalliance.ad.utils.cy.a((Closeable) byteArrayInputStream);
                        com.huawei.openalliance.ad.utils.cy.a((Closeable) bufferedInputStream2);
                        ho.b("NetHandler", "end request");
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayInputStream = null;
        }
        com.huawei.openalliance.ad.utils.cy.a((Closeable) bufferedInputStream);
        ho.b("NetHandler", "end request");
        return response;
    }

    private ju c() {
        ju juVar;
        synchronized (this.l) {
            boolean a2 = jx.a(this.d).a();
            if (this.g == null || a2 != this.j || this.i != this.e.bg()) {
                this.i = this.e.bg();
                b();
            }
            juVar = this.g;
        }
        return juVar;
    }

    private AdContentRsp c(int i, List<String> list, final AdContentRsp adContentRsp) {
        try {
            ho.b("NetHandler", "api request ads from hms rec engine");
            ms a2 = ms.a(this.d);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("slotid", com.huawei.openalliance.ad.utils.be.b(list));
            jSONObject.put(MapKeyNames.LOCAL_RECALL_MAX_COUNT, this.e.n());
            jSONObject.put(MapKeyNames.API_RSP, com.huawei.openalliance.ad.utils.be.b(adContentRsp));
            jSONObject.put(MapKeyNames.ALLOWED_TRADE_MODES, com.huawei.openalliance.ad.utils.be.b(Arrays.asList(com.huawei.openalliance.ad.utils.cj.a(this.d, String.valueOf(i)))));
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final Response response = new Response();
            a2.a(RTCMethods.RECALL_API_AD_BY_REC_ENGINE, jSONObject.toString(), new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.fb.3
                @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                public void onRemoteCallResult(String str, CallResult<String> callResult) {
                    if (callResult != null) {
                        try {
                            if (callResult.getCode() == 200) {
                                ho.b("NetHandler", "recall api ads from hms success");
                                String data = callResult.getData();
                                if (com.huawei.openalliance.ad.utils.cz.b(data)) {
                                    return;
                                }
                                JSONObject jSONObject2 = new JSONObject(data);
                                AdContentRsp adContentRsp2 = (AdContentRsp) com.huawei.openalliance.ad.utils.be.b(jSONObject2.optString(MapKeyNames.AD_RSP_STR), AdContentRsp.class, new Class[0]);
                                Response response2 = (Response) com.huawei.openalliance.ad.utils.be.b(jSONObject2.optString(MapKeyNames.RSP_STR), Response.class, new Class[0]);
                                if (adContentRsp2 != null && response2 != null) {
                                    response.a(response2);
                                    response.a((Response) adContentRsp2);
                                    countDownLatch.countDown();
                                }
                                return;
                            }
                        } catch (Throwable th) {
                            ho.c("NetHandler", "parse ads from hms rec engine error: %s", th.getClass().getSimpleName());
                            return;
                        }
                    }
                    response.a((Response) adContentRsp);
                    countDownLatch.countDown();
                }
            }, String.class);
            try {
                ho.a("NetHandler", "latch result: %s", Boolean.valueOf(countDownLatch.await(2L, TimeUnit.SECONDS)));
            } catch (Throwable th) {
                ho.c("NetHandler", "latch " + th.getClass().getSimpleName());
            }
            return (AdContentRsp) response.b();
        } catch (Throwable th2) {
            ho.c("NetHandler", "api request ads from hms rec engine: %s", th2.getClass().getSimpleName());
            return adContentRsp;
        }
    }

    private int c(AdContentReq adContentReq) {
        if (!com.huawei.openalliance.ad.utils.ck.a("com.google.flatbuffers.FlatBufferBuilder")) {
            ho.b("NetHandler", "fb sdk not available.");
            return 0;
        }
        if (adContentReq == null || com.huawei.openalliance.ad.utils.bg.a(adContentReq.k())) {
            return 0;
        }
        boolean z = false;
        for (AdSlot30 adSlot30 : adContentReq.k()) {
            if (adSlot30 != null && !TextUtils.isEmpty(adSlot30.a())) {
                int d = fg.a(this.d).d(adSlot30.a());
                if (1 == d) {
                    return 1;
                }
                if (2 == d) {
                    z = true;
                }
            }
        }
        return z ? 2 : 0;
    }

    private void b(final List<String> list, final List<String> list2) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.fb.7
            @Override // java.lang.Runnable
            public void run() {
                if (com.huawei.openalliance.ad.utils.bg.a(list) || com.huawei.openalliance.ad.utils.bg.a(list2)) {
                    return;
                }
                new com.huawei.openalliance.ad.analysis.c(fb.this.d).a(fb.this.d.getPackageName(), list, list2);
            }
        });
    }

    private void b() {
        ho.b("NetHandler", "createAdServerRequester lib switch: %d", Integer.valueOf(this.i));
        jx a2 = jx.a(this.d);
        boolean a3 = a2.a();
        boolean b2 = a2.b();
        ho.b("NetHandler", "isNetworkKitEnable: %s, isQuicEnable: %s", Boolean.valueOf(a3), Boolean.valueOf(b2));
        int i = a3 ? 2 : this.i;
        this.j = a3;
        this.g = (ju) new e.a(this.d).c(i).a(new js()).b(new jt()).c(b2).a().a(ju.class);
    }

    private Map<String, String> b(ReqBean reqBean) {
        jy jyVar = new jy(this.d);
        jyVar.b(reqBean);
        return jyVar.a();
    }

    private Integer b(RequestOptions requestOptions) {
        Boolean f = requestOptions != null ? requestOptions.f() : null;
        int i = 0;
        int i2 = (com.huawei.openalliance.ad.utils.g.a(this.d) && bz.a(this.d).g()) ? 1 : 0;
        if (f == null) {
            return Integer.valueOf(i2);
        }
        if (f.booleanValue() && i2 != 0) {
            i = 1;
        }
        return Integer.valueOf(i);
    }

    private Response<AdContentRsp> b(AdContentReq adContentReq, List<String> list, int i, Integer num) {
        try {
            ho.b("NetHandler", "request ads from sdk rec engine");
            return el.a(this.d, adContentReq, list, i, num);
        } catch (Throwable th) {
            ho.c("NetHandler", "recall ads from sdk error: %s", th.getClass().getSimpleName());
            ho.a(5, "NetHandler", "recall ads from sdk error", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Response<AdContentRsp> b(AdContentReq adContentReq) {
        int c2 = c(adContentReq);
        ho.b("NetHandler", "request ad content from server, type: %s", Integer.valueOf(c2));
        if (2 != c2 && 1 != c2) {
            return c().a(this.e.bJ(), adContentReq, a((ReqBean) adContentReq));
        }
        adContentReq.h(Constants.ACD_REALM_FB);
        adContentReq.g(Constants.ACD_REQ_URI_FB);
        return c().a(c2, adContentReq, a((ReqBean) adContentReq));
    }

    private static fx b(Context context) {
        fx fxVar;
        synchronized (c) {
            if (b == null) {
                b = new fb(context);
            }
            fxVar = b;
        }
        return fxVar;
    }

    private AdContentRsp b(int i, List<String> list, AdContentRsp adContentRsp) {
        ho.b("NetHandler", "api request ads from sdk rec engine");
        int n = fh.b(this.d).n();
        List<String> asList = Arrays.asList(com.huawei.openalliance.ad.utils.cj.a(this.d, String.valueOf(i)));
        defpackage.vt vtVar = new defpackage.vt(this.d.getPackageName(), adContentRsp.k(), list, null, i);
        vtVar.c(Integer.valueOf(n));
        vtVar.d(asList);
        vtVar.b(this.d.getPackageName());
        JSONObject b2 = new AdRecommendEngine(this.d).b(vtVar, com.huawei.openalliance.ad.utils.be.b(adContentRsp));
        ho.a("NetHandler", "api request recall result: %s", b2);
        AdContentRsp adContentRsp2 = (AdContentRsp) com.huawei.openalliance.ad.utils.be.a(this.d, b2, AdContentRsp.class, new Class[0]);
        return adContentRsp2 == null ? adContentRsp : adContentRsp2;
    }

    private AdContentReq b(int i, AdSlotParam adSlotParam, List<String> list, List<String> list2, List<String> list3) {
        Integer num;
        boolean z;
        Integer num2;
        Integer num3;
        Map<String, Bundle> map;
        SearchInfo searchInfo;
        String str;
        String str2;
        Integer num4;
        Integer num5;
        String str3;
        String str4;
        ho.b("NetHandler", "prep req info");
        boolean z2 = false;
        if (e() || bz.a()) {
            adSlotParam.b((Integer) 0);
        }
        RequestOptions l = adSlotParam.l();
        Options a2 = a(l);
        if (l != null) {
            if (l.getTagForUnderAgeOfPromise() != null && l.getTagForUnderAgeOfPromise().intValue() == 1) {
                z2 = true;
            }
            num = d(l);
            Integer c2 = c(l);
            Integer b2 = l.b();
            Integer c3 = l.c();
            String consent = l.getConsent();
            String appLang = l.getAppLang();
            String appCountry = l.getAppCountry();
            String searchTerm = l.getSearchTerm();
            Map<String, Bundle> extras = l.getExtras();
            SearchInfo e = l.e();
            num2 = l.getTMax();
            if (TextUtils.isEmpty(consent)) {
                consent = this.e.ar();
            }
            str3 = appLang;
            str4 = appCountry;
            str = searchTerm;
            searchInfo = e;
            num5 = b2;
            str2 = consent;
            map = extras;
            num4 = c3;
            z = z2;
            num3 = c2;
        } else {
            num = null;
            z = false;
            num2 = null;
            num3 = null;
            map = null;
            searchInfo = null;
            str = null;
            str2 = null;
            num4 = null;
            num5 = null;
            str3 = null;
            str4 = null;
        }
        ArrayList arrayList = new ArrayList(4);
        a(i, adSlotParam, arrayList, map);
        int[] b3 = jn.b();
        boolean z3 = z;
        Integer num6 = num2;
        SearchInfo searchInfo2 = searchInfo;
        String str5 = str;
        String str6 = str2;
        Integer num7 = num4;
        Integer num8 = num5;
        AdContentReq adContentReq = new AdContentReq(this.d, arrayList, list, list2, list3, adSlotParam, this.e.aL());
        adContentReq.a(b3);
        if (a2.d()) {
            adContentReq.a(a2);
        }
        if (com.huawei.openalliance.ad.utils.bd.a(adSlotParam) && !TextUtils.isEmpty(adSlotParam.B())) {
            adContentReq.f(adSlotParam.B());
        }
        Location k = adSlotParam.k();
        if (k != null && k.h()) {
            k.a(System.currentTimeMillis());
            adContentReq.a(k);
        }
        if (!TextUtils.isEmpty(str5)) {
            adContentReq.a(new SearchTerm(str5));
        }
        if (searchInfo2 != null) {
            adContentReq.a(searchInfo2);
        }
        adContentReq.b(num);
        adContentReq.a(num3);
        adContentReq.c(num8);
        adContentReq.d(num7);
        adContentReq.e(this.e.at());
        adContentReq.d(this.e.as());
        adContentReq.c(str6);
        adContentReq.h(num6);
        a(i, adSlotParam, str3, str4, adContentReq);
        a(adSlotParam, num3, z3, adContentReq);
        adContentReq.b(!this.e.aM() ? 1 : 0);
        adContentReq.a(this.e.aQ());
        adContentReq.a(adSlotParam.j() ? 2 : 1);
        if (adSlotParam.j()) {
            adContentReq.e((Integer) 1);
        }
        a(adSlotParam, adContentReq);
        adContentReq.a(f(adContentReq));
        adContentReq.f(adSlotParam.C());
        if (!com.huawei.openalliance.ad.utils.x.j(this.d)) {
            d(adContentReq);
            g(adContentReq);
        }
        if (adSlotParam.C() != null) {
            adContentReq.f(adSlotParam.C());
        }
        return adContentReq;
    }

    private int b(Device device) {
        if (!c(device)) {
            return 9;
        }
        if (this.e.af()) {
            return 1 == com.huawei.openalliance.ad.utils.d.q(this.d) ? 6 : 0;
        }
        return 7;
    }

    private boolean a(Integer num, String str, Boolean bool, boolean z) {
        return !TextUtils.isEmpty(str) && (bool == null || !bool.booleanValue()) && !((num != null && 1 == num.intValue()) || z || com.huawei.openalliance.ad.utils.d.q(this.d) == 1);
    }

    private void a(final List<String> list, boolean z) {
        if (!z || com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.fb.4
            @Override // java.lang.Runnable
            public void run() {
                dh.a(fb.this.d, "normal").a(list, true);
                dh.a(fb.this.d, "ar").a(list, true);
            }
        });
    }

    private void a(String str, AdSlotParam adSlotParam, AdSlot30 adSlot30, int i, String str2) {
        if (i != 1) {
            Map<Integer, Integer> map = f6860a;
            if (!map.containsKey(Integer.valueOf(i)) || Integer.parseInt(str2) < map.get(Integer.valueOf(i)).intValue()) {
                return;
            }
            if ((3 == i || 9 == i) && !adSlotParam.A()) {
                return;
            }
            adSlot30.c(fl.a(this.d).a(str, adSlotParam.G()));
            adSlot30.a(e.a());
            return;
        }
        adSlot30.a(e.a());
        adSlot30.c(fl.a(this.d).a(str, adSlotParam.G()));
        List<CachedContent> e = et.b(this.d).e(str);
        if (!com.huawei.openalliance.ad.utils.d.c(this.d, str)) {
            ho.a("NetHandler", "assemble kit cached templates");
            Map<String, List<CachedContent>> f = dc.f();
            if (!com.huawei.openalliance.ad.utils.bl.a(f)) {
                e = a(e, f.get(str));
            }
        }
        if (com.huawei.openalliance.ad.utils.bg.a(e)) {
            return;
        }
        List<String> f2 = f(e);
        a(f2, adSlotParam.j());
        if (com.huawei.openalliance.ad.utils.bg.a(adSlot30.u())) {
            adSlot30.d(e);
            return;
        }
        for (CachedContent cachedContent : adSlot30.u()) {
            if (!f2.contains(cachedContent.a())) {
                e.add(cachedContent);
            }
        }
        adSlot30.d(e);
    }

    private void a(String str, AdSlotParam adSlotParam, AdSlot30 adSlot30, int i) {
        if (com.huawei.openalliance.ad.utils.bd.b(i) && adSlotParam.A()) {
            adSlot30.c(fl.a(this.d).b(str, adSlotParam.G()));
        }
    }

    private void a(Integer num, Device device, String str, Boolean bool, boolean z) {
        String str2;
        int Q = fh.b(this.d).Q();
        if (Q == 0) {
            f(device);
            str2 = "INSAPPS CLOSE";
        } else if (Q == 2) {
            ho.a("NetHandler", "INSAPPS OPEN");
            g(device);
            return;
        } else {
            if (Q != 1) {
                return;
            }
            if (a(num, str, bool, z) && !"0".equals(device.A()) && !"1".equals(fh.b(this.d).bF())) {
                ho.a("NetHandler", "INSAPPS PERSONALIZED");
                f(device);
                return;
            } else {
                g(device);
                str2 = "INSAPPS NON PERSONALIZED";
            }
        }
        ho.a("NetHandler", str2);
    }

    private void a(Integer num, Device device, String str) {
        if (this.e.bV() != 1 && num != null && 1 == num.intValue()) {
            device.c((String) null);
        } else {
            device.c(str);
            d(device);
        }
    }

    private void a(EventReportRsp eventReportRsp, EventReportRsp eventReportRsp2) {
        if (eventReportRsp != null) {
            if (eventReportRsp.responseCode == 0) {
                eventReportRsp2.responseCode = 0;
            }
            List<AdEventResultV2> c2 = eventReportRsp2.c();
            if (c2 == null) {
                c2 = new ArrayList<>();
            }
            List<AdEventResult> a2 = eventReportRsp2.a();
            if (a2 == null) {
                a2 = new ArrayList<>();
            }
            List<AdEventResult> a3 = eventReportRsp.a();
            if (a3 != null) {
                a2.addAll(a3);
            }
            List<AdEventResultV2> c3 = eventReportRsp.c();
            if (c3 != null) {
                c2.addAll(c3);
            }
            eventReportRsp2.a(a2);
            eventReportRsp2.b(c2);
        }
    }

    private void a(AppConfigReq appConfigReq) {
        ag.a a2;
        if (com.huawei.openalliance.ad.utils.ag.b(this.d) && this.e.aL() && (a2 = com.huawei.openalliance.ad.utils.ag.a(this.d)) != null) {
            appConfigReq.f(a2.a());
        }
    }

    private void a(AdContentReq adContentReq, boolean z, boolean z2) {
        if (z2) {
            List<String> b2 = eo.a(this.d).b();
            if (!com.huawei.openalliance.ad.utils.bg.a(b2)) {
                adContentReq.d(b2);
            }
        }
        if (z) {
            Map<String, String> E = adContentReq.E();
            Map<String, String> b3 = this.q.b();
            if (com.huawei.openalliance.ad.utils.bl.a(b3)) {
                return;
            }
            if (!com.huawei.openalliance.ad.utils.bl.a(E)) {
                b3.putAll(E);
            }
            adContentReq.a(b3);
            e(adContentReq);
        }
    }

    private void a(AdContentReq adContentReq, TagCfgModel tagCfgModel, List<String> list, List<String> list2) {
        String a2 = tagCfgModel.a();
        ho.b("NetHandler", "do add tags: %s", a2);
        long parseLong = Long.parseLong(tagCfgModel.b());
        long d = this.q.d(a2);
        if (com.huawei.openalliance.ad.utils.ao.c() - d > parseLong * 60000) {
            ho.a("NetHandler", "tag %s expire, update time: %s", a2, Long.valueOf(d));
            return;
        }
        list.add(a2);
        list2.add(String.valueOf(this.q.c(a2)));
        Map<String, String> E = adContentReq.E();
        String b2 = this.q.b(a2);
        if (com.huawei.openalliance.ad.utils.cz.b(b2)) {
            return;
        }
        if (com.huawei.openalliance.ad.utils.bl.a(E)) {
            E = new HashMap<>();
        }
        E.put(a2, b2);
        adContentReq.a(E);
    }

    private void a(AdSlotParam adSlotParam, Integer num, boolean z, AdContentReq adContentReq) {
        Device i = adContentReq.i();
        ho.b("NetHandler", "use ad slot oaid and limitedTracking");
        i.h(com.huawei.openalliance.ad.utils.d.o(this.d));
        Boolean h = adSlotParam.h();
        if (h != null) {
            i.d(h.booleanValue() ? "0" : "1");
        }
        a(num, i, adSlotParam.g());
        e(i);
        a(i);
        LocationSwitches u = adSlotParam.u();
        if (u != null) {
            i.a(Integer.valueOf(u.b()));
            i.b(Integer.valueOf(u.a()));
        }
        a(num, i, i.p(), h, z);
        a(i, adSlotParam);
    }

    private void a(AdSlotParam adSlotParam, AdContentReq adContentReq) {
        if (adSlotParam.j()) {
            return;
        }
        adContentReq.g(com.huawei.openalliance.ad.utils.cj.b(this.d) ? AiCoreSdkConstant.SUPPORT_RELATION_RANK : AiCoreSdkConstant.NOT_SUPPORT_RELATION_RANK);
        ho.a("NetHandler", "support relate rank: %s .", adContentReq.L());
    }

    private void a(AdSlotParam adSlotParam, AdSlot30 adSlot30, String str) {
        if (adSlotParam.l() == null || adSlotParam.l().getBiddingParam(str) == null) {
            return;
        }
        ho.a("NetHandler", "media request assembles biddingParam to AdSlot30");
        BiddingParam biddingParam = adSlotParam.l().getBiddingParam(str);
        if (biddingParam.getBidFloor() != null) {
            adSlot30.a(biddingParam.getBidFloor().floatValue());
        }
        if (biddingParam.getBidFloorCur() != null) {
            adSlot30.b(biddingParam.getBidFloorCur());
        }
        if (biddingParam.getBpkgName() != null && !biddingParam.getBpkgName().isEmpty()) {
            adSlot30.e(biddingParam.getBpkgName());
        }
        ho.a("NetHandler", "media request end assembles biddingParam to AdSlot30");
    }

    private void a(Device device, AdSlotParam adSlotParam) {
        String str;
        int b2 = b(device);
        if (b2 == 0) {
            Pair<String, Integer> a2 = com.huawei.openalliance.ad.utils.ai.a(this.d);
            str = (String) a2.first;
            b2 = ((Integer) a2.second).intValue();
            if (!TextUtils.isEmpty(str)) {
                device.j(str);
                device.c((String) null);
                device.e(null);
                if (!this.e.ag()) {
                    device.a((String) null);
                    device.g(null);
                    device.b((String) null);
                    device.i(null);
                }
            }
        } else {
            str = "";
        }
        device.j(str);
        adSlotParam.e(b2);
    }

    private void a(Device device) {
        String cn = this.e.cn();
        ho.a("NetHandler", "get the adid is : %s", com.huawei.openalliance.ad.utils.cz.g(cn));
        if (com.huawei.openalliance.ad.utils.cz.b(cn)) {
            return;
        }
        device.k(cn);
    }

    private void a(final int i, final String str, final int i2, final int i3, final String str2, final long j, final boolean z, final Response response, final AdTimeStatistics adTimeStatistics) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.fb.6
            @Override // java.lang.Runnable
            public void run() {
                com.huawei.openalliance.ad.analysis.g gVar = new com.huawei.openalliance.ad.analysis.g(fb.this.d);
                String b2 = com.huawei.openalliance.ad.utils.be.b(adTimeStatistics);
                if (fb.this.a(i2)) {
                    gVar.a(str, i, i2, j, z, response, b2);
                } else {
                    gVar.a(str, i, i3, str2, i2, j, z, response);
                }
            }
        });
    }

    private void a(int i, AdSlotParam adSlotParam, List<AdSlot30> list, Map<String, Bundle> map) {
        List<String> a2 = adSlotParam.a();
        Map<String, Integer> H = adSlotParam.H();
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        for (String str : a2) {
            AdSlot30 a3 = a(i, adSlotParam, map, str);
            Integer num = H.get(str);
            if (num != null && 1 == num.intValue()) {
                a3.a(1);
            }
            list.add(a3);
        }
    }

    private void a(int i, AdSlotParam adSlotParam, String str, AdSlot30 adSlot30) {
        List<String> a2 = es.a(this.d).a(i, str);
        if (!com.huawei.openalliance.ad.utils.d.c(this.d, str)) {
            ho.a("NetHandler", "assemble Kit cached 2.0 contentIds");
            List<String> e = dc.e();
            if (!com.huawei.openalliance.ad.utils.bg.a(e)) {
                if (a2 == null) {
                    a2 = new ArrayList<>();
                }
                for (String str2 : e) {
                    if (!a2.contains(str2)) {
                        a2.add(str2);
                    }
                }
            }
        }
        a(a2, adSlotParam.j());
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = a2.iterator();
        while (it.hasNext()) {
            arrayList.add(new CachedContent(it.next(), 2, null));
        }
        adSlot30.d(arrayList);
    }

    private void a() {
        ho.b("NetHandler", "createThirdRequester lib switch: %d", Integer.valueOf(this.k));
        this.h = (jw) new e.a(this.d).c(this.k).b(new jt()).a(true).b(false).a().a(jw.class);
    }

    private Map<Integer, AdContentRsp> a(Map<Integer, AdContentRsp> map, AdContentRsp adContentRsp, List<String> list, String str, Response response, long j) {
        int a2 = adContentRsp.a();
        if (200 != a2) {
            ho.b("NetHandler", "ad failed, retcode: %s, reason: %s, requestId: %s.", Integer.valueOf(a2), adContentRsp.b(), str);
        }
        List<Ad30> c2 = adContentRsp.c();
        if (c2 == null || c2.isEmpty()) {
            map.put(-1, adContentRsp);
            a(-1, str, a2, response.a(), response.d(), j, false, response, null);
            return map;
        }
        HashMap hashMap = new HashMap();
        for (Ad30 ad30 : c2) {
            if (ad30 != null) {
                int d = ad30.d();
                String a3 = ad30.a();
                int b2 = ad30.b();
                if (200 != b2) {
                    ho.b("NetHandler", "ad failed, retcode30: %s, slotId: %s.", Integer.valueOf(b2), list);
                }
                list.add(a3);
                if (!hashMap.containsKey(Integer.valueOf(d))) {
                    hashMap.put(Integer.valueOf(d), new ArrayList());
                }
                ((List) hashMap.get(Integer.valueOf(d))).add(ad30);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            List<Ad30> list2 = (List) entry.getValue();
            AdContentRsp o = adContentRsp.o();
            o.c(str);
            o.a(list2);
            o.f(1);
            map.put(Integer.valueOf(intValue), o);
        }
        return map;
    }

    private Map<String, String> a(ConsentConfigReq consentConfigReq) {
        jy jyVar = new jy(this.d);
        jyVar.a(consentConfigReq);
        return jyVar.a();
    }

    private Map<String, String> a(ReqBean reqBean) {
        jy jyVar = new jy(this.d);
        jyVar.a(this.e.aL());
        jyVar.a(reqBean);
        return jyVar.a();
    }

    private List<CachedContent> a(Map<String, List<String>> map) {
        if (com.huawei.openalliance.ad.utils.bl.a(map)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry != null) {
                arrayList.add(new CachedContent(entry.getKey(), 3, entry.getValue()));
            }
        }
        return arrayList;
    }

    private List<CachedContent> a(List<CachedContent> list, List<CachedContent> list2) {
        if (com.huawei.openalliance.ad.utils.bg.a(list2)) {
            return list;
        }
        Map<String, List<String>> e = e(list);
        Map<String, List<String>> e2 = e(list2);
        if (com.huawei.openalliance.ad.utils.bl.a(e2)) {
            return list;
        }
        for (Map.Entry<String, List<String>> entry : e2.entrySet()) {
            if (entry != null) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (e.containsKey(key)) {
                    List<String> list3 = e.get(key);
                    if (list3 == null) {
                        list3 = new ArrayList<>();
                    }
                    HashSet hashSet = new HashSet(list3);
                    hashSet.addAll(value);
                    value = new ArrayList<>(hashSet);
                }
                e.put(key, value);
            }
        }
        return a(e);
    }

    private List<ImpEX> a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : bundle.keySet()) {
                arrayList.add(new ImpEX(str, com.huawei.openalliance.ad.utils.cz.d(bundle.getString(str))));
            }
            return arrayList;
        } catch (Throwable th) {
            ho.c("NetHandler", "toImpEXs err: %s", th.getClass().getSimpleName());
            return arrayList;
        }
    }

    private Response<AdContentRsp> a(AdContentReq adContentReq, List<String> list, int i, Integer num) {
        if (c(list)) {
            el.a(list, 1);
            return a(adContentReq, i);
        }
        if (!el.a(this.d)) {
            return b(adContentReq);
        }
        el.a(list, 0);
        return b(adContentReq, list, i, num);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x017d, code lost:
    
        if (r20.o() == 2) goto L72;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0159 A[Catch: all -> 0x010f, TryCatch #8 {all -> 0x010f, blocks: (B:6:0x001c, B:9:0x0020, B:12:0x002b, B:68:0x0036, B:16:0x0057, B:18:0x005d, B:21:0x0070, B:46:0x008d, B:49:0x00a1, B:51:0x00b8, B:40:0x0114, B:42:0x011b, B:43:0x0120, B:32:0x018a, B:26:0x0152, B:28:0x0159, B:29:0x015e, B:58:0x00c1, B:60:0x00e2), top: B:5:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x011b A[Catch: all -> 0x010f, TryCatch #8 {all -> 0x010f, blocks: (B:6:0x001c, B:9:0x0020, B:12:0x002b, B:68:0x0036, B:16:0x0057, B:18:0x005d, B:21:0x0070, B:46:0x008d, B:49:0x00a1, B:51:0x00b8, B:40:0x0114, B:42:0x011b, B:43:0x0120, B:32:0x018a, B:26:0x0152, B:28:0x0159, B:29:0x015e, B:58:0x00c1, B:60:0x00e2), top: B:5:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.openalliance.ad.net.http.Response<com.huawei.openalliance.ad.beans.server.AdContentRsp> a(com.huawei.openalliance.ad.beans.server.AdContentReq r20, com.huawei.openalliance.ad.beans.parameter.AdSlotParam r21, int r22, java.lang.String r23, long r24, com.huawei.openalliance.ad.beans.metadata.AdTimeStatistics r26) {
        /*
            Method dump skipped, instructions count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.fb.a(com.huawei.openalliance.ad.beans.server.AdContentReq, com.huawei.openalliance.ad.beans.parameter.AdSlotParam, int, java.lang.String, long, com.huawei.openalliance.ad.beans.metadata.AdTimeStatistics):com.huawei.openalliance.ad.net.http.Response");
    }

    private Response<AdContentRsp> a(final AdContentReq adContentReq, int i) {
        try {
            ho.b("NetHandler", "request ads from hms rec engine");
            long c2 = com.huawei.openalliance.ad.utils.ao.c();
            ms a2 = ms.a(this.d);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MapKeyNames.REMOTE_REQ, com.huawei.openalliance.ad.utils.be.b(adContentReq));
            jSONObject.put(MapKeyNames.ALLOWED_TRADE_MODES, com.huawei.openalliance.ad.utils.be.b(Arrays.asList(com.huawei.openalliance.ad.utils.cj.a(this.d, String.valueOf(i)))));
            jSONObject.put(MapKeyNames.LOCAL_RECALL_MAX_COUNT, this.e.n());
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final Response<AdContentRsp> response = new Response<>();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            a2.a(RTCMethods.RECALL_AD_BY_REC_ENGINE, jSONObject.toString(), new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.fb.2
                @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                public void onRemoteCallResult(String str, CallResult<String> callResult) {
                    if (callResult != null) {
                        try {
                            if (callResult.getCode() == 200) {
                                ho.a("NetHandler", "recall ads from hms success");
                                String data = callResult.getData();
                                if (com.huawei.openalliance.ad.utils.cz.b(data)) {
                                    return;
                                }
                                JSONObject jSONObject2 = new JSONObject(data);
                                AdContentRsp adContentRsp = (AdContentRsp) com.huawei.openalliance.ad.utils.be.b(jSONObject2.optString(MapKeyNames.AD_RSP_STR), AdContentRsp.class, new Class[0]);
                                Response response2 = (Response) com.huawei.openalliance.ad.utils.be.b(jSONObject2.optString(MapKeyNames.RSP_STR), Response.class, new Class[0]);
                                if (adContentRsp != null && response2 != null) {
                                    response.a(response2);
                                    response.a((Response) adContentRsp);
                                    response.e(response2.r());
                                    countDownLatch.countDown();
                                }
                                return;
                            }
                        } catch (Throwable th) {
                            ho.c("NetHandler", "parse ads from hms rec engine error: %s", th.getClass().getSimpleName());
                            return;
                        }
                    }
                    com.huawei.openalliance.ad.utils.m.b(new Runnable() { // from class: com.huawei.openalliance.ad.fb.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (atomicBoolean.get()) {
                                return;
                            }
                            Response b2 = fb.this.b(adContentReq);
                            if (b2 != null) {
                                synchronized (response) {
                                    response.a(b2);
                                    response.a((Response) b2.b());
                                }
                            }
                            countDownLatch.countDown();
                        }
                    });
                }
            }, String.class);
            try {
                boolean await = countDownLatch.await(2L, TimeUnit.SECONDS);
                ho.a("NetHandler", "latch result: %s", Boolean.valueOf(await));
                if (!await) {
                    ho.a("NetHandler", "request directly from the realtime again");
                    atomicBoolean.set(true);
                    Response<AdContentRsp> b2 = b(adContentReq);
                    if (b2 != null) {
                        synchronized (response) {
                            response.a(b2);
                            response.a((Response<AdContentRsp>) b2.b());
                        }
                    }
                }
                long c3 = com.huawei.openalliance.ad.utils.ao.c() - c2;
                ho.a("NetHandler", "recall ads from hms rec engine cost: %s", Long.valueOf(c3));
                response.f(c3);
            } catch (Throwable th) {
                ho.c("NetHandler", "latch " + th.getClass().getSimpleName());
            }
            return response;
        } catch (Throwable th2) {
            ho.c("NetHandler", "request ads from hms rec engine error: %s", th2.getClass().getSimpleName());
            return null;
        }
    }

    private Response<AdContentRsp> a(int i, Integer num, AdContentReq adContentReq) {
        if (adContentReq == null) {
            return null;
        }
        List<AdSlot30> k = adContentReq.k();
        if (com.huawei.openalliance.ad.utils.bg.a(k)) {
            ho.c("NetHandler", "slots is empty");
            return null;
        }
        List<String> d = d(k);
        if (com.huawei.openalliance.ad.utils.bg.a(d)) {
            return null;
        }
        return com.huawei.openalliance.ad.utils.cj.a(this.d, adContentReq.o(), d) ? a(adContentReq, d, i, num) : b(adContentReq);
    }

    public static fx a(Context context) {
        return b(context);
    }

    private AdContentRsp a(int i, List<String> list, AdContentRsp adContentRsp) {
        return c(list) ? c(i, list, adContentRsp) : el.a(this.d) ? b(i, list, adContentRsp) : adContentRsp;
    }

    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    private Options a(RequestOptions requestOptions) {
        Options options = new Options();
        int q = com.huawei.openalliance.ad.utils.d.q(this.d);
        boolean booleanValue = com.huawei.openalliance.ad.utils.cg.a(this.d).ae().booleanValue();
        boolean booleanValue2 = com.huawei.openalliance.ad.utils.cg.a(this.d).af().booleanValue();
        ?? r4 = 1;
        r4 = 1;
        if (!booleanValue && q != 1) {
            r4 = 0;
        }
        if (ho.a()) {
            ho.a("NetHandler", "actual coppa is %s, tfua is %s", Boolean.valueOf((boolean) r4), Boolean.valueOf(booleanValue2));
        }
        options.a(Integer.valueOf((int) r4));
        options.b(Integer.valueOf(booleanValue2 ? 1 : 0));
        if (requestOptions != null) {
            if (ho.a()) {
                ho.a("NetHandler", "media coppa is %s,tfua is %s", requestOptions.getTagForChildProtection(), requestOptions.getTagForUnderAgeOfPromise());
            }
            if (r4 == 0) {
                options.a(requestOptions.getTagForChildProtection());
            }
            if (!booleanValue2) {
                options.b(requestOptions.getTagForUnderAgeOfPromise());
            }
            options.a(requestOptions.a());
        }
        return options;
    }

    private App a(int i, AdSlotParam adSlotParam, String str, String str2, AdContentReq adContentReq) {
        App i2 = adSlotParam.i();
        if (i2 != null) {
            adContentReq.a(i2);
        }
        App h = adContentReq.h();
        if (h != null) {
            if (!TextUtils.isEmpty(str)) {
                h.d(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                h.e(str2);
            }
            String a2 = adSlotParam.a(this.d, i);
            if (com.huawei.openalliance.ad.utils.cz.b(h.d())) {
                h.c(a2);
            }
            LocationSwitches u = adSlotParam.u();
            if (u != null) {
                h.b(Integer.valueOf(u.c()));
            }
            RequestOptions l = adSlotParam.l();
            AppExt appExt = new AppExt();
            appExt.a(b(l));
            h.a(appExt);
        } else {
            ho.c("NetHandler", "req.getApp__() is null and can't set lang or country");
        }
        return h;
    }

    private AdSlot30 a(int i, AdSlotParam adSlotParam, Map<String, Bundle> map, String str) {
        AdSlot30 adSlot30 = new AdSlot30(str, adSlotParam.e(), adSlotParam.f(), i, adSlotParam.c());
        int m = adSlotParam.m();
        if (m > 0) {
            adSlot30.a(Integer.valueOf(m));
        }
        int n = adSlotParam.n();
        if (n > 0) {
            adSlot30.d(Integer.valueOf(n));
        }
        List<String> v = adSlotParam.v();
        if (!com.huawei.openalliance.ad.utils.bg.a(v)) {
            adSlot30.b(v);
        }
        if (1 == i || 18 == i) {
            Integer r = adSlotParam.r();
            if (r != null) {
                adSlot30.f(r);
            }
            Integer q = adSlotParam.q();
            if (q != null) {
                adSlot30.e(q);
            }
            int b2 = adSlotParam.b();
            if (b2 == 1 || b2 == 0) {
                adSlot30.g(Integer.valueOf(b2));
            }
        }
        adSlot30.i(adSlotParam.x());
        adSlot30.j(adSlotParam.y());
        adSlot30.k(adSlotParam.z());
        adSlot30.b(adSlotParam.o());
        adSlot30.c(adSlotParam.p());
        if (!com.huawei.openalliance.ad.utils.bl.a(map)) {
            adSlot30.a(a(map.get(str)));
        }
        adSlot30.h(adSlotParam.t());
        if (i == 1) {
            a(i, adSlotParam, str, adSlot30);
        }
        if (com.huawei.openalliance.ad.utils.bd.a(adSlotParam.C()) && 7 != i) {
            a(str, adSlotParam, adSlot30, i);
        } else if (!com.huawei.openalliance.ad.utils.cz.b(e.a())) {
            a(str, adSlotParam, adSlot30, i, e.a());
        }
        a(adSlotParam, adSlot30, str);
        return adSlot30;
    }

    private fb(Context context) {
        final Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        this.e = fh.b(context);
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.fb.1
            @Override // java.lang.Runnable
            public void run() {
                com.huawei.openalliance.ad.utils.cr.a(applicationContext);
            }
        });
        this.q = fo.a(context);
    }

    static {
        HashMap hashMap = new HashMap();
        f6860a = hashMap;
        hashMap.put(3, Integer.valueOf(Constants.NATIVE_TEMPLATE_MIN_VER));
        hashMap.put(9, Integer.valueOf(Constants.ICON_TEMPLATE_MIN_VER));
        hashMap.put(7, Integer.valueOf(Constants.REWARD_TEMPLATE_MIN_VER));
        hashMap.put(12, Integer.valueOf(Constants.INTERSTITIAL_TEMPLATE_MIN_VER));
    }
}
