package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.main.model.AppLangItem;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.VastAttribute;
import health.compact.a.GRSManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes7.dex */
public class rua {

    /* renamed from: a, reason: collision with root package name */
    private String f16911a = "";
    private Context c;
    private String d;

    public rua(Context context, String str) {
        this.c = context;
        this.d = str;
        LogUtil.a("HMSAuth_CloudAuthUtil", "getUserAccessToken:  " + str);
    }

    private int d(final String str, final String str2, final String str3) {
        this.f16911a = "";
        LogUtil.a("HMSAuth_CloudAuthUtil", "Start getHttpsReq");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        long currentTimeMillis = System.currentTimeMillis();
        ThreadPoolManager.d().execute(new Runnable() { // from class: rua.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HMSAuth_CloudAuthUtil", "Start get httpResult");
                rua.this.f16911a = rjx.d(str3, str, str2);
                rua ruaVar = rua.this;
                if (ruaVar.d(ruaVar.f16911a)) {
                    LogUtil.a("HMSAuth_CloudAuthUtil", "get httpResult again, userAt after refresh", rua.this.d);
                    rua ruaVar2 = rua.this;
                    ruaVar2.f16911a = rjx.d(str3, ruaVar2.d, str2);
                }
                LogUtil.a("HMSAuth_CloudAuthUtil", "httpResult:", rua.this.f16911a);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
            LogUtil.a("HMSAuth_CloudAuthUtil", "get http needs ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " ms");
            return 1;
        } catch (InterruptedException unused) {
            LogUtil.b("HMSAuth_CloudAuthUtil", "InterruptedException");
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str) {
        LogUtil.c("HMSAuth_CloudAuthUtil", "isUserAtExpired check jsonResult");
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(VastAttribute.ERROR) && "401".equals(jSONObject.getString(VastAttribute.ERROR))) {
                    this.d = HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
                    return true;
                }
            } catch (JSONException unused) {
                LogUtil.b("HMSAuth_CloudAuthUtil", "http response jsonObject == null");
                return false;
            }
        }
        LogUtil.c("HMSAuth_CloudAuthUtil", "jsonResult is empty.", this.d);
        return false;
    }

    public String a() {
        Locale locale = this.c.getResources().getConfiguration().locale;
        return locale.getLanguage().toLowerCase() + Constants.LINK + locale.getCountry().toLowerCase();
    }

