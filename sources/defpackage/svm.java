package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsApi;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class svm {
    private static volatile svm b;
    private static final Object d = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f17251a = new HashMap();
    private Map<String, String> c = new HashMap();
    private Context g;

    private svm(Context context) {
        if (context != null) {
            this.g = context.getApplicationContext();
        }
    }

    public static svm b(Context context) {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new svm(context);
                }
            }
        }
        return b;
    }

    public String b(String str, String str2) {
        stq.d("OverSeasManager", " getGrsUrlSync method BEGIN", false);
        d(str2);
        String str3 = this.f17251a.get(str);
        if (TextUtils.isEmpty(str3)) {
            stq.e("OverSeasManager", "urlResult is null, and modelName is " + str, false);
        }
        stq.c("OverSeasManager", " getGrsUrlSync method END,,cost :" + (System.currentTimeMillis() - System.currentTimeMillis()), false);
        return str3;
    }

    private void c(String str) {
        stq.c("OverSeasManager", " getUrlMapFromGrs BEGIN.", false);
        Map<String, String> c = c(str, this.g);
        if (!c.isEmpty()) {
            this.f17251a.putAll(c);
        }
        if (this.f17251a.isEmpty()) {
            stq.e("OverSeasManager", " getUrlMapFromGrs bad, get url map empty.", false);
        } else {
            stc.b(this.g).d("account_service_country_code", str);
            stq.c("OverSeasManager", " getUrlMapFromGrs god, get url map not empty.", false);
        }
        stq.c("OverSeasManager", " getUrlMapFromGrs END.urlMap is empty :" + this.f17251a.isEmpty(), false);
    }

    private Map<String, String> c(String str, Context context) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setAppName(svk.e());
        grsBaseInfo.setSerCountry(str);
        if (context == null) {
            return new HashMap();
        }
        GrsApi.grsSdkInit(context, grsBaseInfo);
        Map<String, String> synGetGrsUrls = GrsApi.synGetGrsUrls(svk.a());
        HashMap hashMap = new HashMap();
        if (synGetGrsUrls != null && !synGetGrsUrls.isEmpty()) {
            if (!str.equals("CN")) {
                synGetGrsUrls.put("WALLET", synGetGrsUrls.get("VIRTUALCARD"));
            }
            hashMap.putAll(synGetGrsUrls);
        }
        Map<String, String> synGetGrsUrls2 = GrsApi.synGetGrsUrls("com.huawei.cloud.agreementservice");
        if (synGetGrsUrls2 != null && !synGetGrsUrls2.isEmpty()) {
            hashMap.put("GREMENT", synGetGrsUrls2.get("ROOT"));
            hashMap.put("GREMENT_COMMON", synGetGrsUrls2.get("COMMONAPI"));
        } else {
            stq.e("OverSeasManager", " getUrlMapFromGrs bad, agreementUrl is empty.", false);
        }
        String synGetGrsUrl = GrsApi.synGetGrsUrl("com.huawei.cloud.ccs", "ROOT");
        if (!TextUtils.isEmpty(synGetGrsUrl)) {
            hashMap.put("WALLET_CA", synGetGrsUrl);
        }
        Map<String, String> synGetGrsUrls3 = GrsApi.synGetGrsUrls("com.huawei.cloud.feedback");
        if (synGetGrsUrls3 != null && !synGetGrsUrls3.isEmpty()) {
            hashMap.put("FEEDBACK_ROOT", synGetGrsUrls3.get("ROOT"));
            hashMap.put("FEEDBACK_REPORT", synGetGrsUrls3.get("REPORT"));
            hashMap.put("FEEDBACK_LOG", synGetGrsUrls3.get("LOG"));
        } else {
            stq.e("OverSeasManager", " getUrlMapFromGrs bad, feedBackUrl is empty.", false);
        }
        stq.c("OverSeasManager", " getUrlMapFromGrs done  = " + hashMap.size(), false);
        return hashMap;
    }

    String c(String str, String str2) {
        synchronized (this) {
            Map<String, String> map = this.c;
            if (map == null || map.size() == 0) {
                this.c = svl.c(this.g).d(str, svk.a());
                stq.c("OverSeasManager", "getUrlFromLocal is done", false);
            }
            Map<String, String> map2 = this.c;
            if (map2 != null && map2.size() > 0) {
                stq.c("OverSeasManager", "getUrlFromLocal is done = " + this.c.get(str2), false);
                return this.c.get(str2);
            }
            if (this.c == null) {
                stq.c("OverSeasManager", "getUrlFromLocal  addressLocalUrlMap is null", false);
            } else {
                stq.c("OverSeasManager", "getUrlFromLocal  addressLocalUrlMap size:" + this.c.size(), false);
            }
            return " ";
        }
    }

    private void d(String str) {
        if (!e(null)) {
            stq.c("OverSeasManager", " initGrs no need init.", false);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            stq.c("OverSeasManager", " initGrs isEmpty .", false);
            str = svv.e();
        }
        stq.c("OverSeasManager", "initGrs", false);
        c(str);
    }

    private boolean e(String str) {
        boolean z;
        if (TextUtils.isEmpty(str) || !stc.b(this.g).c("account_service_country_code", "").equalsIgnoreCase(str) || this.f17251a.isEmpty()) {
            z = true;
        } else {
            stq.d("OverSeasManager", " isNeedInit set the mem as sp.", false);
            z = false;
        }
        stq.d("OverSeasManager", " isNeedInit :" + z, false);
        return z;
    }
}
