package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.inner.AdRequestParam;
import com.huawei.openalliance.ad.beans.inner.ApiReqParam;
import com.huawei.openalliance.ad.beans.metadata.AdTimeStatistics;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.beans.metadata.CheckResult;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdContentReq;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.AdConfigMapKey;
import com.huawei.openalliance.ad.constant.AnalyticsEventType;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.IntervalMethods;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.constant.SpKeys;
import com.huawei.openalliance.ad.constant.TagKey;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.HiAdSplash;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ks;
import com.huawei.openalliance.ad.net.http.Response;
import com.huawei.openalliance.ad.utils.m;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ol implements ql {

    /* renamed from: a, reason: collision with root package name */
    private Context f7349a;
    private fs b;
    private gd c;
    private fy d;
    private fx e;
    private gc f;
    private DelayInfo g;

    public AdContentRsp b(AdSlotParam adSlotParam) {
        d(adSlotParam);
        if (adSlotParam != null) {
            adSlotParam.a();
        }
        adSlotParam.a(a(this.f7349a.getPackageName(), adSlotParam));
        return a(12, adSlotParam, (List<String>) null, (List<String>) null, (List<String>) null);
    }

    @Override // com.huawei.openalliance.ad.ql
    public void a(AdContentRsp adContentRsp, AdSlotParam adSlotParam, qw qwVar, final qk qkVar, long j, int i) {
        pm pmVar;
        int i2;
        List<ContentRecord> list;
        String str;
        ContentRecord contentRecord;
        long j2;
        String str2;
        ContentRecord contentRecord2;
        if (adContentRsp == null) {
            ho.c("AdReqProcessor", "dealResponse adContentRsp is null");
            a().b((Integer) (-1));
            if (qkVar != null) {
                qkVar.a(null);
                return;
            }
            return;
        }
        ho.b("AdReqProcessor", "dealResponse");
        a().u().h(com.huawei.openalliance.ad.utils.ao.c());
        List<ContentRecord> b = qwVar.b(adContentRsp);
        a().u().i(com.huawei.openalliance.ad.utils.ao.c());
        int b2 = adSlotParam.b();
        List<String> a2 = adSlotParam.a();
        String str3 = (a2 == null || a2.size() <= 0) ? null : a2.get(0);
        pm pmVar2 = new pm(this.f7349a, b, false, adSlotParam.D());
        pmVar2.a(adContentRsp.d());
        pmVar2.b(adContentRsp.e());
        pmVar2.a(Integer.valueOf(b2));
        if (qkVar != null) {
            pmVar = pmVar2;
            i2 = b2;
            String str4 = str3;
            list = b;
            str = null;
            a(pmVar2, b2, adContentRsp.k(), str4, a(), adSlotParam.D());
        } else {
            pmVar = pmVar2;
            i2 = b2;
            list = b;
            str = null;
        }
        long c = com.huawei.openalliance.ad.utils.ao.c();
        ContentRecord a3 = pmVar.a(j, i2, i);
        a().e(com.huawei.openalliance.ad.utils.ao.c() - c);
        if (qkVar != null) {
            contentRecord = a3;
            a(pmVar, j, i2, adContentRsp.k(), i);
        } else {
            contentRecord = a3;
        }
        if (contentRecord != null) {
            j2 = com.huawei.openalliance.ad.utils.ao.c();
            TemplateData aT = contentRecord.aT();
            ContentRecord a4 = pmVar.a(contentRecord, i2, j2);
            ho.b("AdReqProcessor", "query content record: " + com.huawei.openalliance.ad.utils.cz.b(a4));
            if (a4 != null) {
                a4.y(adContentRsp.k());
                a4.T(adContentRsp.u());
                String m = a4.m();
                if (a4.aO() == -1 || 2 == a4.aO()) {
                    a4.a(aT);
                }
                str = m;
            }
            contentRecord2 = a4;
            str2 = str;
        } else {
            j2 = 0;
            str2 = str;
            contentRecord2 = str2;
        }
        a(j, pmVar, contentRecord2);
        a(contentRecord2);
        final pm pmVar3 = pmVar;
        a(adContentRsp, list, i2, str2, contentRecord2, j2);
        if (qkVar != null) {
            qkVar.a(contentRecord2);
        }
        pmVar3.c();
        new pm(this.f7349a, qwVar.a(adContentRsp), true, adSlotParam.D()).a(j, i2, i);
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ol.19
            @Override // java.lang.Runnable
            public void run() {
                com.huawei.openalliance.ad.utils.bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.ol.19.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            ho.a("AdReqProcessor", "deleteInvalidContents");
                            pmVar3.a();
                        } catch (Throwable th) {
                            ho.c("AdReqProcessor", "deleteInvalidContents err: %s", th.getClass().getSimpleName());
                        }
                    }
                }, ol.this.f.ah());
                pmVar3.b();
                if (qkVar == null) {
                    pmVar3.d();
                }
            }
        }, m.a.DISK_CACHE, false);
    }

    @Override // com.huawei.openalliance.ad.ql
    public void a(DelayInfo delayInfo) {
        this.g = delayInfo;
    }

    public String a(String str, int i, AdSlotParam adSlotParam, ApiReqParam apiReqParam) {
        d(adSlotParam);
        adSlotParam.a(a(this.f7349a.getPackageName(), adSlotParam));
        AdContentReq a2 = this.e.a(i, adSlotParam, (List<String>) null, (List<String>) null, (List<String>) null);
        a2.b(str);
        a(i, apiReqParam, a2);
        try {
            try {
                return ks.a.a(this.f7349a, a2.getClass()).a(a2, new js());
            } catch (Exception unused) {
                ho.c("AdReqProcessor", "getAdContentReq error");
                ek.a(this.f7349a).f();
                return "";
            }
        } finally {
            ek.a(this.f7349a).f();
        }
    }

    @Override // com.huawei.openalliance.ad.ql
    public AdContentRsp a(AdSlotParam adSlotParam, List<String> list, List<String> list2) {
        if (adSlotParam == null) {
            return null;
        }
        d(adSlotParam);
        return a(new AdRequestParam.Builder().a(2).a(adSlotParam).a(list).d(list2).a());
    }

    public AdContentRsp a(AdSlotParam adSlotParam, String str) {
        d(adSlotParam);
        List<String> a2 = this.d.a();
        App a3 = a(this.f7349a.getPackageName(), adSlotParam);
        if (a3 == null) {
            a3 = new App(this.f7349a);
        }
        a3.c(str);
        adSlotParam.a(a3);
        AdContentRsp a4 = a(60, adSlotParam, a2, (List<String>) null, (List<String>) null);
        c(a4);
        return a4;
    }

    public AdContentRsp a(AdSlotParam adSlotParam) {
        d(adSlotParam);
        adSlotParam.a(a(this.f7349a.getPackageName(), adSlotParam));
        return a(7, adSlotParam, (List<String>) null, (List<String>) null, (List<String>) null);
    }

    public AdContentRsp a(Context context, AdSlotParam adSlotParam, String str, int i, List<String> list) {
        d(adSlotParam);
        List<String> a2 = this.c.a();
        App a3 = a(context.getPackageName(), adSlotParam);
        if (a3 == null) {
            a3 = new App(context);
        }
        a3.c(str);
        adSlotParam.a(a3);
        return a(i, adSlotParam, list, (List<String>) null, a2);
    }

    @Override // com.huawei.openalliance.ad.ql
    public AdContentRsp a(Context context, AdSlotParam adSlotParam, int i) {
        App app;
        if (adSlotParam == null) {
            return null;
        }
        try {
            d(adSlotParam);
            int i2 = -1;
            if (context != null) {
                i2 = HiAdSplash.getInstance(context).getAllowMobileTraffic();
                app = a(context.getPackageName(), adSlotParam);
            } else {
                app = null;
            }
            adSlotParam.a(i2);
            adSlotParam.a(app);
            AdContentRsp a2 = a(i, adSlotParam, (List<String>) null, (List<String>) null, (List<String>) null);
            a(i, a2, adSlotParam.j());
            return a2;
        } catch (Throwable th) {
            ho.c("AdReqProcessor", "request splash ad error: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public DelayInfo a() {
        if (this.g == null) {
            this.g = new DelayInfo();
        }
        return this.g;
    }

    public Pair<String, Map<Integer, AdContentRsp>> a(String str, long j, Set<Integer> set, boolean z) {
        ho.a("AdReqProcessor", "requestAdViaApi");
        ArrayList arrayList = new ArrayList();
        Map<Integer, AdContentRsp> a2 = this.e.a(str, j, arrayList);
        long c = com.huawei.openalliance.ad.utils.ao.c();
        Response response = new Response();
        response.b(1);
        String str2 = "";
        if (a2.isEmpty()) {
            a("", -1, c - j, (AdSlotParam) null, response, (List<String>) null);
            return new Pair<>("", a2);
        }
        for (Map.Entry<Integer, AdContentRsp> entry : a2.entrySet()) {
            int intValue = entry.getKey().intValue();
            AdContentRsp value = entry.getValue();
            str2 = value.k();
            if (intValue == 60) {
                c(value);
            }
            if (!set.contains(Integer.valueOf(intValue))) {
                ho.b("AdReqProcessor", "adContentRsp is discard, adType: %s ", Integer.valueOf(intValue));
                value = null;
            }
            pt.a(this.f7349a, value, intValue);
            response.a((Response) value);
            a(intValue, value);
        }
        if (z && !com.huawei.openalliance.ad.utils.bg.a(arrayList)) {
            HiAd.a(this.f7349a).a((String) arrayList.get(0));
        }
        return new Pair<>(str2, a2);
    }

    private void j() {
        if (this.f.b(IntervalMethods.NOTIFY_KIT_AD_REQ)) {
            ho.a("AdReqProcessor", "within time interval, skip: %s", IntervalMethods.NOTIFY_KIT_AD_REQ);
        } else {
            this.f.t(IntervalMethods.NOTIFY_KIT_AD_REQ);
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.10
                @Override // java.lang.Runnable
                public void run() {
                    if (bz.b(ol.this.f7349a)) {
                        if (Constants.HW_INTELLIEGNT_PACKAGE.equalsIgnoreCase(ol.this.f7349a.getPackageName()) || !fh.b(ol.this.f7349a).bt()) {
                            ms.a(ol.this.f7349a).a(RTCMethods.REPORT_CONSENT, null, null, null);
                        } else {
                            ms.a(ol.this.f7349a).i();
                        }
                        long bs = ol.this.f.bs();
                        long c = com.huawei.openalliance.ad.utils.ao.c();
                        if (c - bs > 3600000) {
                            ol.this.f.f(c);
                            HiAd.a(ol.this.f7349a).a(ol.this.f.aL());
                        }
                    }
                }
            });
        }
    }

    private List<String> i() {
        try {
            return e.b().a((Bundle) null);
        } catch (Throwable th) {
            ho.b("AdReqProcessor", "get blkTptList err: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    private void h() {
        com.huawei.openalliance.ad.utils.cg a2 = com.huawei.openalliance.ad.utils.cg.a(this.f7349a);
        a2.b(new ArrayList());
        Map<String, String> aa = a2.aa();
        if (!com.huawei.openalliance.ad.utils.bl.a(aa)) {
            aa.put(TagKey.HW_U001, "");
        }
        a2.a(aa);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (com.huawei.openalliance.ad.utils.x.j(this.f7349a)) {
            ho.a("AdReqProcessor", "no need to query ins app on tv");
        } else if (bz.b(this.f7349a)) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.16
                @Override // java.lang.Runnable
                public void run() {
                    fw a2 = ey.a(ol.this.f7349a);
                    if (com.huawei.openalliance.ad.utils.ao.e(ol.this.f7349a)) {
                        if (ol.this.f()) {
                            ho.b("AdReqProcessor", "do query ins app");
                            Pair<Integer, String> a3 = com.huawei.openalliance.ad.utils.bz.a(ol.this.f7349a, "/insapp/query", "");
                            ol.this.f.t(SpKeys.METHOD_QUERY_INSTALL_LIST);
                            if (a3 == null) {
                                ho.b("AdReqProcessor", "query ins app failed");
                                return;
                            }
                            ho.b("AdReqProcessor", "query ins app return code: %s", a3.first);
                            ho.a("AdReqProcessor", "query ins app return result: %s", a3.second);
                            a2.a(((Integer) a3.first).intValue());
                            ol.this.a(a2, a3);
                            return;
                        }
                        return;
                    }
                    ol.this.a(a2);
                }
            });
        } else {
            ho.a("AdReqProcessor", "only query ins app on hw device");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        if (-1 == this.f.o()) {
            ho.b("AdReqProcessor", "query ins app disabled");
            return false;
        }
        if (!this.f.a(SpKeys.METHOD_QUERY_INSTALL_LIST, 180)) {
            return true;
        }
        ho.a("AdReqProcessor", "query ins app later");
        return false;
    }

    private void e() {
        ho.a("AdReqProcessor", "query install app list");
        if (f()) {
            long nextInt = new SecureRandom().nextInt(60000);
            ho.a("AdReqProcessor", "query ins app random : %s", Long.valueOf(nextInt));
            com.huawei.openalliance.ad.utils.bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.ol.15
                @Override // java.lang.Runnable
                public void run() {
                    ol.this.g();
                }
            }, nextInt);
        }
    }

    private void d(AdSlotParam adSlotParam) {
        c(adSlotParam);
        j();
        RequestOptions a2 = com.huawei.openalliance.ad.utils.cn.a(this.f7349a, adSlotParam.l());
        Location a3 = com.huawei.openalliance.ad.utils.bi.a(this.f7349a, a2, adSlotParam.k() != null ? adSlotParam.k().a() : null);
        adSlotParam.a(a3);
        adSlotParam.a(a3.f());
        adSlotParam.a(a2);
        if (com.huawei.openalliance.ad.utils.x.j(this.f7349a)) {
            return;
        }
        b();
        c();
        com.huawei.openalliance.ad.utils.de.a(this.f7349a);
    }

    private void d() {
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ol.13
            @Override // java.lang.Runnable
            public void run() {
                new df(ol.this.f7349a).a();
            }
        });
    }

    private void c(final AdContentRsp adContentRsp) {
        if (adContentRsp == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.11
            @Override // java.lang.Runnable
            public void run() {
                ol.this.f.g(adContentRsp.j());
            }
        });
    }

    private void c(AdSlotParam adSlotParam) {
        String str;
        try {
            HiAd.a(this.f7349a).a(adSlotParam.a().get(0));
        } catch (RuntimeException unused) {
            str = "refreshConfig RuntimeException";
            ho.c("AdReqProcessor", str);
        } catch (Exception unused2) {
            str = "refreshConfig Exception";
            ho.c("AdReqProcessor", str);
        }
    }

    private void c() {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.12
            @Override // java.lang.Runnable
            public void run() {
                if (fh.b(ol.this.f7349a).j()) {
                    final fq a2 = eo.a(ol.this.f7349a);
                    if (com.huawei.openalliance.ad.utils.ao.c() - a2.a() <= 43200000) {
                        return;
                    }
                    ho.a("AdReqProcessor", "asyn query aud id");
                    a2.a(com.huawei.openalliance.ad.utils.ao.c());
                    try {
                        ms.a(ol.this.f7349a).a(RTCMethods.QUERY_AUD_ID, null, new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.ol.12.1
                            @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                            public void onRemoteCallResult(String str, CallResult<String> callResult) {
                                if (callResult == null || callResult.getCode() != 200) {
                                    return;
                                }
                                ho.a("AdReqProcessor", "asyn query aud id success");
                                a2.a(callResult.getData());
                            }
                        }, String.class);
                    } catch (Throwable th) {
                        ho.c("AdReqProcessor", "asyn query aud id failed: %s", th.getClass().getSimpleName());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i, long j, AdSlotParam adSlotParam, Response response, List<String> list) {
        new com.huawei.openalliance.ad.analysis.g(this.f7349a).a(str, i, j, adSlotParam, response, com.huawei.openalliance.ad.utils.be.b(a().u()), list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AdContentRsp adContentRsp, int i) {
        List<ContentRecord> a2 = pg.a(adContentRsp, i);
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(this.f7349a);
        ArrayList arrayList = new ArrayList();
        for (ContentRecord contentRecord : a2) {
            if (contentRecord != null) {
                contentRecord.a(b);
                Context context = this.f7349a;
                ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
                ouVar.a(contentRecord);
                ouVar.n();
                if (contentRecord.aW() == 1) {
                    arrayList.add(contentRecord);
                }
            }
        }
        new com.huawei.openalliance.ad.analysis.c(this.f7349a).a(arrayList);
    }

    private void b(AdContentRsp adContentRsp) {
        DelayInfo a2 = a();
        if (adContentRsp == null) {
            a2.b(-1);
            return;
        }
        a2.c(adContentRsp.t());
        a2.b(adContentRsp.a());
        a2.c(adContentRsp.r());
    }

    private void b() {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.1
            @Override // java.lang.Runnable
            public void run() {
                if (fh.b(ol.this.f7349a).i()) {
                    final gh a2 = fo.a(ol.this.f7349a);
                    if (com.huawei.openalliance.ad.utils.ao.c() - a2.a() <= 43200000) {
                        return;
                    }
                    ho.a("AdReqProcessor", "asyn query user tag");
                    a2.a(com.huawei.openalliance.ad.utils.ao.c());
                    try {
                        ms.a(ol.this.f7349a).a(RTCMethods.QUERY_USER_TAG, null, new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.ol.1.1
                            @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                            public void onRemoteCallResult(String str, CallResult<String> callResult) {
                                if (callResult == null || callResult.getCode() != 200) {
                                    return;
                                }
                                ho.a("AdReqProcessor", "asyn query user tag success");
                                a2.a(callResult.getData());
                            }
                        }, String.class);
                    } catch (Throwable th) {
                        ho.c("AdReqProcessor", "asyn query user tag failed: %s", th.getClass().getSimpleName());
                    }
                }
            }
        });
    }

    private boolean a(ApiReqParam apiReqParam) {
        return apiReqParam != null && apiReqParam.a();
    }

    private void a(final boolean z, final List<String> list) {
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ol.5
            @Override // java.lang.Runnable
            public void run() {
                ol.this.a((List<String>) list, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<String> list, boolean z) {
        if (list == null || list.size() == 0) {
            ho.d("AdReqProcessor", "slotIds is empty");
            return;
        }
        String packageName = this.f7349a.getPackageName();
        int i = z ? 2 : 1;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            com.huawei.openalliance.ad.utils.h.a(this.f7349a, packageName, AnalyticsEventType.AD_REQ, String.valueOf(i), it.next(), "");
        }
    }

    private void a(final String str, final int i, final AdSlotParam adSlotParam) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.8
            @Override // java.lang.Runnable
            public void run() {
                com.huawei.openalliance.ad.analysis.g gVar = new com.huawei.openalliance.ad.analysis.g(ol.this.f7349a);
                List<String> a2 = adSlotParam.a();
                int size = com.huawei.openalliance.ad.utils.bg.a(a2) ? 0 : a2.size();
                Integer valueOf = adSlotParam.m() > 0 ? Integer.valueOf(adSlotParam.m()) : null;
                boolean j = adSlotParam.j();
                AdTimeStatistics adTimeStatistics = new AdTimeStatistics();
                adTimeStatistics.a(ol.this.a().u().a());
                adTimeStatistics.d(ol.this.a().u().d());
                adTimeStatistics.c(ol.this.a().u().c());
                gVar.a(str, i, size, valueOf, j, adTimeStatistics);
            }
        });
    }

    private void a(final String str, final int i, final long j, final AdSlotParam adSlotParam, final Response response, final List<String> list) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.7
            @Override // java.lang.Runnable
            public void run() {
                ol.this.b(str, i, j, adSlotParam, response, list);
            }
        });
    }

    private void a(final qt qtVar, final long j, final int i, final String str, final int i2) {
        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.ol.4
            @Override // java.lang.Runnable
            public void run() {
                ContentRecord contentRecord;
                ContentRecord g = dc.g();
                if (g != null) {
                    ContentRecord a2 = qtVar.a(g, i, j, com.huawei.openalliance.ad.utils.cp.b(ol.this.f7349a), i2);
                    if (a2 != null) {
                        contentRecord = qtVar.a(a2, i, com.huawei.openalliance.ad.utils.ao.c());
                        Object[] objArr = new Object[1];
                        objArr[0] = Boolean.valueOf(contentRecord != null);
                        ho.a("AdReqProcessor", "normal ad downloaded: %s", objArr);
                        if (contentRecord != null) {
                            contentRecord.y(str);
                        }
                    } else {
                        contentRecord = null;
                    }
                    dc.a(contentRecord);
                }
            }
        });
    }

    private void a(final qt qtVar, final int i, final String str, final String str2, final DelayInfo delayInfo, final int i2) {
        ho.b("AdReqProcessor", "recordSpareAd");
        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.ol.3
            @Override // java.lang.Runnable
            public void run() {
                String str3;
                int i3;
                ContentRecord h = dc.h();
                com.huawei.openalliance.ad.analysis.c cVar = new com.huawei.openalliance.ad.analysis.c(ol.this.f7349a);
                String str4 = null;
                if (h == null) {
                    cVar.a(new com.huawei.openalliance.ad.analysis.a(str, str2, i2), 101, (String) null, (String) null);
                    return;
                }
                String l = h.l();
                String m = h.m();
                delayInfo.e(h.m());
                ho.a("AdReqProcessor", "check spare ad exist");
                CheckResult a2 = qtVar.a(h);
                com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a(str, l, i2);
                aVar.a(m);
                if (a2 == null || !a2.a()) {
                    dc.b((ContentRecord) null);
                    if (a2 != null) {
                        str4 = a2.b();
                        str3 = a2.c();
                    } else {
                        str3 = null;
                    }
                    cVar.a(aVar, 104, str4, str3);
                    return;
                }
                h.i(a2.b());
                h.j(a2.c());
                h.q(a2.d());
                qtVar.a(h, h.m());
                ContentRecord a3 = qtVar.a(h, i, com.huawei.openalliance.ad.utils.ao.c());
                dc.b(a3);
                Object[] objArr = new Object[1];
                objArr[0] = Boolean.valueOf(a3 != null);
                ho.a("AdReqProcessor", "spare ad downloaded: %s", objArr);
                if (a3 != null) {
                    a3.y(str);
                    a3.c(true);
                    delayInfo.b(true);
                    i3 = 200;
                } else {
                    dc.b((ContentRecord) null);
                    i3 = 103;
                }
                cVar.a(aVar, i3, (String) null, (String) null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(fw fwVar, Pair<Integer, String> pair) {
        if (200 != ((Integer) pair.first).intValue()) {
            a(fwVar);
            return;
        }
        String str = (String) pair.second;
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            ho.b("AdReqProcessor", "query ins app result blank");
        } else {
            com.huawei.openalliance.ad.utils.ba.a(this.f7349a, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(fw fwVar) {
        ho.a("AdReqProcessor", "clear ins app info");
        fwVar.c(Constants.INS_APPS_ENCODED);
        fwVar.c(Constants.ENCODING_MODE);
        fwVar.c(Constants.LABEL_GEN_TIME);
        h();
    }

    private void a(ContentRecord contentRecord) {
        if (contentRecord == null || contentRecord.h() == null || contentRecord.h().K() == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.e.a(this.f7349a, contentRecord.h().K());
    }

    private void a(AdContentRsp adContentRsp, List<ContentRecord> list, int i, String str, ContentRecord contentRecord, long j) {
        ContentRecord contentRecord2;
        DelayInfo a2 = a();
        a2.b(Integer.valueOf(com.huawei.openalliance.ad.analysis.d.a(adContentRsp, contentRecord, str, i, j, list)));
        if (com.huawei.openalliance.ad.utils.bg.a(list) || (contentRecord2 = list.get(0)) == null) {
            return;
        }
        a2.a(Integer.valueOf(contentRecord2.D()));
        a2.b(Collections.singletonList(contentRecord2.m()));
    }

    private void a(final AdContentRsp adContentRsp, final int i) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ol.9
            @Override // java.lang.Runnable
            public void run() {
                ol.this.b(adContentRsp, i);
            }
        });
    }

    private void a(final AdContentRsp adContentRsp) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ol.14
            @Override // java.lang.Runnable
            public void run() {
                com.huawei.openalliance.ad.utils.cj.a(ol.this.f7349a, adContentRsp);
            }
        });
    }

    private void a(AdContentReq adContentReq) {
        ho.b("AdReqProcessor", "configBasicTemplateParam");
        adContentReq.f(e.a());
        adContentReq.c(i());
    }

    private void a(AdSlotParam adSlotParam, Response<AdContentRsp> response, AdContentRsp adContentRsp) {
        Integer C = adSlotParam.C();
        if (C != null) {
            if (response != null) {
                response.b(C.intValue());
            }
            if (adContentRsp != null) {
                adContentRsp.f(C.intValue());
            }
        }
    }

    private void a(final Context context, final String str, final List<String> list, final int i, final Response<AdContentRsp> response) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.6
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.g(context).a(str, list, i, response);
            }
        });
    }

    private void a(final long j, final qt qtVar, final ContentRecord contentRecord) {
        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.ol.2
            @Override // java.lang.Runnable
            public void run() {
                ContentRecord contentRecord2 = contentRecord;
                if (contentRecord2 == null || contentRecord2.h() == null || com.huawei.openalliance.ad.utils.bg.a(contentRecord.h().g())) {
                    return;
                }
                qtVar.a(contentRecord.h().g().get(0), contentRecord, j);
            }
        });
    }

    private void a(final int i, final AdContentRsp adContentRsp, final boolean z) {
        if (adContentRsp == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ol.17
            @Override // java.lang.Runnable
            public void run() {
                if (1 == i) {
                    Map<String, String> b = z ? com.huawei.openalliance.ad.utils.cf.b(adContentRsp) : com.huawei.openalliance.ad.utils.cf.a(adContentRsp);
                    if (com.huawei.openalliance.ad.utils.bl.a(b)) {
                        return;
                    }
                    fd.a(ol.this.f7349a).a(b);
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(AdConfigMapKey.SPLASH_PRELOAD_MODE, com.huawei.openalliance.ad.utils.be.b(b));
                        ms.a(ol.this.f7349a).a(RTCMethods.UPDATE_MEDIA_CONFIG, jSONObject.toString(), new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.ol.17.1
                            @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                            public void onRemoteCallResult(String str, CallResult<String> callResult) {
                                if (callResult == null || callResult.getCode() != 200) {
                                    return;
                                }
                                ho.a("AdReqProcessor", "update media config success");
                                String data = callResult.getData();
                                if (com.huawei.openalliance.ad.utils.cz.b(data)) {
                                    return;
                                }
                                fd.a(ol.this.f7349a).a((Map<String, String>) com.huawei.openalliance.ad.utils.be.b(data, Map.class, new Class[0]));
                            }
                        }, String.class);
                    } catch (Throwable th) {
                        ho.c("AdReqProcessor", "update media config to kit error:%s", th.getClass().getSimpleName());
                    }
                }
            }
        });
    }

    private void a(final int i, final AdContentRsp adContentRsp) {
        com.huawei.openalliance.ad.utils.m.b(new Runnable() { // from class: com.huawei.openalliance.ad.ol.18
            @Override // java.lang.Runnable
            public void run() {
                AdContentRsp adContentRsp2 = adContentRsp;
                if (adContentRsp2 != null) {
                    ol.this.b(adContentRsp2, i);
                }
                com.huawei.openalliance.ad.utils.cj.a(ol.this.f7349a, adContentRsp);
            }
        });
    }

    private void a(int i, ApiReqParam apiReqParam, AdContentReq adContentReq) {
        if (com.huawei.openalliance.ad.utils.ao.g()) {
            if (7 == i || 12 == i) {
                a(adContentReq);
            }
            if (a(apiReqParam)) {
                a(adContentReq);
                adContentReq.b(apiReqParam.b());
            }
        }
    }

    private String a(App app, String str) {
        return (app == null || TextUtils.isEmpty(app.c())) ? str : app.c();
    }

    private AdContentRsp a(AdRequestParam adRequestParam) {
        AdContentRsp adContentRsp;
        int a2 = adRequestParam.a();
        AdSlotParam b = adRequestParam.b();
        List<String> c = adRequestParam.c();
        b.d(a2);
        if (!qj.a(this.f7349a, b)) {
            ho.b("AdReqProcessor", "request discard");
            return null;
        }
        String s = b.s();
        if (com.huawei.openalliance.ad.utils.cz.b(s) || b.j()) {
            s = com.huawei.openalliance.ad.utils.ao.a();
        }
        String str = s;
        long c2 = com.huawei.openalliance.ad.utils.ao.c();
        adRequestParam.a(str);
        adRequestParam.a(c2);
        adRequestParam.a(a().u());
        Response<AdContentRsp> a3 = this.e.a(adRequestParam);
        AdContentRsp b2 = a3 != null ? a3.b() : null;
        long c3 = com.huawei.openalliance.ad.utils.ao.c();
        a(b, a3, b2);
        AdContentRsp a4 = pt.a(this.f7349a, b2, a2);
        b(a4);
        if (a3 != null) {
            a().h(a3.r());
            a().i(a3.s());
        }
        if (a4 != null) {
            a4.g(a(b.i(), this.f7349a.getPackageName()));
            a4.c(str);
            a4.a(b);
            a(a4, a2);
            adContentRsp = a4;
        } else {
            adContentRsp = a4;
            a(this.f7349a, str, b.a(), a2, a3);
        }
        a(str, a2, b);
        a(str, a2, c3 - c2, b, a3, c);
        a(b.j(), b.a());
        d();
        AdContentRsp adContentRsp2 = adContentRsp;
        a(adContentRsp2);
        e();
        qn a5 = by.a(this.f7349a).a(1, b.d());
        if (a5 != null) {
            a5.b();
        }
        return adContentRsp2;
    }

    private AdContentRsp a(int i, AdSlotParam adSlotParam, List<String> list, List<String> list2, List<String> list3) {
        return a(new AdRequestParam.Builder().a(i).a(adSlotParam).a(list).b(list2).c(list3).a());
    }

    private App a(String str, AdSlotParam adSlotParam) {
        RequestOptions l;
        if (WhiteListPkgList.inWhiteList(str, com.huawei.openalliance.ad.utils.i.e(this.f7349a, str))) {
            App i = adSlotParam.i();
            return (i != null || (l = adSlotParam.l()) == null || l.getApp() == null) ? i : new App(this.f7349a, l.getApp());
        }
        ho.c("AdReqProcessor", "can not set app info:" + str);
        return null;
    }

    public ol(Context context) {
        this.f7349a = context.getApplicationContext();
        this.b = es.a(context);
        this.c = fk.a(context);
        this.d = fc.a(context);
        this.e = fb.a(context);
        this.f = fh.b(context);
    }
}
