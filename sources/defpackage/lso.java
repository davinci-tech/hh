package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.CountryConfig;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.opendevice.open.c;
import com.huawei.opendevice.open.f;
import java.util.Locale;

/* loaded from: classes9.dex */
public class lso {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f14854a = false;
    private static String b = "";
    private static String c = "";
    private static String d = "UNKNOWN";
    private static String e = "";
    private static f f = null;
    private static lsr g = null;
    private static String j = "";

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, String str, String str2) {
        String str3 = Locale.getDefault().getLanguage().toLowerCase(Locale.getDefault()) + Constants.LINK + Locale.getDefault().getCountry().toLowerCase(Locale.getDefault());
        String t = dd.t(context);
        g.c(str2);
        g.d(str3);
        g.b(t);
        return str + Constants.LANGUAGE + str3 + Constants.VERSION + str2 + Constants.SCRIPT + t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(Context context, String str, String str2) {
        String str3 = Locale.getDefault().getLanguage().toLowerCase(Locale.getDefault()) + Constants.LINK + Locale.getDefault().getCountry().toLowerCase(Locale.getDefault());
        String t = dd.t(context);
        g.c(str2);
        g.d(str3);
        g.b(t);
        return str + Constants.LANGUAGE + str3 + "&branchid=0&version=" + str2 + "&contenttag=default";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, c cVar) {
        if (TextUtils.isEmpty(str)) {
            cVar.g();
        } else {
            ho.b("PrivacyUrlUtil", "statement url= %s", dl.a(str));
            cVar.a(str);
        }
        f fVar = f;
        if (fVar != null) {
            fVar.a(g);
        }
    }

    public static void d(final Context context, final c cVar) {
        m.b(new Runnable() { // from class: lso.4
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder sb;
                String str;
                String str2;
                ho.b("PrivacyUrlUtil", "config whyThisAdStatement url, isChina: %s", Boolean.valueOf(lso.f14854a));
                lso.c(context);
                String a2 = cz.a(context, "haid_third_ad_info");
                if (lso.f14854a) {
                    sb = new StringBuilder();
                    sb.append(a2);
                    str = Constants.THIRD_AD_INFO_CN;
                } else {
                    sb = new StringBuilder();
                    sb.append(a2);
                    str = Constants.THIRD_AD_INFO_OVERSEA;
                }
                sb.append(str);
                String sb2 = sb.toString();
                lso.j += sb2;
                if (!TextUtils.isEmpty(lso.b)) {
                    str2 = lso.b + sb2;
                } else {
                    ho.b("PrivacyUrlUtil", "grs url return null or empty, use local defalut url.");
                    str2 = lso.j;
                }
                String unused = lso.b = str2;
                lso.b(lso.b(context, lso.b, "20221229"), cVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        String str;
        boolean d2 = bz.a(context).d();
        f14854a = d2;
        if (d2) {
            str = "CN";
        } else {
            String a2 = new CountryCodeBean(context).a();
            d = a2;
            str = "UNKNOWN".equalsIgnoreCase(a2) ? "EU" : d;
        }
        d = str;
        cg.a(context).z(d);
        g = new lsr();
        gc b2 = fh.b(context);
        e = b2.a(context, "amsServer");
        b = b2.a(context, "h5Server");
        if (ho.a()) {
            ho.a("PrivacyUrlUtil", "base url for %s is: %s; h5Server is: %s", "amsServer", dl.a(e), dl.a(b));
        }
        c = cz.a(context, "hiad_privacyServer");
        j = b(context, d);
    }

    public static void c(f fVar) {
        f = fVar;
    }

    public static void b(final Context context, final c cVar) {
        m.b(new Runnable() { // from class: lso.1
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder sb;
                Context context2;
                String str;
                String str2;
                String str3;
                ho.b("PrivacyUrlUtil", "config privacy statement url, isChina: %s.", Boolean.valueOf(lso.f14854a));
                lso.c(context);
                if (lso.f14854a) {
                    sb = new StringBuilder();
                    context2 = context;
                    str = "hiad_privacyPath";
                } else {
                    sb = new StringBuilder();
                    context2 = context;
                    str = "hiad_thirdPrivacyOverseaPath";
                }
                sb.append(cz.a(context2, str));
                sb.append(lso.d);
                String sb2 = sb.toString();
                lso.c += sb2;
                if (!TextUtils.isEmpty(lso.e)) {
                    str2 = lso.e + sb2;
                } else {
                    ho.b("PrivacyUrlUtil", "grs url return null or empty, use local defalut url.");
                    str2 = lso.c;
                }
                String unused = lso.e = str2;
                lso.g.e("privacy" + lso.d);
                if (lso.f14854a) {
                    lso.g.e("privacyThirdCN");
                    str3 = "20230927";
                } else {
                    str3 = "20221229";
                }
                lso.b(lso.a(context, lso.e, str3), cVar);
            }
        });
    }

    private static String b(Context context, String str) {
        String str2;
        if (CountryConfig.isDR1(str, null)) {
            str2 = "haid_h5_content_server_CN";
        } else if (CountryConfig.isDR2(str, null)) {
            str2 = "haid_h5_content_server_HK";
        } else if (CountryConfig.isDR3(str, null)) {
            str2 = "haid_h5_content_server_EU";
        } else if (CountryConfig.isDR4(str, null)) {
            str2 = "haid_h5_content_server_RU";
        } else {
            ho.c("PrivacyUrlUtil", "getH5LocalUrl error, countryCode not belong to any site.");
            str2 = "haid_h5_content_server";
        }
        return cz.a(context, str2);
    }
}
