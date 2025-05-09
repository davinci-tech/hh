package com.huawei.openalliance.ad.analysis;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.consent.bean.network.ApiStatisticsReq;
import com.huawei.hms.ads.consent.bean.network.ConfirmResultReq;
import com.huawei.openalliance.ad.beans.metadata.OaidRecord;
import com.huawei.openalliance.ad.beans.metadata.TouchPoint;
import com.huawei.openalliance.ad.cu;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.qy;
import com.huawei.openalliance.ad.rv;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import defpackage.lsr;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class h extends e implements cu {
    public void g(ContentRecord contentRecord) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAppointSuccess, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("93");
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.ak(contentRecord.ad());
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onAppointSuccess RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onAppointSuccess Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cu
    public void f(ContentRecord contentRecord, String str) {
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onFeedbackAction, contentRecord is null.");
                return;
            }
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            if (ho.a()) {
                ho.a("AnalysisReport", "onFeedbackAction, extraStr1: %s", str);
            }
            a2.aj("157");
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.ak(str);
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (Exception e) {
            ho.c("AnalysisReport", "onFeedbackAction Exception:" + e.getClass().getSimpleName());
        }
    }

    public void f(ContentRecord contentRecord) {
        a("17", contentRecord, (String) null);
    }

    public void e(ContentRecord contentRecord, String str) {
        String str2;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "reportInstallPopUpEvent, data is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj(str);
            e.H(contentRecord.n());
            e.p(contentRecord.m());
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(e, false, true);
        } catch (RuntimeException unused) {
            str2 = "reportInstallPopUpEvent RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "reportInstallPopUpEvent Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void d(ContentRecord contentRecord, String str) {
        StringBuilder sb;
        char c;
        try {
            b e = e(contentRecord);
            if (e == null) {
                ho.d("AnalysisReport", "onRewardAdPopUpReport get analysisInfo failed");
                return;
            }
            int hashCode = str.hashCode();
            if (hashCode != 48718) {
                switch (hashCode) {
                    case 48694:
                        if (str.equals("127")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 48695:
                        if (str.equals("128")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 48696:
                        if (str.equals("129")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
            } else {
                if (str.equals("130")) {
                    c = 3;
                }
                c = 65535;
            }
            if (c == 0) {
                e.aj("127");
            } else if (c == 1) {
                e.aj("128");
            } else if (c == 2) {
                e.aj("129");
            } else if (c == 3) {
                e.aj("130");
            }
            ho.b("AnalysisReport", "adType is " + e.t());
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onRewardAdPopUpReport RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onRewardAdPopUpReport Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void d(ContentRecord contentRecord) {
        a("18", contentRecord, (String) null);
    }

    @Override // com.huawei.openalliance.ad.cu
    public void c(String str) {
        String str2;
        try {
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("69");
            if (!TextUtils.isEmpty(str)) {
                a2.r(str);
            }
            new ou(this.f6636a, null).b(a2, false, true);
        } catch (RuntimeException unused) {
            str2 = "onActiveAppFromBackBtn RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "onActiveAppFromBackBtn Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    public void c(ContentRecord contentRecord, String str) {
        String str2;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAppNotificationOperateAction, contentRecord is null");
                return;
            }
            String l = contentRecord.l();
            String m = contentRecord.m();
            int e = contentRecord.e();
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            a2.aj("70");
            a2.o(l);
            a2.p(m);
            a2.a(e);
            if (!TextUtils.isEmpty(str)) {
                a2.r(str);
            }
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, e));
            ouVar.a(contentRecord);
            ouVar.b(a2, false, true);
        } catch (RuntimeException unused) {
            str2 = "onAppNotificationOperateAction RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "onAppNotificationOperateAction Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    public void c(ContentRecord contentRecord, int i) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onCancelAppointmentFailed, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("96");
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.c(i);
            a2.ak(contentRecord.ad());
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onCancelAppointmentFailed RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onCancelAppointmentFailed Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void c(ContentRecord contentRecord) {
        a("117", contentRecord, (String) null);
    }

    public void b(String str) {
        StringBuilder sb;
        try {
            b a2 = a();
            if (a2 == null) {
                return;
            }
            ho.b("AnalysisReport", "onConsentConfirm");
            a2.aj("66");
            a2.C(OaidRecord.LIMIT_OAID_OPEN_KEY.equalsIgnoreCase(str) ? "0" : "1");
            a(this.f6636a, a2);
            b(this.f6636a, a2);
            new ou(this.f6636a, sc.a(this.f6636a, -1)).b(a2, false, true);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onConsentConfirm RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onConsentConfirm Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void b(AdLandingPageData adLandingPageData) {
        String str;
        try {
            if (adLandingPageData == null) {
                ho.c("AnalysisReport", "onLandingOpenAppDialogCancel, data is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("62");
            new ou(this.f6636a, sc.a(this.f6636a, adLandingPageData.getAdType())).b(a(a2, adLandingPageData), false, true);
        } catch (RuntimeException unused) {
            str = "onLandingOpenAppDialogCancelError RuntimeException";
            ho.c("AnalysisReport", str);
        } catch (Exception unused2) {
            str = "onLandingOpenAppDialogCancelError Exception";
            ho.c("AnalysisReport", str);
        }
    }

    public void b(ContentRecord contentRecord, String str) {
        a("64", contentRecord, str);
    }

    public void b(ContentRecord contentRecord, int i) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onCancelAppointmentSuccess, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("95");
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.c(i);
            a2.ak(contentRecord.ad());
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onCancelAppointmentSuccess RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onCancelAppointmentSuccess Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void b(ContentRecord contentRecord) {
        a("116", contentRecord, (String) null);
    }

    public void a(String str, String str2, String str3, String str4) {
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.a(1);
            a2.aj(str);
            a2.ak(str2);
            a2.al(str3);
            a2.am(str4);
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, true, true);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "onDbSizeReport ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(String str, String str2, long j) {
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("161");
            a2.c(j);
            a2.ak(str);
            a2.al(str2);
            a2.c(0);
            new ou(this.f6636a, null).b(a2, false, true);
        } catch (Throwable unused) {
            ho.c("AnalysisReport", "onDynamicLoaderSuccess Exception");
        }
    }

    public void a(String str, String str2, int i, String str3) {
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("161");
            a2.c(i);
            a2.ak(str);
            a2.al(str2);
            a2.am(str3);
            new ou(this.f6636a, null).b(a2, false, true);
        } catch (Throwable unused) {
            ho.c("AnalysisReport", "onDynamicLoaderException Exception");
        }
    }

    public void a(String str, String str2) {
        StringBuilder sb;
        try {
            if (OaidRecord.LIMIT_OAID_OPEN_KEY.equalsIgnoreCase(str2) || OaidRecord.LIMIT_OAID_CLOSE_KEY.equalsIgnoreCase(str2)) {
                b(str2);
            }
            OaidRecord r = fh.b(this.f6636a).r(str2);
            if (r == null) {
                r = new OaidRecord();
            }
            ho.b("AnalysisReport", "onOaidSettingReport");
            r.c();
            if (fh.b(this.f6636a).a(r.b())) {
                ho.b("AnalysisReport", "report oaid setting event");
                b a2 = a(false);
                if (a2 == null) {
                    return;
                }
                a(this.f6636a, a2);
                b(this.f6636a, a2);
                a2.aj(str);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("count", r.a());
                a2.r(cz.d(jSONObject.toString()));
                new ou(this.f6636a, sc.a(this.f6636a, -1)).b(a2, true, true);
                r.a(System.currentTimeMillis());
                r.a(0);
            }
            fh.b(this.f6636a).a(str2, r);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onOaidSettingReport RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onOaidSettingReport Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.cu
    public void a(String str, ConfirmResultReq confirmResultReq, qy qyVar) {
        StringBuilder sb;
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<ApiStatisticsReq> it = confirmResultReq.getCaches().iterator();
            while (it.hasNext()) {
                com.huawei.openalliance.ad.beans.inner.ApiStatisticsReq convert = it.next().convert();
                b a2 = a(str, true);
                if (a2 == null) {
                    return;
                }
                a2.aj("66");
                a2.a(ao.a("yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date(convert.e())));
                a2.r(cz.d(convert.g()));
                a2.z("3.4.74.310");
                a2.A(convert.a());
                a2.B(convert.b());
                a2.b(convert.c());
                a2.c(convert.d());
                a2.C(convert.i());
                if (convert.h() != null) {
                    a2.G(convert.h());
                }
                a2.F(com.huawei.openalliance.ad.utils.d.h(this.f6636a));
                arrayList.add(a2);
            }
            new ou(this.f6636a, sc.a(this.f6636a, -1)).a(str, arrayList, qyVar);
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

    public void a(lsr lsrVar) {
        try {
            if (lsrVar == null) {
                ho.c("AnalysisReport", "onPrivacyStatementOpen, privacyInfo is null");
                return;
            }
            b a2 = a(this.f6636a.getPackageName(), true);
            if (a2 == null) {
                return;
            }
            if (ho.a()) {
                ho.a("AnalysisReport", "onPrivacyStatementOpen, type: %s", lsrVar.d());
            }
            a2.aj("120");
            a2.ad(lsrVar.d());
            a2.ae(lsrVar.b());
            a2.a(lsrVar.a());
            a2.b(lsrVar.e());
            a2.af(lsrVar.c());
            a2.ag(lsrVar.h());
            new ou(this.f6636a, new rv(this.f6636a)).b(a2, true, true);
        } catch (RuntimeException e) {
            ho.c("AnalysisReport", "onPrivacyStatementOpen RuntimeException： %s", e.getClass().getSimpleName());
        } catch (Exception e2) {
            ho.c("AnalysisReport", "onPrivacyStatementOpen Exception： %s", e2.getClass().getSimpleName());
        }
    }

    public void a(AdLandingPageData adLandingPageData, String str) {
        String str2;
        try {
            if (d(str)) {
                if (adLandingPageData == null) {
                    ho.c("AnalysisReport", "onLandingOpenAppDialogCancel, data is null");
                    return;
                }
                b a2 = a();
                if (a2 == null) {
                    return;
                }
                a2.aj(str);
                b a3 = a(a2, adLandingPageData);
                a3.ak("1");
                new ou(this.f6636a, sc.a(this.f6636a, adLandingPageData.getAdType())).b(a3, false, true);
            }
        } catch (RuntimeException unused) {
            str2 = "onLandPagePopUpReport RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "onLandPagePopUpReport Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    public void a(AdLandingPageData adLandingPageData) {
        String str;
        try {
            if (adLandingPageData == null) {
                ho.c("AnalysisReport", "onLandingOpenAppDialogAccept, data is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("61");
            new ou(this.f6636a, sc.a(this.f6636a, adLandingPageData.getAdType())).b(a(a2, adLandingPageData), false, true);
        } catch (RuntimeException unused) {
            str = "onLandingOpenAppDialogAcceptError RuntimeException";
            ho.c("AnalysisReport", str);
        } catch (Exception unused2) {
            str = "onLandingOpenAppDialogAcceptError Exception";
            ho.c("AnalysisReport", str);
        }
    }

    public void a(ContentRecord contentRecord, String str) {
        a("63", contentRecord, str);
    }

    public void a(ContentRecord contentRecord, int i) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAppointFailed, contentRecord is null.");
                return;
            }
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("94");
            a2.a(contentRecord.e());
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.H(contentRecord.n());
            a2.c(i);
            a2.ak(contentRecord.ad());
            new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e())).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onAppointSuccess RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onAppointSuccess Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(ContentRecord contentRecord) {
        a("115", contentRecord, (String) null);
    }

    public void a(int i, int i2, ContentRecord contentRecord) {
        String str;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onDownloadClick, contentRecord is null");
                return;
            }
            String l = contentRecord.l();
            String m = contentRecord.m();
            int e = contentRecord.e();
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            a2.aj("14");
            a2.o(l);
            a2.p(m);
            a2.a(e);
            a2.r(be.b(new TouchPoint(i, i2, contentRecord.n())));
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, e));
            ouVar.a(contentRecord);
            ouVar.b(a2, false, true);
        } catch (RuntimeException unused) {
            str = "onDownloadClick RuntimeException";
            ho.c("AnalysisReport", str);
        } catch (Exception unused2) {
            str = "onDownloadClick Exception";
            ho.c("AnalysisReport", str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private boolean d(String str) {
        char c;
        if (str == null) {
            return false;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode != 48718) {
            switch (hashCode) {
                case 48694:
                    if (str.equals("127")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 48695:
                    if (str.equals("128")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 48696:
                    if (str.equals("129")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals("130")) {
                c = 3;
            }
            c = 65535;
        }
        return c == 0 || c == 1 || c == 2 || c == 3;
    }

    private void a(String str, ContentRecord contentRecord, String str2) {
        String str3;
        try {
            ho.b("AnalysisReport", "report dialog action:" + str);
            if (contentRecord == null) {
                ho.c("AnalysisReport", "reportDialogActionEvent, contentRecord is null");
                return;
            }
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            a2.aj(str);
            a2.o(contentRecord.l());
            a2.p(contentRecord.m());
            a2.a(contentRecord.e());
            if (!TextUtils.isEmpty(str2)) {
                a2.r(str2);
            }
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, contentRecord.e()));
            ouVar.a(contentRecord);
            ouVar.b(a2, false, true);
        } catch (RuntimeException unused) {
            str3 = "reportDialogActionEvent RuntimeException";
            ho.c("AnalysisReport", str3);
        } catch (Exception unused2) {
            str3 = "reportDialogActionEvent Exception";
            ho.c("AnalysisReport", str3);
        }
    }

    public h(Context context) {
        super(context);
    }
}
