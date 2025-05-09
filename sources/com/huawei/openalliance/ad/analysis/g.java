package com.huawei.openalliance.ad.analysis;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.beans.inner.LocationSwitches;
import com.huawei.openalliance.ad.beans.metadata.AdTimeStatistics;
import com.huawei.openalliance.ad.beans.metadata.BluetoothInfo;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.ct;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.du;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.net.http.Response;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.cw;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.utils.q;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class g extends e implements ct {
    public void a(List<ContentResource> list, Integer num) {
        StringBuilder sb;
        try {
            if (bg.a(list)) {
                ho.c("AnalysisReport", "onContentResourceRemoved, contentRecord is null");
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            int size = list.size();
            String str = null;
            int i = -1;
            int i2 = 1;
            for (int i3 = 0; i3 < size; i3++) {
                ContentResource contentResource = list.get(i3);
                if (i3 == 0) {
                    i = contentResource.d();
                    i2 = contentResource.f();
                    str = contentResource.a();
                } else {
                    sb2.append(",");
                }
                sb2.append(contentResource.c());
                sb2.append("#");
                sb2.append(contentResource.d());
                sb2.append("#");
                sb2.append(contentResource.h());
                sb2.append("#");
                sb2.append(contentResource.e());
                sb2.append("#");
                sb2.append(contentResource.b());
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.a(i);
            a2.aj("77");
            a2.r(sb2.toString());
            a2.b(Integer.valueOf(i2));
            a2.q(str);
            if (num != null) {
                a2.ak(String.valueOf(num));
            }
            new ou(this.f6636a, sc.a(this.f6636a, -1)).b(a2, false, true);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onContentResourceRemoved RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onContentResourceRemoved Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, List<String> list, int i, Response response) {
        String str2;
        String str3;
        int i2;
        Throwable n;
        if (response == null || (n = response.n()) == null) {
            str2 = null;
            str3 = "unknown";
            i2 = -1;
        } else {
            str2 = n.getClass().getSimpleName();
            str3 = n.getMessage();
            i2 = a(n);
        }
        for (String str4 : list) {
            if (!TextUtils.isEmpty(str4)) {
                a(str, str4, i, i2, str2, str3);
            }
        }
    }

    public void a(String str, String str2, int i, int i2, String str3, String str4) {
        StringBuilder sb;
        try {
            b a2 = a(true);
            if (a2 == null) {
                return;
            }
            a2.aj("81");
            a2.o(str2);
            a2.s(str);
            a2.a(i);
            a2.c(i2);
            a2.ak(str3);
            a2.r(str4);
            new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onInnerError RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onInnerError Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, Long l, Long l2, boolean z, ContentRecord contentRecord, String str2, HttpConnection httpConnection) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAdResDownload, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("72");
            e.q(str);
            e.ak(str2);
            e.al(contentRecord.T());
            a(e, httpConnection, str);
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onAdResDownload RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onAdResDownload Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, Long l, Long l2, Long l3, Long l4, boolean z, ContentRecord contentRecord, String str2, long j, HttpConnection httpConnection) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAdResDownloadSuccess, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("5");
            e.r("isCached:" + z);
            e.q(str);
            if (l3 != null) {
                if (l != null) {
                    long longValue = l3.longValue() - l.longValue();
                    ho.a("AnalysisReport", "onAdResDownloadSuccess - adReqToContentDownloadDuration: %d", Long.valueOf(longValue));
                    e.t(String.valueOf(longValue));
                }
                if (l2 != null) {
                    long longValue2 = l3.longValue() - l2.longValue();
                    ho.a("AnalysisReport", "onAdResDownloadSuccess - contentDownloadDuration: %d", Long.valueOf(longValue2));
                    e.u(String.valueOf(longValue2));
                    if (longValue2 > 0 && j > 0) {
                        long j2 = ((100000 * j) / longValue2) / 100;
                        e.f(j2);
                        if (ho.a()) {
                            ho.a("AnalysisReport", "onAdResDownloadSuccess - total download time: %d(ms) total download size: %d(bytes) avg. speed: %d(bytes/s)", Long.valueOf(longValue2), Long.valueOf(j), Long.valueOf(j2));
                        }
                    }
                }
                if (l4 != null && !z) {
                    long longValue3 = l4.longValue() - l3.longValue();
                    if (ho.a()) {
                        ho.a("AnalysisReport", "onAdResDownloadSuccess - fileOperateDuration: %s", Long.valueOf(longValue3));
                    }
                    e.d(longValue3);
                }
            }
            e.g(j);
            String f = cw.f(this.f6636a);
            if (!TextUtils.isEmpty(f)) {
                e.h(ae.d(f).longValue());
                e.i(ae.c(f).longValue());
            }
            String g = cw.g(this.f6636a);
            if (!TextUtils.isEmpty(g)) {
                e.j(ae.d(g).longValue());
                e.k(ae.c(g).longValue());
            }
            e.ak(str2);
            e.al(contentRecord.T());
            a(e, httpConnection, str);
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onAdResDownloadSuccess RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onAdResDownloadSuccess Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, Long l, Long l2, long j, ContentRecord contentRecord, String str2) {
        String str3;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAdResCheckFailed, contentRecord is null");
                return;
            }
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("3");
            b a3 = a(a2, contentRecord);
            a3.q(str);
            if (l != null) {
                long longValue = j - l.longValue();
                ho.a("AnalysisReport", "onAdResCheckFailed - adReqToContentDownloadDuration: %d", Long.valueOf(longValue));
                a3.t(String.valueOf(longValue));
            }
            if (l2 != null) {
                long longValue2 = j - l2.longValue();
                ho.a("AnalysisReport", "onAdResCheckFailed - contentDownloadDuration: %d", Long.valueOf(longValue2));
                a3.u(String.valueOf(longValue2));
            }
            a3.ak(str2);
            new ou(this.f6636a, sc.a(this.f6636a, a3.t().intValue()), contentRecord).b(a3, false, true);
        } catch (RuntimeException unused) {
            str3 = "onAdResCheckFailed RuntimeException";
            ho.c("AnalysisReport", str3);
        } catch (Exception unused2) {
            str3 = "onAdResCheckFailed Exception";
            ho.c("AnalysisReport", str3);
        }
    }

    public void a(String str, ContentRecord contentRecord, long j) {
        String str2;
        try {
            if (str == null || contentRecord == null) {
                ho.c("AnalysisReport", "onAdExpire, contentRecord or ExceptionType is null ");
                return;
            }
            String l = contentRecord.l();
            String m = contentRecord.m();
            int e = contentRecord.e();
            b a2 = a(contentRecord.am());
            if (a2 == null) {
                return;
            }
            a2.aj(str);
            a2.o(l);
            a2.p(m);
            a2.a(e);
            if (j > 0) {
                a2.d(j);
            }
            ou ouVar = new ou(this.f6636a, sc.a(this.f6636a, e));
            ouVar.a(contentRecord);
            ouVar.b(a2, false, true);
        } catch (RuntimeException unused) {
            str2 = "onAdInvalid RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "onAdInvalid Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    public void a(String str, int i, String str2, Long l, Long l2, long j, ContentRecord contentRecord, String str3, HttpConnection httpConnection) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onAdResDownloadFailed, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("2");
            e.q(str);
            e.r("httpCode:" + i + ", reason:" + str2);
            e.c(i);
            e.w(str2);
            if (l != null) {
                long longValue = j - l.longValue();
                ho.a("AnalysisReport", "onAdResDownloadFailed - adReqToContentDownloadDuration: %d", Long.valueOf(longValue));
                e.t(String.valueOf(longValue));
            }
            if (l2 != null) {
                long longValue2 = j - l2.longValue();
                ho.a("AnalysisReport", "onAdResDownloadFailed - contentDownloadDuration: %d", Long.valueOf(longValue2));
                e.u(String.valueOf(longValue2));
            }
            String f = cw.f(this.f6636a);
            if (!TextUtils.isEmpty(f)) {
                e.h(ae.d(f).longValue());
                e.i(ae.c(f).longValue());
            }
            String g = cw.g(this.f6636a);
            if (!TextUtils.isEmpty(g)) {
                e.j(ae.d(g).longValue());
                e.k(ae.c(g).longValue());
            }
            e.ak(str3);
            e.al(contentRecord.T());
            a(e, httpConnection, str);
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onAdResDownloadFailed RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onAdResDownloadFailed Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005e A[Catch: Exception -> 0x010d, RuntimeException -> 0x0116, TryCatch #2 {RuntimeException -> 0x0116, Exception -> 0x010d, blocks: (B:4:0x0015, B:11:0x0039, B:12:0x003c, B:14:0x004c, B:19:0x005e, B:20:0x0064, B:22:0x0074, B:23:0x007b, B:26:0x00f0, B:28:0x00f8, B:30:0x00fc, B:35:0x0029, B:37:0x002f, B:42:0x0011), top: B:41:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0074 A[Catch: Exception -> 0x010d, RuntimeException -> 0x0116, TryCatch #2 {RuntimeException -> 0x0116, Exception -> 0x010d, blocks: (B:4:0x0015, B:11:0x0039, B:12:0x003c, B:14:0x004c, B:19:0x005e, B:20:0x0064, B:22:0x0074, B:23:0x007b, B:26:0x00f0, B:28:0x00f8, B:30:0x00fc, B:35:0x0029, B:37:0x002f, B:42:0x0011), top: B:41:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r19, int r20, long r21, com.huawei.openalliance.ad.beans.parameter.AdSlotParam r23, com.huawei.openalliance.ad.net.http.Response r24, java.lang.String r25, java.util.List<java.lang.String> r26) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.analysis.g.a(java.lang.String, int, long, com.huawei.openalliance.ad.beans.parameter.AdSlotParam, com.huawei.openalliance.ad.net.http.Response, java.lang.String, java.util.List):void");
    }

    public void a(String str, int i, int i2, String str2, int i3, long j, boolean z, Response response) {
        int h;
        StringBuilder sb;
        if (response == null) {
            h = 0;
        } else {
            try {
                h = response.h();
            } catch (RuntimeException e) {
                e = e;
                sb = new StringBuilder("onAdRequestFail RuntimeException:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AnalysisReport", sb.toString());
                return;
            } catch (Exception e2) {
                e = e2;
                sb = new StringBuilder("onAdRequestFail Exception:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AnalysisReport", sb.toString());
                return;
            }
        }
        b a2 = a(h);
        if (a2 == null) {
            return;
        }
        a2.aj(h == 1 ? "76" : h == 3 ? "124" : z ? HealthZonePushReceiver.SLEEP_TIME_NOTIFY : "8");
        a2.s(str);
        a2.r("httpCode:" + i2 + ", reason:" + str2 + ", retCode:" + i3);
        a2.a(i);
        a(a2, response, j);
        new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, true);
    }

    public void a(String str, int i, int i2, Integer num, boolean z, AdTimeStatistics adTimeStatistics) {
        StringBuilder sb;
        try {
            b a2 = a();
            if (a2 == null) {
                return;
            }
            a2.aj("113");
            a2.s(str);
            a2.a(i);
            a2.b(Integer.valueOf(!z ? 1 : 0));
            a2.Y(be.b(adTimeStatistics));
            a2.al(String.valueOf(i2));
            if (num != null) {
                a2.am(String.valueOf(num));
            }
            new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, false);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onAdCounting RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("onAdCounting Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(String str, int i, int i2, long j, boolean z, Response response, String str2) {
        int h;
        StringBuilder sb;
        if (response == null) {
            h = 0;
        } else {
            try {
                h = response.h();
            } catch (RuntimeException e) {
                e = e;
                sb = new StringBuilder("onAdRequestSuccess RuntimeException:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AnalysisReport", sb.toString());
                return;
            } catch (Exception e2) {
                e = e2;
                sb = new StringBuilder("onAdRequestSuccess Exception:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AnalysisReport", sb.toString());
                return;
            }
        }
        b a2 = a(h);
        if (a2 == null) {
            return;
        }
        a2.aj(h == 1 ? "75" : h == 3 ? "123" : z ? "28" : "7");
        a2.s(str);
        a2.r("retCode:" + i2);
        a2.a(i);
        a2.Y(str2);
        a2.y(com.huawei.openalliance.ad.utils.d.m(this.f6636a));
        a(a2, response, j);
        new ou(this.f6636a, sc.a(this.f6636a, i)).b(a2, false, false);
    }

    public void a(String str, int i, int i2) {
        try {
            if (cz.b(str) || i <= 0) {
                ho.b("AnalysisReport", "no fc");
            }
            b a2 = a();
            a2.aj("162");
            a2.o(str);
            a2.a(i2);
            a2.ak(String.valueOf(i));
            new ou(this.f6636a, sc.a(this.f6636a, -1)).b(a2, false, true);
        } catch (Throwable th) {
            ho.c("AnalysisReport", "fc analysis err, %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.ct
    public void a(rt rtVar, String str, long j, long j2, int i, String str2) {
        char c;
        if (rtVar == null) {
            ho.c("AnalysisReport", "reportAdResDownloadEvent, sourceParam is null");
            return;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 50) {
            if (str.equals("2")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 51) {
            if (str.equals("3")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 53) {
            if (str.equals("5")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 1755) {
            if (hashCode == 1756 && str.equals("73")) {
                c = 4;
            }
            c = 65535;
        } else {
            if (str.equals("72")) {
                c = 3;
            }
            c = 65535;
        }
        if (c == 0) {
            a(rtVar.g(), i, str2, rtVar.i(), Long.valueOf(j), j2, rtVar.j(), rtVar.o(), rtVar.l());
            return;
        }
        if (c == 1) {
            a(rtVar.g(), rtVar.i(), Long.valueOf(j), j2, rtVar.j(), rtVar.o());
            return;
        }
        if (c == 2) {
            a(rtVar.g(), rtVar.i(), Long.valueOf(j), rtVar.n(), Long.valueOf(j2), false, rtVar.j(), rtVar.o(), rtVar.m(), rtVar.l());
        } else if (c == 3) {
            a(rtVar.g(), rtVar.i(), Long.valueOf(j), false, rtVar.j(), rtVar.o(), rtVar.l());
        } else {
            if (c != 4) {
                return;
            }
            a(rtVar.j(), rtVar.o());
        }
    }

    public void a(du duVar, String str, long j) {
        char c;
        if (duVar == null) {
            ho.c("AnalysisReport", "reportAdResDownloadEvent, task is null");
            return;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.b(duVar.G());
        contentRecord.e(duVar.B());
        contentRecord.d(duVar.C());
        contentRecord.f(duVar.n());
        contentRecord.o(duVar.I());
        DownloadTask.b j2 = duVar.j();
        if (j2 == null) {
            j2 = DownloadTask.b.NONE;
        }
        int a2 = j2.a();
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 50) {
            if (str.equals("2")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 53) {
            if (hashCode == 1755 && str.equals("72")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("5")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            a(duVar.a(), a2, duVar.H(), null, duVar.E(), j, contentRecord, null, duVar.u());
        } else if (c == 1) {
            a(duVar.a(), null, duVar.E(), duVar.F(), Long.valueOf(j), duVar.D(), contentRecord, null, duVar.f(), duVar.u());
        } else {
            if (c != 2) {
                return;
            }
            a(duVar.a(), (Long) null, duVar.E(), duVar.D(), contentRecord, (String) null, duVar.u());
        }
    }

    public void a(ContentRecord contentRecord, String str) {
        StringBuilder sb;
        try {
            if (contentRecord == null) {
                ho.c("AnalysisReport", "onDiskSpaceInsufficient, contentRecord is null");
                return;
            }
            b e = e(contentRecord);
            if (e == null) {
                return;
            }
            e.aj("73");
            e.ak(str);
            new ou(this.f6636a, sc.a(this.f6636a, e.t().intValue()), contentRecord).b(e, false, true);
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("onDiskSpaceInsufficient RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("onDiskSpaceInsufficient Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
        }
    }

    public void a(int i, a aVar, String str, ContentRecord contentRecord) {
        String str2;
        try {
            b a2 = a(true);
            if (a2 != null && aVar != null) {
                a2.aj("54");
                a2.a(aVar.d());
                a2.s(aVar.a());
                a2.o(aVar.b());
                a2.c(i);
                a2.ak("normal");
                a2.am(str);
                if (contentRecord != null) {
                    a2.H(contentRecord.n());
                    a2.p(contentRecord.m());
                    a2.a(Integer.valueOf(contentRecord.D()));
                    a2.ak(contentRecord.ap() ? "exsplash" : "normal");
                    a2.al(String.valueOf(contentRecord.as()));
                }
                a2.r("errorCode:" + i + ", reason:" + b(i));
                ho.a("AnalysisReport", "onSplashAdLoadFailed, reason: %s", a2.r());
                new ou(this.f6636a, sc.a(this.f6636a, aVar.d())).b(a2, false, true);
            }
        } catch (RuntimeException unused) {
            str2 = "onSplashAdLoadFailed RuntimeException";
            ho.c("AnalysisReport", str2);
        } catch (Exception unused2) {
            str2 = "onSplashAdLoadFailed Exception";
            ho.c("AnalysisReport", str2);
        }
    }

    private String b(int i) {
        String str;
        try {
            str = a(ErrorCode.class, Integer.valueOf(i));
        } catch (IllegalAccessException unused) {
            ho.c("AnalysisReport", "getVariableNameByValue Exception");
            str = null;
        }
        return !TextUtils.isEmpty(str) ? str.replace("error_code_", "") : str;
    }

    private void a(AdSlotParam adSlotParam, Response response, String str, List<String> list, b bVar, boolean z) {
        if (response != null) {
            bVar.am(String.valueOf(response.i()));
            bVar.w(response.j());
            HttpConnection m = response.m();
            if (m != null) {
                bVar.v(cz.d(m.a()));
            }
            bVar.aq(String.valueOf(response.o() ? 1 : 0));
            bVar.at(String.valueOf(response.t()));
        }
        if (!bg.a(list)) {
            bVar.ar(String.valueOf(list.size()));
        }
        if (adSlotParam != null) {
            bVar.e(adSlotParam.E());
            ho.a("AnalysisReport", "grpIdCode: %s", Integer.valueOf(bVar.aH()));
        }
        if (z) {
            bVar.Y(str);
            if (adSlotParam != null) {
                if (ho.a()) {
                    ho.a("AnalysisReport", "WlacStatus: %s", adSlotParam.w());
                }
                bVar.as(String.valueOf(adSlotParam.w()));
            }
        }
    }

    private void a(b bVar, Response response) {
        if (bVar == null || response == null) {
            return;
        }
        Object b = response.b();
        AdContentRsp adContentRsp = b instanceof AdContentRsp ? (AdContentRsp) b : null;
        if (adContentRsp == null || adContentRsp.s() == null) {
            return;
        }
        bVar.ap(be.b(adContentRsp.s()));
    }

    private void a(b bVar, HttpConnection httpConnection, String str) {
        if (httpConnection != null) {
            bVar.v(cz.d(httpConnection.a()));
            bVar.aa(cz.d(httpConnection.b()));
            bVar.ab(cz.d(httpConnection.c()));
            bVar.ac(cz.d(httpConnection.a("dl-from")));
        }
        String as = bVar.as();
        String at = bVar.at();
        if (TextUtils.isEmpty(as) || TextUtils.isEmpty(at)) {
            try {
                String host = Uri.parse(str).getHost();
                bVar.aa(cz.d(host));
                bVar.ab(cz.d(InetAddress.getByName(host).getHostAddress()));
            } catch (Throwable th) {
                ho.c("AnalysisReport", "onAdResDownload parse url exception: %s", th.getClass().getSimpleName());
            }
        }
        if (ho.a()) {
            ho.a("AnalysisReport", "onAdResDownload analysisType: %s, cdnDomain: %s, cdnIp: %s, dlFrom: %s", bVar.aK(), dl.a(bVar.as()), dl.a(bVar.at()), bVar.au());
        }
    }

    private void a(final int i, AdSlotParam adSlotParam, final b bVar) {
        Location k = adSlotParam == null ? null : adSlotParam.k();
        bVar.o(System.currentTimeMillis());
        if (k != null) {
            Double c = k.c();
            Double b = k.b();
            bVar.b(c);
            bVar.a(b);
            a(bVar, b, c, 1);
        }
        bVar.p(System.currentTimeMillis());
        final int y = fh.b(this.f6636a).y();
        bVar.a(Long.valueOf(dd.f()));
        q.a(this.f6636a, new q.a() { // from class: com.huawei.openalliance.ad.analysis.g.1
            @Override // com.huawei.openalliance.ad.utils.q.a
            public void a(List<BluetoothInfo> list, int i2) {
                if (list != null) {
                    int size = list.size();
                    int i3 = y;
                    if (size > i3) {
                        list = list.subList(0, i3);
                    }
                }
                if (!bg.a(list)) {
                    bVar.V(be.b(list));
                }
                bVar.f(Integer.valueOf(i2));
                if (ho.a()) {
                    ho.a("AnalysisReport", "wifi retCode: %s,  bt retCode: %s", bVar.ak(), bVar.al());
                }
                new ou(g.this.f6636a, sc.a(g.this.f6636a, i)).b(bVar, true, false);
            }
        });
    }

    private String a(Class cls, Object obj) {
        for (Field field : cls.getDeclaredFields()) {
            if (((~field.getModifiers()) & 24) == 0 && field.get(cls).equals(obj)) {
                return field.getName().toLowerCase(Locale.ENGLISH);
            }
        }
        return null;
    }

    private String a(RequestOptions requestOptions) {
        StringBuilder sb = new StringBuilder("111");
        RequestOptions requestConfiguration = HiAd.getInstance(this.f6636a).getRequestConfiguration();
        String str = (requestConfiguration == null || requestConfiguration.isRequestLocation()) ? "1" : "0";
        String str2 = (requestOptions == null || requestOptions.isRequestLocation()) ? "1" : "0";
        LocationSwitches a2 = bi.a(this.f6636a);
        String str3 = a2.c() == 1 ? "1" : "0";
        String str4 = a2.b() == 1 ? "1" : "0";
        String str5 = a2.a() != 1 ? "0" : "1";
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        sb.append(str5);
        ho.a("AnalysisReport", "addition info is %s", sb.toString());
        return sb.toString();
    }

    private int a(Throwable th) {
        if (th instanceof SSLHandshakeException) {
            return 10000;
        }
        if (th instanceof SocketTimeoutException) {
            return 10001;
        }
        if (th instanceof ConnectException) {
            return 10002;
        }
        if (th instanceof UnknownHostException) {
            return 10003;
        }
        return th instanceof JSONException ? 10004 : -1;
    }

    public g(Context context) {
        super(context);
    }
}
