package com.huawei.openalliance.ad.analysis;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.beans.inner.ApiStatisticsReq;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.cs;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.gk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ie;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.i;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.rv;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.tf;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import defpackage.vf;
import java.net.URL;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class c extends e implements cs {
    @Override // com.huawei.openalliance.ad.cs
    public void d(ContentRecord contentRecord, int i) {
        if (contentRecord == null) {
            return;
        }
        try {
            ho.a("AnalysisReport", "on preload hit report: %d", Integer.valueOf(i));
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("177");
            e.ak(String.valueOf(i));
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onPreloadHitReport ex: %s", th.getClass().getSimpleName());
        }
    }

    public void d(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("166");
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, true, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onNotifyReward ex: %s", th.getClass().getSimpleName());
        }
    }

    public void c(ContentRecord contentRecord, int i) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("175");
            e.ak(String.valueOf(i));
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "OnlinePlayInCacheMode ex: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void c(ContentRecord contentRecord) {
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onSplashIconShow, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("137");
            a2.s(contentRecord.ag());
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (Exception e) {
            ho.c("AnalysisReport", "onSplashIconShow Exception:" + e.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void b(String str, String str2, ContentRecord contentRecord) {
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.s(contentRecord.ag());
            e.aj("2100027");
            e.ak(str);
            e.al(str2);
            new ou(this.f6636a, new rv(this.f6636a)).b(e, false, true);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "reportTaskException err: %s.", th.getClass().getSimpleName());
        }
    }

    public void b(String str, int i, String str2, String str3, long j) {
        StringBuilder sb;
        try {
            ho.a("AnalysisReport", "onExLinkedShow");
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("84");
            a2.a(i);
            a2.i(str);
            a2.o(str2);
            a2.p(str3);
            a2.d(j);
            new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onExLinkedShow RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onExLinkedShow Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void b(AdLandingPageData adLandingPageData) {
        String str;
        try {
            if (adLandingPageData == null) {
                ho.c("AnalysisReport", "onLandingUrlOverride, data is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("60");
            new ou(this.f6636a, sc.a(this.f6636a, adLandingPageData.getAdType())).b(a(a2, adLandingPageData), false, true);
        } catch (RuntimeException unused) {
            str = "onLandingUrlOverrideError RuntimeException";
            ho.c("AnalysisReport", str);
        } catch (Exception unused2) {
            str = "onLandingUrlOverrideError Exception";
            ho.c("AnalysisReport", str);
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void b(ContentRecord contentRecord, String str, String str2) {
        String str3;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onMagazineReportExtraData, contentRecord is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("178");
            b a3 = a(a2, contentRecord);
            a3.ak(str);
            a3.al(str2);
            ho.a("AnalysisReport", "report [TYPE_MAGAZINE_ADDITION_DATA_REPORT],contentId: %s, exception: exception_%s,additionInfo: %s, extraStr1: %s, extraStr2: %s", contentRecord.m(), a3.aK(), a3.r(), a3.aM(), a3.aN());
            new ou(this.f6636a, sc.a(this.f6636a, a3.t().intValue()), contentRecord).b(a3, false, true);
        } catch (RuntimeException unused) {
            str3 = "onMagazineReportExtraData RuntimeException";
            ho.c("AnalysisReport", str3);
        } catch (Exception unused2) {
            str3 = "onMagazineReportExtraData Exception";
            ho.c("AnalysisReport", str3);
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void b(ContentRecord contentRecord, String str) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("170");
            e.ak(str);
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onRecallReport ex: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void b(ContentRecord contentRecord, int i) {
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onSplashIconNotCached, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("140");
            a2.s(contentRecord.ag());
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.c(i);
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (Exception e) {
            ho.c("AnalysisReport", "onSplashIconNotCached Exception:" + e.getClass().getSimpleName());
        }
    }

    public void b(ContentRecord contentRecord) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onVideoNotDownloadInNonWifi, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("121");
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onVideoNotDownloadInNonWifi RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onVideoNotDownloadInNonWifi Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(List<ContentRecord> list) {
        if (bg.a(list)) {
            ho.a("AnalysisReport", "recall records empty");
            return;
        }
        int size = list.size();
        ho.b("AnalysisReport", "report client recall, size: %s", Integer.valueOf(size));
        int i = 0;
        while (i < size) {
            try {
                ContentRecord contentRecord = list.get(i);
                b e = e(contentRecord);
                if (e != null) {
                    e.aj("170");
                    e.ak(Constants.EXTRA_CLIENT_RECALL);
                    new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, i == size + (-1), false);
                }
            } catch (Throwable th) {
                ho.c("AnalysisReport", "onRecallContentReport ex: %s", th.getClass().getSimpleName());
            }
            i++;
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(Throwable th) {
        StringBuilder sb;
        try {
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("1");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(th.getClass().getSimpleName());
            sb2.append(",");
            sb2.append(th.getMessage());
            sb2.append(",");
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                sb2.append(stackTraceElement.toString());
                sb2.append(",");
            }
            a2.r(sb2.toString());
            a2.a(-1);
            new ou(this.f6636a, sc.a(this.f6636a, -1)).b(a2, false, true);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onAnalysis RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onAnalysis Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(String str, List<String> list, List<String> list2) {
        try {
            b a2 = a(str);
            a2.aj("2100006");
            a2.ak(cz.a(list, ","));
            a2.al(cz.a(list2, ","));
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onTagReport ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(String str, String str2, boolean z) {
        String str3;
        try {
            ho.a("AnalysisReport", "onDownloadImg, contentId:" + str2 + ", succ:" + z);
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("10");
            a2.r("download success:" + z);
            a2.p(str2);
            a2.o(str);
            a2.a(2);
            a2.ak(z ? "1" : "0");
            ho.a("AnalysisReport", "report [TYPE_DOWNLOAD_IMG],contentId: %s, exception: exception_%s,additionInfo: %s, extraStr1: %s", str2, a2.aK(), a2.r(), a2.aM());
            new ou(this.f6636a, sc.a(this.f6636a, a2.t().intValue())).b(a2, true, false);
        } catch (RuntimeException unused) {
            str3 = "onDownloadImg RuntimeException";
            ho.c("AnalysisReport", str3);
        } catch (Exception unused2) {
            str3 = "onException Exception";
            ho.c("AnalysisReport", str3);
        }
    }

    public void a(String str, String str2, String str3, String str4, long j, ContentRecord contentRecord) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onUploadThirdPartyEventFail, contentRecord is null");
                return;
            }
            b a2 = a(true, contentRecord.am());
            if (a2 == null) {
                return;
            }
            String l = contentRecord.l();
            String m = contentRecord.m();
            int e = contentRecord.e();
            a2.o(l);
            a2.p(m);
            a2.a(e);
            a2.aj("9");
            a2.w(str3);
            a2.v(new URL(str2).getHost());
            a2.s(str4);
            a2.c(j);
            a2.ak(str);
            new ou(this.f6636a, sc.a(this.f6636a, e)).a(a2, true, false);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onUploadThirdPartyEventFail RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onUploadThirdPartyEventFail Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, String str2, String str3, AdLandingPageData adLandingPageData) {
        StringBuilder sb;
        try {
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("22");
            a2.r(str3);
            a2.w(str2);
            a2.q(str);
            b a3 = a(a2, adLandingPageData);
            new ou(this.f6636a, sc.a(this.f6636a, a3.t().intValue())).b(a3, false, true);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onLandPageOpenFail RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onLandPageOpenFail Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, String str2, String str3, long j, ContentRecord contentRecord) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onUploadThirdPartyEventSuccess, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("19");
            e.v(new URL(str2).getHost());
            e.s(str3);
            e.c(j);
            e.ak(str);
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).a(e, true, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onUploadThirdPartyEventSuccess RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onUploadThirdPartyEventSuccess Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(String str, String str2, ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("2100013");
            e.ak(str);
            e.al(str2);
            e.am(contentRecord.aN());
            new ou(this.f6636a, new rv(this.f6636a)).b(e, false, true);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "loadInterstitialTemplate err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(String str, String str2, DelayInfo delayInfo, int i, int i2) {
        ApiStatisticsReq apiStatisticsReq = new ApiStatisticsReq();
        apiStatisticsReq.b(str);
        apiStatisticsReq.d(str2);
        apiStatisticsReq.a(delayInfo);
        apiStatisticsReq.b(i2);
        apiStatisticsReq.c(i);
        if (delayInfo != null && delayInfo.F() != null) {
            apiStatisticsReq.d(delayInfo.F().intValue());
        }
        a(apiStatisticsReq);
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(String str, Integer num, String str2, ContentRecord contentRecord, boolean z) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAdEventFilter, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("99");
            int e = contentRecord.e();
            a2.a(e);
            a2.p(contentRecord.m());
            a2.s(contentRecord.ag());
            a2.d(contentRecord.am());
            a2.ak(str);
            if (num != null) {
                a2.al(num.toString());
            }
            a2.am(str2);
            a2.an(contentRecord.j());
            a2.b(z ? 1 : 0);
            if (ho.a()) {
                ho.a("AnalysisReport", "filterEvent eventType: %s, result: %s", str, Integer.valueOf(a2.D()));
            }
            new ou(this.f6636a, sc.a(this.f6636a, e)).b(a2, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onAdEventFilter RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onAdEventFilter Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, ContentRecord contentRecord, boolean z) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAdEventAddToCache, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("100");
            int e = contentRecord.e();
            a2.a(e);
            a2.p(contentRecord.m());
            a2.s(contentRecord.ag());
            a2.d(contentRecord.am());
            a2.ak(str);
            a2.an(contentRecord.j());
            a2.b(z ? 1 : 0);
            if (ho.a()) {
                ho.a("AnalysisReport", "cacheEvent, eventType: %s, result: %s", str, Integer.valueOf(a2.D()));
            }
            new ou(this.f6636a, sc.a(this.f6636a, e)).b(a2, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onAdEventAddToCache RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onAdEventAddToCache Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, ContentRecord contentRecord, int i, String str2, String str3) {
        StringBuilder sb;
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("88");
            e.B(str2);
            e.r(str3);
            e.c(i);
            e.ak(com.huawei.openalliance.ad.utils.d.n(this.f6636a));
            e.al(com.huawei.openalliance.ad.utils.d.o(this.f6636a));
            e.am(str);
            if (ho.a()) {
                ho.a("AnalysisReport", "onAgApiCalled additionInfo: %s apiName: %s, statusCode: %s", str3, str2, Integer.valueOf(i));
            }
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, false);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onAgApiCalled RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onAgApiCalled Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("2100003");
            e.ak(str);
            new ou(this.f6636a, new rv(this.f6636a)).b(e, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onShareClickReport ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(String str, ApiStatisticsReq apiStatisticsReq) {
        StringBuilder sb;
        try {
            if (apiStatisticsReq == null) {
                ho.c("AnalysisReport", "onApiStatisticsReport, apiStatisticsReq is null ");
                return;
            }
            b a2 = a(str, true);
            if (a2 == null) {
                return;
            }
            a2.d(apiStatisticsReq.l());
            a(a2, apiStatisticsReq);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onApiStatisticsReport RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onApiStatisticsReport Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, b bVar) {
        try {
            b a2 = a("", true);
            if (a2 == null) {
                return;
            }
            a2.aj("2100002");
            a2.ak(bVar.aM());
            a2.al(bVar.aN());
            a2.am(bVar.aO());
            a2.an(bVar.aP());
            a2.ao(bVar.aQ());
            a2.ap(bVar.aR());
            a2.aq(bVar.aS());
            a2.ar(bVar.aT());
            a2.as(bVar.aU());
            ho.a("AnalysisReport", "remaining app data: %s", bVar.aO());
            new ou(this.f6636a, new rv(this.f6636a)).a(a2);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onCacheRecordCollected ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(String str, long j, String str2, String str3, int i) {
        String str4;
        try {
            ho.b("AnalysisReport", "onAidlCalledResult:" + str);
            if (TextUtils.isEmpty(str)) {
                ho.c("AnalysisReport", "onAidlCalledResult, exceptionType is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj(str);
            a2.c(j);
            a2.w(str2);
            a2.r(str3);
            a2.y(com.huawei.openalliance.ad.utils.d.m(this.f6636a));
            a2.a(i);
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, false, true);
        } catch (RuntimeException unused) {
            str4 = "onAidlCalledResult RuntimeException";
            ho.c("AnalysisReport", str4);
        } catch (Exception unused2) {
            str4 = "onAidlCalledResult Exception";
            ho.c("AnalysisReport", str4);
        }
    }

    public void a(String str, long j) {
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("143");
            a2.ak(String.valueOf(j));
            a2.am(str);
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onDbSizeReport ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(String str, int i, String str2, String str3, String str4, boolean z) {
        StringBuilder sb;
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("101");
            a2.a(i);
            a2.p(str4);
            a2.ak(str);
            a2.s(str2);
            a2.an(str3);
            a2.b(z ? 1 : 0);
            if (ho.a()) {
                ho.a("AnalysisReport", "rptEvent, eventType: %s, result: %s", str, Integer.valueOf(a2.D()));
            }
            new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, true);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onAdEventUpload RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onAdEventUpload Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, int i, String str2, String str3, long j) {
        StringBuilder sb;
        try {
            ho.a("AnalysisReport", "onExLinkedOvertime");
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("83");
            a2.a(i);
            a2.i(str);
            a2.o(str2);
            a2.p(str3);
            a2.d(j);
            new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onExLinkedOvertime RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onExLinkedOvertime Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, int i, String str2, String str3) {
        StringBuilder sb;
        try {
            ho.a("AnalysisReport", "onExLinkedNotShow");
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("82");
            a2.a(i);
            a2.i(str);
            a2.o(str2);
            a2.p(str3);
            new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onExLinkedNotShow RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onExLinkedNotShow Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, int i, String str2, ContentRecord contentRecord, boolean z) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onImageLoadFailed, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("104");
            e.q(str);
            e.c(i);
            e.r(str2);
            e.ak(str2);
            e.al("normal");
            e.am(String.valueOf(z));
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, false);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onImageLoadFailed RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onImageLoadFailed Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(String str, int i, ContentRecord contentRecord) {
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.s(contentRecord.ag());
            e.aj("2100026");
            e.ak(str);
            e.al(cz.a(Integer.valueOf(i)));
            new ou(this.f6636a, new rv(this.f6636a)).b(e, false, true);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "reportNoMzUrl err: %s.", th.getClass().getSimpleName());
        }
    }

    public void a(String str, int i, int i2, ContentRecord contentRecord) {
        String str2;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onPlacementPlayError, contentRecord is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("11");
            b a3 = a(a2, contentRecord);
            a3.q(str);
            a3.r("errorcode:" + i + ", extra:" + i2);
            new ou(this.f6636a, sc.a(this.f6636a, a3.t().intValue()), contentRecord).b(a3, false, true);
        } catch (RuntimeException unused) {
            str2 = "onPlacementPlayError RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "onPlacementPlayError Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(final String str, final int i, final int i2, final int i3, final String str2) {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.c.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    c cVar = c.this;
                    b a2 = cVar.a(cVar.f6636a.getPackageName(), true);
                    a2.aj(str2);
                    a2.ak(String.valueOf(str));
                    a2.al(String.valueOf(i));
                    a2.am(String.valueOf(i2));
                    a2.an(String.valueOf(i3));
                    if (ho.a()) {
                        ho.a("AnalysisReport", "update database name is %s, exception type is %s, oldVersion is %s, newVersion is %s, upgradeFlag is %s", str, str2, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
                    }
                    new ou(c.this.f6636a, new rv(c.this.f6636a)).b(a2, false, false);
                } catch (Throwable th) {
                    ho.c("AnalysisReport", "update database exception: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    public void a(String str, int i) {
        StringBuilder sb;
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.a(i);
            a2.aj("85");
            a2.o(str);
            new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onSplashDismissForExSplash RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onSplashDismissForExSplash Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(final tf tfVar, final String str) {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.c.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    c cVar = c.this;
                    b a2 = cVar.a(cVar.f6636a.getPackageName());
                    a2.aj("180");
                    a2.H(tfVar.c());
                    a2.p(tfVar.b());
                    a2.a(tfVar.a());
                    a2.ak(tfVar.d());
                    a2.al(tfVar.e());
                    a2.am(str);
                    ho.c("AnalysisReport", "start activity error %s", str);
                    new ou(c.this.f6636a, new rv(c.this.f6636a)).b(a2, false, false);
                } catch (Throwable th) {
                    ho.c("AnalysisReport", "onStartActivityException ex: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    public void a(com.huawei.openalliance.ad.processor.eventbeans.b bVar, ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("2100004");
            e.ak(bVar.b());
            e.al(be.b(bVar));
            MaterialClickInfo h = bVar.h();
            if (h != null && h.h() != null && h.i() != null) {
                e.c(h.h().longValue() - h.i().longValue());
            }
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onInvlidClickReport ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(AdLandingPageData adLandingPageData) {
        String str;
        try {
            if (adLandingPageData == null) {
                ho.c("AnalysisReport", "onLandingPageBlocked, data is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj(HealthZonePushReceiver.BODY_TEMPERATURE_HIGH_NOTIFY);
            new ou(this.f6636a, sc.a(this.f6636a, adLandingPageData.getAdType())).b(a(a2, adLandingPageData), false, true);
        } catch (RuntimeException unused) {
            str = "onPlacementPlayError RuntimeException";
            ho.c("AnalysisReport", str);
        } catch (Exception unused2) {
            str = "onPlacementPlayError Exception";
            ho.c("AnalysisReport", str);
        }
    }

    public void a(ContentRecord contentRecord, String str, String str2, String str3) {
        String str4;
        JSONObject jSONObject;
        try {
            ho.b("AnalysisReport", "report read channel info");
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onReadChannelInfo, contentRecord is null");
                return;
            }
            String l = contentRecord.l();
            String m = contentRecord.m();
            int e = contentRecord.e();
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            a2.aj("71");
            a2.o(l);
            a2.p(m);
            a2.a(e);
            a2.ak(str3);
            a2.y(com.huawei.openalliance.ad.utils.d.m(this.f6636a));
            if (!TextUtils.isEmpty(str)) {
                a2.r(str);
                try {
                    jSONObject = new JSONObject(cz.c(str));
                } catch (JSONException unused) {
                    ho.c("AnalysisReport", "transfer channel info to json error");
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    a2.al(cz.d(jSONObject.optString("channelId")));
                }
            }
            a2.am(cz.d(str2));
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, e));
            ouVar.a(contentRecord);
            ouVar.b(a2, true, true);
        } catch (RuntimeException unused2) {
            str4 = "onReadChannelInfo RuntimeException";
            ho.c("AnalysisReport", str4);
        } catch (Exception unused3) {
            str4 = "onReadChannelInfo Exception";
            ho.c("AnalysisReport", str4);
        }
    }

    public void a(ContentRecord contentRecord, String str, String str2) {
        String str3;
        try {
            ho.b("AnalysisReport", "report write channel info");
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onWriteChannelInfo, contentRecord is null");
                return;
            }
            String l = contentRecord.l();
            String m = contentRecord.m();
            int e = contentRecord.e();
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            a2.aj(HealthZonePushReceiver.FAMILY_CARE_NOTIFY);
            a2.o(l);
            a2.p(m);
            a2.a(e);
            a2.ak(str2);
            if (!TextUtils.isEmpty(str)) {
                a2.r(str);
            }
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, e));
            ouVar.a(contentRecord);
            ouVar.b(a2, true, true);
        } catch (RuntimeException unused) {
            str3 = "reportDialogActionEvent RuntimeException";
            ho.c("AnalysisReport", str3);
        } catch (Exception unused2) {
            str3 = "reportDialogActionEvent Exception";
            ho.c("AnalysisReport", str3);
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(ContentRecord contentRecord, String str, a aVar) {
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onFullScreenNotifyAction, contentRecord is null.");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            if (ho.a()) {
                ho.a("AnalysisReport", "onFullScreenNotifyAction, extraStr1: %s", str);
            }
            if (aVar != null) {
                if (ho.a()) {
                    ho.a("AnalysisReport", "onFullScreenNotifyAction, extraStr2: %s, extraStr3: %s", aVar.e(), aVar.f());
                }
                a2.al(aVar.e());
                a2.am(aVar.f());
            }
            a2.aj("126");
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.ak(str);
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (Exception e) {
            ho.c("AnalysisReport", "onFullScreenNotifyAction Exception:" + e.getClass().getSimpleName());
        }
    }

    public void a(ContentRecord contentRecord, String str) {
        String str2;
        if (contentRecord == null) {
            ho.c("AnalysisReport", "onAppInstalled, contentRecord is null ");
            return;
        }
        try {
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            a2.aj("50");
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.a(contentRecord.e());
            a2.r(String.valueOf(Process.myPid()));
            if (!TextUtils.isEmpty(str)) {
                a2.ak(str);
            }
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e()));
            ouVar.a(contentRecord);
            ouVar.b(a2, false, true);
        } catch (RuntimeException unused) {
            str2 = "onAppInstalled RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "onAppInstalled Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(ContentRecord contentRecord, long j, long j2, a aVar) {
        StringBuilder sb;
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("86");
            e.c(j);
            e.d(j2);
            if (contentRecord != null) {
                e.ak(contentRecord.ay());
                e.al(String.valueOf(contentRecord.az()));
            }
            if (aVar != null) {
                e.am(String.valueOf(aVar.g()));
                e.an(aVar.h());
            }
            if (ho.a()) {
                ho.a("AnalysisReport", "onVideoStartTimeCost duration: %s, bufferingDuration: %s, fileSize:%s, sequence: %s, playMode: %s", Long.valueOf(e.aL()), Long.valueOf(e.aW()), e.aM(), e.aO(), e.aN());
            }
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue())).b(e, false, false);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onVideoStartTimeCost RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onVideoStartTimeCost Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(ContentRecord contentRecord, long j, long j2, int i) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            ho.b("AnalysisReport", "onVideoPlayEx, contentId: %s", contentRecord.m());
            if (ho.a()) {
                ho.a("AnalysisReport", "onVideoPlayEx, waitingTime: %s, playedTime: %s", Long.valueOf(j), Long.valueOf(j2));
            }
            e.aj("160");
            e.c(j);
            e.d(j2);
            e.ak(String.valueOf(i));
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, true);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onVideoPlayException ex: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(ContentRecord contentRecord, long j, long j2) {
        a(contentRecord, j, j2, (a) null);
    }

    public void a(ContentRecord contentRecord, long j, int i) {
        String str;
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("142");
            VideoInfo R = contentRecord.R();
            if (R != null) {
                long a2 = ie.a().a(R.a());
                e.d(R.c());
                e.e(a2);
            }
            e.ak(cz.a(Integer.valueOf(i)));
            ho.b("AnalysisReport", "adType is " + e.t());
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, true, true);
        } catch (RuntimeException unused) {
            str = "onVideoStreamError RuntimeException";
            ho.c("AnalysisReport", str);
        } catch (Exception unused2) {
            str = "onVideoStreamError Exception";
            ho.c("AnalysisReport", str);
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(ContentRecord contentRecord, int i, int i2, String str) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onContentOrentationError, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("108");
            e.s(str);
            e.ak(String.valueOf(i));
            e.al(String.valueOf(contentRecord.as()));
            e.am(String.valueOf(i2));
            if ((contentRecord.R() != null && contentRecord.R().m() == null) || (contentRecord.Q() != null && (contentRecord.Q().d() == 0 || contentRecord.Q().e() == 0))) {
                e.c(1);
            }
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, false);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onContentOrrentationError RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onContentOrrentationError Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(ContentRecord contentRecord, int i, int i2) {
        StringBuilder sb;
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("119");
            b a3 = a(a2, contentRecord);
            a3.c(i);
            if (ho.a()) {
                ho.a("AnalysisReport", "onSpareSplashMediaPathChecked resultCode: %s", Integer.valueOf(i));
            }
            new ou(this.f6636a, sc.a(this.f6636a, i2), contentRecord).b(a3, false, true);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onSpareSplashMediaPathChecked RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onSpareSplashMediaPathChecked Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(ContentRecord contentRecord, int i) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onStartSpareSplashAd, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("105");
            a2.s(contentRecord.ag());
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.ak(String.valueOf(contentRecord.as()));
            a2.c(i);
            if (ho.a()) {
                ho.a("AnalysisReport", "onStartSpareSplashAd resultCode: %s", Integer.valueOf(i));
            }
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onStartSpareSplashAd RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onStartSpareSplashAd Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(ContentRecord contentRecord) {
        String str;
        if (contentRecord == null) {
            ho.c("AnalysisReport", "onPraise, contentRecord is null ");
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("2100001");
            e.c(cz.a(contentRecord.aA(), -1));
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e()));
            ouVar.a(contentRecord);
            ouVar.b(e, false, true);
        } catch (RuntimeException unused) {
            str = "onPraise RuntimeException";
            ho.c("AnalysisReport", str);
        } catch (Exception unused2) {
            str = "onPraise Exception";
            ho.c("AnalysisReport", str);
        }
    }

    public void a(Content content, int i, int i2) {
        try {
            b a2 = a(this.f6636a.getPackageName());
            a2.aj("2100010");
            a2.H(content.h());
            a2.a(i2);
            a2.p(content.g());
            a2.ak(String.valueOf(i));
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onContentFilterException ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(ApiStatisticsReq apiStatisticsReq) {
        StringBuilder sb;
        try {
            if (apiStatisticsReq == null) {
                ho.c("AnalysisReport", "onApiStatisticsReport, apiStatisticsReq is null ");
                return;
            }
            b a2 = a(true, apiStatisticsReq.l());
            if (a2 == null) {
                return;
            }
            a(a2, apiStatisticsReq);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onApiStatisticsReport RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onApiStatisticsReport Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(a aVar, int i, String str, String str2) {
        StringBuilder sb;
        try {
            b a2 = a(true);
            if (a2 != null && aVar != null) {
                a2.aj("107");
                a2.a(aVar.d());
                a2.s(aVar.a());
                a2.o(aVar.b());
                a2.p(aVar.c());
                a2.c(i);
                a2.ak(str);
                a2.al(str2);
                a2.am("normal");
                if (ho.a()) {
                    ho.a("AnalysisReport", "onRecordSpareSplashAdFailed resultCode: %s", Integer.valueOf(i));
                }
                new ou(this.f6636a, sc.a(this.f6636a, aVar.d())).b(a2, false, false);
            }
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onRecordSpareAdFailed RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onRecordSpareAdFailed Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(VideoConfiguration videoConfiguration, boolean z, boolean z2, int i) {
        try {
            b a2 = a(this.f6636a.getPackageName());
            if (a2 == null) {
                return;
            }
            a2.aj("2200195");
            a2.ak(this.f6636a.getPackageName());
            a2.ap(String.valueOf(i));
            if (videoConfiguration == null) {
                return;
            }
            a2.al(String.valueOf(z));
            a2.am(String.valueOf(videoConfiguration.getAutoPlayNetwork()));
            a2.an(String.valueOf(z2));
            a2.ao(String.valueOf(videoConfiguration.isMute()));
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, false, true);
            if (ho.a()) {
                ho.a("AnalysisReport", "ExceptionType is %s, Media pkgName is %s, AdType is %s, ServerFirst is %s, AutoPlay is %s, ServerFirst is %s, isMute is %s", "2200195", this.f6636a.getPackageName(), Integer.valueOf(i), Boolean.valueOf(z), Integer.valueOf(videoConfiguration.getAutoPlayNetwork()), Boolean.valueOf(z2), Boolean.valueOf(videoConfiguration.isMute()));
            }
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onSetVideoConfigMedia err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(vf vfVar) {
        if (vfVar == null) {
            return;
        }
        try {
            b a2 = a(vfVar.o(), true);
            a2.aj(vfVar.e());
            a2.ak(vfVar.c());
            a2.al(vfVar.b());
            a2.am(vfVar.i());
            a2.an(vfVar.g());
            a2.ao(vfVar.f());
            a2.ap(vfVar.h());
            a2.aq(vfVar.j());
            a2.ar(vfVar.m());
            a2.as(vfVar.l());
            a2.at(vfVar.a());
            a2.a(vfVar.d());
            a2.d(vfVar.k());
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onEventProcessCallBackFromRecEngine ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(Bundle bundle, ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            if (bundle != null) {
                gk gkVar = new gk(bundle);
                e.aj(gkVar.d(ParamConstants.Param.EXCEPTION_TYPE));
                e.ak(gkVar.d(ParamConstants.Param.EXTRA_STR1));
                e.al(gkVar.d(ParamConstants.Param.EXTRA_STR2));
                e.am(gkVar.d(ParamConstants.Param.EXTRA_STR3));
                e.an(gkVar.d(ParamConstants.Param.EXTRA_STR4));
                e.ao(gkVar.d(ParamConstants.Param.EXTRA_STR5));
            }
            e.a(1);
            new ou(this.f6636a, new rv(this.f6636a)).b(e, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "splashEventReport ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(Context context, IAd iAd, int i) {
        String str;
        String l;
        boolean z;
        try {
            if (context == null || iAd == null) {
                ho.c("AnalysisReport", "param err");
                return;
            }
            if (iAd instanceof com.huawei.openalliance.ad.inter.data.e) {
                str = ((com.huawei.openalliance.ad.inter.data.e) iAd).getSlotId();
            } else if (iAd instanceof com.huawei.openalliance.ad.inter.data.g) {
                str = ((com.huawei.openalliance.ad.inter.data.g) iAd).F();
            } else {
                if (iAd instanceof i) {
                    l = ((i) iAd).a() != null ? ((i) iAd).a().l() : "";
                    z = true;
                    b a2 = a();
                    a2.aj("2200198");
                    a2.o(l);
                    a2.H(iAd.getTaskId());
                    a2.ak(cz.a(Integer.valueOf(i)));
                    a2.al(cz.a(Boolean.valueOf(z)));
                    new ou(context, sc.a(context, -1)).b(a2, false, true);
                }
                str = "";
            }
            l = str;
            z = false;
            b a22 = a();
            a22.aj("2200198");
            a22.o(l);
            a22.H(iAd.getTaskId());
            a22.ak(cz.a(Integer.valueOf(i)));
            a22.al(cz.a(Boolean.valueOf(z)));
            new ou(context, sc.a(context, -1)).b(a22, false, true);
        } catch (Throwable unused) {
            ho.d("AnalysisReport", "report download inter failed");
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(int i, String str, String str2, String str3, ContentRecord contentRecord, String str4) {
        String str5;
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.s(contentRecord.ag());
            ho.b("AnalysisReport", "InvokeMZ, type is %s", Integer.valueOf(i));
            if (i == 0) {
                str5 = "2100024";
            } else if (i == 1) {
                str5 = "2100022";
            } else if (i != 2) {
                return;
            } else {
                str5 = "2100023";
            }
            e.aj(str5);
            e.ak(str);
            e.al(str2);
            e.am(str3);
            e.an(str4);
            new ou(this.f6636a, new rv(this.f6636a)).b(e, false, false);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "invoke MZ SDK err: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(final int i, final Integer num, final Integer num2) {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.c.1
            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                b a2 = cVar.a(cVar.f6636a.getPackageName(), true);
                try {
                    a2.aj("2100008");
                    a2.ak(String.valueOf(i));
                    a2.al(String.valueOf(num));
                    a2.am(String.valueOf(num2));
                    a2.d(System.currentTimeMillis());
                    new ou(c.this.f6636a, new rv(c.this.f6636a)).b(a2, false, false);
                } catch (Throwable th) {
                    ho.c("AnalysisReport", "report update uiengine error: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.cs
    public void a(int i, ContentRecord contentRecord) {
        try {
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.s(contentRecord.ag());
            e.aj("2100025");
            e.ak(cz.a(Integer.valueOf(i)));
            new ou(this.f6636a, new rv(this.f6636a)).b(e, false, true);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "reportSdkMonitors err: %s.", th.getClass().getSimpleName());
        }
    }

    private void a(b bVar, ApiStatisticsReq apiStatisticsReq) {
        bVar.aj("65");
        bVar.a(ao.a("yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date(apiStatisticsReq.e())));
        bVar.r(cz.d(apiStatisticsReq.g()));
        bVar.A(apiStatisticsReq.a());
        bVar.B(apiStatisticsReq.b());
        bVar.b(apiStatisticsReq.c());
        bVar.c(apiStatisticsReq.d());
        bVar.s(apiStatisticsReq.j());
        bVar.p(apiStatisticsReq.m());
        int k = apiStatisticsReq.k();
        bVar.a(k);
        bVar.c(apiStatisticsReq.f());
        a(bVar, apiStatisticsReq.n());
        bVar.F(com.huawei.openalliance.ad.utils.d.h(this.f6636a));
        bVar.D(com.huawei.openalliance.ad.utils.d.a(this.f6636a, false));
        if (ho.a()) {
            ho.a("AnalysisReport", "onApiStatisticsReport apiName: %s requestId: %s  requestType: %s adType: %s resultCode: %s  e2e: %s", bVar.C(), bVar.s(), Integer.valueOf(bVar.N()), bVar.t(), Integer.valueOf(bVar.E()), bVar.aJ());
            ho.a("AnalysisReport", "onApiStatisticsReport, uuid: %s, uuid: %s", bVar.I(), bVar.G());
            ho.a("AnalysisReport", "rec engine cost time: %s", Long.valueOf(bVar.bh()));
            ho.a("AnalysisReport", "hms rec engine cost time: %s", Long.valueOf(bVar.bi()));
        }
        new ou(this.f6636a, sc.a(this.f6636a, k)).b(bVar, false, true);
    }

    public c(Context context) {
        super(context);
    }
}
