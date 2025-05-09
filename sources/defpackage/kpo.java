package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class kpo implements ParamsFactory {
    private final LoginInit c;
    private final Context e;

    public kpo() {
        Context context = BaseApplication.getContext();
        this.e = context;
        this.c = LoginInit.getInstance(context);
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        String huidOrDefault = this.c.getHuidOrDefault();
        if (!this.c.isBrowseMode() && huidOrDefault != null) {
            hashMap.put("x-huid", huidOrDefault);
        }
        hashMap.put("x-version", CommonUtil.c(this.e));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> hashMap = obj == null ? new HashMap<>() : lql.d(obj);
        hashMap.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        b(hashMap);
        hashMap.put("oaId", "");
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10000), "health_product_recommend");
        if (b != null) {
            hashMap.put("isTrackingEnabled", b);
        } else {
            hashMap.put("isTrackingEnabled", "0");
        }
        if (this.c.isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
            hashMap.put("source", 2);
        } else {
            hashMap.put("appId", sqq.e());
            hashMap.put("source", 1);
        }
        hashMap.put("deviceType", sqq.c());
        hashMap.put("upDeviceType", this.c.getDeviceType());
        hashMap.put("deviceId", this.c.getDeviceId());
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, Integer.valueOf(nsn.b()));
        c(hashMap);
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put("appType", Integer.valueOf("com.huawei.bone".equals(BaseApplication.getAppPackage()) ? 2 : 1));
        hashMap.put("ts", Long.valueOf(ghz.e(Calendar.getInstance().getTimeInMillis())));
        hashMap.put("timeZone", ggl.b());
        LogUtil.a("HealthWeight_WeightCloudFactory", "the iVersion is: ", "6");
        hashMap.put("iVersion", "6");
        hashMap.put("language", a());
        hashMap.put("lang", bed.a());
        hashMap.put("environment", Integer.valueOf(CommonUtil.l(BaseApplication.getContext())));
        hashMap.put("countryCode", GRSManager.a(this.e).getCommonCountryCode());
        return hashMap;
    }

    private String a() {
        Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;
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

    private void b(Map<String, Object> map) {
        if (this.c.getAccountInfo(1011) != null) {
            map.put("token", this.c.getAccountInfo(1008));
            map.put("siteId", Integer.valueOf(CommonUtils.h(this.c.getAccountInfo(1009))));
        }
    }

    private void c(Map<String, Object> map) {
        map.put("bindDeviceType", null);
    }
}