    public List<AppLangItem> c(String str, String str2, String str3) {
        if (d(str, str2, str3) == 1) {
            LogUtil.c("HMSAuth_CloudAuthUtil", "getAppLangItem after getReq");
            long currentTimeMillis = System.currentTimeMillis();
            String str4 = this.f16911a;
            if (str4 == null || TextUtils.isEmpty(str4)) {
                LogUtil.c("HMSAuth_CloudAuthUtil", "httpsResult is null.");
                return null;
            }
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(this.f16911a);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    AppLangItem appLangItem = new AppLangItem();
                    if (jSONObject.has(VastAttribute.ERROR)) {
                        return null;
                    }
                    appLangItem.setAppId(jSONObject.getString("appId"));
                    appLangItem.setAppName(jSONObject.getString("appName"));
                    appLangItem.setAppIconPath(jSONObject.getString("appIconPath"));
                    arrayList.add(appLangItem);
                }
                LogUtil.c("HMSAuth_CloudAuthUtil", "getAppLangItem api needs ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " ms");
                return arrayList;
            } catch (JSONException unused) {
                LogUtil.b("HMSAuth_CloudAuthUtil", "getAppLangItem parse param json fail!");
                return null;
            }
        }
        LogUtil.c("HMSAuth_CloudAuthUtil", "getHttpsReq in getAppLangItem failed.");
        return null;
    }

    public ehp e(String str, String str2, String str3) {
        if (d(str, str2, str3) == 1) {
            LogUtil.c("HMSAuth_CloudAuthUtil", "getScopeLangItem after getReq");
            String str4 = this.f16911a;
            if (str4 == null || TextUtils.isEmpty(str4)) {
                LogUtil.c("HMSAuth_CloudAuthUtil", "httpsResult is null.");
                return null;
            }
            ehp ehpVar = new ehp();
            try {
                JSONObject jSONObject = new JSONObject(this.f16911a);
                if (jSONObject.has(VastAttribute.ERROR)) {
                    return null;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("url2Desc");
                Iterator<String> keys = jSONObject2.keys();
                HashMap hashMap = new HashMap(16);
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    hashMap.put(valueOf, jSONObject2.getString(valueOf));
                }
                ehpVar.b(hashMap);
                ehpVar.c(jSONObject.getString("authTime"));
                ehpVar.e(jSONObject.getString("appName"));
                ehpVar.d(jSONObject.getString("appIconPath"));
                return ehpVar;
            } catch (JSONException unused) {
                LogUtil.b("HMSAuth_CloudAuthUtil", "getScopeLangItem parse param json fail!");
                return null;
            }
        }
        LogUtil.c("HMSAuth_CloudAuthUtil", "getHttpsReq in getScopeLangItem fail.");
        return null;
    }

    public void a(String str, String str2, String str3) {
        if (d(str, str2, str3) == 1) {
            LogUtil.c("HMSAuth_CloudAuthUtil", "deleteAllItem after getReq");
            String str4 = this.f16911a;
            if (str4 == null || TextUtils.isEmpty(str4)) {
                LogUtil.c("HMSAuth_CloudAuthUtil", "httpsResult is null.");
                return;
            }
            return;
        }
        LogUtil.b("HMSAuth_CloudAuthUtil", "deleteAllItem req fail!");
    }

    public void b(String str, String str2, String str3) {
        if (d(str, str2, str3) == 1) {
            LogUtil.c("HMSAuth_CloudAuthUtil", "typeItemDelete after getReq");
            String str4 = this.f16911a;
            if (str4 == null || TextUtils.isEmpty(str4)) {
                LogUtil.c("HMSAuth_CloudAuthUtil", "httpsResult is null.");
                return;
            }
            return;
        }
        LogUtil.b("HMSAuth_CloudAuthUtil", "typeItemDelete req fail!");
    }

    public String b() {
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "");
        if (TextUtils.isEmpty(noCheckUrl)) {
            return "";
        }
        if (noCheckUrl.startsWith("https://")) {
            return noCheckUrl + "/healthkit/v1/consents";
        }
        return "https://" + noCheckUrl + "/healthkit/v1/consents";
    }

    public String c(String str, String str2) {
        if (str.isEmpty()) {
            LogUtil.a("HMSAuth_CloudAuthUtil", "Url is null.");
            return null;
        }
        if (str2.isEmpty()) {
            str2 = "2";
        }
        StringBuilder sb = new StringBuilder(b());
        sb.append("?lang=");
        sb.append(str);
        sb.append("&careType=");
        sb.append(str2);
        LogUtil.a("HMSAuth_CloudAuthUtil", "getAppLangItemUrl in util: " + sb.toString());
        return sb.toString();
    }

    public String b(String str, String str2) {
        LogUtil.a("HMSAuth_CloudAuthUtil", "getScopeLangItemUrl");
        if (str.isEmpty() || str2.isEmpty()) {
            LogUtil.a("HMSAuth_CloudAuthUtil", "WRONG INPUT URL.");
            return null;
        }
        StringBuilder sb = new StringBuilder(b());
        sb.append("/");
        sb.append(str);
        sb.append("?lang=");
        sb.append(str2);
        LogUtil.a("HMSAuth_CloudAuthUtil", "getScopeLangItemUrl in util: " + sb.toString());
        return sb.toString();
    }

    public String a(String str) {
        LogUtil.a("HMSAuth_CloudAuthUtil", "getDeleteAllItemUrl");
        if (str.isEmpty()) {
            LogUtil.a("HMSAuth_CloudAuthUtil", "WRONG INPUT URL at getDeleteAllItemUrl.");
            return null;
        }
        StringBuilder sb = new StringBuilder(b());
        sb.append("/");
        sb.append(str);
        LogUtil.a("HMSAuth_CloudAuthUtil", "getDeleteAllItemUrl in util: " + sb.toString());
        return sb.toString();
    }

    public String e(String str, String str2) {
        LogUtil.a("HMSAuth_CloudAuthUtil", "getTypeDeleteItemUrl");
        if (str.isEmpty() || str2.isEmpty()) {
            LogUtil.a("HMSAuth_CloudAuthUtil", "WRONG INPUT URL.");
            return null;
        }
        try {
            String replace = URLEncoder.encode(str2, "UTF-8").replace(Marker.ANY_NON_NULL_MARKER, com.huawei.operation.utils.Constants.PERCENT_20);
            StringBuilder sb = new StringBuilder(b());
            sb.append("/");
            sb.append(str);
            sb.append("?scopesUrl=");
            sb.append(replace);
            LogUtil.a("HMSAuth_CloudAuthUtil", "getTypeDeleteItemUrl in util: " + sb.toString());
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            LogUtil.c("HMSAuth_CloudAuthUtil", "Exception in getTypeDeleteItemUrl");
            return null;
        }
    }
}
