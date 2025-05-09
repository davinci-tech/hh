package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class nhk implements ParamsFactory {
    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        Context e = BaseApplication.e();
        LoginInit loginInit = LoginInit.getInstance(e);
        if (!loginInit.isBrowseMode()) {
            String huidOrDefault = loginInit.getHuidOrDefault();
            if (!TextUtils.isEmpty(huidOrDefault)) {
                hashMap.put("x-huid", huidOrDefault);
            }
        }
        hashMap.put("x-version", CommonUtil.c(e));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map hashMap = obj == null ? new HashMap() : lql.d(obj);
        hashMap.put("oaId", "");
        hashMap.put("bindDeviceType", null);
        hashMap.put("iVersion", "6");
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, Integer.valueOf(nsn.b()));
        hashMap.put("deviceType", sqq.c());
        hashMap.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        long currentTimeMillis = System.currentTimeMillis();
        hashMap.put("ts", Long.valueOf(currentTimeMillis));
        hashMap.put("timeZone", jdl.q(currentTimeMillis));
        hashMap.put("appType", Integer.valueOf("com.huawei.bone".equals(BaseApplication.d()) ? 2 : 1));
        Context e = BaseApplication.e();
        hashMap.put("environment", Integer.valueOf(CommonUtil.l(e)));
        hashMap.put("countryCode", GRSManager.a(e).getCommonCountryCode());
        String b = SharedPreferenceManager.b(e, String.valueOf(10000), "health_product_recommend");
        if (TextUtils.isEmpty(b)) {
            hashMap.put("isTrackingEnabled", "0");
        } else {
            hashMap.put("isTrackingEnabled", b);
        }
        LoginInit loginInit = LoginInit.getInstance(e);
        hashMap.put("deviceId", loginInit.getDeviceId());
        hashMap.put("upDeviceType", loginInit.getDeviceType());
        if (loginInit.getAccountInfo(1011) != null) {
            hashMap.put("token", loginInit.getAccountInfo(1008));
            hashMap.put("siteId", Integer.valueOf(CommonUtils.h(loginInit.getAccountInfo(1009))));
        }
        if (loginInit.isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
            hashMap.put("source", 2);
        } else {
            hashMap.put("appId", sqq.e());
            hashMap.put("source", 1);
        }
        hashMap.put("lang", bed.a());
        hashMap.put("language", a());
        return hashMap;
    }

    private String a() {
        Locale locale = BaseApplication.e().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if ("ZH".equalsIgnoreCase(language)) {
            String script = locale.getScript();
            if ("HANS".equalsIgnoreCase(script)) {
                return "zh-CN";
            }
            if ("HANT".equalsIgnoreCase(script) && "CN".equalsIgnoreCase(country)) {
                return "zh-HK";
            }
        }
        return TextUtils.concat(language, Constants.LINK, country).toString();
    }
}
