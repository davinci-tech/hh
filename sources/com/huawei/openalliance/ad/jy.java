package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.beans.base.ReqBean;
import com.huawei.openalliance.ad.beans.server.ConsentConfigReq;
import com.huawei.openalliance.ad.beans.server.EventReportReq;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jy extends com.huawei.openalliance.ad.net.http.c {

    /* renamed from: a, reason: collision with root package name */
    private Context f7150a;
    private boolean b = false;
    private String c = "POST";

    public void b(ReqBean reqBean) {
        a("X-HW-AD-Sdkver", "3.4.74.310");
        a("X-HW-App-Id", reqBean.c());
        b(reqBean, "100003");
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void a(ConsentConfigReq consentConfigReq) {
        a("X-HW-AD-KitVersion", consentConfigReq.d());
        a("X-HW-App-Id", consentConfigReq.c());
        b(consentConfigReq, consentConfigReq.c());
    }

    public void a(ReqBean reqBean) {
        Pair pair;
        b();
        a("X-HW-AD-Sdkver", "3.4.74.310");
        a("X-HW-AD-Pkgname", this.f7150a.getPackageName());
        a("X-HW-AD-Osver", Build.VERSION.RELEASE);
        if (reqBean instanceof EventReportReq) {
            a("User-Agent", com.huawei.openalliance.ad.utils.d.k(this.f7150a));
        }
        Pair<Integer, Pair<String, String>> d = ek.a(this.f7150a).d();
        if (d == null) {
            d = com.huawei.openalliance.ad.utils.bv.f(this.f7150a);
        }
        if (d != null && (pair = (Pair) d.second) != null) {
            a("X-HW-AD-Mcc", (String) pair.first);
            a("X-HW-AD-Mnc", (String) pair.second);
        }
        a(j2.v, Constants.GZIP);
        a("Authorization", a(reqBean, "100003"));
        a("Content-Type", "application/json");
        e();
        c();
    }

    private void e() {
        Pair<String, Boolean> b = com.huawei.openalliance.ad.utils.d.b(this.f7150a, true);
        if (b == null || TextUtils.isEmpty((CharSequence) b.first) || ((Boolean) b.second).booleanValue()) {
            return;
        }
        a("X-HW-AD-Oaid", (String) b.first);
    }

    private void d() {
        String b = com.huawei.openalliance.ad.utils.x.b();
        if (com.huawei.openalliance.ad.utils.cz.b(b)) {
            return;
        }
        a("X-HW-AD-Model", b.toUpperCase(Locale.ENGLISH));
    }

    private void c() {
        if (com.huawei.openalliance.ad.utils.cz.b(a().get("X-HW-AD-Oaid")) && this.b && !bz.b(this.f7150a) && bz.a(this.f7150a).d()) {
            a("X-HW-AD-Androidid", com.huawei.openalliance.ad.utils.d.g(this.f7150a));
        }
    }

    private void b(ReqBean reqBean, String str) {
        d();
        a(j2.v, Constants.GZIP);
        String a2 = a(reqBean, str);
        if (!TextUtils.isEmpty(a2)) {
            a("Authorization", a2);
        }
        a("Content-Type", "application/json");
        e();
    }

    private void b() {
        String b = bz.a(this.f7150a).b();
        if (com.huawei.openalliance.ad.utils.cz.b(b)) {
            return;
        }
        a("X-HW-AD-Model", b.toUpperCase(Locale.ENGLISH));
    }

    public static void a(Map<String, String> map) {
        map.remove("X-HW-AD-Androidid");
        map.remove("X-HW-AD-Mcc");
        map.remove("X-HW-AD-Mnc");
    }

    private String a(String str, String str2, String str3, String str4, String str5) {
        return com.huawei.openalliance.ad.utils.ap.a(str + ":" + str2 + ":" + com.huawei.openalliance.ad.utils.f.b(str4, com.huawei.openalliance.ad.utils.cp.a(this.f7150a)), str3 + ":" + this.c + ":" + str5);
    }

    private String a(ReqBean reqBean, String str) {
        String a2 = reqBean.a();
        String valueOf = String.valueOf(com.huawei.openalliance.ad.utils.ao.c());
        return "Digest username=" + str + ",realm=" + a2 + ",nonce=" + valueOf + ",response=" + a(str, a2, valueOf, reqBean.a(this.f7150a), reqBean.b()) + ",algorithm=HmacSHA256";
    }

    public jy(Context context) {
        this.f7150a = context;
    }
}
