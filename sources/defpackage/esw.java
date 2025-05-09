package defpackage;

import android.content.Context;
import android.os.Build;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class esw implements ParamsFactory {
    private final Context b;
    private final LoginInit e;

    public esw() {
        Context context = BaseApplication.getContext();
        this.b = context;
        this.e = LoginInit.getInstance(context);
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        String huidOrDefault = this.e.getHuidOrDefault();
        if (!this.e.isBrowseMode() && huidOrDefault != null) {
            hashMap.put("x-huid", huidOrDefault);
        }
        hashMap.put("x-version", CommonUtil.c(this.b));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> hashMap = obj == null ? new HashMap<>() : lql.d(obj);
        hashMap.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        a(hashMap);
        hashMap.put("oaId", "");
        String b = SharedPreferenceManager.b(this.b, Integer.toString(10000), "health_product_recommend");
        if (b != null) {
            hashMap.put("isTrackingEnabled", b);
        } else {
            hashMap.put("isTrackingEnabled", "0");
        }
        if (this.e.isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", sqq.e());
        }
        hashMap.put("deviceType", sqq.c());
        hashMap.put("upDeviceType", this.e.getDeviceType());
        hashMap.put("deviceId", this.e.getDeviceId());
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, Integer.valueOf(nsn.b()));
        b(hashMap);
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put("appType", Integer.valueOf("com.huawei.bone".equals(BaseApplication.getAppPackage()) ? 2 : 1));
        hashMap.put("ts", Long.valueOf(ghz.e(Calendar.getInstance().getTimeInMillis())));
        hashMap.put("timeZone", ggl.b());
        LogUtil.a("Suggestion_CloudFactory", "the iVersion is: ", "6");
        hashMap.put("iVersion", "6");
        hashMap.put("language", LanguageUtil.j(this.b) ? ProfileRequestConstants.X_LANGUAGE_VALUE : "en_US");
        hashMap.put("lang", etd.a());
        hashMap.put("environment", Integer.valueOf(CommonUtil.l(BaseApplication.getContext())));
        hashMap.put("countryCode", GRSManager.a(this.b).getCommonCountryCode());
        return hashMap;
    }

    private void a(Map<String, Object> map) {
        if (this.e.getAccountInfo(1011) != null) {
            map.put("token", this.e.getAccountInfo(1008));
            map.put("siteId", Integer.valueOf(Integer.parseInt(this.e.getAccountInfo(1009))));
        }
    }

    private void b(Map<String, Object> map) {
        map.put("bindDeviceType", null);
    }
}
